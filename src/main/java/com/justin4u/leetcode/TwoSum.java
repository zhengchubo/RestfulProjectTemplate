package com.justin4u.leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
 * <p>
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2.
 * <p>
 * Note:
 * <p>
 * Your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution and you may not use the same element twice.
 * Example:
 * <p>
 * Input: numbers = [2, 7, 11, 15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 */
public class TwoSum {

    private static final Logger logger = LoggerFactory.getLogger(TwoSum.class);

    public static void main(String[] args) {
        int[] numbers = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] result = solution(numbers, target);
        int[] result2 = solution2(numbers, target);
        System.out.println(Arrays.toString(result));
        System.out.println(Arrays.toString(result2));
        logger.info("asdf{}", "ab");
    }

    /**
     * time: O(n^2), space: O(1)
     *
     * @param input
     * @param target
     * @return
     */
    public static int[] solution(int[] input, int target) {
        if (input == null) {
            return new int[2];
        }
        for (int i = 0; i < input.length; i++) {
            for (int j = i+1; j < input.length; j++) {
                if (input[i] + input[j] == target) {
                    return new int[]{i+1, j+1};
                }
            }
        }
        return new int[2];
    }


    /**
     * time: O(n), space: O(1)
     *
     * @param numbers
     * @param target
     * @return
     */
    public static int[] solution2(int[] numbers, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (map.containsKey(numbers[i])) {
                // when we found the second value in iteration, then the answer is come out.
                // the first index is from the value, and the second index is from current iteration index.
                result[0] = map.get(numbers[i]) + 1;
                result[1] = i + 1;
            } else {
                // the key is the value of second number, the value is the index of first number
                map.put(target - numbers[i], i);
            }
        }
        return result;
    }
}
