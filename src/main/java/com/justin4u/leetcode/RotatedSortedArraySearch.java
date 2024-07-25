package com.justin4u.leetcode;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * <p>
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * <p>
 * You may assume no duplicate exists in the array.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 * Output: 4
 * Example 2:
 * <p>
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 */
public class RotatedSortedArraySearch {

    public static void main(String[] args) {
        RotatedSortedArraySearch instance = new RotatedSortedArraySearch();
        System.out.println(instance.solution(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(instance.solution(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
    }

    public int solution(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (target == arr[mid]) {
                return mid;
            }
            /**
             * ├── 左值 小于等于 中值（左半是递增的）
             * │   ├── 寻找值 位于 左半部分中（从左半部分寻找）
             * │   └── 否则（从右半部分寻找）
             * ├── 左值 大于 中值（右半是递增的）
             * │   ├── 寻找值 位于 右半部分中（从右半部分寻找）
             * │   └── 否则（从左半部分寻找）
             */
            if (arr[left] <= arr[mid]) {
                if (arr[left] <= target && target < arr[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (arr[mid] < target && target < arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
