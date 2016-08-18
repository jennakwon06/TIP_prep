package Q5_1;

/**
 * Created by JennaKwon on 5/31/16.
 *
 * Question 5.1
 * Compute the parity of a word
 * Parity of a binary number = 1 if # of 1s = odd, 0 if even
 *
 * Solution 1) Parity of a single 32-bit ints
 * Iteratively turn off the lowest set bit
 * O(k) -> k = number of 1s in the number
 * Worst case O(n)
 *
 * Solution 2) Parity of many 32-bit integers
 * Caching + parallel processing
 *
 */
public class ComputeParity {

    public final int WORD_SIZE = 16;
    public final int BIT_MASK = 0b11111111;

    /**
     * Refinement to brute force algorithm
     * Turn off the lowest set bit iteratively until the number is 0
     * Worst case O(n) (every bit is set)
     * Faster than O(log n) algorithm on very sparse input
     */
    public static int computeParity(int x) {
        int counter = 0;
        while (x != 0) {
            x &= (x - 1);
            counter++;
        }
        return counter % 2;
    }

    /**
     * O(log n) algorithm for computing parity
     * Use associative / commutative property of XOR
     */
    public static int computeParityWithXOR(int x) {
        x ^= x >>> 16; //do a lookup once we get to 16 bits?
        x ^= x >>> 8;
        x ^= x >>> 4;
        x ^= x >>> 2;
        x ^= x >>> 1;
        return x & 0x01;
    }

    /**
     * Build a cache with 2^8 entries for our 32-bit word
     * Each entry holds parity for a 8-bit word
     * Then, break up the 32-bit word into four pieces and compute parity
     * Final parity of 32-bit word can be computed with pairwise-XORing of 8-bit words
     *
     * O(n / L); n = word size, L = width of cache keys.
     *
     *
     */
    public static int computeParityWithCaching(int x, int[] cache) {
        int w1 = (x >> 24) & 0b11111111;
        int w2 = x >> 16 & 0b11111111;
        int w3 = x >> 8 & 0b11111111;
        int w4 = x & 0b11111111;

        int parity = cache[w1];
        for (int i : new int[]{w2, w3, w4}) {
            parity ^= cache[i];
        }
        return parity;
    }

    /**
     * Build precomputed parity table
     * O(2^(n/4)) entries, n = world size
     * @param cache
     */
    public static void buildCache(int[] cache) {
        for (int i = 0; i < cache.length; i++) {
            cache[i] = computeParityWithXOR(i);
        }
    }


    public static void main(String[] args) {
        System.out.println("Number w/ parity 0");
        int b1 = 0b00110001001101001001000000011110; //32 bit word, 12 bits set
        System.out.println(computeParity(b1)); //return 0
        System.out.println(computeParityWithXOR(b1)); //return 0

        System.out.println("Number w/ parity 1");
        int b2 = 0b00110001001101001001000000011111; //32 bit word, 13 bits set
        System.out.println(computeParity(b2)); //return 1
        System.out.println(computeParityWithXOR(b2)); //return 1

        int[] cache = new int[2 << 8];
        buildCache(cache);
        System.out.println("Using cache; should return 0");
        System.out.println(computeParityWithCaching(b1, cache)); //return 0
        System.out.println("Using cache; should return 1");
        System.out.println(computeParityWithCaching(b2, cache)); //return 0
    }
}
