package com.javasm.supermarket.dao;

import com.javasm.supermarket.bean.Type;

import java.util.List;

/**
 * @author: Lisa
 * @className: TypeDao
 * @description:
 * @date: 2022/3/17 10:56
 * @version: 0.1
 * @since: jdk11
 */
public interface TypeDao {

    List<Type> findAllType() throws Exception;

    int addType(Type type) throws Exception;

    Type findTypeById(Integer typeId) throws Exception;

    int updateTypeById(Type type) throws Exception;

    void deleteTypeById(int typeId) throws Exception;
}
