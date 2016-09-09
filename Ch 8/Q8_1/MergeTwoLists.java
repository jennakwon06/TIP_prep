package Q8_1;

import library.AssortedMethods;
import library.LinkedListNode;

import java.util.Arrays;

/**
 * Merge two lists 
 * 
 * Exercise - compare with merging two arrays 
 */
public class MergeTwoLists {
    
    public static LinkedListNode merge(LinkedListNode<Integer> a1, LinkedListNode<Integer>  a2) {
        LinkedListNode resultHead = new LinkedListNode();
        LinkedListNode current = resultHead;
        
        while (a1 != null && a2 != null) {
            if (a1.data > a2.data) {
                current.next = a2;
                a2 = a2.next;
            } else {
                current.next = a1;
                a1 = a1.next;
            }
            current = current.next;
        }

        current.next = a1 != null ? a1 : a2; //append remaining

        // returning resultHead would print the dummy data
        return resultHead.next;
    }

    public static int[] mergeArrays(int[] a1, int[] a2) {
        int[] res = new int[a1.length + a2.length];
        int i = 0, j = 0;

        while (i < a1.length && j < a2.length) {
            res[i + j] = a1[i] < a2[j] ? a1[i++] : a2[j++];
        }

        while (i < a1.length) {
            res[i + j] = a1[i++];
        }

        while (j < a2.length) {
            res[i + j] = a2[j++];
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{2,3,4,6,8,9,14};
        int[] arr2 = new int[]{3,3,5,9,9,9,10,12};

        LinkedListNode head1 = AssortedMethods.createLinkedListFromArray(arr1);
        LinkedListNode head2 = AssortedMethods.createLinkedListFromArray(arr2);
        System.out.println(head1.printForward());
        System.out.println(head2.printForward());
        System.out.println(merge(head1, head2).printForward());
        
        System.out.println(Arrays.toString(mergeArrays(arr1, arr2)));


    }
}
