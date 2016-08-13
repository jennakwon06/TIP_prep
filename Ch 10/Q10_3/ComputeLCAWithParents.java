package Q10_3;

import library.BinaryTreeNodeWithParent;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by JennaKwon on 6/7/16.
 */
public class ComputeLCAWithParents {


    public static int computeLCA(BinaryTreeNodeWithParent root, int a, int b) {
        // find two nodes
        BinaryTreeNodeWithParent first = find(root, a);
        BinaryTreeNodeWithParent second = find(root, b);

        System.out.println(first.data);
        System.out.println(second.data);

        LinkedList<BinaryTreeNodeWithParent> firstArr = new LinkedList<>();
        BinaryTreeNodeWithParent cur = first;
        while (cur.parent != null) {
            firstArr.add(cur.parent);
            cur = cur.parent;
        }

        LinkedList<BinaryTreeNodeWithParent> secondArr = new LinkedList<>();
        BinaryTreeNodeWithParent cur2 = second;
        while (cur2.parent != null) {
            secondArr.add(cur2.parent);
            cur2 = cur2.parent;
        }

        System.out.println(Arrays.toString(firstArr.toArray()));
        System.out.println(Arrays.toString(secondArr.toArray()));
        return 0;
    }

    public static BinaryTreeNodeWithParent find(BinaryTreeNodeWithParent root, int a) {
        if (root == null) {
            return null;
        }
        if (root.data == a) {
            return root;
        }

        BinaryTreeNodeWithParent left = find(root.left, a);
        BinaryTreeNodeWithParent right = find(root.right, a);

        if (left != null) {return left;}
        if (right != null) {return right;}

        return null;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        BinaryTreeNodeWithParent bt = BinaryTreeNodeWithParent.createMinimalBST(arr);

        bt.print();


        // violating bst property
        bt.left.left.data = 14;
        bt.right.right.data = 2;

        System.out.println(computeLCA(bt, 14, 3)); //print 14
    }

}
