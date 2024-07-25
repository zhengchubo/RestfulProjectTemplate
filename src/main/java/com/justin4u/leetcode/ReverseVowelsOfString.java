package com.justin4u.leetcode;

import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * com.justin4u.leetcode
 * Write a function that takes a string as input and reverse only the vowels of a string.
 * Example 1:
 *
 * Input: "hello"
 * Output: "holle"
 *
 * Example 2:
 *
 * Input: "leetcode"
 * Output: "leotcede"
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2020-11-04</pre>
 */
public class ReverseVowelsOfString {

    private static final Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

    public static void main(String[] args) {
        Assert.isTrue(Objects.equals("holle", solution("hello")), "Error");
        Assert.isTrue(Objects.equals("leotcede", solution("leetcode")), "Error");
    }

    /**
     * 使用双指针，一个指针从头向尾遍历，一个指针从尾到头遍历，当两个指针都遍历到元音字符时，交换这两个元音字符。
     *
     * 为了快速判断一个字符是不是元音字符，我们将全部元音字符添加到集合 HashSet 中，从而以 O(1) 的时间复杂度进行该操作。
     *
     *     时间复杂度为 O(N)：只需要遍历所有元素一次
     *     空间复杂度 O(1)：只需要使用两个额外变量
     * @param str
     * @return
     */
    public static final String solution(String str) {
        if (Objects.isNull(str) || str.length() == 0) {
            return str;
        }
        char[] result = new char[str.length()];
        int left = 0;
        int right = str.length() - 1;
        while (left <= right) {
            char leftC = str.charAt(left);
            char rightC = str.charAt(right);
            if (!vowels.contains(leftC)) {
                result[left] = leftC;
                left++;
            } else if (!vowels.contains(rightC)) {
                result[right] = rightC;
                right--;
            } else {
                result[left] = rightC;
                result[right] = leftC;
                left++;
                right--;
            }
        }
        return new String(result);
    }
}
