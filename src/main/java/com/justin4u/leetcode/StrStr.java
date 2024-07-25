package com.justin4u.leetcode;

/**
 * com.justin4u.leetcode
 * <p>
 * Description
 * Implement strStr().
 * <p>
 * Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 * <p>
 * Example 1:
 * <p>
 * Input: haystack = "hello", needle = "ll"
 * Output: 2
 * Example 2:
 * <p>
 * Input: haystack = "aaaaa", needle = "bba"
 * Output: -1
 * Clarification:
 * <p>
 * What should we return when needle is an empty string? This is a great question to ask during an interview.
 * <p>
 * For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr
 * () and Java's indexOf().
 * <p>
 * Tags:** Two Pointers, String
 *
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2019-05-13</pre>
 */
public class StrStr {

    public int solution(String haystack, String needle) {
        int result = -1;

        if (null == haystack || 0 == haystack.length() ||
                null == needle || 0 == haystack.length() ||
                needle.length() > haystack.length()) {
            return result;
        }
        int needleLength = needle.length();
        for (int i = 0; i < haystack.length() - needleLength; i++) {
            for (int j = 0; j < needleLength; j++) {
                if (j == needleLength - 1) {
                    return i;
                }
                if (haystack.charAt(i) != needle.charAt(j)) {
                    break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        StrStr instance = new StrStr();
        int result = instance.solution("hello", "ll");
        int result2 = instance.solution("aaaaa", "bba");
        System.out.println(result);
        System.out.println(result2);
    }
}
