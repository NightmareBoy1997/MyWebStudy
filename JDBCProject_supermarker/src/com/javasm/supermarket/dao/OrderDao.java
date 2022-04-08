package com.javasm.supermarket.dao;

import java.math.BigDecimal;

/**
 * @author: Lisa
 * @className: OrderDao
 * @description:
 * @date: 2022/3/19 11:25
 * @version: 0.1
 * @since: jdk11
 */
public interface OrderDao {

    int addOrder(Integer memberId, BigDecimal totalMoney, int payType) throws Exception;

    void addOrderAndDetail(int oid, int pid, int buyNum) throws Exception;
}
