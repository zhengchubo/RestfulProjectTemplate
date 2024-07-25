package com.justin4u.leetcode;

/**
 *
 */
public class RegularExprMatcher {
    public static void main(String[] args) {
        RegularExprMatcher matcher = new RegularExprMatcher();
        Boolean result = matcher.isMatch("aa", "a");
        Boolean result2 = matcher.isMatch("aa", "a*");
        Boolean result3 = matcher.isMatch("ab", ".*");
        Boolean result4 = matcher.isMatch("aab", "c*a*b");
        Boolean result5 = matcher.isMatch("mississippi", "mis*is*p*.");
        System.out.println(result);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
        System.out.println(result5);
    }

    public boolean isMatch(String str, String p) {
        if (str == null || p == null) {
            return false;
        }

        if (p.length() == 0) {
            return str.length() == 0;
        }

        if (p.length() == 1) {
            if (str.length() == 1 && (p.charAt(0) == str.charAt(0) || p.charAt(0) == '.')) {
                return true;
            }
            return false;
        }

        if (p.charAt(1) != '*') {
            if (str.length() == 0) {
                return false;
            }
            if (firstCharMatched(str, p)) {
                return isMatch(str.substring(1), p.substring(1));
            } else {
                return false;
            }
        }

        while (str.length() != 0 && firstCharMatched(str, p)) {
            if (isMatch(str, p.substring(2))) {
                return true;
            }
            str = str.substring(1);
        }

        return isMatch(str, p.substring(2));
    }

    private boolean firstCharMatched(String str, String p) {
        return p.charAt(0) == '.' || p.charAt(0) == str.charAt(0);
    }
}
