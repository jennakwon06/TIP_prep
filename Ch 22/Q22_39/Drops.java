package Q22_39;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by JennaKwon on 5/12/16.
 *
 * Question 22.39
 * Given C cases and maximum of D allowable drops, what's the maximum # floors tht you can test in the worst-case
 *
 * Can you do this in O(n) space?
 *
 */
public class Drops {

    /**
     * O((c + 1)(d + 1)) time complexity, o(cd) space
     * F(c, d) = F(c - 1, d - 1) + 1 + F(c, d - 1)
     * @param c
     * @param d
     * @return
     */
    public static int numFloors(int c, int d) {
        int[][] memo = new int[c + 1][d + 1];

        for (int[] row: memo) Arrays.fill(row, -1);

        return numFloorsRecursive(c, d, memo);
    }

    private static int numFloorsRecursive(int c, int d, int[][] memo) {
        if (c == 0 || d == 0) {
            return 0;
        }

        if (c == 1) {
            return d;
        }

        if (memo[c][d] == -1) {
            memo[c][d] = numFloorsRecursive(c, d - 1, memo)
                        + numFloorsRecursive(c - 1, d - 1, memo) + 1;
        }

        return memo[c][d];
    }

    /**
     * O(c) time complexity
     * Rewrite function
     * F(c, d) = F(c - 1, d - 1) + 1 + F(c, d - 1)
     * Let A[j] = A[j - 1] + a[j] + 1, and account for D with an outer loop.
     */
    public static int numFloors2(int c, int d) {
        int[] memo = new int[c + 1];

        for (int i = 1; i <= d; i++) { //progressively allow more drops
            System.out.println(Arrays.toString(memo));
            for (int j = c; j > 0; j--) {
                memo[j] = memo[j - 1] + memo[j] + 1;
            }
        }

        return memo[c];
    }

    /**
     * Solution in the book
     * Referred here to check answers
     * @param c
     * @param d
     * @return
     */
    public static int getHeight(int c, int d) {
        List<List<Integer>> F = new ArrayList<>(c + 1);
        for (int i = 0; i < c + 1; ++i) {
            F.add(new ArrayList(Collections.nCopies(d + 1, -1)));

        }
        return getHeightHelper(c, d, F);
    }

    private static int getHeightHelper(int c, int d, List<List<Integer>> F) {
        if (c == 0 || d == 0) {
            return 0;
        } else if (c == 1) {
            return d;
        } else {
            if (F.get(c).get(d) == -1) {
                F.get(c).set(d, getHeightHelper(c, d - 1, F)
                        + getHeightHelper(c - 1, d - 1, F)
                        + 1);
            }
        }
        return F.get(c).get(d);
    }


    public static void main(String[] arg) {
        System.out.println(numFloors(1, 5)); // 5
        System.out.println(numFloors(2, 5)); // 15
        System.out.println(numFloors(3, 5)); // 25
        System.out.println(numFloors(2, 6)); // 11

        System.out.println(numFloors2(1, 5)); // 5
        System.out.println(numFloors2(2, 5)); // 15
        System.out.println(numFloors2(3, 5)); // 25
        System.out.println(numFloors2(2, 6)); // 11

    }
}
