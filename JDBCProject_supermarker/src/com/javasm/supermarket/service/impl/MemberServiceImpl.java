package com.javasm.supermarket.service.impl;

import com.javasm.supermarket.bean.Member;
import com.javasm.supermarket.dao.MemberDao;
import com.javasm.supermarket.dao.impl.MemberDaoImpl;
import com.javasm.supermarket.service.MemberService;
import lombok.SneakyThrows;

/**
 * @author: Lisa
 * @className: MemberServiceImpl
 * @description:
 * @date: 2022/3/19 11:06
 * @version: 0.1
 * @since: jdk11
 */
public class MemberServiceImpl implements MemberService {

    private static MemberDao memberDao = new MemberDaoImpl();

    @SneakyThrows
    @Override
    public Member findMemberByNameAndPass(String name, String pass) {
        return memberDao.findMemberByNameAndPass(name, pass);
    }
}
