package Q10_13;

import library.BinaryTreeNodeSimple;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by JennaKwon on 5/19/16.
 *
 * Question 10.13
 * Compute the exterior of a binary tree
 *
 * Approach
 * - need all leftmost node and rightmost node
 * - Break it down by root.left & root.right
 * - Specifically, root.left & root.right each print all boundary and its leaves
 * - Concatenate them together
 */
public class ExteriorOfATree {

    public static List<Integer> computeExteriorOfATree(BinaryTreeNodeSimple root) {

        LinkedList<Integer> exteriorList = new LinkedList<>();

        LinkedList<Integer> leftList = computeLeftBoundaryAndLeaves(root.left, true);
        LinkedList<Integer> rightList = computeRightBoundaryAndLeaves(root.right, true);
        exteriorList.addAll(leftList);
        exteriorList.add(root.data);
        exteriorList.addAll(rightList);

        return exteriorList;
    }

    // compute left to right order
    public static LinkedList<Integer> computeLeftBoundaryAndLeaves(
                                    BinaryTreeNodeSimple root, boolean isLeftBoundary) {
        LinkedList<Integer> exteriorList = new LinkedList<>();

        if (root != null) {
            if (isLeftBoundary && isLeaf(root)) {
                exteriorList.add(root.data);

            }
            // left first to get boundaries
            exteriorList.addAll(computeLeftBoundaryAndLeaves(root.left, isLeftBoundary));
            // get leaves left to right
            exteriorList.addAll(computeLeftBoundaryAndLeaves(root.right, isLeftBoundary && root.left == null));
        }

        return exteriorList;
    }

    // compute left to right order, pushing leaves then boundaries
    public static LinkedList<Integer> computeRightBoundaryAndLeaves(
                                    BinaryTreeNodeSimple root, boolean isRightBoundary) {

        LinkedList<Integer> exteriorList = new LinkedList<>();

        if (root != null) {
            // left first to get leaves
            exteriorList.addAll(computeLeftBoundaryAndLeaves(root.left, isRightBoundary && root.right == null));
            // get boundaries ons the right side
            exteriorList.addAll(computeLeftBoundaryAndLeaves(root.right, isRightBoundary));

            if (isRightBoundary && isLeaf(root)) {
                exteriorList.add(root.data);

            }
        }

        return exteriorList;
    }

    public static boolean isLeaf(BinaryTreeNodeSimple node) {
        return node.left == null && node.right == null;
    }

    public static void main(String[] args) {

        String s = "";

        HashSet<Character> longestSubstringChars = new HashSet<>();
        HashSet<Character> currentChars = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            Character curChar = s.charAt(i);
            if (!currentChars.contains(curChar)) {
                currentChars.add(curChar);
            } else {
                if (currentChars.size() > longestSubstringChars.size()) {
                    longestSubstringChars = currentChars;
                }
                currentChars = new HashSet<>();
                currentChars.add(curChar);

            }
        }

    }

}

