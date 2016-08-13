package Q6_5;

import java.util.Arrays;

/**
 * Created by JennaKwon on 6/1/16.
 *
 * Question 6.5
 * Delete all occurrences of a key from an array
 *
 *
 */
public class DeleteFromArray {

    /**
     * Assume positive array
     * O(n)
     * @param arr
     */
    public static void deleteFromArray(int[] arr) {
        int lastAdded = -1;
        int index = 0;
        //iterate through the array, and keep track of the "last added element"
        for (int i : arr) {
            if (i != lastAdded) {
                arr[index++] = i;
                lastAdded = i;
            }
        }

        for (int i = index; i < arr.length; i++) {
            arr[i] = -1;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 9, 9, 9, 9, 10, 10, 12, 13};
        deleteFromArray(arr);
        System.out.println(Arrays.toString(arr));
    }
}
