package com.justin4u.playground.retry;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * com.justin4u.playground
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2020-12-03</pre>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Retryable {
    int maxAttempts() default 0;
}
