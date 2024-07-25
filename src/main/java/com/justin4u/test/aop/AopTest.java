package com.justin4u.test.aop;

import java.io.InputStream;
import java.util.Collection;

public class AopTest {
    public static void main(String[] args) {
        InputStream is = AopTest.class.getClassLoader().getResourceAsStream("config.properties");
        Object bean = new BeanFactory(is).getBean("xxx");
        System.out.println(bean.getClass().getName());
        ((Collection) bean).clear();
    }
}
