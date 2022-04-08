package com.javasm.supermarket.sql;

/**
 * @author: Lisa
 * @className: TypeSql
 * @description:
 * @date: 2022/3/17 10:53
 * @version: 0.1
 * @since: jdk11
 */
public interface TypeSql {

    //查询所有类型的数据
    String FIND_ALL_TYPE = "select * from type";
    String INSERT_TYPE = "insert into type (type_name, parent_id, parent, type_status) values (?,?,?,?)";
    String FIND_TYPE_BY_ID = "SELECT * FROM type WHERE id=?";
    String UPDATE_TYPE_BY_ID = "UPDATE type SET type_name=?, parent_id=?, parent=?, type_status=? WHERE id=?";
    String DELETE_TYPE_BY_ID = "DELETE FROM type WHERE id=?";
}
