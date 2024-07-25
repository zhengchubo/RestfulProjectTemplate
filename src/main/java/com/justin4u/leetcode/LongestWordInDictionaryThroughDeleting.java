package com.justin4u.leetcode;

import java.util.Arrays;
import java.util.List;

/**
 * com.justin4u.leetcode
 *  Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than
 *  one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.
 *
 * Example 1:
 *
 * Input:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 *
 * Output:
 * "apple"
 *
 * Example 2:
 *
 * Input:
 * s = "abpcplea", d = ["a","b","c"]
 *
 * Output:
 * "a"
 *
 * Note:
 *
 *     All the strings in the input will only contain lower-case letters.
 *     The size of the dictionary won't exceed 1,000.
 *     The length of all the strings in the input won't exceed 1,000.
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2020-12-01</pre>
 */
public class LongestWordInDictionaryThroughDeleting {

    public static void main(String[] args) {
        List<String> dictionary1 = Arrays.asList("ale","apple","monkey","plea");
        String result1 = LongestWordInDictionaryThroughDeleting.solution("abpcplea", dictionary1);
        System.out.println("apple".equals(result1));
    }

    /**
     *
     * @param s
     * @param dictionary
     * @return
     */
    private static final String solution(String s, List<String> dictionary) {
        String result = "";

        for (String t : dictionary) {
            int rLength = result.length(), tLength = t.length();
            if (rLength > tLength || (rLength == tLength && result.compareTo(t) < 0)) {
                continue;
            }
            if (isSubString(s, t)) {
                result = t;
            }
        }

        return result;
    }

    /**
     * 通过删除字符串 s 中的一个字符能得到字符串 t，可以认为 t 是 s 的子序列，我们可以使用双指针来判断一个字符串 t 是否为另一个字符串 s 的子序列。
     * @param s
     * @param t
     * @return
     */
    private static final boolean isSubString(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == t.length();
    }
}
