package com.justin4u.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * <p>
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII,
 * which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
 * <p>
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not
 * IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX. There are six instances where subtraction
 * is used:
 * <p>
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.
 * <p>
 * Example 1:
 * <p>
 * Input: "III"
 * Output: 3
 * Example 2:
 * <p>
 * Input: "IV"
 * Output: 4
 * Example 3:
 * <p>
 * Input: "IX"
 * Output: 9
 * Example 4:
 * <p>
 * Input: "LVIII"
 * Output: 58
 * Explanation: C = 100, L = 50, XXX = 30 and III = 3.
 * Example 5:
 * <p>
 * Input: "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */
public class Roman2Integer {

    private static final Map<Character, Integer> map = new HashMap<Character, Integer>() {
        {
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }
    };

    public static void main(String[] args) {
        Roman2Integer converter = new Roman2Integer();
        System.out.println(converter.solution("III"));
        System.out.println(converter.solution("IV"));
        System.out.println(converter.solution("IX"));
        System.out.println(converter.solution("LVIII"));
        System.out.println(converter.solution("MCMXCIV"));
    }

    /**
     * 结果值初始为第一位值，从右往左迭代，当左侧值小于右侧值时，结果值里减去当前值；否则，直接往结果值里累加上当前值。
     * @param s
     * @return
     */
    public int solution(String s) {
        int result = map.get(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int current = map.get(s.charAt(i));
            int pre = map.get(s.charAt(i - 1));
            if (current <= pre) {
                result += current;
            } else {
                result = result - pre + (current - pre);
            }
        }
        return result;
    }

    /**
     * 结果值初始为最后一位值，从右往左迭代，当左侧值小于右侧值时，结果值里减去当前值；否则，直接往结果值里累加上当前值。
     * @param s
     * @return
     */
    public int solution2(String s) {
        int l = s.length();
        int i = l - 2;
        int sum = map.get(s.charAt(l - 1));
        while (i >= 0) {
            int current = map.get(s.charAt(i));
            int right = map.get(s.charAt(i + 1));
            if (current < right) {
                sum -= current;
            } else {
                sum += current;
            }
            i--;
        }
        return sum;
    }

}
