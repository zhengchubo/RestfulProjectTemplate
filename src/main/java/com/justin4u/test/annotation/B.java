package com.justin4u.test.annotation;

import java.util.HashMap;
import java.util.Map;

@A(name = "type", gid = Long.class)
public class B {
    @A(name = "param", id = 1, gid = Long.class)
    private Integer age;

    @A(name = "construct", id = 2, gid = Long.class)
    public B() {
    }

    @A(name = "public method", id = 3, gid = Long.class)
    public void a() {
    }

    @A(name = "protected method", id = 4, gid = Long.class)
    protected void b() {
    }

    @A(name = "private method", id = 5, gid = Long.class)
    private void c() {
        Map<String, String> map = new HashMap<>(0);
    }

    public void b(Integer a) {
    }

}
