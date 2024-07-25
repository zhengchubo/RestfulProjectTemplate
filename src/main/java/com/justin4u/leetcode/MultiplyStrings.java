package com.justin4u.leetcode;

import java.util.Objects;

/**
 * com.justin4u.leetcode
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-10-15</pre>
 * <p>
 * <p>
 * <p>
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
 * <p>
 * Example 1:
 * <p>
 * Input: num1 = "2", num2 = "3"
 * Output: "6"
 * <p>
 * Example 2:
 * <p>
 * Input: num1 = "123", num2 = "456"
 * Output: "56088"
 * <p>
 * Note:
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contain only digits 0-9.
 * Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class MultiplyStrings {

    public static void main(String[] args) {
        String result = solution("2", "3");
        System.out.println(Objects.equals(result, "6"));
        System.out.println(Objects.equals(solution("123", "456"), "56088"));
    }

    public static String solution(String n1, String n2) {
        if (Objects.equals(n1, "0") || Objects.equals(n2, "0")) {
            return "0";
        }
        int length1 = n1.length();
        int length2 = n2.length();
        int length = length1 + length2;
        char[] cSum = new char[length];
        char[] c1 = n1.toCharArray();
        char[] c2 = n2.toCharArray();
        for (int i = length1 - 1; i >= 0; i--) {
            int c = c1[i] - '0';
            for (int j = length2 - 1; j >= 0; j--) {
                cSum[i + j + 1] += (c * (c2[j] - '0'));
            }
        }
        for (int i = length - 1; i >= 0; i--) {
            if (cSum[i]>9) {
                // 进位
                cSum[i-1] += cSum[i] / 10;
                cSum[i] %= 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        // 遍历char数组，直到找到非零数字
        for (; ; ++i) {
            if (cSum[i] != 0) {
                break;
            }
        }
        // 从第一个非零数字开始，拼接后面所有数字，通过 + '0' 方式转为数字后再转为char
        for (; i < cSum.length; ++i) {
            sb.append((char) (cSum[i] + '0'));
        }
        return sb.toString();
    }
}
