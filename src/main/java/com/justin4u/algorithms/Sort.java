package com.justin4u.algorithms;

public class Sort {
    // judge whether a is less than b.
    protected static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    // exchange the item at index i in array a with the one at index j.
    protected static void swap(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
