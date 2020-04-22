package com.jeasonchan.dailyexercise.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component   //其他用到该类的app，在编译时会将该类解析成字节码，并放进jar包，运行时框架扫描到该类拥有Component注解，则将其注入容器，并调用接口方法进行应用背景注入
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }


    public static Object getBeanByName(String beanName) {
        return applicationContext.getBean(beanName);
    }
}
