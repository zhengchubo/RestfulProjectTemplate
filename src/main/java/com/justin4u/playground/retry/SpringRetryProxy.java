package com.justin4u.playground.retry;

import org.springframework.cglib.proxy.Enhancer;

/**
 * com.justin4u.playground.retry
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2020-12-03</pre>
 */
public class SpringRetryProxy {
    public Object newProxyInstance(Object target) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(new AnnotationAwareRetryOperationsInterceptor());
        return enhancer.create();
    }
}
