package com.justin4u.service;

import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;

/**
 * com.justin4u.service
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-06-09</pre>
 */
public class TestService {

    @Resource(name = "redisStringTemplate")
    private ValueOperations<String, String> valueOperations;

    public void test() {
        final String key = "KEY_NAME";
        // 放入缓存
        valueOperations.set(key, "value", 3, TimeUnit.MINUTES);
        // 取出缓存
        String redisResult = valueOperations.get(key);
        System.out.println(redisResult);
    }

}
