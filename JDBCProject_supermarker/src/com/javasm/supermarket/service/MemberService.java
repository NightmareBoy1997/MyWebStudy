package com.javasm.supermarket.service;

import com.javasm.supermarket.bean.Member;

/**
 * @author: Lisa
 * @className: MemberService
 * @description:
 * @date: 2022/3/19 11:06
 * @version: 0.1
 * @since: jdk11
 */
public interface MemberService {
    Member findMemberByNameAndPass(String name, String pass);
}
