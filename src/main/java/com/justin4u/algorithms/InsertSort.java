package com.justin4u.algorithms;

public class InsertSort extends Sort {

    /**
     * 插入排序：第i次迭代，将第i个元素与之左侧的每一个比它大的元素进行交换
     * 特点：运行时间与输入有关，当输入为有序时，时间复杂度为 O(n)
     * 适合于部分有序数组，也适合于小规模数组。
     *
     * @param a
     */
    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) {
                    swap(a, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }
}
