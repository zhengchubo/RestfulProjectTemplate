package com.justin4u.leetcode;

import org.springframework.util.Assert;

/**
 * com.justin4u.leetcode
 *  Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 *
 * Example 1:
 *
 * Input: "aba"
 * Output: True
 *
 * Example 2:
 *
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 *
 * Note:
 *
 *     The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2020-11-05</pre>
 */
public class ValidPalindrome {

    public static void main(String[] args) {
        Assert.isTrue(solution("aba"), "Error aba");
        Assert.isTrue(solution("abca"), "Error abca");
        Assert.isTrue(solution("abab"), "Error abab");
        Assert.isTrue(!solution("abddca"), "Error abddca");
    }

    /**
     * 本题的关键是处理删除一个字符。在使用双指针遍历字符串时，如果出现两个指针指向的字符不相等的情况，我们就试着删除一个字符，再判断删除完之后的字符串是否是回文字符串。
     *
     * 在判断是否为回文字符串时，我们不需要判断整个字符串，因为左指针左边和右指针右边的字符之前已经判断过具有对称性质，所以只需要判断中间的子字符串即可。
     *
     * 在试着删除字符时，我们既可以删除左指针指向的字符，也可以删除右指针指向的字符。
     * @param s
     * @return
     */
    public static final boolean solution(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindrome(s, left+1, right) || isPalindrome(s, left, right-1);
            }
            left++;
            right--;
        }
        return true;
    }

    private static final boolean isPalindrome(String s, int left, int right) {
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
