package com.justin4u.leetcode;

/**
 * com.justin4u.leetcode
 * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 * <p>
 * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
 * <p>
 * Example 1:
 * <p>
 * Input: 4
 * Output: 2
 * <p>
 * Example 2:
 * <p>
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since
 * the decimal part is truncated, 2 is returned.
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-08-02</pre>
 */
public class SquareRoot {
    public int solution(int i) {
        long n = i;
        while (n * n > i) {
            n = (n + i / n) >> 1;
        }
        return (int) n;
    }

    public static void main(String[] args) {
        SquareRoot instance = new SquareRoot();
        int input = 10;
        int result = instance.solution(input);
        System.out.println(result);
    }
}
