package com.justin4u.leetcode;

import com.justin4u.datastructure.Node;

public class ReverseNodesInGroup {

    public static void main(String[] args) {
        ReverseNodesInGroup instance = new ReverseNodesInGroup();
        Node result = instance.solution(instance.generateLink(), 1);
        System.out.println(result);
        Node result2 = instance.solution(instance.generateLink(), 2);
        System.out.println(result2);
        Node result3 = instance.solution(instance.generateLink(), 3);
        System.out.println(result3);
        Node result5 = instance.solution(instance.generateLink(), 5);
        System.out.println(result5);
        Node result6 = instance.solution(instance.generateLink(), 6);
        System.out.println(result6);
    }

    public Node solution(Node head, int k) {
        Node x = new Node(0);
        x.next = head;
        Node pointer = x;
        while (pointer != null) {
            pointer.next = this.reverse(pointer.next, k);
            for (int i = 0; i < k && pointer != null; i++) {
                pointer = pointer.next;
            }
        }
        return x.next;
    }

    public Node reverse(Node head, int k) {
        if (head == null) {
            return null;
        }
        if (k < 1) {
            return head;
        }
        Node end = head;
        while (end !=null && k > 0) {
            end = end.next;
            k--;
        }

        if (k > 0) {
            return head;
        }

        Node pointer = head;
        Node previous = end;
        while (pointer != end) {
            Node next = pointer.next;
            pointer.next = previous;
            previous = pointer;
            pointer = next;
        }

        return previous;
    }

    private Node generateLink() {
        Node head = new Node(1);
        Node pointer = head;
        for (int i = 2; i <= 5; i++) {
            Node n = new Node(i);
            pointer.next = n;
            pointer = pointer.next;
        }
        return head;
    }
}
