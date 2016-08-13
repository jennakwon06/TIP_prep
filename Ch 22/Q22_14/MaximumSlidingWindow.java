package Q22_14;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by JennaKwon on 5/15/16.
 *
 * Question 22.14
 * Compute the maximum of a sliding window
 *
 * Approach
 * 1) Utilize a dequeue that keeps track of maximum in the current window
 *
 */
public class MaximumSlidingWindow {

    public static int[] maximums(int[] arr, int k) {
        if (arr.length == 0) {return new int[0];}

        int[] result = new int[arr.length - k + 1];
        LinkedList<Integer> deque = new LinkedList<>();

        for (int i = 0; i < arr.length; i++) {
            while (deque.peekLast() != null && arr[deque.peekLast()] < arr[i]) {
                deque.removeLast();
            }

            deque.addLast(i);

            if (i >= k - 1) {
                if (i - deque.peekFirst() >= k) {
                    deque.removeFirst();
                }
                result[i - k + 1] = arr[deque.peekFirst()];
            }
        }

        return result;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,3,-1,5,3,6,7};
        System.out.println(Arrays.toString(maximums(arr, 3)));
    }
}
