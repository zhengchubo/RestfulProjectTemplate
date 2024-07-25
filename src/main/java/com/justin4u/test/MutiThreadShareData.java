package com.justin4u.test;

public class MutiThreadShareData {

    private static MutiShareData mutiShareData = new MutiShareData();

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> System.out.println(Thread.currentThread() + ":{j from " + mutiShareData.getJ() + " + to: " + mutiShareData.increment() + "}")).start();
        }

        for (int j = 0; j < 2; j++) {
            new Thread(() -> System.out.println(Thread.currentThread() + ":{j from " + mutiShareData.getJ() + " - to: " + mutiShareData.decrement() + "}")).start();
        }
    }
}

class MutiShareData {
    private int j = 0;

    public synchronized int increment() {
        return ++j;
    }

    public synchronized int decrement() {
        return --j;
    }

    public synchronized int getJ() {
        return j;
    }

    public synchronized void setJ(int j) {
        this.j = j;
    }

}