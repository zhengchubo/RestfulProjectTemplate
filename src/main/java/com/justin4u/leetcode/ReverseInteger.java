package com.justin4u.leetcode;

/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 * Example 1:
 *
 * Input: 123
 * Output: 321
 * Example 2:
 *
 * Input: -123
 * Output: -321
 * Example 3:
 *
 * Input: 120
 * Output: 21
 *
 * Assume we are dealing with an environment which could only store integers within the 32-bit
 * signed integer range: [−2^31,  2^31 − 1]. For the purpose of this problem, assume that your
 * function returns 0 when the reversed integer overflows.
 */
public class ReverseInteger {
    public static void main(String[] args) {
        ReverseInteger reverseInteger = new ReverseInteger();
        int n1= 123;
        int n2 = -123;
        int n3 = 120;
        int ret1 = reverseInteger.reverse(n1);
        int ret2 = reverseInteger.reverse(n2);
        int ret3 = reverseInteger.reverse(n3);
        System.out.println(n1 + " => " + ret1);
        System.out.println(n2 + " => " + ret2);
        System.out.println(n3 + " => " + ret3);

        System.out.println(reverseInteger.reverse(-100) + " <= " + "-100");
        System.out.println(reverseInteger.reverse(1002) + " <= " + "1002");
        System.out.println(reverseInteger.reverse(123) + " <= " + "123");


        System.out.println(reverseInteger.reverse(1463847412) + " <= " + "1463847412");
        System.out.println(reverseInteger.reverse(2147447412) + " <= " + "2147447412");

        System.out.println(reverseInteger.reverse(1000000003) + " <= " + "1000000003");
        System.out.println(reverseInteger.reverse(2147483647) + " <= " + "2147483647");
        System.out.println(reverseInteger.reverse(-2147483648) + " <= " + "-2147483648");

        System.out.println(reverseInteger.reverse(0) + " <= 0");
    }

    /**
     * 通过对 10 进行取模，就可以得到最近一位的数字，把它作为结果值的第一位。
     * 然后呢？除以 10，得到去除最后一位数字的值，再循环使用前面的步骤。
     * 在每次的循环中，都要把前一次的结果乘以 10 后，再加上本次的数字，从而可以拼接出最终结果。
     * 要注意的是：1.当操作的对象可以被 10 整除时，循环结束。2.当结果值会溢出时，直接返回 0
     * @param x
     * @return
     */
    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            if (result > Integer.MAX_VALUE / 10 || result < Integer.MIN_VALUE / 10) {
                return 0;
            }
            int tmp = x % 10;
            result = result * 10 + tmp;
            x = x / 10;
        }
        return result;
    }
}
