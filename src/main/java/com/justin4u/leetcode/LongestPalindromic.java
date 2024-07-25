package com.justin4u.leetcode;

/**
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000,
 * and there exists one unique longest palindromic substring.
 */
public class LongestPalindromic {
    public static void main(String[] args) {
        LongestPalindromic palindromic = new LongestPalindromic();
        String str = "abacdfgdcaba";
        String result = palindromic.solution(str);
        System.out.println(result);
    }

    private String findPalindromic(String str, int left, int right) {
        int len = str.length();
        while (left >= 0 && right <= len - 1 && str.charAt(left) == str.charAt(right)) {
            left--;
            right++;
        }
        return str.substring(left + 1, right);
    }

    private String solution(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            // odd case
            String substr = this.findPalindromic(str, i, i);
            if (substr.length() > result.length()) {
                result = substr;
            }
            // even case
            substr = this.findPalindromic(str, i, i + 1);
            if (substr.length() > result.length()) {
                result = substr;
            }
        }
        return result;
    }
}
