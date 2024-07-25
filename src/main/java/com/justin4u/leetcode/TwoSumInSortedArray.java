package com.justin4u.leetcode;

import java.util.Arrays;

/**
 * Given an array of integers that is already sorted in ascending order,
 * find two numbers such that they add up to a specific target number.
 * <p>
 * The function twoSum should return indices of the two numbers such that they add up to the target,
 * where index1 must be less than index2. Please note that your returned answers (both index1 and index2)
 * are not zero-based.
 * <p>
 * You may assume that each input would have exactly one solution.
 * <p>
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 */
public class TwoSumInSortedArray {
    public static void main(String[] args) {
        int[] numbers = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] result = solution(numbers, target);
        System.out.println(Arrays.toString(result));
    }


    /**
     * time: O(n), space: O(1)
     * 解题思路：使用2个指针，一个从数组头位开始（即整个数组中最小者），一个从数组尾位开始（即最大者），二者的方向相反。
     * 1）如果二者之和即为目标值，将对应的两个索引加一后，得出结果
     * 2）如果二者之和大于目标值，将大者的索引减一，继续遍历
     * 3）如果二者之和小于目标值，将小者的索引加一，继续遍历
     * @param numbers
     * @param target
     * @return
     */
    public static final int[] solution(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left+1,right+1};
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }
        return new int[2];
    }
}
