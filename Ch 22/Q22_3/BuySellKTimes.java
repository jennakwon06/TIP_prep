package Q22_3;


import java.util.*;

/**
 * Created by JennaKwon on 8/10/16.
 */
public class BuySellKTimes {

    public static int buyStockKTimes(int[] prices) {
        return 0;
    }

    public static class Node {
        public int data;
        public Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public String toString() {
            return "( " + data + ")";
        }
    }

    public static Node insertionSort(Node head) {
        Node dummy = new Node(-1, head);
        Node cur = head;


        while (cur != null) {
            Node iter = cur.next;
            while (iter != null) {
                if (iter.data < cur.data) {
                    cur.next = iter.next;
                    iter.next = cur;
                    iter = cur.next;
                } else {
                    iter = iter.next;
                }
            }
            cur = cur.next; // cur ust point at last changed location
        }

        return dummy.next;
    }


    public static int[] doInsertionSort(int[] input){
        int temp;
        for (int i = 1; i < input.length; i++) {
            for(int j = 1 ; j <= i ; j++){
                if(input[j - 1] > input[j]){
                    temp = input[j];
                    input[j] = input[j - 1];
                    input[j - 1] = temp;
                }
            }
        }
        return input;
    }

    public BuySellKTimes() {
    }

    public static void main(String[] args) {
        int[] arr = new int[]{12, 11, 13, 9, 12, 8, 7, 14, 13, 15};
//        System.out.println(buyStockKTimes(arr));

        Node n1 = new Node(5, null);
        Node n2 = new Node(8, n1);
        Node n3 = new Node(2, n2);
        Node n4 = new Node(10, n3);

        insertionSort(n4);

        System.out.println(n3.next);
        System.out.println(n3.next.next);

        System.out.println(doInsertionSort(arr));
        System.out.println(Arrays.toString(arr));


    }
}
