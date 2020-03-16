package com.jeasonchan.readproperties.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


@Component
public class SpringUtil implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
        System.out.println("=========================\n" +
                "SpringUtil.applicationContext" + "\n" +
                "=======================配置成功============");
    }


    private static ApplicationContext applicationContext = null;

    public static ApplicationContext getApplicationContext() {
        return SpringUtil.applicationContext;
    }

    public static Object getBeanByName(String name) {
        return getApplicationContext().getBean(name);
    }

}
