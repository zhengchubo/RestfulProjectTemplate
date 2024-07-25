package com.justin4u.test;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyAdvice implements Advice {

    long beginTime = 0;

    public static Object getProxy(final Object target, final Advice advice) {
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), (proxy1, method, args) -> {
            advice.beforeMethod(method);
            Object retVal = method.invoke(target, args);
            advice.afterMethod(method);
            return retVal;
        });
        return proxy;
    }

    @Override
    public void beforeMethod(Method method) {
        System.out.println(method.getName() + "() before at " + beginTime);
        beginTime = System.currentTimeMillis();
    }

    @Override
    public void afterMethod(Method method) {
        long endTime = System.currentTimeMillis();
        System.out.println(method.getName() + "() cost total " + (endTime - beginTime));
    }
}
