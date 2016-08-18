package Q6_3;

import java.util.*;

/**
 * Question: Multiply two arbitrary-precision integers
 */
public class MultiplyTwoIntegers {


    /**
     * TODO how to handle negatives?
     */
    public static List<Integer> multiply(List<Integer> a1, List<Integer> a2) {
        if (a1.size() == 1 && a1.get(0) == 0
            || a2.size() == 1 && a2.get(0) == 0) {return Arrays.asList(0);}

        Integer[] arr = new Integer[a1.size() + a2.size()];
        Arrays.fill(arr, 0);
        List<Integer> res = Arrays.asList(arr);
        List<Integer> res2 = new ArrayList<>();

        Collections.reverse(a1);
        Collections.reverse(a2);

        for (int i = 0; i < a1.size(); i++) {
            for (int j = 0; j < a2.size(); j++) {
                res.set(i + j, res.get(i + j) + a1.get(i) * a2.get(i));
                res.set(i + j + 1, res.get(i + j + 1) + res.get(i + j) / 10);
                res.set(i + j, res.get(i + j) % 10);
            }
        }

        Collections.reverse(res);

        for (int i = 0; i < res.size(); i++) {
            if (res.get(i) != 0) {
                res2.add(res.get(i));
            }
        }

        return res2;
    }



    public static void main(String[] args) {
        assert(multiply(Arrays.asList(0), Arrays.asList(-1, 0, 0, 0))
                .equals(Arrays.asList(0)));
        assert(multiply(Arrays.asList(0), Arrays.asList(1, 0, 0, 0))
                .equals(Arrays.asList(0)));
        assert(multiply(Arrays.asList(9), Arrays.asList(9))
                .equals(Arrays.asList(8, 1)));
        assert(multiply(Arrays.asList(9), Arrays.asList(9, 9, 9, 9))
                .equals(Arrays.asList(8, 9, 9, 9, 1)));
    }
}
