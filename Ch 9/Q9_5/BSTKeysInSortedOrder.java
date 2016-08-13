package Q9_5;

import library.BinaryTreeNodeSimple;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by JennaKwon on 6/6/16.
 *
 * Given tree and key, return the sorted list of the key and its descendents, WO recursion.
 *
 * Similar to Ch10's "Q10_9.ConstantInOrderTraversal", but we do not have parent
 * The additional O(n) for parent fields can be replaced with a stack
 */
public class BSTKeysInSortedOrder {

    /**
     * Mimic recursion with stack
     * @param root
     * @param startKey
     * @return
     */
    public static ArrayList<Integer> BSTKeysInSortedOrder(BinaryTreeNodeSimple root, int startKey) {
        ArrayList<Integer> list = new ArrayList<>();
        BinaryTreeNodeSimple cur = root;
        Stack<BinaryTreeNodeSimple> stack = new Stack<>();

        stack.add(root);

        while (!stack.isEmpty()) {

            // visit left
            if (stack.peek() == cur) { //going down
                while (cur.left != null) {
                    stack.add(cur.left);
                    cur = cur.left;
                }
            }

            // visit current
            BinaryTreeNodeSimple elem = stack.pop();
            list.add(elem.data); //

            // visit right
            if (elem.right != null) {
                stack.add(elem.right);
                cur = elem.right;
            }
        }

        return list;
    }


    /**
     * solution in the book - same runtime & space complexity
     */
    public static ArrayList<Integer> BSTKeysInSortedOrder2(BinaryTreeNodeSimple root, int startKey) {
        ArrayList<Integer> list = new ArrayList<>();
        BinaryTreeNodeSimple cur = root;
        Stack<BinaryTreeNodeSimple> stack = new Stack<>();

        while (!stack.isEmpty() || cur!= null) {

            // visit left
            if (cur != null) {
                stack.add(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                list.add(cur.data);
                cur = cur.right;
            }
        }

        return list;
    }


    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 14, 15, 16, 17, 19, 20};

        // create unbalanced tree
        BinaryTreeNodeSimple root = BinaryTreeNodeSimple.createMinimalBST(array);
        root.left.left.left.left = new BinaryTreeNodeSimple(0);
        root.left.left.left.left.left = new BinaryTreeNodeSimple(-1);
        root.left.left.left.left.left.left = new BinaryTreeNodeSimple(-2);
        root.left.right = null;
        root.right.left.left = null;

        root.print();

        System.out.println(BSTKeysInSortedOrder(root, 5));
        System.out.println(BSTKeysInSortedOrder2(root, 5));

    }
}
