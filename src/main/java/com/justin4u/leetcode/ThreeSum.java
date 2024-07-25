package com.justin4u.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique
 * triplets in the array which gives the sum of zero.
 * <p>
 * Note:
 * <p>
 * The solution set must not contain duplicate triplets.
 * <p>
 * Example:
 * <p>
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * <p>
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 */
public class ThreeSum {

    public static void main(String[] args) {
        ThreeSum instance = new ThreeSum();
        Set<int[]> ret = instance.solution(new int[]{-1, 0, 1, 2, -1, -4,0,0});
        for (int[] item : ret) {
            System.out.println(Arrays.toString(item));
        }
    }

    public Set<int[]> solution(int[] nums) {
        if (nums.length < 3) {
            return null;
        }

        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        Set<int[]> set = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        set.add(new int[]{nums[i], nums[j], nums[k]});
                    }
                }
            }
        }
        return set;
    }

    public Set<int[]> solution2(int[] nums) {
        Arrays.sort(nums);
        Set<int[]> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int total = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int leftValue = nums[left];
                int rightValue = nums[right];
                if (leftValue + rightValue == total) {
                    set.add(new int[]{total, leftValue, rightValue});
                    left++;
                    right--;
                } else if (leftValue + rightValue < total) {
                    while (left < right && leftValue + rightValue < total) {
                        left++;
                    }
                } else {
                    while (left < right && leftValue + rightValue > total) {
                        right--;
                    }
                }
            }
        }
        return set;
    }

}
