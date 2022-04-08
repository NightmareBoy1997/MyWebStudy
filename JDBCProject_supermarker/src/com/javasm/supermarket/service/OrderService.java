package com.javasm.supermarket.service;

import com.javasm.supermarket.bean.Member;

import java.math.BigDecimal;

/**
 * @author: Lisa
 * @className: OrderService
 * @description:
 * @date: 2022/3/19 11:15
 * @version: 0.1
 * @since: jdk11
 */
public interface OrderService {
    void addOrder(Member member, BigDecimal totalMoney, int payType);
}
