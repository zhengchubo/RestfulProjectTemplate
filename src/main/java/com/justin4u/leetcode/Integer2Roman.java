package com.justin4u.leetcode;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
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
 *
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not
 * IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX. There are six instances where subtraction
 * is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
 *
 * Example 1:
 *
 * Input: 3
 * Output: "III"
 * Example 2:
 *
 * Input: 4
 * Output: "IV"
 * Example 3:
 *
 * Input: 9
 * Output: "IX"
 * Example 4:
 *
 * Input: 58
 * Output: "LVIII"
 * Explanation: C = 100, L = 50, XXX = 30 and III = 3.
 * Example 5:
 *
 * Input: 1994
 * Output: "MCMXCIV"
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */
public class Integer2Roman {

    private static final int[] ints = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    private static final String[] romans = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V",
            "IV", "I"};

    public static void main(String[] args) {
        Integer2Roman converter = new Integer2Roman();
/*
        System.out.println(3 + " => " + converter.solution(3));
        System.out.println(4 + " => " + converter.solution(4));
        System.out.println(9 + " => " + converter.solution(9));
        System.out.println(58 + " => " + converter.solution(58));
        System.out.println(1994 + " => " + converter.solution(1994));
*/
        System.out.println(converter.solution2(900));
    }

    public String solution(int n) {
        if (n < 1 || n > 3999) {
            return null;
        }
        String result = "";
        for (int i = 0; i < ints.length; i++) {
            int item = ints[i];
            while (n >= item) {
                n = n - item;
                result += romans[i];
            }
        }
        return result;
    }

    public String solution2(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }
}
