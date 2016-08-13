package Q17_11;

import java.util.Arrays;
import java.util.List;

/**
 * Created by JennaKwon on 5/15/16.
 *
 * Question 17.11
 * Minimize messiness defined by the sum of squared blanks at the end of lines
 *
 * Solution 1 (not implemented)
 * - Greedy approach
 * - Does not produce optimal solution
 *
 * Solution 2 (prettyPrint)
 * - My incorrect attempt without DP
 *
 * Solution 3 (prettyPrintRecursive)
 * - From the book
 * - Recursively compute minimum messiness
 * - M(i) = Math.min( f(i,j) + M(j - 1) )
 *      f(i, j) = messiness of a single line consisting of words j to i
 *      M(i) = minimum messiness for first i words.
 * - Cache M
 * - "Optimum placement for the first i words is not always an optimum placement for the first i - 1 words"
 * - "But, if the optimum placement for the ith word consists of the last line having j, j + 1, ... i,
 *      then, in this placement, the first j - 1 words must be placed optimally".
 */
public class PrettyPrinting {


    /**
     * Solution in EPI
     */
    public static int minimumMessiness(List<String> words, int lineLength) {
        // minimumMessiness[i] is the minimum messiness when placing words[0 : i].
        int[] minimumMessiness = new int[words.size()];
        Arrays.fill(minimumMessiness, Integer.MAX_VALUE);
        int numRemainingBlanks = lineLength - words.get(0).length();
        minimumMessiness[0] = numRemainingBlanks * numRemainingBlanks;
        for (int i = 1; i < words.size(); ++i) {
            numRemainingBlanks = lineLength - words.get(i).length();
            minimumMessiness[i]
                    = minimumMessiness[i - 1] + numRemainingBlanks * numRemainingBlanks;
            // Try adding words.get(i - 1), words.get(i - 2), ...
            for (int j = i - 1; j >= 0; --j) {
                numRemainingBlanks -= (words.get(j).length() + 1);
                if (numRemainingBlanks < 0) {
                    // Not enough space to add more words.
                    break;
                }
                int firstJMessiness = j - 1 < 0 ? 0 : minimumMessiness[j - 1];
                int currentLineMessiness = numRemainingBlanks * numRemainingBlanks;
                minimumMessiness[i] = Math.min(minimumMessiness[i],
                        firstJMessiness + currentLineMessiness);
            }
        }
        System.out.println(Arrays.toString(minimumMessiness));
        return minimumMessiness[words.size() - 1];
    }

    /**
     * M(i) = Math.min(f(j, i) + M(j - 1)) for j < i
     */

    public static int prettyPrintRecursive(List<String> text, int maxCharacters) {
        int[] cache = new int[text.size()];
        prettyPrintRecursiveHelper(text, maxCharacters, maxCharacters, cache, text.size() - 1);
        System.out.println(Arrays.toString(cache));
        return cache[text.size() - 1];
    }


//  Calculate minimum messiness including text[index]
    public static int prettyPrintRecursiveHelper(List<String> text, int remainingCharacters, int maxCharacters, int[] cache, int index) {
        if (index < 0 || remainingCharacters < 0) {
            return 0;
        }

//        List<String> strList2 = Arrays.asList("aaa", "bbb", "c", "d", "ee", "ff", "gggggg");

        if (cache[index] == 0) {
            // information about word at index
            int curLineLength = text.get(index).length();
            remainingCharacters -= curLineLength;
            int min = (int) Math.pow(remainingCharacters, 2);

            for (int j = index - 1; j >= 0; j--) {
                int curLineMessiness = (int) Math.pow(remainingCharacters, 2);
                int prevLineMessiness = prettyPrintRecursiveHelper(text, remainingCharacters, maxCharacters, cache, j - 1);
                min = Math.min(curLineMessiness +  prevLineMessiness, min);
            }

            cache[index] = min;
        }

        return cache[index];
    }


    /**
     * Calculates messiness in a line from jth to ith word
     * @param text
     * @param maxCharacters
     * @return
     */
    public static int calculateMessiness(String[] text, int linelen, int maxCharacters) {
//        int totalChar = calculateTotalChars(text, j, i);
        return (int) Math.pow(maxCharacters - linelen, 2);
    }

    /**
     * Calculates total characters in the string array
     * @param text
     * @return
     */
    public static int calculateTotalChars(String[] text) {
        int totalChars = 0;
        for (String str : text) {
            totalChars += str.length();
            totalChars++;
        }
        totalChars--;
        return totalChars;
    }

    /**
     * Calculates total characters in a line from jth to ith word, inclusive
     * @param text
     * @param i
     * @param j
     * @return
     */
    public static int calculateTotalChars(String[] text, int i, int j) {
        int totalChars = 0;
        int cur;
        for (cur = j; cur <= i; cur++) {
            totalChars += text[cur].length();
            totalChars++;
        }
        totalChars--;
        return totalChars;
    }


    public static void main(String[] args) {
        String[] text = new String[]{
                "maps", "tan", "tree", "apple", "cans"
                , "help", "aped", "pree", "pret", "apes"
                , "flat", "trap", "fret", "trip", "trie"
                , "frat", "fril"};

        List<String> strList = Arrays.asList(text);
        List<String> strList2 = Arrays.asList("aaa", "bbb", "c", "d", "ee", "ff", "gggggg");

        System.out.println("Correct Solution");
        System.out.println(minimumMessiness(strList, 11));

        System.out.println("Correct Solution 2");
        System.out.println(minimumMessiness(strList2, 11));

        System.out.println("Pretty Print Recursive " + prettyPrintRecursive(strList, 11));
        System.out.println("Pretty Print Recursive 2" + prettyPrintRecursive(strList2, 11));

    }
}
