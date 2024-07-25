package com.justin4u.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * <p>
 * Example:
 * <p>
 * Input:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * Output: 1->1->2->3->4->4->5->6
 */
public class MergeNSortedList {

    public static void main(String[] args) {
        MergeNSortedList instance = new MergeNSortedList();
        List<ListNode> list = new ArrayList<>();
        ListNode l1 = new ListNode(1);
        ListNode l12 = new ListNode(4);
        ListNode l13 = new ListNode(5);
        l1.next = l12;
        l12.next = l13;
        System.out.println(l1);
        ListNode l2 = new ListNode(1);
        ListNode l23 = new ListNode(3);
        ListNode l24 = new ListNode(4);
        l2.next = l23;
        l23.next = l24;
        System.out.println(l2);
        ListNode l3 = new ListNode(2);
        ListNode l36 = new ListNode(6);
        l3.next = l36;
        list.add(l1);
        list.add(l2);
        list.add(l3);
        System.out.println(l3);
        ListNode result = instance.solution(list);
        System.out.println(result);
    }

    public ListNode solution(List<ListNode> list) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>();
        for (ListNode node : list) {
            queue.add(node);
        }

        ListNode ret = null;
        ListNode cur = null;
        while (queue.size() > 0) {
            //System.out.println("<<" + Arrays.toString(queue.toArray()));
            ListNode node = queue.poll();
            //System.out.println(">>" + Arrays.toString(queue.toArray()));
            if (ret == null) {
                ret = node;
                cur = node;
            } else {
                cur.next = node;
                cur = cur.next;
            }
            if (node.next != null) {
                queue.add(node.next);
            }
            //System.out.println("==" + node);
            //System.out.println("--" + ret);
        }
        return ret;
    }

    private static class ListNode implements Comparable<ListNode> {
        int val;
        ListNode next;

        ListNode(int v) {
            val = v;
        }

        @Override
        public String toString() {
            return val + "--->" + next;
        }

        @Override
        public int compareTo(ListNode n) {
            return this.val - n.val;
        }
    }
}
