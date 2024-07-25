package com.justin4u.leetcode;

/**
 * com.justin4u.leetcode
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 * <p>
 * If the last word does not exist, return 0.
 * <p>
 * Note: A word is defined as a character sequence consists of non-space characters only.
 * <p>
 * Example:
 * <p>
 * Input: "Hello World"
 * Output: 5
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-07-11</pre>
 */
public class LastWordLength {

    public static void main(String[] args) {
        String str = " Hello World ";
        int result = solution2(str);
        System.out.println(result);
    }

    public static final int solution(String str) {
        if (null == str) {
            return 0;
        }
        int len = str.length() - 1;
        int end = len;
        char[] chars = str.toCharArray();
        for (int i = len; i >= 0; i--) {
            char c = chars[i];
            if (c != ' ') {
                end = i;
                break;
            }
        }
        for (int i = end; i >= 0; i--) {
            char c = chars[i];
            if (c == ' ') {
                return end - i;
            }
        }
        return 0;
    }

    public static int solution2(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        int length = s.length() - 1;
        int p = length;
        int lastWordEdge = length;
        char[] chars = s.toCharArray();
        while (p > 0) {
            char c = chars[p];
            if (c != ' ') {
                lastWordEdge = p;
                break;
            }
            p--;
        }
        while (p > 0) {
            char c = chars[p];
            if (c == ' ') {
                return lastWordEdge - p;
            }
            p--;
        }
        return p;
    }

}
