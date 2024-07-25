package com.justin4u.leetcode;

import java.util.Arrays;

/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * If the target is not found in the array, return [-1, -1].
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 * <p>
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 */
public class SearchRange {

    public static void main(String[] args) {
        SearchRange instance = new SearchRange();
        int[] input = new int[]{5, 7, 7, 8, 8, 10};
        int[] output = instance.solution(input, 8);
        int[] output2 = instance.solution(input, 6);
        System.out.println(Arrays.toString(output));
        System.out.println(Arrays.toString(output2));
    }

    public int[] solution(int[] nums, int target) {
        int[] result = new int[]{-1, -1};
        int left = 0;
        int right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        int targetLeft = left;

        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int targetRight = right;

        if (target != nums[targetLeft] || target != nums[targetLeft]) {
            return result;
        }
        return new int[]{targetLeft, targetRight};
    }
}
