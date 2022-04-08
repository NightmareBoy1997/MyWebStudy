package com.javasm.supermarket.menu;

import com.javasm.supermarket.bean.Member;
import com.javasm.supermarket.bean.Product;
import com.javasm.supermarket.common.CartItem;
import com.javasm.supermarket.common.CartService;
import com.javasm.supermarket.service.MemberService;
import com.javasm.supermarket.service.OrderService;
import com.javasm.supermarket.service.ProductService;
import com.javasm.supermarket.service.impl.MemberServiceImpl;
import com.javasm.supermarket.service.impl.OrderServiceImpl;
import com.javasm.supermarket.service.impl.ProductServiceImpl;
import com.javasm.supermarket.util.InputUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @author: Lisa
 * @className: CartMenu
 * @description:
 * @date: 2022/3/19 9:44
 * @version: 0.1
 * @since: jdk11
 */
public class CartMenu {
    public void menu() {
        String s;
        do {
            System.out.println("---------------------1.购买指定商品--------------------");
            System.out.println("---------------------2.修改商品数量--------------------");
            System.out.println("---------------------3.删除购物车商品--------------------");
            System.out.println("---------------------4.查询购物车商品信息--------------------");
            System.out.println("---------------------5.支付--------------------");
            System.out.println("---------------------6.退    出--------------------");
            int choice = InputUtil.inputInt("^[1-6]$", "请录入1-5的数据:");
            switch (choice) {
                case 1:
                    buyProductFun();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    CartService.showProdList();
                    break;
                case 5:
                    pay();
                    break;
                case 6:
                    System.out.println("程序退出");
                    return;
            }
            System.out.println("是否继续操作类型模块?y/n");
            s = InputUtil.inputStr();
        } while (Objects.equals(s, "y"));

    }

    private Member member;

    private void pay() {
        OrderService orderService = new OrderServiceImpl();
        BigDecimal totalMoney = CartService.getTotalMoney();
        System.out.println("一共需要支付:" + totalMoney);
        System.out.println("请选择支付方式：");
        System.out.println("1.现金支付");
        System.out.println("2.余额支付");
        int payType = InputUtil.inputInt();

        switch (payType) {
            case 1:
                member = new Member();
                member.setId(-1);
                break;
            case 2:
                payType = balancePay(totalMoney);
                break;
        }
        orderService.addOrder(member, totalMoney, payType);
        //清空购物车
        CartService.cart.clear();
    }

    private static final MemberService memberService = new MemberServiceImpl();

    private int balancePay(BigDecimal totalMoney) {
        System.out.println("请录入用户名:");
        String name = InputUtil.inputStr();

        System.out.println("请录入密码:");
        String pass = InputUtil.inputStr();
        member = memberService.findMemberByNameAndPass(name, pass);
        BigDecimal balance = member.getBalance();
        if (balance.compareTo(totalMoney) < 0) {
            //余额不足
            System.out.println("请使用现金支付");
            return 1;
        }
        return 2;
    }

    public static void main(String[] args) {
        System.out.println(new BigDecimal("100").compareTo(new BigDecimal("200")));
    }

    private static ProductService productService = new ProductServiceImpl();

    //购物商品 存储购物车
    private void buyProductFun() {
        //1.查询目前在售的商品-----> 分页展示
        int page = 1;
        final int size = 2;
        int totalCount = productService.findProductCount();
        int totalPage = totalCount / size;
        totalPage = (totalCount % size) == 0 ? totalPage : totalPage + 1;


        do {
            System.out.println("第<<" + page + ">>页的数据如下:");
            List<Product> productList = productService.findProductByPage(page, size);
            productList.forEach(System.out::println);
            int choice = InputUtil.inputInt("^[1-3]$", "请选择功能: 1. 购买指定的商品  2. 继续分页查询 3.结束购买");
            switch (choice) {
                case 1:
                    putProdIntoCart();
                    break;
                case 2:
                    page = continueShowPage(totalPage);
                    break;
                case 3:
                    return;
            }
        } while (true);


    }

    private void putProdIntoCart() {
        System.out.println("请录入要购买的商品id:");
        int pid = InputUtil.inputInt();
        Product product = productService.findProductById(pid);
        System.out.println("购买的商品详情如下:" + product);

        String prodName = product.getProdName();
        Integer prodStore = product.getProdStore();

        System.out.println("请录入要购买的<<" + prodName + ">>数量:");
        int buyNum = InputUtil.inputInt();
        CartItem cartItem = CartService.getProdById(pid);
        int oldNum = 0;
        if (cartItem != null) {
            oldNum = cartItem.getBuyNum();
        }
        if (buyNum + oldNum > prodStore) {
            System.out.println("<<" + prodName + ">>库存不足  无法购买");
            return;
        }
        //库存ok  可以购买  购买的商品存储购物车
        //购物车是一个容器---> 数组  集合  List<Product>----> 购物车存储购物项buyNum  小计  商品
        // Map<Integer,Product>   key: 商品id  v: 购买数量
        //10个商品  购买的时候已经查询10次   结账: 又查询
        //List<CartItem>   Map<Integer,CartItem>   判断之前是否买过
        CartService.addProdToCart(product, buyNum);
    }

    private int continueShowPage(int totalPage) {
        for (int pageCount = 1; pageCount <= totalPage; pageCount++) {
            System.out.print(pageCount + "\t");
        }
        System.out.println("请录入要查询的页数:");
        return InputUtil.inputInt();
    }
}
