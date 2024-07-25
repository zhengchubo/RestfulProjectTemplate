package com.justin4u.leetcode;

import org.springframework.util.Assert;

/**
 * com.justin4u.leetcode
 *
 * Given a non-negative integer c, decide whether there're two integers a and b such that a^2 + b^2 = c.
 * Input: c = 5
 * Output: true
 * Explanation: 1 * 1 + 2 * 2 = 5
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2020-11-04</pre>
 */
public class SumOfSquareNumbers {

    public static void main(String[] args) {
        Assert.isTrue(solution(5) == true, "Error");
        Assert.isTrue(solution(3) == false, "Error");
        Assert.isTrue(solution(4) == true, "Error");
        Assert.isTrue(solution(2) == true, "Error");
        Assert.isTrue(solution(1) == true, "Error");
    }

    /**
     * 题目描述：判断一个非负整数是否为两个整数的平方和。
     *
     * 可以看成是在元素为 0~target 的有序数组中查找两个数，使得这两个数的平方和为 target，如果能找到，则返回 true，表示 target 是两个整数的平方和。
     *
     * 本题和 167. Two Sum II - Input array is sorted 类似，只有一个明显区别：一个是和为 target，一个是平方和为 target。本题同样可以使用双指针得到两个数，使其平方和为 target。
     *
     * 本题的关键是右指针的初始化，实现剪枝，从而降低时间复杂度。设右指针为 x，左指针固定为 0，为了使 02 + x2 的值尽可能接近 target，我们可以将 x 取为 sqrt(target)。
     *
     * 因为最多只需要遍历一次 0~sqrt(target)，所以时间复杂度为 O(sqrt(target))。又因为只使用了两个额外的变量，因此空间复杂度为 O(1)。
     * @param c
     * @return
     */
    public static final boolean solution(int c) {
        int a = 0;
        int b = c;
        while (a <= b) {
            int squareValue = a * a + b * b;
            System.out.println(a + "^2 + " + b + "^2" + " ?= " + squareValue);
            if (squareValue == c) {
                System.out.println(a + "^2 + " + b + "^2" + " = " + c);
                return true;
            } else if (squareValue < c) {
                a++;
            } else {
                b--;
            }
        }
        return false;
    }
}
