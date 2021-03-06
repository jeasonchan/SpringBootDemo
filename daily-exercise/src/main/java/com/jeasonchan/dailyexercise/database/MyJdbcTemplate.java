package com.jeasonchan.dailyexercise.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyJdbcTemplate {
    public JdbcTemplate jdbcTemplate;

    @Autowired
    public MyJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        System.out.println("jdbcTemplate通过构造函数注入完成！");
    }


    @Override
    public String toString() {
        return "MyJdbcTemplate{" +
                "jdbcTemplate=" + jdbcTemplate +
                '}';
    }
}
