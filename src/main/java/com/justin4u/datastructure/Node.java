package com.justin4u.datastructure;

public class Node <T> {
    public T value;
    public Node next;

    public Node(T value) {
        this.value = value;
        this.next = null;
    }
    public Node(T value, Node next) {
        this.value = value;
        this.next = next;
    }

    @Override
    public String toString() {
        return value + "-->" + next;
    }
}
