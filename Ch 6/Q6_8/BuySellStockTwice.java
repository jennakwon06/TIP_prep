package Q6_8;

/**
 * Created by JennaKwon on 8/10/16.
 */
public class BuySellStockTwice {

    public static int buySellStockTwice(int[] prices) {

        int forward[] = new int[prices.length];
        int backward[] = new int[prices.length];

        // compute max profit array going forward
        // forward[i] holds the max profit of buying / selling on ith day
        // max in this array would be the answer for the buy / sell once
        int forwardMin = Integer.MAX_VALUE;
        int forwardProfit = Integer.MIN_VALUE;
        for (int i = 0; i < forward.length; i++) {
            forwardMin = Math.min(forwardMin, prices[i]);
            forwardProfit = Math.max(forwardProfit, prices[i] - forwardMin);
            forward[i] = forwardProfit;
        }

        // backward[i] holds the max profit of buying / selling on ith day
        // compute max profit going backward
        int backwardMax = Integer.MIN_VALUE;
        int backwardProfit = Integer.MIN_VALUE;
        for (int i = backward.length - 1; i >= 0; i--) {
            backwardMax = Math.max(backwardMax, prices[i]);
            backwardProfit = Math.max(backwardProfit, backwardMax - prices[i]);
            backward[i] = backwardProfit;
        }


        int totalMax = Integer.MIN_VALUE;
        for (int i = 1; i < backward.length; i++) {
            totalMax = Math.max(totalMax, backward[i] + forward[i - 1]);
        }

        return totalMax;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{12, 11, 13, 9, 12, 8, 14, 13, 15};

        System.out.println(buySellStockTwice(arr));


    }
}
