package com.justin4u.leetcode;

/**
 * com.justin4u.leetcode
 * <p>
 * Description
 * Implement pow(x, n), which calculates x raised to the power n (xn).
 * <p>
 * Example 1:
 * <p>
 * Input: 2.00000, 10
 * Output: 1024.00000
 * Example 2:
 * <p>
 * Input: 2.10000, 3
 * Output: 9.26100
 * Example 3:
 * <p>
 * Input: 2.00000, -2
 * Output: 0.25000
 * Explanation: 2^-2 = 1/2^2 = 1/4 = 0.25
 * Note:
 * <p>
 * -100.0 < x < 100.0
 * n is a 32-bit signed integer, within the range [−231, 231 − 1]
 * Tags: Math, Binary Search
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-05-20</pre>
 */
public class Pow {

    public static void main(String[] args) {
        Pow instance = new Pow();
        System.out.println(instance.solution(2, 10));
        System.out.println(instance.solution(2.1, 3));
        System.out.println(instance.solution(2, -2));
    }
    private double solution(double x, int n) {
        if (n < 0) {
            return doPow(1 / x, -n);
        }
        return doPow(x, n);
    }

    private double doPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }

        double v = doPow(x, n >>> 1);
        if (n % 2 == 0) {
            return v * v;
        } else {
            return v * v * x;
        }
    }
}
