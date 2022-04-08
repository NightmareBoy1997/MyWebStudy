package com.javasm.supermarket.dao.impl;

import com.javasm.supermarket.bean.Member;
import com.javasm.supermarket.dao.MemberDao;
import com.javasm.supermarket.sql.MemberSql;
import com.javasm.supermarket.util.DruidUtil;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;

/**
 * @author: Lisa
 * @className: MemberDaoImpl
 * @description:
 * @date: 2022/3/19 11:07
 * @version: 0.1
 * @since: jdk11
 */
public class MemberDaoImpl implements MemberDao {

    private static QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());

    private Connection connection;

    public MemberDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public MemberDaoImpl() {
    }

    @Override
    public Member findMemberByNameAndPass(String name, String pass) throws Exception {

        return queryRunner.query(
                MemberSql.FIND_BY_NAME_PASS,
                new BeanHandler<>(Member.class, new BasicRowProcessor(new GenerousBeanProcessor())),
                name, pass
        );
    }

    //"update member set name=?, password=?, user_image=?, phone=?, balance=?, point=? where id = ?";
    @Override
    public void updateMember(Member member) throws Exception {
        if(connection==null){
            queryRunner.update(MemberSql.UPDATE_MEMBER,
                    member.getName(),
                    member.getPassword(),
                    member.getUserImage(),
                    member.getPhone(),
                    member.getBalance(),
                    member.getPoint(),
                    member.getId()
            );
            return;
        }
        new QueryRunner().update(connection, MemberSql.UPDATE_MEMBER,
                member.getName(),
                member.getPassword(),
                member.getUserImage(),
                member.getPhone(),
                member.getBalance(),
                member.getPoint(),
                member.getId()
        );
    }
}
