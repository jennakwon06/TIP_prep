package Q7_5;

/**
 * Checking palindrome is quite easy with two indices.
 * Thought it is notable to write one that considers only letter or digits
 */
public class PalindromeChecker {


    public static boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            while (!Character.isLetterOrDigit(s.charAt(i)) && i < j) { i++; }
            while (!Character.isLetterOrDigit(s.charAt(j)) && j < i) { j++; }
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt((j)))) { return false; }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("This is not a palindrome"));
        System.out.println(isPalindrome("A man, a plan, a canal, Panama.")); //true

    }

}
