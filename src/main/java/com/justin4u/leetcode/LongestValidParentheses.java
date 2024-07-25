package com.justin4u.leetcode;

import java.util.Stack;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 *
 * Example 1:
 *
 * Input: "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()"
 * Example 2:
 *
 * Input: ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()"
 */
public class LongestValidParentheses {

    public static void main(String[] args) {
        LongestValidParentheses instance = new LongestValidParentheses();
        System.out.println(instance.solution("(()"));
        System.out.println(instance.solution(")()())"));
    }

    public int solution(String input) {
        Stack<Character> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < input.length(); i++) {
            Character c = input.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else if (c == ')' && !stack.empty()) {
                Character c2 = stack.pop();
                if (c2 == '(') {
                    j += 2;
                }
            }
        }
        return j;
    }
}
