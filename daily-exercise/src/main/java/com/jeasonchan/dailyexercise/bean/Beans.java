package com.jeasonchan.dailyexercise.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Beans {

    @Bean("arrayList")
    public <T> List<T> arrayList() {
        return new ArrayList<>();
    }
}
