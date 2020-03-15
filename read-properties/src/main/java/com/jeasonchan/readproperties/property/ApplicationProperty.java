package com.jeasonchan.readproperties.property;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ApplicationProperty {
    @Value("${application.name}")
    String name;

    @Value("${application.version}")
    String version;
}
