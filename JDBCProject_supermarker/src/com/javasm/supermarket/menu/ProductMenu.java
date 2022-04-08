package com.javasm.supermarket.menu;

import com.javasm.supermarket.bean.Product;
import com.javasm.supermarket.bean.Type;
import com.javasm.supermarket.service.ProductService;
import com.javasm.supermarket.service.TypeService;
import com.javasm.supermarket.service.impl.ProductServiceImpl;
import com.javasm.supermarket.service.impl.TypeServiceImpl;
import com.javasm.supermarket.util.InputUtil;

import java.util.List;
import java.util.Objects;

/**
 * @author: Lisa
 * @className: ProductMenu
 * @description:
 * @date: 2022/3/18 10:17
 * @version: 0.1
 * @since: jdk11
 */
public class ProductMenu {

    public static void main(String[] args) {
        new ProductMenu().menu();
    }

    public void menu() {
        String s;
        do {
            System.out.println("---------------------1.新增商品--------------------");
            System.out.println("---------------------2.删除商品--------------------");
            System.out.println("---------------------3.修改商品--------------------");
            System.out.println("---------------------4.分页查询--------------------");
            System.out.println("---------------------5.退    出--------------------");
            int choice = InputUtil.inputInt("^[1-5]$", "请录入1-5的数据:");
            switch (choice) {
                case 1:
                    addProductFun();
                    break;
                case 2:
                    deleteProductFun();
                    break;
                case 3:
                    updateProductFun();
                    break;
                case 4:
                    showProductByPage();
                    break;
                case 5:
                    System.out.println("程序退出");
                    return;
            }
            System.out.println("是否继续操作商品模块?y/n");
            s = InputUtil.inputStr();
        } while (Objects.equals(s, "y"));
    }

    private void showProductByPage() {
        System.out.println("目前在售产品信息如下:");
        final int size = 2;
        int page = 1;
        int totalCount = productService.findProductCount();
        int totalPage = totalCount / size;
        totalPage = (totalCount % size == 0) ? totalPage : totalPage + 1;
        do {
            System.out.println("第<<" + page + ">>页信息如下:");
            List<Product> productList = productService.findProductByPage(page, size);
            productList.forEach(System.out::println);
            System.out.println("是否继续查询其他商品?y/n");
            String s = InputUtil.inputStr();
            if (Objects.equals("n", s)) {
                break;
            }
            page = continuePage(totalPage);
        } while (true);
    }

    private void updateProductFun() {
        final int size = 2;
        int page = 1;
        int totalCount = productService.findProductCount();
        int totalPage = totalCount / size;
        totalPage = (totalCount % size == 0) ? totalPage : totalPage + 1;
        do {
            System.out.println("第<<" + page + ">>页信息如下:");
            List<Product> productList = productService.findProductByPage(page, size);
            productList.forEach(System.out::println);
            System.out.println("请选择要执行的功能: 1. 修改商品  2. 继续分页查询 3.删除商品");
            switch (InputUtil.inputInt()) {
                case 1:
                    updateProduct();
                    return;
                case 2:
                    page = continuePage(totalPage);
                    break;
                case 3:
                    deleteProductFun();
                    return;
            }
        } while (true);
    }

    private int continuePage(int totalPage) {
        for (int pageNum = 1; pageNum <= totalPage; pageNum++) {
            System.out.print(pageNum + "\t");
        }
        System.out.println();
        System.out.println("请录入要查看的页数:");
        return InputUtil.inputInt();
    }

    private void updateProduct() {
        System.out.println("请录入要修改的商品的id:");
        int pid = InputUtil.inputInt();
        Product product = productService.findProductById(pid);
        System.out.println("修改的商品详情如下:" + product);
        System.out.println("请选择要修改的字段(格式:1,2): 1.prodName 2.typeId 3.prodPrice 4.prodStore 5.prodImage 6.prodStatus 7.prodDesc");
        String str = InputUtil.inputStr();
        String[] array = str.split(",");
        for (String numStr : array) {
            int choice = Integer.parseInt(numStr);
            switch (choice) {
                case 1:
                    System.out.println("请录入新的商品名称");
                    product.setProdName(InputUtil.inputStr());
                    break;
                case 2:
                    typeService.findAllType();
                    System.out.println("请选择商品所属类型:");
                    product.setTypeId(InputUtil.inputInt());
                    break;
                case 3:
                    System.out.println("请录入商品单价:");
                    product.setProdPrice(Double.parseDouble(InputUtil.inputStr()));
                    break;
                case 4:
                    System.out.println("请录入商品库存:");
                    product.setProdStore(InputUtil.inputInt());
                    break;
                case 5:
                    System.out.println("请录入商品图片路径:");
                    product.setProdImage(InputUtil.inputStr());
                    break;
                case 6:
                    System.out.println("请录入商品状态: 1. 在售  2. 下架  3.删除");
                    product.setProdStatus(InputUtil.inputInt());
                    break;
                case 7:
                    System.out.println("请录入商品描述:");
                    product.setProdDesc(InputUtil.inputStr());
                    break;
            }
            productService.addAndUpdateProduct(product);
            System.out.println("修改商品<<" + product.getProdName() + ">>成功");
        }
    }

    private void deleteProductFun() {
        System.out.println("请录入要删除的商品id:");
        int pid = InputUtil.inputInt();
        System.out.println("确认删除吗?y/n");
        String str = InputUtil.inputStr();
        if (Objects.equals("n", str)) return;
        Product product = productService.findProductById(pid);
        product.setProdStatus(3);
        productService.addAndUpdateProduct(product);
        System.out.println("删除id为<<" + pid + ">>商品成功");
    }

    private static final TypeService typeService = new TypeServiceImpl();
    private static final ProductService productService = new ProductServiceImpl();

    private void addProductFun() {
        Product product = new Product();
        System.out.println("请录入商品的名称:");
        product.setProdName(InputUtil.inputStr());
        typeService.findAllType();
        System.out.println("请选择商品所属类型:");
        product.setTypeId(InputUtil.inputInt());
        System.out.println("请录入商品库存:");
        product.setProdStore(InputUtil.inputInt());
        System.out.println("请录入商品单价:");
        product.setProdPrice(Double.parseDouble(InputUtil.inputStr()));
        System.out.println("请录入商品图片路径:");
        product.setProdImage(InputUtil.inputStr());

        System.out.println("请录入商品状态: 1. 在售  2. 下架  3.删除");
        product.setProdStatus(InputUtil.inputInt());

        System.out.println("请录入商品描述:");
        product.setProdDesc(InputUtil.inputStr());

        System.out.println(productService.addAndUpdateProduct(product));
        System.out.println("新增商品<<" + product.getProdName() + ">>成功");
    }
}
