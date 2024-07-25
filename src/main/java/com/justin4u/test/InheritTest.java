package com.justin4u.test;

/**
 * 1）静态变量只会初始化（执⾏）⼀次。
 * 2）当有⽗类时，完整的初始化顺序为：⽗类静态变量（静态代码块）->⼦类静态变量（静态代码块）->⽗类⾮静态变量（⾮静态代码块）->⽗类构造器 ->⼦类⾮静态变量（⾮静态代码块）->⼦类构造器 。
 */
public class InheritTest {

    public static void main(String[] args) {
        A ab = new B();
        ab = new B();
        // ABabab2
        System.out.println(test());
        // 1
        System.out.println(test2());
    }

    public static int test() {
        try {
            return 1;
        } finally {
            return 2;
        }
    }

    public static int test2() {
        int i=0;
        try {
            i=1;
            return i;
        } finally {
            i=2;
        }
    }
}

class A {
    static {
        System.out.println("A");
    }
    public A() {
        System.out.println("a");
    }
}

class B extends A {
    static {
        System.out.println("B");
    }

    public B() {
        System.out.println("b");
    }
}
