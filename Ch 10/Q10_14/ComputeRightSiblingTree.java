package Q10_14;

/**
 * Created by JennaKwon on 9/4/16.
 */
public class ComputeRightSiblingTree {

    public static class Node<T> {
        T data;
        Node left;
        Node right;
        Node sibling;

        public Node(T data) {
            this.data = data;
        }
    }

    public static void computeRightSiblings(Node root) {

        // recursively do this
        if (root != null && root.left != null) {
            root.left.sibling = root.right;
            if (root.sibling != null) {
                root.right.sibling = root.sibling.left;
            }
            computeRightSiblings(root.left);
            computeRightSiblings(root.right);
        }


    }

    public static void main(String[] args) {
        Node<Integer> root = new Node<>(3);
        root.left = new Node<>(2);
        root.right = new Node<>(5);
        computeRightSiblings(root);
        System.out.println(root.sibling == null);
        System.out.println(root.left.sibling == root.right);
        System.out.println(root.right.sibling == null);

        //     3
        //  2     5
        // 1 7   4 6

        Node<Integer> root2 = new Node<>(3);
        root2.left = new Node<>(2);
        root2.left.right = new Node<>(7);
        root2.left.left = new Node<>(1);
        root2.right = new Node<>(5);
        root2.right.left = new Node<>(4);
        root2.right.right = new Node<>(6);
        computeRightSiblings(root2);
        System.out.println(root2.sibling == null);
        System.out.println(root2.left.sibling == root2.right);
        System.out.println(root2.left.left.sibling == root2.left.right);
        System.out.println(root2.left.right.sibling == root2.right.left);
        System.out.println(root2.right.left.sibling == root2.right.right);

        //            8
        //     4            12
        //  2    6      10      14
        // 1 3  5  7  9   11  13  15

    }


}
