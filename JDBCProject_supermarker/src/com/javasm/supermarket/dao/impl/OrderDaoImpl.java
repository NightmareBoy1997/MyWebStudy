package com.javasm.supermarket.dao.impl;

import com.javasm.supermarket.dao.OrderDao;
import com.javasm.supermarket.sql.OrderSql;
import com.javasm.supermarket.util.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;

/**
 * @author: Lisa
 * @className: OrderDaoImpl
 * @description:
 * @date: 2022/3/19 11:25
 * @version: 0.1
 * @since: jdk11
 */
public class OrderDaoImpl implements OrderDao {
    private Connection connection;

    public OrderDaoImpl() {
    }

    public OrderDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int addOrder(Integer memberId, BigDecimal totalMoney, int payType) throws Exception {
        return new QueryRunner().insert(connection,
                OrderSql.ADD_ORDER,
                new ScalarHandler<BigInteger>(),
                memberId,totalMoney,payType).intValue();
    }

    @Override
    public void addOrderAndDetail(int oid, int pid, int buyNum) throws Exception {
        new QueryRunner().update(connection,OrderSql.ADD_ORDER_DETAIL,oid,pid,buyNum);
    }
}
