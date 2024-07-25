package com.justin4u.playground.retry;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * com.justin4u.playground.retry
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2020-12-03</pre>
 */
public class AnnotationAwareRetryOperationsInterceptor implements MethodInterceptor {

    /**
     * 记录重试次数
     */
    private int times = 0;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //获取拦截的方法中的Retryable注解
        Retryable retryable = method.getAnnotation(Retryable.class);
        if (retryable == null) {
            return methodProxy.invokeSuper(o, objects);
        } else { //有Retryable注解，加入异常重试逻辑
            int maxAttemps = retryable.maxAttempts();
            try {
                return methodProxy.invokeSuper(o, objects);
            } catch (Throwable e) {
                if (times++ == maxAttemps) {
                    System.out.println("已达最大重试次数：" + maxAttemps + ",不再重试！");
                } else {
                    System.out.println("调用" + method.getName() + "方法异常，开始第" + times + "次重试。。。");
                    //注意这里不是invokeSuper方法，invokeSuper会退出当前interceptor的处理
                    methodProxy.invoke(o, objects);
                }
            }
        }
        return null;
    }
}
