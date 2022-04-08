package com.javasm.supermarket;

import com.javasm.supermarket.common.RoleEnum;
import com.javasm.supermarket.menu.CartMenu;
import com.javasm.supermarket.menu.ProductMenu;
import com.javasm.supermarket.menu.TypeMenu;
import com.javasm.supermarket.util.InputUtil;

import java.util.Objects;

/**
 * @author: Lisa
 * @className: SupermarketMgr
 * @description:
 * @date: 2022/3/17 10:29
 * @version: 0.1
 * @since: jdk11
 */
public class SupermarketMgr {


    public void start() {
        //登录
        //根据登录用户的用户名和密码进行判断是哪一个角色
        login();
        InputUtil.close();

    }

    private void login() {

        cashierMenu();
       /* System.out.println("--------------------欢迎登录-----------------");
        System.out.println("请录入用户名:");
        String name = InputUtil.inputStr();

        System.out.println("请录入密码:");
        String pass = InputUtil.inputStr();

        if (Objects.equals(name, RoleEnum.ADMIN.getName()) && Objects.equals(pass, RoleEnum.ADMIN.getPass())) {
            //管理员
            adminMenu();
        } else if (Objects.equals(name, RoleEnum.CASHIER.getName()) && Objects.equals(pass, RoleEnum.CASHIER.getPass())) {
            //收银员
            cashierMenu();
        } else {
            InputUtil.close();
            throw new RuntimeException("录入用户名或者密码有误，不存在此用户，请联系管理员");
        }*/
    }

    //管理员的模块
    private void adminMenu() {
        System.out.println("欢迎你:" + RoleEnum.ADMIN.getName());
        String str;
        do {
            System.out.println("管理员权限功能：");
            System.out.println("-----------------1.类型管理------------------");
            System.out.println("-----------------2.商品管理------------------");
            System.out.println("-----------------3.会员管理------------------");
            System.out.println("-----------------4.退   出------------------");
            int choice = InputUtil.inputInt("^[1-4]$", "请录入1-4数据:");
            switch (choice) {
                case 1:
                    //操作类型模块
                    new TypeMenu().menu();
                    break;
                case 2:
                    new ProductMenu().menu();
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("程序退出");
                    return;
            }
            System.out.println("是否继续操作管理员模块?y/n");
            str = InputUtil.inputStr();
        } while (str.equalsIgnoreCase("y"));
    }

    //收银员的模块
    private void cashierMenu() {

        String str;
        do {
            System.out.println("1.购物车模块");
            System.out.println("2. 订单查询");
            System.out.println("3. 排行统计");
            System.out.println("4. 退   出");

            int choice = InputUtil.inputInt("^[1-4]$", "请录入1-4之间的数据：");
            switch (choice) {
                case 1:
                    new CartMenu().menu();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("程序退出");
                    return;
            }
            System.out.println("是否继续操作收银员模块?y/n");
            str = InputUtil.inputStr();
        } while (Objects.equals("y", str));

    }


}
