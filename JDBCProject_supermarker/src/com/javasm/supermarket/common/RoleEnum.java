package com.javasm.supermarket.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: Lisa
 * @className: RoleEnum
 * @description:
 * @date: 2022/3/17 10:34
 * @version: 0.1
 * @since: jdk11
 */
@AllArgsConstructor
@Getter
public enum RoleEnum {

    ADMIN("admin", "1234"),
    CASHIER("cashier", "1234");

    private final String name;
    private final String pass;
}
