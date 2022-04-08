package com.javasm.supermarket.service.impl;

import com.javasm.supermarket.bean.Product;
import com.javasm.supermarket.bean.Type;
import com.javasm.supermarket.dao.ProductDao;
import com.javasm.supermarket.dao.TypeDao;
import com.javasm.supermarket.dao.impl.ProductDaoImpl;
import com.javasm.supermarket.dao.impl.TypeDaoImpl;
import com.javasm.supermarket.service.TypeService;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Lisa
 * @className: TypeServiceImpl
 * @description:
 * @date: 2022/3/17 11:06
 * @version: 0.1
 * @since: jdk11
 */
public class TypeServiceImpl implements TypeService {

    private static final TypeDao typeDao = new TypeDaoImpl();
    private static final ProductDao productDao = new ProductDaoImpl();

    //缓存的思想: 类型的数据  一般都不变  每次查询数据库 性能比较低的
    //一般不变的数据  其实可以存储在内存/缓存
    //查询数据----> 从缓存去查询
    //1. 有  直接操作缓存的数据
    //2. 没有 从数据库查询 并存储缓存

    //优点: 提高了查询的性能
    //缺点: 更新了  缓存是不知道的  不能实时同步
    //更新了---->更改缓存的数据
    //通过缓存获得存储的数据----> 缓存可以存储很多的数据----> 容器 Map
    private static Map<String, Object> cache = new HashMap<>(16);

    @Override
    public void findAllType() {
        try {
            List<Type> typeList = (List<Type>) cache.get("typeList");
            if (typeList == null) {
                //第一次查询类型
                typeList = typeDao.findAllType();
                cache.put("typeList", typeList);
            }
            String str = "|-";
            for (Type type : typeList) {
                if (type.getParentId().equals(0)) {
                    System.out.println(str + type.getId() + ":" + type.getTypeName());
                    //找指定的父类型的子级类型
                    findChildType(type, "| " + str, typeList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @SneakyThrows
    public boolean addUpdateType(Type type) {
        //判断指定的父级类型是否作为父类型使用
        Integer parentId = type.getParentId();//新类型的父类型的id的数据
        if (parentId != 0) {
            //根据类型id查询
            Type parentType = typeDao.findTypeById(parentId);
            if (!parentType.isParent()) {
                parentType.setParent(true);
                //修改父级类型的数据
                typeDao.updateTypeById(parentType);
            }
        } else type.setParent(true);
        if (type.getId() == null) {
            typeDao.addType(type);
        } else {
            typeDao.updateTypeById(type);
        }
        cache.put("typeList", typeDao.findAllType());
        return true;
    }

    @SneakyThrows
    @Override
    public boolean deleteType(int typeId) {
        Type type = typeDao.findTypeById(typeId);
        String typeName = type.getTypeName();
        //类型作为父级类型  不能删除
        if (type.isParent()) {
            System.out.println("<<" + typeName + ">>是作为父类型，无法删除");
            return false;
        }
        //删除子级类型:  1. 没有商品关联的时候 可以删除  2. 有删除关联的时候 不能删除
        //查询商品是否使用这个你要删除的类型
        List<Product> productList = productDao.findProductByTypeId(typeId);
        if (!productList.isEmpty()) {
            System.out.println("<<" + typeName + ">>是关联了具体商品，无法删除");
            return false;
        }
        //到底是否是真的删除? 仅仅是更改状态还是直接删除?
        typeDao.deleteTypeById(typeId);
        cache.put("typeList", typeDao.findAllType());
        return true;
    }

    @SneakyThrows
    @Override
    public Type findTypeById(int typeId) {
        return typeDao.findTypeById(typeId);
    }


    private void findChildType(Type parentType, String s, List<Type> typeList) {
        for (Type type : typeList) {
            if (type.getParentId().equals(parentType.getId())) {
                System.out.println(s + type.getId() + ":" + type.getTypeName());
                //如果子级类型是父级类型
                if (type.isParent()) {
                    findChildType(type, "| " + s, typeList);
                }
            }
        }
    }
}
