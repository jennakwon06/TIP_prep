package Q7_1;

/**
 * Created by JennaKwon on 6/2/16.
 *
 * Question 7.1
 * Convert string to int
 *
 */
public class StringIntegerConversion {


    /**
     * Brute force ascii to integer
     */
    public static int atoiRightToLeft(String str) {
        int digitCounter = 1;
        int result = 0;

        for (int i = str.length() - 1; i >= 0; i--) {
            if (i == 0 && str.charAt(i) == '-') {
                return result * - 1;
            }
            result += (int) str.charAt(i) - '0' * digitCounter;
            digitCounter *= 10;
        }
        return result;
    }

    /**
     * More elegant solution is to iterate the string from left to right. Why?
     * @TODO whitepsaces? overflow?
     */
    public static int atoiLeftToRight(String str) {
        int result = 0;
        boolean isNegative = false;

        str = str.trim();

        for (char c : str.toCharArray()) {
            if (c == '-') {
                isNegative = true;
            } else if (c == '+') {

            } else {
                result = (result * 10) + ((int) c - '0');

            }
        }

        return isNegative ? -result : result;
    }

    /**
     * Integer to ascii
     * @param num
     * @return
     */
    public static String itoa(int num) {
        StringBuilder result = new StringBuilder();
        boolean isNegative = num < 0;
        num = isNegative ? num * -1 : num;

        while (num != 0) {
            int remainder = num % 10;
            num /= 10;
            result.append(remainder);
        }

        return isNegative ? '-' + result.reverse().toString() : result.reverse().toString();
    }

    /**
     *
     * @param args
     */

    public static void main(String[] args) {
        String str = "-1234";
        String str1 = "1234";
        Integer integer = -1234;
        Integer integer1 = 1234;


        System.out.println(atoiRightToLeft(str));
        System.out.println(atoiRightToLeft(str1));

        System.out.println(atoiLeftToRight(str));
        System.out.println(atoiLeftToRight(str1));

        System.out.println(itoa(integer));
        System.out.println(itoa(integer1));
    }
}
