package Q22_21;

/**
 * Created by JennaKwon on 6/2/16.
 */
public class DetectUniqueElement {

    public static int detectUniqueElement2(int[] arr) {
        int result = arr[0];

        for (int i = 1; i < arr.length; i++) {
            result ^= arr[i];
        }

        return result;
    }

    public static int detectUniqueElement3(int[] arr) {
        int result = 0;

        System.out.println(Integer.toBinaryString(result));

        for (int i = 1; i < arr.length; i++) {

            result ^= arr[i];

            System.out.println("Before " + Integer.toBinaryString(result));

            if (result != 0) {
                result -= arr[i];
            }

            System.out.println("After " + Integer.toBinaryString(result));

        }

        return result;
    }



    public static void main(String[] args) {
        int[] arr2 = new int[]{2,2,3,3,4,4,5,6,6,7,7};
        int[] arr3 = new int[]{2,2,2,4,4,4,5,6,6,6,7,7,7};
                            // 0,1,2,3,4,5,6,7,8,9,10,11,12

        System.out.println(detectUniqueElement2(arr2));
        System.out.println(detectUniqueElement3(arr3));
    }
}
