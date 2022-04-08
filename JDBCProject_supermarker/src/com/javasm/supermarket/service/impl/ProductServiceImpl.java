package com.javasm.supermarket.service.impl;

import com.javasm.supermarket.bean.Product;
import com.javasm.supermarket.dao.ProductDao;
import com.javasm.supermarket.dao.impl.ProductDaoImpl;
import com.javasm.supermarket.service.ProductService;
import com.javasm.supermarket.util.FileUtil;
import lombok.SneakyThrows;

import java.util.List;

/**
 * @author: Lisa
 * @className: ProductServiceImpl
 * @description:
 * @date: 2022/3/18 10:25
 * @version: 0.1
 * @since: jdk11
 */
public class ProductServiceImpl implements ProductService {

    private static final ProductDao productDao = new ProductDaoImpl();

    @SneakyThrows
    @Override
    public int addAndUpdateProduct(Product product) {
        String prodImage = product.getProdImage();
        if (!prodImage.isBlank() && !prodImage.startsWith("upload")) {
            //商品图片上传
            product.setProdImage(FileUtil.uploadProductImage(product.getProdImage()));
        }
        if (product.getId() == null) {
            return productDao.addProduct(product);
        }
        return productDao.updateProductById(product);
    }

    @SneakyThrows
    @Override
    public List<Product> findProductByPage(int page, int size) {
        return productDao.findProductByPage(page, size);
    }

    @SneakyThrows
    @Override
    public int findProductCount() {
        return productDao.findProductCount();
    }

    @SneakyThrows
    @Override
    public Product findProductById(int pid) {
        return productDao.findProductById(pid);
    }
}
