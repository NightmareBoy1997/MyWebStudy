package com.javasm.supermarket.sql;

/**
 * @author: Lisa
 * @className: ProductSql
 * @description:
 * @date: 2022/3/17 15:44
 * @version: 0.1
 * @since: jdk11
 */
public interface ProductSql {
    String FIND_PRODUCT_BY_TYPE = "SELECT * FROM product WHERE type_id=?";
    String ADD_PRODUCT = "INSERT INTO product (prod_name, type_id, prod_price, prod_store, prod_image, prod_status, prod_desc) VALUES (?,?,?,?,?,?,?)";
    String FIND_PRODUCT_BY_PAGE = "SELECT * FROM product WHERE prod_status=1 ORDER by id DESC LIMIT ?,?";

    String FIND_PRODUCT_COUNT = "SELECT count(*) FROM product WHERE prod_status=1";
    String FIND_PRODUCT_BY_ID = "SELECT * FROM product WHERE id=?";
    String UPDATE_PRODUCT_BY_ID = "UPDATE product SET prod_name=?, type_id=?, prod_price=?, prod_store=?, prod_image=?, prod_status=?, prod_desc=? WHERE id=?";
}
