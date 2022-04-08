package com.javasm.supermarket.service;

import com.javasm.supermarket.bean.Product;

import java.util.List;

/**
 * @author: Lisa
 * @className: ProductService
 * @description:
 * @date: 2022/3/18 10:25
 * @version: 0.1
 * @since: jdk11
 */
public interface ProductService {
    List<Product> findProductByPage(int page, int size);

    int findProductCount();

    Product findProductById(int pid);

    int addAndUpdateProduct(Product product);
}
