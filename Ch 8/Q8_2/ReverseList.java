package Q8_2;

import library.AssortedMethods;
import library.LinkedListNode;

/**
 * Created by JennaKwon on 6/3/16.
 */
public class ReverseList {

    public static LinkedListNode reverseListIteratively(LinkedListNode head) {
        LinkedListNode prev = null;
        LinkedListNode next;
        
        while (head != null) {
            next = head.next;
            head.next = prev;
            prev = head;
            head = next; 
        }

        return prev;
    }

    public static LinkedListNode reverseListRecursively(LinkedListNode head) {
        LinkedListNode dummy = new LinkedListNode();
        reverseListHelper(head, dummy);
        head.next = null;
        return dummy.next;
    }

    private static LinkedListNode reverseListHelper(LinkedListNode node, LinkedListNode dummy) {
        if (node.next == null) {
            dummy.setNext(node);
            return node;
        }

        LinkedListNode next = reverseListHelper(node.next, dummy);
        next.next = node;

        return node;
    }


    public static void main(String[] args) {
        int[] arr1 = new int[]{2,3,4,6,8,8};

        LinkedListNode head1 = AssortedMethods.createLinkedListFromArray(arr1);
        System.out.println(head1.printForward());

        LinkedListNode newHead = reverseListRecursively(head1);
        LinkedListNode newHead2 = reverseListIteratively(head1);

        System.out.println(newHead.printForward());
        System.out.println(newHead2.printForward());
    }
}
