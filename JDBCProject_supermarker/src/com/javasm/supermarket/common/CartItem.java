package com.javasm.supermarket.common;

import com.javasm.supermarket.bean.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author: Lisa
 * @className: CartItem
 * @description: 购物车里面的购买的信息
 * @date: 2022/3/19 10:10
 * @version: 0.1
 * @since: jdk11
 */
@Setter
@Getter
public class CartItem {

    private Product product;
    private Integer buyNum;
    private BigDecimal money;

    public BigDecimal getMoney() {
        money = new BigDecimal(buyNum.toString()).multiply(new BigDecimal(product.getProdPrice().toString()));
        return money;
    }

    public CartItem(Product product, Integer buyNum) {
        this.product = product;
        this.buyNum = buyNum;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "productId=" + product.getId() +
                ", productName=" + product.getProdName() +
                ", productStore=" + product.getProdStore() +
                ", productPrice=" + product.getProdPrice() +
                ", buyNum=" + buyNum +
                ", money=" + getMoney() +
                '}';
    }
}
