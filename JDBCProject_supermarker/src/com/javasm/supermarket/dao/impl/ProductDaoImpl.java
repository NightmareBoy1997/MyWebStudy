package com.javasm.supermarket.dao.impl;

import com.javasm.supermarket.bean.Product;
import com.javasm.supermarket.dao.ProductDao;
import com.javasm.supermarket.sql.ProductSql;
import com.javasm.supermarket.util.DruidUtil;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigInteger;
import java.sql.Connection;
import java.util.List;

/**
 * @author: Lisa
 * @className: ProductDaoImpl
 * @description:
 * @date: 2022/3/17 15:41
 * @version: 0.1
 * @since: jdk11
 */
public class ProductDaoImpl implements ProductDao {

    private Connection connection;

    public ProductDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public ProductDaoImpl() {
    }

    private static final QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());

    @Override
    public List<Product> findProductByTypeId(int typeId) throws Exception {
        return queryRunner.query(ProductSql.FIND_PRODUCT_BY_TYPE,
                new BeanListHandler<>(Product.class, new BasicRowProcessor(new GenerousBeanProcessor())), typeId);
    }

    @Override
    public int addProduct(Product product) throws Exception {
        return queryRunner.update(ProductSql.ADD_PRODUCT,
                product.getProdName(),
                product.getTypeId(),
                product.getProdPrice(),
                product.getProdStore(),
                product.getProdImage(),
                product.getProdStatus(),
                product.getProdDesc()
        );
    }

    @Override
    public List<Product> findProductByPage(int page, int size) throws Exception {
        return queryRunner.query(
                ProductSql.FIND_PRODUCT_BY_PAGE,
                new BeanListHandler<>(Product.class, new BasicRowProcessor(new GenerousBeanProcessor())),
                (page - 1) * size, size);
    }

    @Override
    public int findProductCount() throws Exception {
        return queryRunner.query(ProductSql.FIND_PRODUCT_COUNT, new ScalarHandler<Long>()).intValue();
    }

    @Override
    public Product findProductById(int pid) throws Exception {
        return queryRunner.query(ProductSql.FIND_PRODUCT_BY_ID, new BeanHandler<>(Product.class,
                new BasicRowProcessor(new GenerousBeanProcessor())), pid);
    }

    @Override
    public int updateProductById(Product product) throws Exception {
        //这里有问题
        return new QueryRunner().update(connection, ProductSql.UPDATE_PRODUCT_BY_ID,
                product.getProdName(),
                product.getTypeId(),
                product.getProdPrice(),
                product.getProdStore(),
                product.getProdImage(),
                product.getProdStatus(),
                product.getProdDesc(),
                product.getId()
        );
    }
}
