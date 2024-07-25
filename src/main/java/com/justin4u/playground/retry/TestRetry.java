package com.justin4u.playground.retry;

/**
 * com.justin4u.playground.retry
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2020-12-03</pre>
 */
public class TestRetry {

    public static void main(String[] args) throws Exception {
        UserService userService = new UserServiceImpl();
        SpringRetryProxy proxy = new SpringRetryProxy();
        UserService userService1 = (UserService) proxy.newProxyInstance(userService);
        //userService1.add();
        userService1.query();
    }
}
