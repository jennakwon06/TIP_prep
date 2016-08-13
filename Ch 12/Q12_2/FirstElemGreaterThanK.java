package Q12_2;

/**
 * Created by JennaKwon on 6/10/16.
 */
public class FirstElemGreaterThanK {


    /**
     * Look for the rightmost endpoint of K + 1
     */
    public static int binarySearch(int[] arr, int k) {
        int lo = 0;
        int hi = arr.length - 1;
        int mid = -1;
        int result = -1;

        while (lo <= hi) {
            mid = (hi - lo) / 2 + lo;
            System.out.println(lo);
            System.out.println(hi);
            System.out.println(mid);
            if (arr[mid] > k) { //search left
                hi = mid - 1;
            } else if (arr[mid] == k) {
                result = mid;
                lo = mid + 1; // keep searching on right
            } else {
                lo = mid + 1;
            }
        }

        System.out.println("end");
        System.out.println(lo);
        System.out.println(hi);
        System.out.println(mid);

        return lo;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-14, -10, 2, 108,  108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 243, 285, 285, 285, 401};

        System.out.println(binarySearch(arr, 108)); // return 15
    }


}
