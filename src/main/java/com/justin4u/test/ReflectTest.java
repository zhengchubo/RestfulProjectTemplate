package com.justin4u.test;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReflectTest {

    static int count = 0;
    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        //        test2();
        //test3();
        test6();
    }

    public static void test() {

        int[] a1 = new int[]{1, 2, 3};
        int[] a2 = new int[5];
        int[][] a3 = new int[2][3];
        System.out.println(a1.getClass());
        System.out.println(a1.getClass() == a2.getClass());
        System.out.println(a3.getClass());
        System.out.println(a3.getClass().getSuperclass());
    }

    public static void test2() {
        ArrayList collection5 = new ArrayList<Integer>();
        ArrayList<String> collection6 = collection5;//编译通过
    }

    public static void test3() {
        List<Integer> c1 = new ArrayList<Integer>();
        List<String> c2 = new ArrayList<String>();
        System.out.println(c1.getClass() == c2.getClass());
        System.out.println(c1.getClass().getName());
    }

    public static void test4() {
        class MyTimerTask extends TimerTask {

            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " bomb!");
                new Timer().schedule(new MyTimerTask(), 2000 + 1000 * (++count % 2));
            }
        }
        new Timer().schedule(new MyTimerTask(), 2000);
    }

    public static void test5() {
        Business business = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 50; i++) {
                    business.sub(i);
                }
            }
        }).start();
        for (int i = 1; i < 50; i++) {
            business.main(i);
        }
    }

    public static void test6() {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
    }

    public static void test7() {
        Executors.newScheduledThreadPool(3).scheduleAtFixedRate(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("ScheduledThreadPool " + Thread.currentThread().getName());
                    }
                }, 3, 1, TimeUnit.SECONDS
        );

    }
}
