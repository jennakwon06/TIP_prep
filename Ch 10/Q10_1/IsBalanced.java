package Q10_1;

import library.BinaryTreeNodeSimple;

import java.util.HashMap;

/**
 * Created by JennaKwon on 5/14/16.
 *
 * Question 10.1
 * Check if a tree is balanced - balanced if the height difference between left and right subtree is at most one.
 * Height = max(left.height, right.height)
 *
 * Solution 1 (isBalancedBruteForce / isBalanced)
 * - Compute each node's height, and the height difference of left and right children at every node
 * - O(n^2) runtime brute-force, but O(n) with caching
 *
 * Solution 2 (isBalancedCache)
 * - cache result
 *
 * Solution 3 (isBalanced)
 * - Most optimal
 * Check height as recursing down
 *
 */


public class IsBalanced {

    public static int getHeightBruteForce(BinaryTreeNodeSimple root, HashMap<Integer, Integer> accum) {
        if (root == null) {
            return -1;
        }

        return Math.max(getHeightBruteForce(root.left, accum), getHeightBruteForce(root.right, accum)) + 1;
    }

    public static boolean isBalancedBruteForce(BinaryTreeNodeSimple root) {
        HashMap<Integer, Integer> heights = new HashMap<>();

        if (root == null) {return true;}

        int heightDiff = getHeightBruteForce(root.left, heights) - getHeightBruteForce(root.right, heights);

        if (Math.abs(heightDiff) > 1) {
            return false;
        } else {
            return isBalancedBruteForce(root.left) && isBalancedBruteForce(root.right);
        }
    }

    /**
     * My solution - cache
     */
    public static int getHeight(BinaryTreeNodeSimple root, HashMap<Integer, Integer> accum) {
        if (root == null) {
            return 0;
        }

        if (accum.containsKey(root.data)) {
            return accum.get(root.data);
        }

        int leftHeight = getHeight(root.left, accum);
        if (leftHeight == -1) {return -1;}

        int rightHeight = getHeight(root.right, accum);
        if (rightHeight == -1) {return -1;}

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        int height = Math.max(leftHeight, rightHeight) + 1;
        accum.put(root.data, height);
        return height;

    }

    public static boolean isBalancedCache(BinaryTreeNodeSimple root) {
        HashMap<Integer, Integer> heights = new HashMap<>();

        return getHeight(root, heights) != -1;
    }

    /**
     * Solution in CtCI
     */
    public static int checkHeightImproved(BinaryTreeNodeSimple root) {
        if (root == null) {
            return -1;
        }
        int leftHeight = checkHeightImproved(root.left);
        if (leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE; // Propagate error up

        int rightHeight = checkHeightImproved(root.right);
        if (rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE; // Propagate error up

        int heightDiff = leftHeight - rightHeight;
        if (Math.abs(heightDiff) > 1) {
            return Integer.MIN_VALUE; // Found error -> pass it back
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public static boolean isBalancedImproved(BinaryTreeNodeSimple root) {
        return checkHeightImproved(root) != Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7};
        long startTime, endTime;
        BinaryTreeNodeSimple balanced = BinaryTreeNodeSimple.createMinimalBST(arr);
        BinaryTreeNodeSimple unbalanced = balanced.clone();
        unbalanced.find(7).insertInOrder(8);
        unbalanced.find(7).insertInOrder(9);

        balanced.print(); // print tree
        unbalanced.print();

        startTime = System.nanoTime();
        System.out.println(isBalancedBruteForce(balanced)); //true
        endTime = System.nanoTime();
        System.out.println("Total execution time: " + (endTime - startTime) );

        startTime = System.nanoTime();
        System.out.println(isBalancedBruteForce(unbalanced)); //false
        endTime = System.nanoTime();
        System.out.println("Total execution time: " + (endTime - startTime) );

        startTime = System.nanoTime();
        System.out.println(isBalancedCache(balanced)); //true
        endTime = System.nanoTime();
        System.out.println("Total execution time: " + (endTime - startTime) );

        startTime = System.nanoTime();
        System.out.println(isBalancedCache(unbalanced)); //false
        endTime = System.nanoTime();
        System.out.println("Total execution time: " + (endTime - startTime) );

        startTime = System.nanoTime();
        System.out.println(isBalancedImproved(balanced)); //true
        endTime = System.nanoTime();
        System.out.println("Total execution time: " + (endTime - startTime) );

        startTime = System.nanoTime();
        System.out.println(isBalancedImproved(unbalanced)); //false
        endTime = System.nanoTime();
        System.out.println("Total execution time: " + (endTime - startTime) );


    }
}
