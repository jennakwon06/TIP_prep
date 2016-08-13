package Q22_19;

/**
 * Created by JennaKwon on 6/1/16.
 *
 * Question 22.19
 * Search two sorted arrays - duplicates may exist within and across arrays
 *
 * Solution 1)
 * Merge step; keep two pointers, compare beginnings of each list and count off smaller elements
 *
 */
public class SearchTwoSortedArrays {

    /**
     * Merge & keep track of distinct elements seen so far
     * Worst case O(m + n) when k = m + n
     * @param arr1
     * @param arr2
     * @param k
     * @return
     */
    public static int searchTwoSortedArrays(int[] arr1, int[] arr2, int k) {
        if (k > arr1.length + arr2.length) {return Integer.MIN_VALUE;}

        int indx1 = 0;
        int indx2 = 0;
        int lastSeenElem = Integer.MIN_VALUE;
        int counter = 0;

        while (indx1 + indx2 < arr1.length + arr2.length && counter != k) {
            if (arr1[indx1] > arr2[indx2]) {
                if (arr2[indx2] != lastSeenElem) {
                    lastSeenElem = arr2[indx2];
                    counter++;
                }
                indx2++;
            } else if (arr1[indx1] < arr2[indx2]) {
                if (arr1[indx1] != lastSeenElem) {
                    lastSeenElem = arr1[indx1];
                    counter++;
                }
                indx1++;
            } else { //equal
                if (arr1[indx1] != lastSeenElem) {
                    lastSeenElem = arr1[indx1];
                    counter++;
                }
                indx1++;
                indx2++;
            }
        }
        return lastSeenElem;
    }

    /**
     * @TODO implement Modified Binary Search
     * Hint: take advantage of the fact that arrays are sorted
     */
    public static int searchTwoSortedArraysBinarySearch(int[] arr1, int[] arr2, int k) {
        return  0;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1,2,2,3,3,4,5,6,7,7,7,8,8,9,10};
        int[] arr2 = new int[]{-1,4,4,4,5,5,6,15};

        System.out.println(searchTwoSortedArrays(arr1, arr2, 6)); //find 5

    }
}
