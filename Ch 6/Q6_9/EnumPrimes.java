package Q6_9;

import java.util.*;

/**
 * Problem:
 * Enumerate all numbers up to n
 */
public class EnumPrimes {

    // difficult question - analyze the runtime.
    // Interestingly, there is a similar way of shrinking down the size of a problem
    // that yields runtimes of the form O(log log n).
    // Instead of dividing the input in half at each layer, what happens if we take the square root of the size at each layer?
    public static List<Integer> generateAllPrimesUpToN(int n) {
        List<Integer> primeNumbers = new ArrayList<>();
        boolean[] candidates = new boolean[n + 1];
        Arrays.fill(candidates, true);

        for (int i = 2; i <= n; i++) {
            if (candidates[i]) {
                primeNumbers.add(i);
                for (int j = i; j <= n; j += i) {
                    candidates[j] = false;
                }
            }
        }
        return primeNumbers;
    }

    public static void main(String[] args) {
        System.out.println(generateAllPrimesUpToN(1000));
    }
}
