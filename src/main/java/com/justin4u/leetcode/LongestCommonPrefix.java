package com.justin4u.leetcode;

import java.util.Arrays;
import java.util.Objects;

/**
 * find the longest common prefix string amongst an array of strings.
 * <p>
 * If there is no common prefix, return an empty string "".
 * <p>
 * Example 1:
 * <p>
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 * <p>
 * Input: ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * Note:
 * <p>
 * All given inputs are in lowercase letters a-z.
 */
public class LongestCommonPrefix {

    public static void main(String[] args) {
        LongestCommonPrefix instance = new LongestCommonPrefix();
        System.out.println(instance.solution2(new String[]{"flower", "flow", "flight"}));
        System.out.println(instance.solution2(new String[]{"dog", "racecar", "car"}));
        System.out.println(instance.solution2(new String[]{"abc", "a", "abcd"}));
        System.out.println(instance.solution2(new String[]{"abcsdfg", "abc", "abcdasdf"}));
    }

    public String solution(String[] strs) {
        if (strs == null || strs.length <= 1) {
            return "";
        }
        String result = "";
        for (int i = 0; i < strs[0].length(); i++) {
            boolean flag = true;
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length()) {
                    flag = false;
                    break;
                }
                if (strs[j].charAt(i) != strs[j - 1].charAt(i)) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                break;
            } else {
                result += strs[0].charAt(i);
            }
        }
        return result;
    }

    /**
     * 1. 校验输入
     * 2. 求得数组中最小长度的元素的长度值
     * 3. 两层循环，外层为最小长度值，确保在遍历所有字符串是不越界；内层循环为对数组遍历所有字符串，将第一个元素作为基准，从第二个元素开始，当任意一个元素的某个字符与第一个元素同位字符不同时，返回第一个元素在当前位置前的子串。
     * 4. 循环外，返回第一个元素的子串，长度等于数组中最小长度的元素长度值，即最小长度的元素本身。
     * @param inputs
     * @return
     */
    public String solution2(String[] inputs) {
        if (Objects.isNull(inputs) || inputs.length <= 1) {
            return "";
        }
        int minLength = Integer.MAX_VALUE;
        for (String item : inputs) {
            minLength = Math.min(minLength, item.length());
        }
        String firstItem = inputs[0];
        int lenth = inputs.length;
        for (int i = 0; i < minLength; i++) {
            for (int j = 1; j < lenth; j++) {
                String currentItem = inputs[j];
                if (firstItem.charAt(i) != currentItem.charAt(i)) {
                    return currentItem.substring(0, i);
                }
            }
        }
        return firstItem.substring(0, minLength);
    }
}
