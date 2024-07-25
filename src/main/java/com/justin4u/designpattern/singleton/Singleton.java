package com.justin4u.designpattern.singleton;

public class Singleton {
    private static Singleton instance = new Singleton();

    private Singleton() {
    }

    //@org.jetbrains.annotations.Contract(pure = true)
    public static Singleton getInstance() {
        return instance;
    }
}
