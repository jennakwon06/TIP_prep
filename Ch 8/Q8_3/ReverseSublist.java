package Q8_3;

import library.AssortedMethods;
import library.LinkedListNode;

/**
 * Given a list L and two integers s & f, reverse the order of the nodes from the sth node to the fth node.
 */
public class ReverseSublist {

    @SuppressWarnings("unchecked")
    public static LinkedListNode<Integer> reverseSublist(LinkedListNode<Integer> L, int start,
                                                   int finish) {
        if (start == finish) { // No need to reverse since start == finish.
            return L;
        }

        LinkedListNode<Integer> dummyHead = new LinkedListNode<>(0);
        dummyHead.next = L;
        LinkedListNode<Integer> sublistHead = dummyHead;

        LinkedListNode prev = null;

        int k = 0;
        while (k++ < start) {
            prev = sublistHead;
            sublistHead = sublistHead.next;
        }

        // prev is right before the start of sublist
        // keep track of the sublist head as it becomes the tail.

        LinkedListNode<Integer> tail = sublistHead;

        // reverse
        // SLH = prev
        // SLI = cur
        LinkedListNode<Integer> sublistIter = sublistHead.next;
        while (start++ < finish) {
            LinkedListNode<Integer> next = sublistIter.next;
            sublistIter.next = sublistHead;
            sublistHead = sublistIter;
            sublistIter = next;
        }

        prev.next = sublistHead; // swap
        tail.next = sublistIter;

        return dummyHead.next;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        int[] arr1 = new int[]{1,2,3,4,5,6,7};

        LinkedListNode head1 = AssortedMethods.createLinkedListFromArray(arr1);
        System.out.println(head1.printForward());

        LinkedListNode newHead = reverseSublist(head1, 3, 6);
        System.out.println(newHead.printForward()); // 1->2->6->5->4->3->7
    }
}
