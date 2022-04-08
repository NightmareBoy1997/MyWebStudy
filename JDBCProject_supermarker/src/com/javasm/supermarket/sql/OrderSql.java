package com.javasm.supermarket.sql;

/**
 * @author: Lisa
 * @className: OrderSql
 * @description:
 * @date: 2022/3/19 11:30
 * @version: 0.1
 * @since: jdk11
 */
public interface OrderSql {
    String ADD_ORDER = "INSERT INTO  `order` (mid, total_money, pay_type) VALUES (?,?,?)";
    String ADD_ORDER_DETAIL = "INSERT INTO order_detail (oid, pid, buy_num) VALUES (?,?,?)";
}
