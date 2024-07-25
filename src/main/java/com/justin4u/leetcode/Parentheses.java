package com.justin4u.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is
 * valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 * <p>
 * Example 1:
 * <p>
 * Input: "()"
 * Output: true
 * Example 2:
 * <p>
 * Input: "()[]{}"
 * Output: true
 * Example 3:
 * <p>
 * Input: "(]"
 * Output: false
 * Example 4:
 * <p>
 * Input: "([)]"
 * Output: false
 * Example 5:
 * <p>
 * Input: "{[]}"
 * Output: true
 */
public class Parentheses {

    public static void main(String[] args) {
        Parentheses instance = new Parentheses();
        System.out.println(instance.isValid3("()"));
        System.out.println(instance.isValid3("()[]{}"));
        System.out.println(instance.isValid3("(]"));
        System.out.println(instance.isValid3("([)]"));
        System.out.println(instance.isValid3("{[]}"));
    }

    public boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                char pop = stack.pop();
                if (!((c == ')' && pop == '(') || (c == ']' && pop == '[') || (c == '}' && pop == '{'))) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return stack.empty();
    }

    /**
     * 兼容非括号字符
     * @param str
     * @return
     */
    public boolean isValid2(String str) {
        if (null == str || str.equals("")) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                char pop = stack.pop();
                switch (pop) {
                    case '(':
                        if (c != ')') {
                            return false;
                        }
                        break;
                    case '[':
                        if (c != ']') {
                            return false;
                        }
                        break;
                    case '{':
                        if (c != '}') {
                            return false;
                        }
                        break;
                    default:
                        break;
                }
            } else {
                continue;
            }
        }
        return stack.empty();
    }

    public boolean isValid3(String str) {
        if (null == str || str.equals("")) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (map.containsKey(c)) {
                stack.push(c);
            } else if (map.values().contains(c)) {
                if (!stack.empty() && map.get(stack.peek()) == c) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.empty();
    }
}
