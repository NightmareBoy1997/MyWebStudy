package com.javasm.supermarket.sql;

/**
 * @author: Lisa
 * @className: MemberSql
 * @description:
 * @date: 2022/3/19 11:09
 * @version: 0.1
 * @since: jdk11
 */
public interface MemberSql {
    String FIND_BY_NAME_PASS = "SELECT * FROM member WHERE name=? and password=?";
    String UPDATE_MEMBER = "update member set name=?, password=?, user_image=?, phone=?, balance=?, point=? where id = ?";
}
