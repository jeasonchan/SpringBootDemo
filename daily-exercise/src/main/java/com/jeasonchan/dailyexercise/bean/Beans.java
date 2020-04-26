package com.jeasonchan.dailyexercise.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Beans {

    @Bean("arrayList")
    public <T> List<T> arrayList() {
        return new ArrayList<>();
    }

}
