package com.jeasonchan.readproperties.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ApplicationProperty {
    @Value("${application.name}")//@Value方式适合从配置文件中取单个值和需要校验配置参数的情况
            String name;

    @Value("${application.version}")
    String version;
}
