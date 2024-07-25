package com.justin4u.leetcode;

/**
 * Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.
 * Example 1:
 * <p>
 * Input: 121
 * Output: true
 * Example 2:
 * <p>
 * Input: -121
 * Output: false
 * Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
 * Example 3:
 * <p>
 * Input: 10
 * Output: false
 * Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
 */
public class PalindromeInteger {

    public static void main(String[] args) {
        PalindromeInteger clazz = new PalindromeInteger();
        System.out.println(clazz.solution(121));
        System.out.println(clazz.solution(-121));
        System.out.println(clazz.solution(10));
    }

    /**
     * 先求出实际的互文值，再判断其是否与原值相等
     * @param n
     * @return
     */
    public boolean solution2(Integer n) {
        if (n == null || n < 0) {
            return false;
        }
        int copyN = n;
        int reverse = 0;
        while (copyN > 0) {
            reverse = reverse * 10 + copyN % 10;
            copyN /= 10;
        }
        return reverse == n;
    }

    /**
     * 直接判断传参是否为互文形式，方法为：
     * 1、先计算出传参的基（比如3位数，则基为100），将原值除以基得首位数字
     * 2、再对原值对10取模，得到最后一位数字
     * 3、比较首位数字与最后一位数字是否相等，否为直接返回fase，否则继续
     * 4、通过移除首位及末位数字（先对基取模后再除以10），得到一个新值后，并将基值除以100（因为去掉了首末2位数字），继续循环
     * @param num
     * @return
     */
    public boolean solution(Integer num) {
        System.out.println("==================");
        if (num == null || num < 0) {
            return false;
        }

        int base = 1;
        while (num / base >= 10) {
            base *= 10;
        }
        System.out.println("base: " + base);

        while (num != 0) {
            int left = num / base;
            System.out.println("left: " + left);
            int right = num % 10;
            System.out.println("right: " + right);
            if (left != right) {
                return false;
            }
            num = (num % base) / 10;
            System.out.println("num: " + num);
            base /= 100;
            System.out.println("base2: " + base);
        }
        return true;
    }
}
