package com.javasm.supermarket.dao;

import com.javasm.supermarket.bean.Product;

import java.util.List;

/**
 * @author: Lisa
 * @className: ProductDao
 * @description:
 * @date: 2022/3/17 15:41
 * @version: 0.1
 * @since: jdk11
 */
public interface ProductDao {

    //根据类型id查询关联的商品
    List<Product> findProductByTypeId(int typeId) throws Exception;

    int addProduct(Product product) throws Exception;

    List<Product> findProductByPage(int page, int size) throws Exception;

    int findProductCount() throws Exception;

    Product findProductById(int pid) throws Exception;

    int updateProductById(Product product) throws Exception;
}
