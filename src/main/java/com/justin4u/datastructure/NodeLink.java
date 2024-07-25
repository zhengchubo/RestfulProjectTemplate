package com.justin4u.datastructure;

public class NodeLink {
    Node head;

    public static void main(String[] args) {
        NodeLink link = new NodeLink();
        link.head = new Node(1, new Node(2, new Node(3, new Node(4))));
        link.head = link.reverse(link.head);
        link.print();

        /*
        link.head = new Node(1);
        link.tail = link.head;
        link.tail.next = new Node(2);
        link.tail = link.tail.next;
        link.reversePrint(link.head);
        */
    }

    public void print() {
        Node current = head;
        while (current != null) {
            System.out.print(current.value + " -> ");
            current = current.next;
        }
    }

    public void reversePrint(Node head) {
        if (head != null) {
            reversePrint(head.next);
            System.out.println(head.value);
        }
    }

    public Node reverse(Node head) {
        if (head == null) {
            return null;
        }

        Node result = null;
        Node current = head;
        Node previous = null;
        while (current != null) {
            Node next = current.next;
            result = current;
            current.next = previous;
            previous = current;
            current = next;
        }
        return result;
    }
}
