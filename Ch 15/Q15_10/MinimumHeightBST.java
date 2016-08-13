package Q15_10;

import library.BinaryTreeNodeSimple;
import library.TreeNode;

/**
 * Created by JennaKwon on 5/15/16.
 */
public class MinimumHeightBST {

    public static TreeNode createTree(int[] arr) {
        return helper(arr, 0, arr.length - 1);
    }

    public static BinaryTreeNodeSimple helper(int[] arr, int lo, int hi) {
        if (lo > hi) {
            return null;
        }

        int medianIndex = (lo + hi) / 2;
        BinaryTreeNodeSimple pivot = new BinaryTreeNodeSimple(arr[medianIndex]);

        pivot.left = helper(arr, lo, medianIndex - 1);
        pivot.right = helper(arr, medianIndex + 1, hi);

        return pivot;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};

        BinaryTreeNodeSimple root = (BinaryTreeNodeSimple) createTree(array);

        root.print();
    }
}
