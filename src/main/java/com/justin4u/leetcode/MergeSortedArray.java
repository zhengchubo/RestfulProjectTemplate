package com.justin4u.leetcode;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * com.justin4u.leetcode
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 *
 * Note:
 *
 *     The number of elements initialized in nums1 and nums2 are m and n respectively.
 *     You may assume that nums1 has enough space (size that is equal to m + n) to hold additional elements from nums2.
 *
 * Example:
 *
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 *
 * Output: [1,2,2,3,5,6]
 *
 *
 *
 * Constraints:
 *
 *     -10^9 <= nums1[i], nums2[i] <= 10^9
 *     nums1.length == m + n
 *     nums2.length == n
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2020-11-06</pre>
 */
public class MergeSortedArray {

    public static void main(String[] args) {
        Integer[] nums1 = new Integer[]{1,2,3,0,0,0};
        Integer[] nums2 = new Integer[]{2,5,6};
        Integer[] nums3 = new Integer[]{1,2,2,3,5,6};
        solution(nums1, nums2, 3, 3);
        Assert.isTrue(equals(nums3, nums1), "Error");
    }

    public static final void solution(Integer[] nums1, Integer[] nums2, int m, int n) {
        if (Objects.isNull(nums1) || Objects.isNull(nums2) || nums1.length <= nums2.length) {
            return;
        }
        int idx1 = m - 1;
        int idx2 = n - 1;
        int idx3 = m + n - 1;
        while (idx1 >= 0 || idx2 >=0) {
            if (idx1 < 0) {
                nums1[idx3] = nums2[idx2];
                idx2--;
                idx3--;
            } else if (idx2 < 0) {
                nums1[idx3] = nums1[idx1];
                idx1--;
                idx3--;
            } else if (nums1[idx1] > nums2[idx2]) {
                nums1[idx3] = nums1[idx1];
                idx1--;
                idx3--;
            } else {
                nums1[idx3] = nums2[idx2];
                idx2--;
                idx3--;
            }
        }
        System.out.println(Arrays.toString(nums1));
    }

    private static final boolean equals(Integer[] nums1, Integer[] nums2) {
        if (Objects.equals(nums1, nums2)) {
            return true;
        }
        if (Objects.isNull(nums1) || Objects.isNull(nums2) || nums1.length != nums2.length) {
            return false;
        }
        for (int i = 0; i < nums1.length; i++) {
            if (!Objects.equals(nums1[i], nums2[i])) {
                return false;
            }
        }
        return true;
    }
}
