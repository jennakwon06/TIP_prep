package Q8_7;

import library.AssortedMethods;
import library.LinkedListNode;

/**
 * Created by JennaKwon on 5/09/16.
 *
 * Question 8.3
 * Remove kth element in O(1) in a SLL
 *
 */

public class RemoveNode {
    public static void deleteNode(LinkedListNode node) {
        node.data = node.next.data;
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        LinkedListNode head = AssortedMethods.randomLinkedList(10, 0, 10);
        System.out.println(head.printForward());
        deleteNode(head.next.next.next.next); // delete node 4
        System.out.println(head.printForward());
    }
}
