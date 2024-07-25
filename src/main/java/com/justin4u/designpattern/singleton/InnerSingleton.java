package com.justin4u.designpattern.singleton;

public class InnerSingleton {
    private static class SingletonHolder {
        private static final InnerSingleton instance = new InnerSingleton();
    }

    private InnerSingleton() {
    }

    public static InnerSingleton getInstance() {
        return SingletonHolder.instance;
    }

}
