package Q9_2;

import java.util.Stack;

/**
 * Created by JennaKwon on 6/6/16
 *
 * Question
 * 1. Evaluate POLISH NOTATION (variant of problem) - INFIX
 * 2. Evaluate RPN - POSTFIX
 */
public class EvaluatePN {


    /**
     * Two stacks; ops tack and int stack
     * Left to right
     */
    public static int evaluatePNRightToLeft(String str) {
        String[] splitted = str.split(",");
        Stack<String> opStack = new Stack<>();
        Stack<Integer> intStack = new Stack<>();

        for (int i = 0; i < splitted.length; i++) {
            if ("*/+-".contains(splitted[i])) { //operator
                opStack.push(splitted[i]);
            } else {
                if (intStack.isEmpty() && splitted[i + 1] == null) {
                    return Integer.MIN_VALUE;
                }

                // Case 1: two vals from array
                // Case 2: one val from intstack then one val from array
                // Case 3: two vals from instack
                // Impossible - one val from array and then one val from stack.
                int firstInt = intStack.isEmpty() ? Integer.parseInt(splitted[i++]) : intStack.pop();
                int secondInt = intStack.isEmpty() ? Integer.parseInt(splitted[i]) : intStack.pop();
                String op = opStack.pop();

                switch (op) {
                    case ("*"):
                        intStack.push(firstInt * secondInt);
                        break;
                    case ("/"):
                        intStack.push(firstInt / secondInt);
                        break;
                    case ("+"):
                        intStack.push(firstInt + secondInt);
                        break;
                    case ("-"):
                        intStack.push(firstInt - secondInt);
                        break;
                }
            }
        }

        return intStack.pop();
    }


    /**
     * @TODO USE ONE STACK AND SCAN FROM RIGHT TO LEFT
     */
    public static int evaluateRPOLeftToRight(String str) {
        return 0;
    }

    public static void main(String[] args) {
        String expr1 = "*,-,5,6,7"; // (5 - 6) * 7
        String expr2 = "*,-,+,10,5,6,7"; // ( (10 + 5) - 6) * 7
        String expr3 = "-,*,/,15,-,7,+,1,1,3,+,2,+,1,1"; // (5 − 6) × 7

        System.out.println(evaluatePNRightToLeft(expr1)); // -7
        System.out.println(evaluatePNRightToLeft(expr2)); // 63
        System.out.println(evaluatePNRightToLeft(expr3)); // 5

    }
}
