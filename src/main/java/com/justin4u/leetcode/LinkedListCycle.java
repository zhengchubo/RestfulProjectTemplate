package com.justin4u.leetcode;

import com.justin4u.datastructure.Node;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * com.justin4u.leetcode
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 *
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote
 * the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 *
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 *
 *
 *
 * Example 1:
 * 3 -> 2 -> 0 -> -4 -> [1]
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node (0-indexed).
 *
 * Example 2:
 * 1 -> 2 -> [0]
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
 *
 * Example 3:
 * 1 -> [-1]
 * Input: head = [1], pos = -1
 * Output: false
 * Explanation: There is no cycle in the linked list.
 *
 *
 *
 * Constraints:
 *
 *     The number of the nodes in the list is in the range [0, 10^4].
 *     -10^5 <= Node.val <= 10^5
 *     pos is -1 or a valid index in the linked-list.
 *
 *
 *
 * Follow up: Can you solve it using O(1) (i.e. constant) memory?
 * @author Justin Wu
 * @version 1.0
 * @since <pre>2020-11-09</pre>
 */
public class LinkedListCycle {

    public static void main(String[] args) {
        Node node1 = new Node(3);
        Node tailNode1 = new Node(-4);
        Node secondNode1 = new Node(2, new Node(0, tailNode1));
        node1.next = secondNode1;
        tailNode1.next = secondNode1;

        Node tailNode2 = new Node(2);
        Node node2 = new Node(1, tailNode2);
        tailNode2.next = node2;

        Node node3 = new Node(1);

        Assert.isTrue(solution(node1) == true, "Error: node1 does not have cycle.");
        Assert.isTrue(solution(node2) == true, "Error: node2 dose not have cycle.");
        Assert.isTrue(solution(node3) == false, "Error: node3 has cycle.");
    }

    /**
     * 使用双指针，一个指针每次移动一个节点，一个指针每次移动两个节点，如果存在环，那么这两个指针一定会相遇。
     * @param headNode
     * @return
     */
    public static final boolean solution(Node headNode) {
        if (Objects.isNull(headNode)) {
            return false;
        }
        Node link1 = headNode, link2 = headNode.next;
        while (link1 != null & link2 != null && link2.next != null) {
            if (link1 == link2) {
                return true;
            }
            link1 = link1.next;
            link2 = link2.next.next;
        }
        return false;
    }
}
