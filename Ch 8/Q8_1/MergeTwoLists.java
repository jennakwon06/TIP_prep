package Q8_1;

import library.AssortedMethods;
import library.LinkedListNode;

/**
 * Created by JennaKwon on 6/3/16.
 */
public class MergeTwoLists {

    public static LinkedListNode merge(LinkedListNode l1, LinkedListNode l2) {
        LinkedListNode resultHead = new LinkedListNode();
        LinkedListNode current = resultHead;

        while (l1 != null && l2 != null) {
            if (l1.data > l2.data) {
                current.next = l2;
                l2 = l2.next;
            } else if (l1.data < l2.data) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l1;
                l1 = l1.next; //switching order between this and next line messes up where l1 goes
                current.next.next = l2;
                l2 = l2.next;
                current = current.next;
            }
            current = current.next;
        }

        current.next = l1 != null ? l1 : l2; //append remaining

        // returning resultHead would print the dummy data
        return resultHead.next;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{2,3,4,6,8};
        int[] arr2 = new int[]{3,3,5};

        LinkedListNode head1 = AssortedMethods.createLinkedListFromArray(arr1);
        LinkedListNode head2 = AssortedMethods.createLinkedListFromArray(arr2);
        System.out.println(head1.printForward());
        System.out.println(head2.printForward());

        System.out.println(merge(head1, head2).printForward());
    }
}
