package Q16_7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q16_7 {
    // @include
    public static List<List<String>> palindromePartitioning(String input) {
        List<List<String>> result = new ArrayList<>();
        directedPalindromePartitioning(input, 0, new ArrayList<String>(), result);
        return result;
    }

    private static void directedPalindromePartitioning(String input, int offset, List<String> partialPartition, List<List<String>> result) {
        if (offset == input.length()) {
            result.add(new ArrayList<>(partialPartition));
            return;
        }

        for (int i = offset + 1; i <= input.length(); ++i) {
            System.out.print("beginning of for loop, offset = " + offset + ", i = " + i + "\n");
            String prefix = input.substring(offset, i);
            System.out.print("is palindrome " + isPalindrome(prefix) + "\n");
            if (isPalindrome(prefix)) {
                partialPartition.add(prefix);
                System.out.print("before call offset = " + offset + " i = " + i + "\n");
                directedPalindromePartitioning(input, i, partialPartition, result);
                partialPartition.remove(partialPartition.size() - 1); //
                System.out.print("after call offset = " + offset + " i = " + i + "\n");

            }
        }
    }

    private static boolean isPalindrome(String prefix) {
        for (int i = 0, j = prefix.length() - 1; i < j; ++i, --j) {
            if (prefix.charAt(i) != prefix.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<List<String>> result = palindromePartitioning("abbbac");
        List<List<String>> golden = Arrays.asList(
                Arrays.asList("a", "b", "b", "b", "a", "c"),
                Arrays.asList("a", "b", "bb", "a", "c"),
                Arrays.asList("a", "bb", "b", "a", "c"),
                Arrays.asList("a", "bbb", "a", "c"), Arrays.asList("abbba", "c"));
        System.out.println(result);
        assert(result.equals(golden));    }
}