package com.justin4u.algorithms;

import java.util.Random;

public class ShuffleSort extends Sort {

    /**
     * 洗牌
     *
     * @param a
     */
    public static void shuffle(Comparable[] a) {
        int l = a.length;
        for (int i = 0; i < l; i++) {
            swap(a, i, getRandom(0, l));
        }
    }

    private static int getRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }

    public static void main(String[] args) {
        Integer[] a = {1, 2, 3, 4, 5};
        shuffle(a);
        for (int i : a) {
            System.out.println(i);
        }
    }
}
