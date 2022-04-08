package com.javasm.supermarket.dao.impl;

import com.javasm.supermarket.bean.Type;
import com.javasm.supermarket.dao.TypeDao;
import com.javasm.supermarket.sql.TypeSql;
import com.javasm.supermarket.util.DruidUtil;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

/**
 * @author: Lisa
 * @className: TypeDaoImpl
 * @description:
 * @date: 2022/3/17 11:01
 * @version: 0.1
 * @since: jdk11
 */
public class TypeDaoImpl implements TypeDao {


    private static final QueryRunner queryRunner;

    static {
        queryRunner = new QueryRunner(DruidUtil.getDataSource());
    }

    @Override
    public List<Type> findAllType() throws Exception {
        return queryRunner.query(TypeSql.FIND_ALL_TYPE, new BeanListHandler<>(Type.class, new BasicRowProcessor(new GenerousBeanProcessor())));
    }

    @Override
    public int addType(Type type) throws Exception {
        return queryRunner.update(TypeSql.INSERT_TYPE, type.getTypeName(), type.getParentId(), type.isParent(), 1);
    }

    @Override
    public Type findTypeById(Integer typeId) throws Exception {
        return queryRunner.query(TypeSql.FIND_TYPE_BY_ID, new BeanHandler<>(Type.class, new BasicRowProcessor(new GenerousBeanProcessor())), typeId);
    }

    @Override
    public int updateTypeById(Type type) throws Exception {
        return queryRunner.update(TypeSql.UPDATE_TYPE_BY_ID,
                type.getTypeName(), type.getParentId(), type.isParent(),
                type.isTypeStatus(), type.getId()
        );
    }

    @Override
    public void deleteTypeById(int typeId) throws Exception {
        queryRunner.update(TypeSql.DELETE_TYPE_BY_ID, typeId);
    }
}
