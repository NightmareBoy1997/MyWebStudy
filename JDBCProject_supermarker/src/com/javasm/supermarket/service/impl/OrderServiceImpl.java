package com.javasm.supermarket.service.impl;

import com.javasm.supermarket.bean.Member;
import com.javasm.supermarket.bean.Product;
import com.javasm.supermarket.common.CartItem;
import com.javasm.supermarket.common.CartService;
import com.javasm.supermarket.dao.MemberDao;
import com.javasm.supermarket.dao.OrderDao;
import com.javasm.supermarket.dao.ProductDao;
import com.javasm.supermarket.dao.impl.MemberDaoImpl;
import com.javasm.supermarket.dao.impl.OrderDaoImpl;
import com.javasm.supermarket.dao.impl.ProductDaoImpl;
import com.javasm.supermarket.service.OrderService;
import com.javasm.supermarket.util.DruidUtil;
import org.apache.commons.dbutils.DbUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Map;
import java.util.Set;

/**
 * @author: Lisa
 * @className: OrderServiceImpl
 * @description:
 * @date: 2022/3/19 11:16
 * @version: 0.1
 * @since: jdk11
 */
public class OrderServiceImpl implements OrderService {
    @Override
    public void addOrder(Member member, BigDecimal totalMoney, int payType) {
        Connection connection = null;
        try {
            //0. 手动的从池子里面获得一个闲置的可用的连接对象
            connection = DruidUtil.getDataSource().getConnection();
            //1.关闭事务自动提交
            connection.setAutoCommit(false);
            OrderDao orderDao = new OrderDaoImpl(connection);
            ProductDao productDao = new ProductDaoImpl(connection);
            MemberDao memberDao = new MemberDaoImpl(connection);

            Integer memberId = member.getId();

            //订单表: insert into order (mid,total_money,pay_type) VALUES () 返回订单id
            int oid = orderDao.addOrder(memberId, totalMoney, payType);

            //订单详情表: insert into order_detail (oid,pid,buy_num) values () 购物项
            Set<Map.Entry<Integer, CartItem>> entrySet = CartService.cart.entrySet();
            for (Map.Entry<Integer, CartItem> itemEntry : entrySet) {
                Integer pid = itemEntry.getKey();
                CartItem cartItem = itemEntry.getValue();
                Integer buyNum = cartItem.getBuyNum();

                orderDao.addOrderAndDetail(oid, pid, buyNum);
                //更新商品表:
                Product product = cartItem.getProduct();
                int newStore = product.getProdStore() - buyNum;
                if (newStore == 0) {
                    product.setProdStatus(2);
                }
                product.setProdStore(newStore);
                productDao.updateProductById(product);
            }
            //更新会员表: update
            if (memberId != -1) {
                member.setBalance(member.getBalance().subtract(totalMoney));
                member.setPoint(member.getPoint()+totalMoney.doubleValue());
                memberDao.updateMember(member);
            }

            //2.提交事务并释放资源
            DbUtils.commitAndCloseQuietly(connection);
        } catch (Exception e) {
            //3.回滚事务并释放资源
            e.printStackTrace();
            DbUtils.rollbackAndCloseQuietly(connection);
        }

    }
}
