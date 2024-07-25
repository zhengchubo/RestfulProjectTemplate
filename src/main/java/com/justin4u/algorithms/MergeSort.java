package com.justin4u.algorithms;

public class MergeSort extends Sort {
    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);  //将左边排序
        System.out.println(String.format("a=%s,aux=%s,lo=%d,mid=%d", a, aux, lo, mid));
        sort(a, aux, mid + 1, hi);  //将右边排序
        merge(a, aux, lo, mid, hi); //归并结果
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // a[lo .. mid] 与 a[mid+1 .. hi] 为已有序子数组

        // 拷贝至数组 aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // 合并到 a[]
        int i = 0, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{1,2,3,4,5,6};
        Integer[] b = new Integer[]{};
        sort(a, b, 0, 5);
    }
}
