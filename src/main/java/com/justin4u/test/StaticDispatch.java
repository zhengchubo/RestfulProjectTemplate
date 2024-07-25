package com.justin4u.test;

public class StaticDispatch {
    public static void sayHello(Human guy) {
        System.out.println("hello, guy!");
    }

    public static void sayHello(Man guy) {
        System.out.println("hello, gentleman!");
    }

    public static void sayHello(Woman guy) {
        System.out.println("hello, lady!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sd = new StaticDispatch();
        sd.sayHello(man);
        sd.sayHello(woman);
    }

    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }
}
