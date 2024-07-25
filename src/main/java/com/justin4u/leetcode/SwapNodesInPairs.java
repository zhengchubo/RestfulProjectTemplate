package com.justin4u.leetcode;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * <p>
 * Example:
 * <p>
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * Note:
 * <p>
 * Your algorithm should use only constant extra space.
 * You may not modify the values in the list's nodes, only nodes itself may be changed.
 */
public class SwapNodesInPairs {

    public static void main(String[] args) {
        SwapNodesInPairs instance = new SwapNodesInPairs();
        ListNode list = new ListNode(1);
        ListNode pointer = list;
        for (int i = 2; i < 10; i++) {
            ListNode node = new ListNode(i);
            pointer.next = node;
            pointer = pointer.next;
        }
        System.out.println(list);
        System.out.println(instance.swap0(list));
    }

    public ListNode swap0(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = head.next;
        head.next = swap0(node.next);
        node.next = head;
        return node;
    }

    public ListNode swap(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pointer = head;
        ListNode next = head.next;
        ListNode last = null;
        while (next != null) {
            pointer.next = next.next;
            next.next = pointer;
            // store the head
            if (pointer == head) {
                head = next;
            }
            if (last != null) {
                last.next = next;
            }
            last = pointer;
            // update pointer, next
            pointer = pointer.next;
            if (pointer != null) {
                next = pointer.next;
            } else {
                break;
            }
        }
        return head;
    }
}
