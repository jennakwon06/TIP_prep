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
     * Scan with lo and hi pointers until they cross.
     * Swap two elements when necessary.
     * NOT stable
     * DOES NOT WORK WITH DUPLICATES. WHY?
     */
    public static int[] twoWayPartitionWrong(int[] arr, int index) {
        int pivot = arr[index];
        int n = arr.length - 1;

        swap(arr, n, index); //move pivot out

        int lo = 0;
        int hi = n;

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
     * Does work with duplicates, but does not group duplicates together.
     */
    private static int[] twoWayPartitionRight(int[] arr, int index) {
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
        swap(arr, n, lo);
        return arr;
    }

    /**
     * In place
     * To group equal elements together, you can do 3-way partitioning.
     */
    private static int[] threeWayPartition(int[] arr, int index) {
        int pivot = arr[index];
        int n = arr.length - 1;

        swap(arr, n, index);
        /**
         * Partition list into four groups
         * A[0 (inc) to lo (exc)] = smaller
         * A[lo (inc) to eq (exc)] = equal
         * A[eq (inc) to hi (exc)] = unclassified
         * A[hi (inc) to n - 1 (exc)] = larger
         */
        int lo = 0;
        int eq = 0;
        int hi = n - 1;

        while (eq < hi) { // arr[eq] is an incoming element
            if (arr[eq] < pivot) {
                swap(arr, eq++, lo++);
            } else if (arr[eq] == pivot) {
                eq++;
            } else { // arr[lo] > pivot
                swap(arr, eq, hi--);
            }
        }

        swap(arr, n, hi);

        return arr;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 2, 1, 3, 4, 9, 2, 2, 9, 5, 6, 1, 2, 2, 9, 9, 27, 2, 2, 2, 36, 3};
        System.out.println(Arrays.toString(twoWayPartitionWrong(arr, 2)));
        
        // second arg = index of the pivot
        int[] arr2 = new int[]{1, 1, 2, 1, 3, 4, 9, 2, 2, 9, 5, 6, 1, 2, 2, 9, 9, 27, 2, 2, 2, 36, 3};
        System.out.println(Arrays.toString(twoWayPartitionRight(arr2, 2)));

        int[] arr3 = new int[]{1, 1, 2, 1, 3, 4, 9, 2, 2, 9, 5, 6, 1, 2, 2, 9, 9, 27, 2, 2, 2, 36, 3};
        System.out.println(Arrays.toString(threeWayPartition(arr3, 2)));


    }
}
