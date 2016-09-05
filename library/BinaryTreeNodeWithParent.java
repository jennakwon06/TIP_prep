package library;

/* One node of a binary tree. The data element stored is a single
 * character.
 */
public class BinaryTreeNodeWithParent extends TreeNode {
    public BinaryTreeNodeWithParent left;
    public BinaryTreeNodeWithParent right;
    public BinaryTreeNodeWithParent parent;
    private int size = 0;

    public BinaryTreeNodeWithParent(int d) {
        super(d);
        size = 1;
    }

    public void setLeftChild(BinaryTreeNodeWithParent left) {
        this.left = left;
        if (left != null) {
            left.parent = this;
        }
    }

    public void setRightChild(BinaryTreeNodeWithParent right) {
        this.right = right;
        if (right != null) {
            right.parent = this;
        }
    }

    public void insertInOrder(int d) {
        if (d <= data) {
            if (left == null) {
                setLeftChild(new BinaryTreeNodeWithParent(d));
            } else {
                left.insertInOrder(d);
            }
        } else {
            if (right == null) {
                setRightChild(new BinaryTreeNodeWithParent(d));
            } else {
                right.insertInOrder(d);
            }
        }
        size++;
    }


    public int height() {
        int leftHeight = left != null ? left.height() : 0;
        int rightHeight = right != null ? right.height() : 0;
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public int size() {
        return size;
    }

    private static BinaryTreeNodeWithParent createMinimalBST(int arr[], int start, int end){
        if (end < start) {
            return null;
        }
        int mid = (start + end) / 2;
        BinaryTreeNodeWithParent n = new BinaryTreeNodeWithParent(arr[mid]);
        n.setLeftChild(createMinimalBST(arr, start, mid - 1));
        n.setRightChild(createMinimalBST(arr, mid + 1, end));
        return n;
    }

    public static BinaryTreeNodeWithParent createMinimalBST(int array[]) {
        return createMinimalBST(array, 0, array.length - 1);
    }

    public void setSize(int size) {
        this.size = size;
    }


    public BinaryTreeNodeWithParent clone() {
        BinaryTreeNodeWithParent cloned = new BinaryTreeNodeWithParent(this.data);
        if (this.left != null) {
            cloned.setLeftChild(this.left.clone());
        }
        if (this.right != null) {
            cloned.setRightChild(this.right.clone());
        }
        cloned.setSize(this.size);

        return cloned;
    }

    public void print() {BTreePrinter.printNode2(this);}

}
