package Q22_41;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by JennaKwon on 5/26/16.
 *
 * Question 22.41
 * What is the largest 2D subarray containing only 1s, and the largest square 2D subarray containing only 1s in a boolean 2D array?
 *
 * Largest subarray - Soln 1
 * - BFS from every cell
 * - never visit previously visited cell - start from top & left, and only queue neighbors that are to below & right
 *      this is because your current cell would've been absorbed by previous cells if it were part of the largest area
 *      so there is no point in looking backwards
 * - note to self - how you use visited matrix is different for every problem's requirement
 *
 * Largest square subarray - Soln 2
 * - http://www.geeksforgeeks.org/maximum-size-sub-matrix-with-all-1s-in-a-binary-matrix/
 *
 * Largest rectangle subarray
 * - http://tech-queries.blogspot.com/2011/09/find-largest-sub-matrix-with-all-1s-not.html
 *
 * Variants
 * CtCi 17.24: Find the submatrix with the largest possible sum
 * Ctci 17.23: Find the submatrix with the maximum subsquare whose borders are filled with black pixels
 */
public class Maximum2DSubarray {

    public static class Cell {
        public int r;
        public int c;
        public int data;

        public Cell() {
        }

        public Cell(int r, int c, int data) {
            this.r = r;
            this.c = c;
            this.data = data;
        }

        public String toString() {
            return "r = " + r + " c = " + c + " data = " + data;
        }
    }

    /**
     * Visit adjacent cell to find the larges reach from first starting point
     * Doesn't find the largest area in the entire matrix - finds the largest area reachable from starting point
     * How do we compute the absolute largest area in the matrix?
     * Reset start point ?
     * @param matrix
     * @return
     */
    public static int[][] maximumSubarray(Cell[][] matrix) {
        int r = matrix.length;
        int c = matrix[0].length;
        
        if (r == 0 || c == 0) {return null;}

        // both init to 0
        int[][] result = new int[r][c];

        LinkedList<Cell> queue = new LinkedList<>();
        Cell firstFilled = findFirstCell(matrix);
        if (firstFilled == null) {return result;} //there is no filled area

        //add first filled square
        queue.add(matrix[firstFilled.r][firstFilled.c]);
        result[firstFilled.r][firstFilled.c] = 1;

//        @Schedule one more loop & queue for resetting start points?

        while (!queue.isEmpty()) {
            Cell cur = queue.removeFirst();

            // enqueue if queue doesn't already contain it, if visited array doesn't already have it, and if it is 1

            //bottom
            if (cur.r + 1 < r) {
                Cell bottom = matrix[cur.r + 1][cur.c];
                if (bottom.data == 1 && result[cur.r + 1][cur.c] != 1 && !queue.contains(bottom)) {
                    queue.addLast(bottom);
                    result[cur.r + 1][cur.c] = 1;
                }
            }

            //left
            if (cur.c + 1 < c) {
                Cell left = matrix[cur.r][cur.c + 1];
                if (left.data == 1 && result[cur.r][cur.c + 1] != 1 && !queue.contains(left)) {
                    queue.addLast(left);
                    result[cur.r][cur.c + 1] = 1;
                }
            }

            //up
            if (cur.r - 1 > 0) {
                Cell up = matrix[cur.r - 1][cur.c];
                if (up.data == 1 && result[cur.r - 1][cur.c] != 1 && !queue.contains(up)) {
                    queue.addLast(up);
                    result[cur.r - 1][cur.c] = 1;
                }
            }

            //right
            if (cur.c - 1 > 0) {
                Cell right = matrix[cur.r][cur.c - 1];
                if (right.data == 1 && result[cur.r][cur.c - 1] != 1 && !queue.contains(right)) {
                    queue.addLast(right);
                    result[cur.r][cur.c - 1] = 1;
                }
            }

        }

        return result;
    }

    private static Cell findFirstCell(Cell[][] matrix) {
        Cell firstCell = null;
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                System.out.println("r = " + r + "c = " + c);
                if (matrix[r][c].data == 1) {
                    firstCell = matrix[r][c];
                    return firstCell;
                }
            }
        }
        return firstCell;
    }


    /**
     *
     */
    public static void maximumSquareSubarray() {

    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 0, 0, 0, 1, 1, 1}
                        , {1, 1, 0, 1, 1, 1, 1}
                        , {1, 1, 0, 0, 1, 1, 1}
                        , {1, 1, 1, 1, 1, 0, 0}};

        Cell[][] cellMatrix= new Cell[matrix.length][matrix[0].length];

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                cellMatrix[r][c] = new Cell(r, c, matrix[r][c]);
            }
        }

        System.out.println(Arrays.deepToString(maximumSubarray(cellMatrix)));
    }
}
