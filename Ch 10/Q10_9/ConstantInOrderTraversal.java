package Q10_9;

import library.BinaryTreeNodeWithParent;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by JennaKwon on 5/13/16.
 *
 * Question 10.9
 * Directly implementing traversal with recursion has O(h) complexity.
 * Recursion can be removed with an explicit stack. But the space complexity remains O(h)
 * Can you write a non-recursive inorder traversal without any space? Assume parent fields.
 *
 *
 * Approach
 * - Maintain 3 pointers and think about cases carefully
 * - Node knows where to go if it knows whether it is a left or right subtree of its parent
 * - Use nodes with parent fields - if this is not possible, make a hashmap of nodes and their respective parents
 *
 * Runtime
 * - O(n) traversal
 * - O(1) additional space (extra field in BinaryTreeNodeWithParentWithParent but no recursion)
 *
 * Lessons learned
 * - Kept making the mistake of viewing the problem recursively & solving subproblems
 * - To avoid recursion completely, need an iterative approach
 * - Maintain 3 pointers for tracking previous, current, and where to go next.
 */

public class ConstantInOrderTraversal {

    public static List<Integer> inOrderTraverse(BinaryTreeNodeWithParent node) {
        if (node == null) {
            return null;
        }

        List<Integer> result = new LinkedList<Integer>();

        BinaryTreeNodeWithParent prev = null;
        BinaryTreeNodeWithParent cur = node;
        BinaryTreeNodeWithParent next = null; //set every iteration

        while (cur != null) {
            if (cur.parent == prev) { //going down
                if (cur.left != null) { //left subtree is prioritized (in-order)
                    next = cur.left;
                } else {
                    next = (cur.right != null ? cur.right : cur.parent); //process current and go to right or up
                    result.add(cur.data);
                }
            } else if (prev.parent == cur) { //going up
                if (cur.left == prev) { //current is left subtree of parent
                    result.add(cur.data);
                    next = (cur.right != null ? cur.right : cur.parent); //explore right or go up
                } else if (cur.right == prev) { //current is right subtree of parent
                    next = cur.parent; //go up regardless
                }
            }
            prev = cur;
            cur = next;
        }
        return result;
    }

    public static void main(String[] arg) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, -1, 4};
        BinaryTreeNodeWithParent root = BinaryTreeNodeWithParent.createMinimalBST(array);
        System.out.println(inOrderTraverse(root)); //should print unmodified list
    }
}
