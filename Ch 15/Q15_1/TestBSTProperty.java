package Q15_1;

import library.BinaryTreeNodeSimple;

/**
 * Created by JennaKwon on 6/17/16.
 */
public class TestBSTProperty {

    public static class Interval {
        public int lowerBound = Integer.MIN_VALUE;
        public int upperBound = Integer.MAX_VALUE;

        public Interval(int lowerBound, int upperBound) {
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }

        public Interval() {}
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};

        BinaryTreeNodeSimple root = BinaryTreeNodeSimple.createMinimalBST(array);
        root.print();

        Interval rootInterval = new Interval();

        System.out.println(testBSTProperty(root, rootInterval)); // true
        root.left.left.data = 10;
        root.print();
        System.out.println(testBSTProperty(root, rootInterval)); // false
    }

    public static boolean testBSTProperty(BinaryTreeNodeSimple root, Interval interval) {

        if (root == null) {
            return true;
        }

        if (root.data < interval.lowerBound || root.data > interval.upperBound) {
            return false;
        }

        Interval leftInterval = new Interval(interval.lowerBound, root.data);
        Interval rightInterval = new Interval(root.data, interval.upperBound);

        return testBSTProperty(root.left, leftInterval) && testBSTProperty(root.right, rightInterval);

    }





}
