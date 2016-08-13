package Q22_46;

import java.util.Arrays;

/**
 * Created by JennaKwon on 5/26/16.
 *
 * Question
 * 22.46 Find the longest sum subarray whose sum is no more than k.
 * http://blog.gainlo.co/index.php/2016/06/01/subarray-with-given-sum/?utm_source=email&utm_medium=email&utm_campaign=email
 *
 * Solution
 * - Kadane's algorithm does not work here - we might want to keep the negative element
 * - We might want to keep an element greater than threshold if it can be reduced down with consecutive negative elements
 * - What if we add the smallest minimum number to every element, as well as to our threshold?
 * - we have a strictly positive arr, and we just have to find the longest array w/ adjusted sum?
 *
 */
public class MaximuSumKSubarray {

    public static class Interval {
        int start;
        int end;
        int length = start - end + 1;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
            this.length = start - end;
        }

        public String toString() {
            return "("+ start + "," + end + ")";
        }
    }

    public static class Result {
        public int runningSum = 0;
        public int length = 0;

        public Result(int runningSum, int length) {
            this.runningSum = runningSum;
            this.length = length;
        }

        public String toString() {
            return "("+ runningSum + "," + length + ")";
        }
    }

    /**
     * Possible DP solution?
     * @TODO complete
     *
     * L(i, j) = longest subarray length starting at i, ending at j, whose sum does not exceed k. i < j.
     * L(i, j) = L(i, j - 1)            if L(i, j - 1) + arr[j] > k, for 0 <= i <= k
     *         = L(i, j - 1) + arr[j]   else
     *
     * @return
     */
    public static int longestSumArray(int[] arr, int k) {
        Result[][] memo = new Result[arr.length][arr.length];

        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr.length; col++) {
                memo[row][col] = new Result(0,0);
            }
        }

        for (int row = 0; row < arr.length; row++) {
            longestSumArrayRecursive(arr, row, arr.length - 1, k, memo);
        }

        System.out.println(Arrays.deepToString(memo)); //scan to find max length?

        return 0;
    }

    public static Result longestSumArrayRecursive(int[] arr, int r, int c, int threshold, Result[][] memo) {
        if (r > c) {
            return new Result(0, 0);
        }

        if (memo[r][c].length != 0) {
            return memo[r][c];
        }

        Result withoutJ = longestSumArrayRecursive(arr, r, c - 1, threshold, memo); //running sum without J
        int withJSum = withoutJ.runningSum + arr[c];

        memo[r][c].length = withJSum < threshold ? withoutJ.length + 1 : 0;
        memo[r][c].runningSum = withJSum < threshold ? withJSum : withoutJ.runningSum;

        return memo[r][c];
    }


    /**
     * Iterative
     * Index i is a new endpoint if curSum + arr[i] < k
     * Else, it is a start point, keep looking behind.
     */

    //    once I find an end point, record current length, and move start point to end point.
    public static Interval longestSumArrayIterative(int[] arr, int k) {
        Interval result = new Interval(0, 0);
        Interval maxResult = new Interval(0, 0);

        int startPoint = 0;
        int curSum = 0;
        int curIndex;

        while (startPoint < arr.length) {


            for (curIndex = startPoint; curIndex < arr.length; curIndex++) {
                curSum += arr[curIndex];

                if (curSum < k) { //new end point, record result and reset start point
                    result.start = startPoint;
                    result.end = curIndex;
                    result.length  = result.end - result.start;
                    if (result.length > maxResult.length) {
                        maxResult = result;
                    }
                    result = new Interval(curIndex + 1, curIndex + 1); //new interval to record
                    curSum = 0;
                }
            }
            startPoint++; //search unsuccessful, move over start point
            curSum = 0;
        }

        return maxResult;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{431, -15, 639, 342, -14, 565, -924, 635, 167, -70}; //should return 4

        System.out.println(longestSumArray(arr, 184)); //should return (3, 6)

        System.out.println(longestSumArrayIterative(arr, 184)); //should return (3, 6)
    }
}
