package Q19_7;

import java.util.*;

/**
 * Problem
 * Given a dictionary $D$ and two strings $s$ and $t$, write a program to determine
 * if $s$ produces $t$. Assume that all characters are lowercase alphabets.
 * If $s$ does produce $t$, output the length of a shortest production sequence;
 * otherwise, output $-1$.
 *
 * Solution 1) Modified Dijkstra
 *  - for every str in a queue, generate all strings that differ by 1 and search for it in dictionary
 *  - fast when string is short & dictionary large
 *
 * Solution 2) Modified Dijkstra - 2
 * - given a string, iterate through the dictionary and keep track of strings that differ by 1
 * - populate q with those strings
 * - fast when string is large & dictionary is small
 *
 *
 *
 */

public class TransformStringToAnother {

    private static String randString(int len) {
        Random r = new Random();
        StringBuilder ret = new StringBuilder(len);
        while (len-- > 0) {
            ret.append((char)(r.nextInt(26) + 'a'));
        }
        return ret.toString();
    }

    private static class NodeWithDistance {
        String word;
        int d;

        public NodeWithDistance(String word, int d) {
            this.word = word;
            this.d = d;
        }
    }

    /**
     * Solution 1 implementation
     */
    public static int transformString(Set<String> D, String s, String t) {
        Queue<NodeWithDistance> q = new LinkedList<>();
        D.remove(s); // Marks s as visited by erasing it in D.
        q.add(new NodeWithDistance(s, 0));

        NodeWithDistance f;
        while (!q.isEmpty()) {
            f = q.poll();
            // Returns if we find a match.
            if (f.word.equals(t)) {
                return f.d; // Number of steps reaches t.
            }

            // Tries all possible transformations of f.first.
            String str = f.word;
            for (int i = 0; i < str.length(); ++i) {
                String strStart = i == 0 ? "" : str.substring(0, i);
                String strEnd = i + 1 < str.length() ? str.substring(i + 1) : "";
                for (int j = 0; j < 26; ++j) { // Iterates through 'a' ~ 'z'.
                    String modStr = strStart + (char)('a' + j) + strEnd;
                    if (D.remove(modStr) && !modStr.equals(str)) {
                        q.add(new NodeWithDistance(modStr, f.d + 1));
                    }
                }
            }
        }
        return -1;
    }


    /**
     * Solution 2 implementation
     */
    public static int transformStringJenna(Set<String> D, String s, String t) {
        // return false if s and t are not found in dictionary
        if (!D.contains(s) || !D.contains(t)) {
            return -1;
        }
        LinkedList<NodeWithDistance> q = new LinkedList<>();
        q.add(new NodeWithDistance(s, 0));
        D.remove(s);

        while (!q.isEmpty()) {
            NodeWithDistance cur = q.pop();
            HashSet<String> newD = new HashSet<>();
            newD.addAll(D);

            for (String str : D) {
                if (differsByOnePlace(cur.word, str)) {
                    if (str.equals(t)) {
                        return cur.d + 1;
                    }
                    q.add(new NodeWithDistance(str, cur.d + 1));
                    newD.remove(str);
                }
            }
            D = newD;
        }
        return -1;
    }

    public static boolean differsByOnePlace(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int numDiffs = 0;

        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) numDiffs++;
            if (numDiffs > 2) return false;
        }
        return numDiffs == 1;
    }

    public static void main(String[] args) {
        Random r = new Random();
        int len;
        if (args.length == 1) {
            len = Integer.parseInt(args[0]);
        } else {
            len = r.nextInt(5) + 1;
        }
        String s = randString(len);
        String t = randString(len);
        Set<String> D = new HashSet<>();
        D.add(s);
        D.add(t);
        int n = r.nextInt(1000000) + 1;
        for (int i = 0; i < n; ++i) {
            D.add(randString(len));
        }
        System.out.println(D);
        System.out.println(s + " " + t + " " + D.size());
        long startTime = System.currentTimeMillis();
        System.out.println(transformStringJenna(D, s, t));
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken for dictionary iteration = " + (endTime - startTime));

        startTime = System.currentTimeMillis();
        System.out.println(transformString(D, s, t));
        endTime = System.currentTimeMillis();
        System.out.println("Time taken for string transform  = " + (endTime - startTime));

//          dictionary size ~90000 performance
//          Time taken for dictionary iteration = 9167
//          Time taken for string transform  = 235
    }
}
