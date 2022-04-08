package com.javasm.supermarket.dao;

import com.javasm.supermarket.bean.Member;

/**
 * @author: Lisa
 * @className: MemberDao
 * @description:
 * @date: 2022/3/19 11:07
 * @version: 0.1
 * @since: jdk11
 */
public interface MemberDao {
    Member findMemberByNameAndPass(String name, String pass) throws Exception;

    void updateMember(Member member) throws Exception;
}
