package Q9_1;

/**
 * Created by JennaKwon on 6/6/16.
 *
 * Implement stack that supports max operation
 *
 * Solution with ONE STACK
 * 1. Have each element remember the maximum below it
 *      - Time complexity : O(1) for pop / push
 *      - Space complexity : O(n)
 * 2. Store the gap between current element and max
 *
 * Solution with TWO STACK
 * 1. Keep track of a separate stack / store duplicates as well?
 */
public class Runner {

    public static void main(String[] args) {

        /**
         * Solution 1 - each element remembers the maximum below it
         */
        StackWithMax stack = new StackWithMax();
        int[] array = {2, 1, 3, 1};

        for (int value : array) {
            stack.push(value);
        }

        System.out.println("STACK 1");
        System.out.println(stack.max()); //3

        stack.push(9);
        System.out.println(stack.max()); //9

        System.out.println(stack.pop()); //9
        System.out.println(stack.max()); //3


        /**
         * Solution 2 - store the gap between max and current
         */
        StackWithMax2 stack2 = new StackWithMax2();

        for (int value : array) {
            stack2.push(value);
        }

        System.out.println("STACK 2");
        System.out.println(stack2.max()); // 3

        stack2.push(9);
        System.out.println(stack2.max()); //9

        System.out.println(stack2.pop()); //9
        System.out.println(stack2.max()); //3
        System.out.println(stack2);



        /**
         * Solution 3 -
         */
    }

}