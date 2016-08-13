package Q10_5;

import library.BinaryTreeNodeSimple;

/**
 * Created by JennaKwon on 7/20/16.
 */
public class SumRootToLeafPaths { 
    // @include
    public static int sumRootToLeaf(BinaryTreeNodeSimple tree) {
        return sumRootToLeafHelper(tree, 0);
    }

    private static int sumRootToLeafHelper(BinaryTreeNodeSimple tree,
                                           int partialPathSum) {
        if (tree != null) {
            partialPathSum = partialPathSum * 2 + tree.data;

            if (tree.left == null && tree.right == null) {
                return partialPathSum;
            }

            // Non-leaf.
            return sumRootToLeafHelper(tree.left, partialPathSum)
                    + sumRootToLeafHelper(tree.right, partialPathSum);
        }
        return 0;
    }
    // @exclude

    public static void main(String[] args) {
        // 1
        // 1 0
        // 0 1 0
        BinaryTreeNodeSimple root = new BinaryTreeNodeSimple(1);
        int result = sumRootToLeaf(root);
        System.out.println(result);
        root.left = new BinaryTreeNodeSimple(1);
        result = sumRootToLeaf(root);
        System.out.println(result);
        root.left.left = new BinaryTreeNodeSimple(0);
        result = sumRootToLeaf(root);
        System.out.println(result);
        root.right = new BinaryTreeNodeSimple(0);
        result = sumRootToLeaf(root);
        System.out.println(result);
        root.right.left = new BinaryTreeNodeSimple(1);
        result = sumRootToLeaf(root);
        System.out.println(result);
        root.right.right = new BinaryTreeNodeSimple(0);
        result = sumRootToLeaf(root);
        System.out.println(result);
    }
}
