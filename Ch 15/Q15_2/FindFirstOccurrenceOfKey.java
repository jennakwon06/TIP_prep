package Q15_2;

import library.BinaryTreeNodeWithParent;

/**
 * Created by JennaKwon on 6/17/16.
 */
public class FindFirstOccurrenceOfKey {


    public static BinaryTreeNodeWithParent findFirstOccurrence(BinaryTreeNodeWithParent cur, int k) {
        BinaryTreeNodeWithParent result = new BinaryTreeNodeWithParent(-1);
        findFirstOccurrence(cur, k, result);
        return (result.data == -1 ? null : result);
    }


    public static BinaryTreeNodeWithParent findFirstOccurrence(BinaryTreeNodeWithParent cur, int k,
                                                               BinaryTreeNodeWithParent dummy) {
        if (cur == null) {return null;}

        if (cur.data == k) {
            dummy.data = cur.data;
            dummy.parent = cur.parent;
            return findFirstOccurrence(cur.left, k, dummy);
        } else if (cur.data > k) {
            return findFirstOccurrence(cur.left, k, dummy);
        } else {
            return findFirstOccurrence(cur.right, k, dummy);
        }
    }


    public static BinaryTreeNodeWithParent findFirstOccurrenceClean(BinaryTreeNodeWithParent cur, int k) {
        if (cur == null) {return null;}

        if (cur.data == k) {
            BinaryTreeNodeWithParent node = findFirstOccurrenceClean(cur.left, k);
            return node != null ? node : cur;
        }

        return findFirstOccurrenceClean(cur.data < k ? cur.right : cur.left, k);
    }

    public static BinaryTreeNodeWithParent findFirstOccurrenceIteratively(BinaryTreeNodeWithParent cur, int k) {
        BinaryTreeNodeWithParent firstSoFar = null;

        BinaryTreeNodeWithParent ptr = cur;

        while (ptr != null) {
            if (ptr.data == k) {
                firstSoFar = ptr;
                ptr = ptr.left;
            } else if (ptr.data > k) {
                ptr = ptr.left;
            } else {
                ptr = ptr.right;
            }
        }

        return firstSoFar;
    }



    public static void main(String[] args) {
        int[] array = {1, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 5, 6, 7};

        BinaryTreeNodeWithParent root = BinaryTreeNodeWithParent.createMinimalBST(array);
        root.print();

        System.out.println(findFirstOccurrence(root, 3).parent.data); // 2
        System.out.println(findFirstOccurrence(root, 6).parent.data); // 4

        System.out.println(findFirstOccurrenceClean(root, 3).parent.data); // 2
        System.out.println(findFirstOccurrenceClean(root, 6).parent.data); // 4

        System.out.println(findFirstOccurrenceIteratively(root, 3).parent.data); // 2
        System.out.println(findFirstOccurrenceIteratively(root, 6).parent.data); // 4
    }

}
