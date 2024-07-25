package com.justin4u.test.aop;

import com.justin4u.test.Advice;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactoryBean {
    private Advice advice;
    private Object target;

    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Object getProxy() {
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                advice.beforeMethod(method);
                Object retVal = method.invoke(target, args);
                advice.afterMethod(method);
                return retVal;
            }
        });
        return proxy;
    }
}
