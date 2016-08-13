import java.util.Arrays;

/**
 *
 * Question 16.10
 * Write a program which takes n as input and returns an n-bit gray code
 *
 * Approach
 * Solution 1 (suboptimal)
 * For n bits, create n + 1 bins
 * Put 0 to 2^n-1 integers into bins corresponding to how many 1s it has
 * Visit bin in a specific order
 * Memory space
 *
 * Solution 2:
 * Generate sequences for n bits from n - 1 bits
 * ["0", "1"]
 *
 */

public class GrayCode {

    public static String[] generate(int n) {
        String[] arr = new String[(int) Math.pow(2, n)];

        if (n <= 0) {return arr;}

        arr[0] = "0";
        arr[1] = "1";

        for (int i = 2; i <= n; i++) {
            generateOnes(arr, (int) Math.pow(2, i - 1), (int) Math.pow(2, i) - 1);
            generateZeros(arr, 0, (int) Math.pow(2, i - 1) - 1);
        }
        return arr;
    }

    public static void generateOnes(String[] arr, int lo, int hi) {
        for (int i = 0; i < lo; i++) {
            arr[lo + i] = "1" + arr[lo - i - 1];
        }
    }

    public static void generateZeros(String[] arr, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            arr[i] = "0" + arr[i];
        }
    }


    public static void main(String[] args) {
        System.out.println("main 2");
        System.out.println(Arrays.asList(generate(2)));
        System.out.println("main 3");
        System.out.println(Arrays.asList(generate(3)));
        System.out.println("main 4");
        System.out.println(Arrays.asList(generate(4)));
    }

}