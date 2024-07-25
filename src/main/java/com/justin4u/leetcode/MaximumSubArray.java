package com.justin4u.leetcode;

/**
 * com.justin4u.leetcode
 * <p>
 * Description
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest
 * sum and return its sum.
 * <p>
 * Example:
 * <p>
 * Input: [-2,1,-3,4,-1,2,1,-5,4],
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6.
 * Follow up:
 * <p>
 * If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach,
 * which is more subtle.
 * <p>
 * Tags: Array, Divide and Conquer, Dynamic Programming
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-05-22</pre>
 */
public class MaximumSubArray {

    public int solution(int[] nums) {
        int length = nums.length;
        int tmp = nums[0];
        int result = tmp;
        for (int i = 1; i < length; i++) {
            if (tmp < 0) {
                tmp = 0;
            }
            tmp += nums[i];
            if (tmp > result) {
                result = tmp;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MaximumSubArray instance = new MaximumSubArray();
        int result = instance.solution(new int[] {-2,1,-3,4,-1,2,1,-5,4});
        System.out.println(result);
    }

}
