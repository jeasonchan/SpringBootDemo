package com.jeasonchan.readproperties.controller;

import com.jeasonchan.readproperties.property.ApplicationProperty;
import com.jeasonchan.readproperties.property.DevelopProperty;
import lombok.AllArgsConstructor;
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

    ====================
    使用了两种不同的方式，从配置文件中读取配置：
    @value

    和

    @component + @configurationProperty
    ======================
    在构造函数中使用autowerid自动注入组件实例


    =========



     */
}
