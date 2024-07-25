package com.justin4u.leetcode;

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.
 * <p>
 * Example:
 * <p>
 * Given linked list: 1->2->3->4->5, and n = 2.
 * <p>
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * <p>
 * Given n will always be valid.
 */
public class RemoveNthNodeFromEndOfList {

    public static void main(String[] args) {
        RemoveNthNodeFromEndOfList instance = new RemoveNthNodeFromEndOfList();
        Node head = new Node();
        head.value = 1;
        Node last = head;
        for (int i = 2; i < 6; i++) {
            Node node = new Node();
            node.value = i;
            last.next = node;
            last = node;
        }
        System.out.println("=== before ===");
        printLink(head);
        Node newHead = instance.solution(head, 2);
        System.out.println("=== after ===");
        printLink(newHead);
    }

    public Node solution(Node head, int n) {
        if (head == null || n < 1) {
            return null;
        }
        Node fast = head;
        Node slow = head;
        for (int i = 0; i <= n; i++) {
            if (fast == null) {
                return null;
            }
            fast = fast.next;
        }
        if (fast == null) {
            head = head.next;
            return head;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    private static class Node {
        int value;
        Node next;
    }

    private static void printLink(Node head) {
        Node pointer = head;
        while (pointer != null) {
            System.out.print(pointer.value + "--->");
            pointer = pointer.next;
        }
        System.out.println("nil");
    }

}
