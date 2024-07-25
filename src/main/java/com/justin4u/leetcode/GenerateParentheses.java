package com.justin4u.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * For example, given n = 3, a solution set is:
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class GenerateParentheses {

    public static void main(String[] args) {
        GenerateParentheses instance = new GenerateParentheses();
        List<String> list = instance.generate(3);
        System.out.println(Arrays.toString(list.toArray()));
        List<String> list2 = instance.generate(2);
        System.out.println(Arrays.toString(list2.toArray()));
    }

    public List<String> generate(int n) {
        List<String> list = new ArrayList<>();
        generate(n, n, list, "");
        return list;
    }

    private void generate(int left, int right, List<String> list, String str) {
        //System.out.println("left=" + left + ";right=" + right + ";str=" + str);
        if (left == 0 && right == 0) {
            list.add(str);
        }
        if (left > 0) {
            //System.out.println("Start generate(1)"+ left + ","+right);
            generate(left - 1, right, list, str + "(");
            //System.out.println("End generate(1)"+left+","+right);
        }
        if (left < right && right > 0) {
            //System.out.println("Start generate(2)"+ left + ","+right);
            generate(left, right - 1, list, str + ")");
            //System.out.println("End generate(2)"+left+","+right);
        }
    }
}
