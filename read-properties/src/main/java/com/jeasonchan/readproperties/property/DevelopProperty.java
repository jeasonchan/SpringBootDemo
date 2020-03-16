package com.jeasonchan.readproperties.property;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component("developProperty")
@ConfigurationProperties(prefix = "developer")
// 因为，直接使用的application.yml 作为配置文件，
// 所以，不需要使用@PropertySource(value = {"classpath:person.yaml"}) 指定额外的配置文件来源
public class DevelopProperty {
    private String name;
    private String website;
    private String qq;
    private String phoneNumber;
}
