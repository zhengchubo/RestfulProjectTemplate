package com.justin4u.test.aop;

import com.justin4u.test.Advice;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BeanFactory {
    Properties properties = new Properties();

    public BeanFactory(InputStream is) {
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object getBean(String name) {
        String classname = properties.getProperty(name);
        Object bean = null;
        try {
            Class clazz = Class.forName(classname);
            bean = clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        if (bean instanceof ProxyFactoryBean) {
            ProxyFactoryBean proxyFactoryBean = (ProxyFactoryBean) bean;
            Advice advice = null;
            Object target = null;
            try {
                advice = (Advice) Class.forName(properties.getProperty(name + ".advice")).newInstance();
                target = Class.forName(properties.getProperty(name + ".target")).newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

            proxyFactoryBean.setAdvice(advice);
            proxyFactoryBean.setTarget(target);
            Object proxy = ((ProxyFactoryBean) bean).getProxy();
            return proxy;
        }
        return bean;
    }
}
