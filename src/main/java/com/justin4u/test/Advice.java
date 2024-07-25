package com.justin4u.test;

import java.lang.reflect.Method;

public interface Advice {
    void beforeMethod(Method method);

    void afterMethod(Method method);
}
