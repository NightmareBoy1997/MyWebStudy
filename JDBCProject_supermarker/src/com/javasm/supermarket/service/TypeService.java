package com.javasm.supermarket.service;

import com.javasm.supermarket.bean.Type;

/**
 * @author: Lisa
 * @className: TypeService
 * @description:
 * @date: 2022/3/17 11:05
 * @version: 0.1
 * @since: jdk11
 */
public interface TypeService {

    void findAllType();

    boolean addUpdateType(Type type);

    boolean deleteType(int typeId);

    Type findTypeById(int typeId);

}
