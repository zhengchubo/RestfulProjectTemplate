package com.justin4u.playground.retry;

/**
 * com.justin4u.playground.retry
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2020-12-03</pre>
 */
public class UserServiceImpl implements UserService {
    @Override
    public void add() throws Exception {
        System.out.println("添加用户");
        throw new RuntimeException();
    }

    @Override
    @Retryable(maxAttempts = 3)
    public void query() throws Exception {
        System.out.println("查询用户");
        throw new RuntimeException();
    }
}
