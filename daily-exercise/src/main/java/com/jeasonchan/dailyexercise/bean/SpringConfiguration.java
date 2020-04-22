package com.jeasonchan.dailyexercise.bean;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "server")
public class SpringConfiguration {
    @Value("${server.servlet.context-path}")  //"server.servlet.contextPath"
    private String contextPath;


    @Override
    public String toString() {
        return "SpringConfiguration{" +
                "contextPath='" + contextPath + '\'' +
                '}';
    }
}
