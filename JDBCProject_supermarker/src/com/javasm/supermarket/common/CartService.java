package com.javasm.supermarket.common;

import com.javasm.supermarket.bean.Product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Lisa
 * @className: CartService
 * @description:
 * @date: 2022/3/19 10:15
 * @version: 0.1
 * @since: jdk11
 */
public class CartService {

    private CartService() {
    }

    //创建购物车
    public static Map<Integer, CartItem> cart;

    static {
        cart = new HashMap<>(16);
    }

    public static void addProdToCart(Product product, Integer buyNum) {

        Integer id = product.getId();
        CartItem item = cart.get(id);
        if (item != null) {
            //之前买过这个商品
            item.setBuyNum(item.getBuyNum() + buyNum);
        } else {
            //第一次
            item = new CartItem(product, buyNum);
            cart.put(id, item);
        }
        System.out.println("购买<<" + buyNum + ">>个" + product.getProdName() + "成功");
    }

    public static void showProdList() {
        if (cart.isEmpty()) {
            System.out.println("购物车目前没有商品");
            return;
        }
        //遍历map
        cart.forEach((id, item) -> System.out.println(item));
    }

    public static CartItem getProdById(int pid) {
        return cart.get(pid);
    }

    private static BigDecimal totalMoney;

    public static BigDecimal getTotalMoney() {
        totalMoney = new BigDecimal("0");
        cart.forEach((id, item) -> {
            totalMoney = totalMoney.add(item.getMoney());
        });
        return totalMoney;
    }
}
