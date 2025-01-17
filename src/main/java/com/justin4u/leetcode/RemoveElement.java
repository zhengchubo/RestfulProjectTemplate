package com.justin4u.leetcode;

/**
 * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1)
 * extra memory.
 * <p>
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 * <p>
 * Example 1:
 * <p>
 * Given nums = [3,2,2,3], val = 3,
 * <p>
 * Your function should return length = 2, with the first two elements of nums being 2.
 * <p>
 * It doesn't matter what you leave beyond the returned length.
 * Example 2:
 * <p>
 * Given nums = [0,1,2,2,3,0,4,2], val = 2,
 * <p>
 * Your function should return length = 5, with the first five elements of nums containing 0, 1, 3, 0, and 4.
 * <p>
 * Note that the order of those five elements can be arbitrary.
 * <p>
 * It doesn't matter what values are set beyond the returned length.
 * Clarification:
 * <p>
 * Confused why the returned value is an integer but your answer is an array?
 * <p>
 * Note that the input array is passed in by reference, which means modification to the input array will be known to
 * the caller as well.
 * <p>
 * Internally you can think of this:
 * <p>
 * // nums is passed in by reference. (i.e., without making a copy)
 * int len = removeElement(nums, val);
 * <p>
 * // any modification to nums in your function would be known by the caller.
 * // using the length returned by your function, it prints the first len elements.
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 */
public class RemoveElement {

    public static void main(String[] args) {
        RemoveElement instance = new RemoveElement();
        int[] arr1 = new int[]{3, 2, 2, 3};
        int[] arr2 = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        int j = instance.solution(arr1, 3);
        int k = instance.solution(arr2, 2);
        instance.print(arr1, j);
        instance.print(arr2, k);
    }

    public int solution(int[] arr, int v) {
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != v) {
                arr[j] = arr[i];
                j++;
            }
        }
        return j;
    }

    private void print(int[] arr, int length) {
        System.out.println("The length is " + length);
        for (int i = 0; i < length; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

}
