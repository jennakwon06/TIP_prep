package Q12_1;

/**
 * Created by JennaKwon on 6/10/16.
 */
public class FirstOccurenceOfK {


    /**
     * Look for the leftmost endpoint of K
     */
    public static int binarySearch(int[] arr, int k) {
        int lo = 0;
        int hi = arr.length - 1;
        int mid = -1;

        while (lo <= hi) {
            mid = (hi - lo) / 2 + lo;
            System.out.println(lo);
            System.out.println(hi);
            System.out.println(mid);
            if (arr[mid] < k) { //search right
                lo = mid + 1;
            } else if (arr[mid] == k) {
                hi = mid - 1; // keep searching on left
            } else { // arr[mid] > k, search on left
                hi = mid - 1;
            }
        }

        System.out.println("end");
        System.out.println(lo); //3
        System.out.println(hi); //2
        System.out.println(mid); //3

        return lo;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-14, -10, 2, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 108, 243, 285, 285, 285, 401};

        System.out.println(binarySearch(arr, 108)); // return 3
    }
}
