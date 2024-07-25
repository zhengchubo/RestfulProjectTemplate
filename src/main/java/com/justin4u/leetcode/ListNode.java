package com.justin4u.leetcode;

class ListNode {
    int val;
    ListNode next;

    ListNode(int v) {
        val = v;
    }

    @Override
    public String toString() {
        return val + "--->" + next;
    }
//    public String toString() {
//        return "ListNode{" + "val=" + val + ", next=" + next + '}';
//    }

}
