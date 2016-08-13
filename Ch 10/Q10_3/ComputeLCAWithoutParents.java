package Q10_3;

import library.BinaryTreeNodeSimple;

/**
 * Created by JennaKwon on 6/7/16
 *
 * Compute LCA of two nodes that do not have a parent pointer
 *
 * Soln 1) Top down w/ memory
 * - Use hashmap to record nodes and their parents
 * - Basically emulate having parent pointers
 * - Space usage O(n^2), runtime O(n)
 *
 * Soln 2) Top down w/o memory
 * - Recursively check every node if it contains two nodes by traversing its subtree
 * - if it does, then check its left / right (narrow down)
 * - Recurse down to the subtree then propagate result (true, false) back up
 * - Space usage O(n), runtime O(n^2)
 *
 * Soln 3) Bottom up
 * - propagate result back up
 */
public class ComputeLCAWithoutParents {


    /**
     * Bottom up approach; recurse to bottom and propagate up result
     */
    public static int computeLCA(BinaryTreeNodeSimple root, int a, int b) {
        BinaryTreeNodeSimple dummy = new BinaryTreeNodeSimple(-1);
        Result lca = computeLCAHelper(root, dummy, a, b);
        return dummy.data;
    }

    public static Result computeLCAHelper(BinaryTreeNodeSimple cur, BinaryTreeNodeSimple dummy, int a, int b) {
        Result rightResult = null;
        Result leftResult = null;

        if (cur.left == null && cur.right == null) {
            if (cur.data == a || cur.data == b) {
                return new Result(true, cur.data);
            }
            return new Result(false, -1);
        } else if (cur.left == null) {
            rightResult = computeLCAHelper(cur.right, dummy, a, b);
        } else if (cur.right == null) {
            leftResult = computeLCAHelper(cur.left, dummy, a, b);
        } else {
            leftResult = computeLCAHelper(cur.left, dummy, a, b);
            rightResult = computeLCAHelper(cur.right, dummy, a, b);
        }

        if (leftResult == null) {
            if ((cur.data == a && rightResult.data == b)
                    || (cur.data == b && rightResult.data == a)) {
                dummy.data = cur.data;
                return new Result(true, cur.data); //current is the common ancestor
            }
        }


        if (rightResult == null) {
            if ((cur.data == a && leftResult.data == b)
                    || (cur.data == b && leftResult.data == a)) {
                dummy.data = cur.data;
                return new Result(true, cur.data); //current is the common ancestor
            }
        }


        if ((leftResult.data == a && rightResult.data == b)
                || (leftResult.data == b && rightResult.data == a)) {
            dummy.data = cur.data;
            return new Result(true, cur.data); //current is the common ancestor
        }

        if ((leftResult.data == a && cur.data == b)
                || (leftResult.data == b && cur.data == a)) {
            dummy.data = cur.data;
            return new Result(true, cur.data); //current is the common ancestor
        }

        if ((rightResult.data == a && cur.data == b)
                || (rightResult.data == b && cur.data == a)) {
            dummy.data = cur.data;
            return new Result(true, cur.data); //current is the common ancestor
        }


        return new Result(false, -1);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        BinaryTreeNodeSimple bt = BinaryTreeNodeSimple.createMinimalBST(arr);

        // violating bst property
        bt.left.left.data = 14;
        bt.right.right.data = 2;

        bt.print();

        System.out.println(computeLCA(bt, 14, 3)); //print 14
        System.out.println(computeLCA(bt, 2, 9)); //print 12
    }
}
