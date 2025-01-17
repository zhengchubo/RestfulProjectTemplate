package com.justin4u.leetcode;

/**
 * com.justin4u.leetcode
 * <p>
 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
 * <p>
 * Return the quotient after dividing dividend by divisor.
 * <p>
 * The integer division should truncate toward zero.
 * <p>
 * Example 1:
 * <p>
 * Input: dividend = 10, divisor = 3
 * Output: 3
 * Example 2:
 * <p>
 * Input: dividend = 7, divisor = -3
 * Output: -2
 * Note:
 * <p>
 * Both dividend and divisor will be 32-bit signed integers.
 * The divisor will never be 0.
 * Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range:
 * [−231, 231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division
 * result overflows.
 * Tags: Math, Binary Search
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-05-15</pre>
 */
public class DivideTwoInteger {

    /**
     * 题意是让你算两个整型数相除后的结果，如果结果溢出就返回 MAX_INT，但不能使用乘、除、余的操作符，如果是用加减操作符的话肯定会超时哈，这样的话我们就只能想到位操作符了。
     *
     * 首先，我们分析下溢出的情况，也就是当被除数为 Integer.MIN_VALUE，除数为 -1 时会溢出，因为 |Integer.MIN_VALUE| = Integer.MAX_VALUE + 1。
     *
     * 然后，我们把除数和被除数都转为 long 类型的正数去做下一步操作，我这里以 22 和 3 相除为例子，因为 22 >= 3，我们对 3 进行左移一位，也就是乘 2，结果为 6，比 22 小，我们继续对 6
     * 左移一位结果为 12，还是比 22 小，我们继续对 12 左移一位为 24，比 22 大，这时我们可以分析出，22 肯定比 3 的 4 倍要大，4 是怎么来的？因为我们左移了两次，也就是 1 << 2 =
     * 4，此时我们记下这个 4，然后让 22 - 3 * 4 = 10，因为 10 >= 3，对 10 和 3 进行同样的操作，我们可以得到 2，此时加上上次的 4，和为 6，也就是 22 比 3 的 6 倍要大，此时还剩余
     * 10 - 6 = 4，因为 4 >= 3，所以对 4 和 3 进行同样的操作，我们发现并不能对 3 进行左移了，因为 4 >= 3，所以 1 倍还是有的，所以加上最后的 1，结果为 6 + 1 = 7，也就是 22 整除
     * 3 结果为 7。
     *
     * 最终，我们对结果赋予符号位即可，根据以上思路来书写如下代码应该不是难事了吧。
     * @param dividend
     * @param divisor
     * @return
     */
    private int solution(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        long dvd = Math.abs(dividend);
        long dvr = Math.abs(divisor);
        int ret = 0;
        while (dvd >= dvr) {
            long tmp = dvr, multiple = 1;
            while (dvd >= tmp << 1) {
                tmp <<= 1;
                multiple <<= 1;
            }
            dvd -= tmp;
            ret += multiple;
        }
        return (dividend < 0) ^ (divisor < 0) ? -ret : ret;
    }

    public static void main(String[] args) {
        DivideTwoInteger instance = new DivideTwoInteger();
        int result1 = instance.solution(10, 3);
        int result2 = instance.solution(7, -3);
        System.out.println("10/3=" + result1);
        System.out.println("7/-3=" + result2);
    }
}
