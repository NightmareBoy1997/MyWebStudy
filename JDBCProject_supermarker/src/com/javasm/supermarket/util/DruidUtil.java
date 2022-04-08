package com.javasm.supermarket.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author: Lisa
 * @className: DruidUtil
 * @description:
 * @date: 2022/3/17 11:02
 * @version: 0.1
 * @since: jdk11
 */
public class DruidUtil {

    private DruidUtil() {
    }

    private static DataSource dataSource;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/druid.properties"));
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
