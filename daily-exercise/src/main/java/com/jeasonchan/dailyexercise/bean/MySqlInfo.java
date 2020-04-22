package com.jeasonchan.dailyexercise.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mysql")
@Getter
@Setter
public class MySqlInfo {
    private String url;
    private String user;
    private String password;

    @Override
    public String toString() {
        return "MySqlInfo{" +
                "url='" + url + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
