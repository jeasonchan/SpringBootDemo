package com.jeasonchan.readproperties.controller;

import com.jeasonchan.readproperties.property.ApplicationProperty;
import com.jeasonchan.readproperties.property.DevelopProperty;
import com.jeasonchan.readproperties.util.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ReadPropertyController {
    private ApplicationProperty applicationProperty;
    private final DevelopProperty developProperty;

    @Autowired
    public ReadPropertyController(ApplicationProperty applicationProperty, DevelopProperty developProperty) {
        this.applicationProperty = applicationProperty;
        this.developProperty = developProperty;
    }


    @GetMapping(path = "/property")
    public Map<String, Object> getProperty() {
        Map<String, Object> result = new HashMap<>();
        result.put("applicationProperty", applicationProperty);
        result.put("developProperty", developProperty);
        return result;
    }


    /*
    要点
    ====================
    使用了两种不同的方式，从配置文件中读取配置：
    @value

    和

    @component + @configurationProperty

    ======================
    在构造函数中使用autowerid自动注入组件实例


    ======================
    配置文件里的默认配置属性，也就是隐藏的配置类，包括但不限于：
    server:
         port: 8080
         servlet:
                context-path: /demo

    spring:
      profiles:
        active: B

    application.yml和其余的A配置和B配置形成互补配置，
    因此，application和developer两个配置实例的数据也可能拿到



     */


    //===============================================================
    //尝试获取applicationContext，并从ioc中获取bean对象
    @GetMapping(path = "/SpringUtil")
    public DevelopProperty getDevelopProperty() {
        return (DevelopProperty) SpringUtil.getBeanByName("developProperty");
    }

    /*
    通过以上方式，先获取applicationContext，再通过先获取applicationContext获取IOC中的实例，
    要点的有：
    1、写一个类，能被注入到IOC中，且是实现了ApplicationContextAware接口
    2、被获取的类也同样能够被注入到IOC中

     最简单、主流的注入方式无非是@component  和
     @configutation(使用java类作为装配方式) + @bean（方法名即为bean的名称）

     */
}
