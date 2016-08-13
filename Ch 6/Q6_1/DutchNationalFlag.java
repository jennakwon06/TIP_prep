package Q6_1;

import java.util.Arrays;

/**
 * Created by JennaKwon on 6/1/16.
 *
 * Qustion 6.1
 * Given an array A and index i into A, rearrange elements such that all elements less than A[i] appear first,
 * followed by elements equal to the pivot, followed by elements greater than pivot.
 *
 */
public class DutchNationalFlag {

    /**
     * In place quick sort partition
     * Scan with two pointers
     * NOT stable
     * DOES NOT WORK WITH DUPLICATES
     */
    public static int[] twoWayPartition(int[] arr, int index) {
        int pivot = arr[index];
        int n = arr.length - 1;

        swap(arr, n, index); //move pivot out

        int lo = 0;
        int hi = n - 1;

        while (lo < hi) {
            if (arr[lo] > pivot && arr[hi] < pivot) {
                swap(arr, lo++, hi--);
            } else if (arr[lo] < pivot && arr[hi] < pivot) { //lo is ok; move up
                lo++;
            } else if (arr[lo] > pivot && arr[hi] > pivot) { //hi is ok; move down
                hi--;
            } else { // lo and hi are both ok; move both
                lo++;
                hi--;
            }
        }

        swap(arr, n, hi);

        return arr;
    }

    /**
     * In place quick sort partition
     * Scan with one pointer
     * NOT stable
     * DOES NOT WORK WITH DUPLICATES
     */
    private static int[] twoWayPartition2(int[] arr, int index) {
        int pivot = arr[index];
        int n = arr.length - 1;

        swap(arr, n, index); //move pivot out

        int lo = 0;
        int hi = n - 1;

        for (int i = lo; i < hi; i++) {
            if (arr[i] <= pivot) {
                swap(arr, i, lo++);
            }
        }

        swap(arr, lo, hi);

        return arr;
    }

    /**
     * In place
     * Works with duplicates
     * @TODO implement
     */
    private static int[] threeWayPartition(int[] arr, int index) {
        return arr;
    }



    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 9, 9, 5, 6, 1, 2, 2, 2, 2, 2, 9, 9, 9, 11, 16, 27, 36, 3, 19, 20};

        System.out.println(Arrays.toString(twoWayPartition(arr, 5))); //9, [4, 5, 6, 1, 2, 3, 9, 16, 27, 36, 20, 19, 11]
        System.out.println(Arrays.toString(twoWayPartition2(arr, 5))); //[4, 5, 6, 1, 2, 3, 9, 16, 27, 36, 20, 19, 11]


    }
}
