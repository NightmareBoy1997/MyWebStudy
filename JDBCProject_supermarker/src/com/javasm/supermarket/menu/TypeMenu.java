package com.javasm.supermarket.menu;

import com.javasm.supermarket.bean.Type;
import com.javasm.supermarket.service.TypeService;
import com.javasm.supermarket.service.impl.TypeServiceImpl;
import com.javasm.supermarket.util.InputUtil;

import java.util.Objects;

/**
 * @author: Lisa
 * @className: TypeMenu
 * @description:
 * @date: 2022/3/17 10:46
 * @version: 0.1
 * @since: jdk11
 */
public class TypeMenu {

    private static final TypeService typeService = new TypeServiceImpl();

    public void menu() {

        String s;
        do {
            System.out.println("---------------------1.新增类型--------------------");
            System.out.println("---------------------2.删除类型--------------------");
            System.out.println("---------------------3.修改类型--------------------");
            System.out.println("---------------------4.层级查询--------------------");
            System.out.println("---------------------5.退    出--------------------");
            int choice = InputUtil.inputInt("^[1-5]$", "请录入1-5的数据:");
            switch (choice) {
                case 1:
                    addTypeFun();
                    break;
                case 2:
                    deleteTypeFun();
                    break;
                case 3:
                    updateTypeFun();
                    break;
                case 4:
                    showTypeByLevel();
                    break;
                case 5:
                    System.out.println("程序退出");
                    return;
            }
            System.out.println("是否继续操作类型模块?y/n");
            s = InputUtil.inputStr();
        } while (Objects.equals(s, "y"));
    }

    private void updateTypeFun() {
        showTypeByLevel();
        System.out.println("请录入要修改类型的id:");
        int typeId = InputUtil.inputInt();
        Type type = typeService.findTypeById(typeId);
        System.out.println("类型的原信息如下:" + type);
        System.out.println("请录入类型的新名称:");
        type.setTypeName(InputUtil.inputStr());
        System.out.println("请录入类型的新父类型");
        type.setParentId(InputUtil.inputInt());
        System.out.println("请录入类型的状态: 1.可用 2.删除/不可用");
        int i = InputUtil.inputInt();
        boolean flag = false;
        if (i == 1) {
            flag = true;
        }
        type.setTypeStatus(flag);
        if (typeService.addUpdateType(type)) {
            System.out.println("修改id为<<" + typeId + ">>类型成功");
        }
    }

    private void deleteTypeFun() {
        showTypeByLevel();
        System.out.println("请录入要删除的类型的id:");
        int typeId = InputUtil.inputInt();
        if (typeService.deleteType(typeId)) {
            System.out.println("删除类型id<<" + typeId + ">>成功");
        }
    }

    private void addTypeFun() {
        Type type = new Type();
        System.out.println("请录入新的类型的名称:");
        String typeName = InputUtil.inputStr();
        type.setTypeName(typeName);

        typeService.findAllType();
        System.out.println("请指定该<<" + typeName + ">>类型的父级类型:(作为1级类型 请给0)");
        int parentId = InputUtil.inputInt();
        type.setParentId(parentId);
        typeService.addUpdateType(type);
        System.out.println("新增<<" + typeName + ">>类型成功");
    }

    private void showTypeByLevel() {
        System.out.println("类型信息展示如下:");
        typeService.findAllType();
    }
}
