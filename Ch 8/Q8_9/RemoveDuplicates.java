package Q8_9;

import library.LinkedListNode;

/**
 * Created by JennaKwon on 5/18/16.
 *
 * Question 8.9
 * Remove duplicates from a sorted singly linked list
 *
 */
public class RemoveDuplicates {

    public static void removeDuplicates(LinkedListNode head) {
        if (head == null) {
            return;
        }

        LinkedListNode cur = head;

        while (cur != null) {
            LinkedListNode next = cur.next;
            while (next != null && cur.data == next.data) {
                next = next.next;
            }
            cur.next = next;
            cur = next;
        }
    }


    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(1);
        LinkedListNode n1 = new LinkedListNode(1);
        LinkedListNode n2 = new LinkedListNode(1);
        LinkedListNode n3 = new LinkedListNode(1);
        LinkedListNode n4 = new LinkedListNode(2);
        LinkedListNode n5 = new LinkedListNode(2);
        LinkedListNode n6 = new LinkedListNode(3);
        LinkedListNode n7 = new LinkedListNode(3);

        head.setNext(n1);
        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n4.setNext(n5);
        n5.setNext(n6);
        n6.setNext(n7);

        System.out.println(head.printForward());

        removeDuplicates(head);

        System.out.println(head.printForward());
    }
}
