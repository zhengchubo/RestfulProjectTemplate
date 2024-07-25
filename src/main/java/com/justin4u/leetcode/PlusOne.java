package com.justin4u.leetcode;

import java.util.Arrays;

/**
 * com.justin4u.leetcode
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 * <p>
 * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
 * <p>
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 * <p>
 * Example 2:
 * <p>
 * Input: [4,3,2,1]
 * Output: [4,3,2,2]
 * Explanation: The array represents the integer 4321.
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-07-19</pre>
 */
public class PlusOne {

    public static void main(String[] args) {
        PlusOne instance = new PlusOne();
//        int[] input = new int[]{1, 2, 3};
//        int[] input = new int[]{4,3,2,1};
        int[] input = new int[]{1, 9, 9, 9};
        int[] output = instance.solution(input);
        System.out.println(Arrays.toString(output));
        System.out.println(Arrays.toString(new int[2]));
    }

    public int[] solution(int[] input) {
        int len = input.length;
        int i = len - 1;
        if (input[i] < 9) {
            input[i]++;
        } else {
            while (i >= 0 && input[i] == 9) {
                input[i] = 0;
                i--;
            }
            if (input[0] == 0) {
                input = new int[len + 1];
                input[0] = 1;
            } else {
                input[i]++;
            }
        }
        return input;
    }


}
