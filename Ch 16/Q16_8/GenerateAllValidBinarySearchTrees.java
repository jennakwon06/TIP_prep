package Q16_8;

import library.BinaryTreeNodeSimple;
import java.util.ArrayList;
import java.util.List;

/**
 * Problem
 * - Construct all binary search trees
 */

public class GenerateAllValidBinarySearchTrees {

    /**
     * Returns a number of valid binary search trees
     * You can also alternatively compute the catalan number
     */
    public static int numberOfBinarySearchTrees(int n) {
        int[] numTrees = new int[n + 1];

        // base cases 
        numTrees[0] = 1;
        numTrees[1] = 1;

        for (int numNode = 2; numNode <= n; numNode++) {
            for (int numLeftNode = 0; numLeftNode <= numNode - 1; numLeftNode++) {
                int numRightNode = numNode - numLeftNode - 1;
                numTrees[numNode] += numTrees[numLeftNode] * numTrees[numRightNode];
            }
        }

        return numTrees[n];
    }

    /**
     * Construct all valid binary search trees and return a list 
     * What code changes to "numberOfBinarySearchTrees" do you have to make?
     */
    public static List<BinaryTreeNodeSimple> constructBinarySearchTrees(int numNode) {
        ArrayList<BinaryTreeNodeSimple> result = new ArrayList<>();

        if (numNode == 0) {
            result.add(null);
        }

        for (int numLeftNode = 0; numLeftNode <= numNode - 1; numLeftNode++) {
            int numRightNode = numNode - numLeftNode - 1;
            List<BinaryTreeNodeSimple> leftSubtrees = constructBinarySearchTrees(numLeftNode);
            List<BinaryTreeNodeSimple> rightSubtrees = constructBinarySearchTrees(numRightNode);
            for (BinaryTreeNodeSimple left : leftSubtrees) {
                for (BinaryTreeNodeSimple right : rightSubtrees) {
                    // TODO how would you construct node with valid data as well? Something like below will not work.
//                    int nodeData;
//                    if (left == null && right == null) {
//                        nodeData = 0;
//                    } else if (left == null) {
//                        nodeData = right.data - 1;
//                    } else { // both are not null
//                        nodeData = left.data + 1;
//                    }
                    result.add(new BinaryTreeNodeSimple(0, left, right));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("COUNTING");
        System.out.println(numberOfBinarySearchTrees(3) + "\n\n");

        System.out.println("CONSTRUCTING");
        List<BinaryTreeNodeSimple> res = constructBinarySearchTrees(3);
        for (BinaryTreeNodeSimple n : res) {
            n.print();
        }
    }
}
