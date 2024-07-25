package com.justin4u.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc",
 * which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
 */
public class LongestSubstring {
    public static void main(String[] args) {
        LongestSubstring longestSubstring = new LongestSubstring();
        String str = "abcabcbb";
        System.out.println(longestSubstring.solution(str));
    }

    private int solution(String str) {
        int lastIndex = 0;
        int maxLen = 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            if (set.contains(str.charAt(i))) {
                int newLen = i - lastIndex;
                maxLen = Math.max(newLen, maxLen);
                lastIndex++;
            } else {
                maxLen++;
            }
            set.add(str.charAt(i));
        }
        return maxLen;
    }
}
