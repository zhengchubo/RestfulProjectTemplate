package com.justin4u.test;

import java.util.Random;

public class ThreadLocalTest {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    threadLocal.set(data);
                    System.out.println(Thread.currentThread() + " put data " + data);
                    MyThreadScopeData.getThreadInstance().setName(Thread.currentThread().toString());
                    MyThreadScopeData.getThreadInstance().setAge(data % 10);

                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }

    static class A {
        public void get() {
            int data = threadLocal.get();
            System.out.println("A from " + Thread.currentThread() + " get data " + data);
            MyThreadScopeData myThreadScopeData = MyThreadScopeData.getThreadInstance();
            System.out.println("A from " + myThreadScopeData);
        }
    }

    static class B {
        public void get() {
            int data = threadLocal.get();
            System.out.println("B from " + Thread.currentThread() + " get data " + data);
            MyThreadScopeData myThreadScopeData = MyThreadScopeData.getThreadInstance();
            System.out.println("B from " + myThreadScopeData);
        }
    }
}


class MyThreadScopeData {
    private static ThreadLocal<MyThreadScopeData> data = new ThreadLocal<>();
    private String name;
    private int age;

    private MyThreadScopeData() {
    }

    public static MyThreadScopeData getThreadInstance() {
        MyThreadScopeData instance = data.get();
        if (instance == null) {
            instance = new MyThreadScopeData();
            data.set(instance);
        }
        return instance;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String retVal = super.toString() + "-{name, age}" + ":{" + getName() + "," + getAge() + "}";
        return retVal;
    }
}