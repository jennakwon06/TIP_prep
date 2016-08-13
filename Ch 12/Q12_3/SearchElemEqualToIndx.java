package Q12_3;

/**
 * Created by JennaKwon on 6/10/16.
 */
public class SearchElemEqualToIndx {

    /**
     * Search for A[i] = i
     * Only change - comparison function
     */
    public static int binarySearch(int[] arr) {
        int lo = 0;
        int hi = arr.length - 1;
        int mid;

        while (lo <= hi) {
            mid = (hi - lo) / 2 + lo;
            if (arr[mid] > mid) {
                hi = mid - 1;
            } else if (arr[mid] == mid) {
                return mid;
            } else {
                lo = mid + 1;
            }
        }


        System.out.println("end");
        System.out.println(lo);
        System.out.println(hi);

        return -1;
    }

    /**
     * Search for A[i] = i
     * Comparing between A[mid] and mid may not work because duplicates can skew midpoints
     * Search both endpoints?
     * Only change - comparison function
     */
    public static int binarySearchWithDuplicates(int[] arr) {
        int lo = 0;
        int hi = arr.length - 1;
        int mid;

        while (lo <= hi) {
            mid = (hi - lo) / 2 + lo;
            if (arr[mid] > mid) {
                hi = mid - 1;
            } else if (arr[mid] == mid) {
                return mid;
            } else {
                lo = mid + 1;
            }
        }


        System.out.println("end");
        System.out.println(lo);
        System.out.println(hi);

        return -1;
    }




    public static void main(String[] args) {
        int[] arrNoDups = new int[]{-14, -10, 2, 108, 243, 285, 401};
        int[] arrDuplicates = new int[]{1, 1, 2, 2, 2, 2, 2, 2, 2, 9};

        System.out.println(binarySearchWithDuplicates(arrDuplicates)); // return 9

    }

}
