package library;

/**
 * Created by JennaKwon on 5/17/16.
 */
public class BinaryTreeNodeSimple extends TreeNode {

    public BinaryTreeNodeSimple left;
    public BinaryTreeNodeSimple right;
    public int height = 0;
    private int size = 0;

    public BinaryTreeNodeSimple(int d) {
        super(d);
        size = 1;
    }

    public BinaryTreeNodeSimple(int d, BinaryTreeNodeSimple left, BinaryTreeNodeSimple right) {
        super(d);
        this.left = left;
        this.right = right;
        size = 1;
    }

    public int size() {return size; }

    public int height() {
        int leftHeight = left != null ? left.height() : 0;
        int rightHeight = right != null ? right.height() : 0;
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public void setSize(int size) {
        this.size = size;
    } //for cloning

    public void insertInOrder(int d) {
        if (d <= data) {
            if (left == null) {
                setLeftChild(new BinaryTreeNodeSimple(d));
            } else {
                left.insertInOrder(d);
            }
        } else {
            if (right == null) {
                setRightChild(new BinaryTreeNodeSimple(d));
            } else {
                right.insertInOrder(d);
            }
        }
        size++;
    }

    public boolean isBST() {
        if (left != null) {
            if (data < left.data || !left.isBST()) {
                return false;
            }
        }

        if (right != null) {
            if (data >= right.data || !right.isBST()) {
                return false;
            }
        }

        return true;
    }

    private static BinaryTreeNodeSimple createMinimalBST(int arr[], int start, int end){
        if (end < start) {
            return null;
        }
        int mid = (start + end) / 2;
        BinaryTreeNodeSimple n = new BinaryTreeNodeSimple(arr[mid]);
        n.setLeftChild(createMinimalBST(arr, start, mid - 1));
        n.setRightChild(createMinimalBST(arr, mid + 1, end));
        return n;
    }

    public static BinaryTreeNodeSimple createMinimalBST(int array[]) {
        return createMinimalBST(array, 0, array.length - 1);
    }


    public BinaryTreeNodeSimple find(int d) {
        if (d == data) {
            return this;
        } else if (d <= data) {
            return left != null ? left.find(d) : null;
        } else if (d > data) {
            return right != null ? right.find(d) : null;
        }
        return null;
    }

    public void setLeftChild(BinaryTreeNodeSimple left) {
        this.left = left;
    }

    public void setRightChild(BinaryTreeNodeSimple right) {
        this.right = right;
    }

    public BinaryTreeNodeSimple clone() {
        BinaryTreeNodeSimple cloned = new BinaryTreeNodeSimple(this.data);
        if (this.left != null) {
            cloned.setLeftChild(this.left.clone());
        }
        if (this.right != null) {
            cloned.setRightChild(this.right.clone());
        }
        cloned.setSize(this.size);

        return cloned;
    }

    public void print() {BTreePrinter.printNode(this);}

}
