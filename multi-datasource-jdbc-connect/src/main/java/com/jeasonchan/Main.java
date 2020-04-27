package com.jeasonchan;

import com.jeasonchan.dao.DBService1;
import com.jeasonchan.dao.DBService2;
import com.jeasonchan.util.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        String sql = "show databases";

        System.out.println("连接池1：");
        DBService1 dBService1 = (DBService1) SpringUtil.getBeanByName("dBService1");
        System.out.println(dBService1.getJdbcTemplate().queryForList(sql));


        System.out.println("连接池2：");
        DBService2 dBService2 = (DBService2) SpringUtil.getBeanByName("dBService2");
        System.out.println(dBService2.getJdbcTemplate().queryForList(sql));


    }
}
