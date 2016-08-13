package Q22_7;

import java.util.Arrays;

/**
 * Created by JennaKwon on 5/30/16.
 *
 * Question 22.7
 * Identify positions attacked by rooks in place
 * Same problem as CtCi 1.8
 * Can you do this in O(1) space?
 *
 * Solution 1)
 */
public class Rooks {

    /**
     * O(1) space; use matrix to record the information
     *
     */
    public static void swappedByRooks(int[][] matrix) {
        boolean shouldClearFirstRow = false;
        boolean shouldClearFirstCol = false;

        for (int r = 0; r < matrix.length; r++) {
            if (matrix[r][0] == 0) {
                shouldClearFirstCol = true;
                break;
            }
        }

        for (int c = 0; c < matrix[0].length; c++) {
            if (matrix[0][c] == 0) {
                shouldClearFirstRow = true;
                break;
            }
        }

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                if (matrix[r][c] == 0) {
                    matrix[r][0] = 0; //mark in first col
                    matrix[0][c] = 0; //mark in first row
                }
            }
        }

        for (int r = 1; r < matrix.length; r++) {
            if (matrix[r][0] == 0) {
                clearRow(r, matrix);
            }
        }

        for (int c = 1; c < matrix.length; c++) {
            if (matrix[0][c] == 0) {
                clearCol(c, matrix);
            }
        }

        if (shouldClearFirstCol) {
            clearCol(0, matrix);
        }

        if (shouldClearFirstRow) {
            clearRow(0, matrix);
        }
    }



    private static void clearRow(int r, int[][] matrix) {
        for (int c = 0; c < matrix[0].length; c++) {
            matrix[r][c] = 0;
        }
    }

    private static void clearCol(int c, int[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            matrix[r][c] = 0;
        }
    }



    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 1, 1, 1, 0, 1}
                        , {1, 0, 1, 1, 1, 1, 1}
                        , {1, 1, 1, 1, 1, 1, 1}
                        , {1, 1, 1, 1, 1, 1, 1}
                        , {1, 1, 1, 0, 1, 1, 1}
                        , {1, 1, 1, 1, 1, 1, 1}
                        , {0, 1, 1, 1, 1, 1, 1}};

        swappedByRooks(matrix);

        System.out.println(Arrays.deepToString(matrix));
        /** should be **/
//        int[][] matrix = {{0, 0, 0, 0, 0, 0, 0}
//                        , {0, 0, 0, 0, 0, 0, 0}
//                        , {0, 0, 1, 0, 1, 0, 1}
//                        , {0, 0, 1, 0, 1, 0, 1}
//                        , {0, 0, 0, 0, 0, 0, 0}
//                        , {0, 0, 1, 0, 1, 0, 1}
//                        , {0, 0, 0, 0, 0, 0, 0}};

    }
}
