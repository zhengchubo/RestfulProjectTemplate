package com.justin4u.leetcode;

/**
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the
 * nodes of the first two lists.
 *
 * Example:
 *
 * Input: 1->2->4, 1->3->4
 * Output: 1->1->2->3->4->4
 */
public class MergeTwoSortedList {

    public static void main(String[] args) {
        MergeTwoSortedList instance = new MergeTwoSortedList();
        ListNode l1 = new ListNode(1);
        ListNode l12 = new ListNode(2);
        ListNode l14 = new ListNode(4);
        l1.next = l12;
        l12.next = l14;
        ListNode l2 = new ListNode(1);
        ListNode l23 = new ListNode(3);
        ListNode l24 = new ListNode(4);
        l2.next = l23;
        l23.next = l24;

        ListNode ret = instance.solution(l1, l2);
        System.out.println(ret);
    }

    public ListNode solution(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode ret = new ListNode(0);
        ListNode current = ret;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        if (l1 != null) {
            current.next = l1;
        } else {
            current.next = l2;
        }
        return ret.next;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int v) {
            val = v;
        }

        @Override
        public String toString() {
            return "" + val + "--->" + next;
        }
    }
}
