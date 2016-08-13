package Q7_7;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by JennaKwon on 5/18/16.
 *
 * Question 7.7
 * Given phone number as input (string of digits), return all possible character sequences that correspond to the phone number.
 * Example: "2276696" = "ACRONYM"
 *
 * Approach 1 (computeMnemonicsIterative)
 * Iterate through each digit in the input string
 * For each digit, look up its mapping
 * Iteratively calculate all possible sequences for ith digit from i-1th digit
 *  Complexity
 *  Best case: O(4^n), n = number of digits, 4 = max characters for each digit.
 *  4^n recursive calls, and each string has length and base case entails making a copy of the string.
 *
 * Approach 2 (computeMnemonicsRecursive)
 *
 */
public class ComputeAllMnemonics {

    public static void initializeMap(HashMap<String, String>  map) {
        map.put("0", "");
        map.put("1", "");
        map.put("2", "ABC");
        map.put("3", "DEF");
        map.put("4", "GHI");
        map.put("5", "JKL");
        map.put("6", "MNO");
        map.put("7", "PQRS");
        map.put("8", "TUV");
        map.put("9", "WXYZ");
    }

    public static List<String> computeMnemonicsIterative(String number) {
        HashMap<String, String> map = new HashMap<>();
        initializeMap(map);

        LinkedList<String> mnemonics = new LinkedList<>();
        mnemonics.add("");

        for (char digit : number.toCharArray()) {
            LinkedList<String> newMnemonics = new LinkedList<>();
            for (char letter : map.get(String.valueOf(digit)).toCharArray()) {
                for (String str : mnemonics) {
                    newMnemonics.add(str + letter);
                }
            }
            mnemonics = newMnemonics;
        }

        return mnemonics;
    }

    public static List<String> computeMnemonicsRecursive(String number) {
        HashMap<String, String> map = new HashMap<>();
        initializeMap(map);
        LinkedList<String> mnemonics = new LinkedList<>();
        mnemonics.add("");
        return recursiveHelper(number, mnemonics, number.length() - 1, map);
    }

    public static List<String> recursiveHelper(String number, LinkedList<String> accum, int index, HashMap<String, String> map) {
        if (index < 0) {
            return accum;
        }

        String curDigit = String.valueOf(number.charAt(index));

        LinkedList<String> newMnemonics = new LinkedList<>();
        for (char letter : map.get(String.valueOf(curDigit)).toCharArray()) {
            for (String elem : accum) {
                newMnemonics.add(letter + elem);
            }
        }
        accum = newMnemonics;

        return recursiveHelper(number, accum, --index, map);
    }

    public static void main(String[] args) {
        System.out.println(computeMnemonicsIterative("29"));
        System.out.println(computeMnemonicsRecursive("29"));
    }
}
