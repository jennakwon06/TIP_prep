package Q11_3;

import java.util.*;

/**
 * Created by JennaKwon on 5/12/16.
 *
 * Question 11.3
 * Each element is at most k locations away from its original location. Sort this array.
 *
 *
 * Lesson learned
 * - Sorting is n log n at best. How can we make this better with the extra information?
 * - No point doing O(n*k) sorting because this may not be better than n log (n)
 * - why do we use heap? To enforce log k behavior. What makes it log k? The invariant that size remains at k + 1.
 * - How do we maintain that size? Initialie it with first k + 1 elements. Then, as we proceed, add one to the heap & extract from the heap.
 *
 * ksortWithHeapWrong - my attempt
 * wrong because it only looks ahead "k" positions - does not look back
 *
 * ksortWithHeapRight - correct solution
 * initialize with k + 1 elements in the heap, then add & poll one by one.
 *
 */
public class SortAlmostSorted {

    public static void ksortWithHeapWrong(int[] arr, int k) {
        // min heap
        PriorityQueue<Integer> heap = new PriorityQueue<>(k + 1, (Integer s1, Integer s2) -> s1 - s2);
        // for max heap, flip s1 & s2
        int i = 0;
        int counter = 0;

        while (i < arr.length) {
            int j, w;

            for (j = i; j <= i + k; j++) {
                if (j >= 0 && j + 1 < arr.length) {
                    System.out.println(arr[j]);
                    heap.add(arr[j]);
                }
            }

            for (w = j; w >= i - k; w--) {
                if (null != heap.peek()) {
                    System.out.println(counter);
                    arr[counter++] = heap.poll();
                }
            }
            i += k + 1;
        }
    }

    public static void ksortWithHeapCorrect(int[] arr, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(k + 1, (Integer s1, Integer s2) -> s1 - s2);

        int counter = 0;

        for (int i = 0; i <= k; i++) {
            heap.add(arr[i]);
        }

        for (int i = k + 1; i < arr.length + k; i++) { // going upto arr.length + k allows addition for last k elems
            System.out.println(heap);
            arr[counter++] = heap.poll();
            if (i < arr.length) { // last k elements are in the heap
                heap.add(arr[i]);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3 ,-1, -4, 2 ,6 ,4 ,5 ,8, 7, 9, 13, 10, 12, 16};
        int[] arr2 = arr.clone();
        ksortWithHeapWrong(arr, 3);
        System.out.println(Arrays.toString(arr));

        ksortWithHeapCorrect(arr2, 3);
        System.out.println(Arrays.toString(arr2));

    }
}
