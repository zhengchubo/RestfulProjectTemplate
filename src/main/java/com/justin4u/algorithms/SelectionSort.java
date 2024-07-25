package com.justin4u.algorithms;

public class SelectionSort extends Sort {

    /**
     * 选择排序：对数组迭代，对下标i的右侧元素进行选择，将其中最小者与下标i对应的元素进行交换。
     * 特点：运行时间与输入无关，无论输入时是否已排序，时间复杂度均为 O(n^2)
     *
     * @param a
     */
    public static void sort(Comparable[] a) {
        int l = a.length;
        for (int i = 0; i < l; i++) {
            int min = i;
            for (int j = i + 1; j < l; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            swap(a, i, min);
        }
    }


}
