package com.justin4u.test;

public class Business {
    private boolean canDoSub = true;

    public synchronized void sub(int i) {
        while (!canDoSub) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j < 5; j++) {
            System.out.println("sub thread count " + j + ", " + i + "/50");
        }
        canDoSub = false;
        this.notify();
    }

    public synchronized void main(int i) {
        while (canDoSub) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int j = 1; j < 10; j++) {
            System.out.println("main thread count " + j + ", " + i + "/50");
        }
        canDoSub = true;
        this.notify();
    }
}
