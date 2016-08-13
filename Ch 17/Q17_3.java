import library.AssortedMethods;

import java.util.Arrays;

/**
 * Created by JennaKwon on 7/31/16.
 *
 * Question : Count the number of ways to traverse a 2D array WITH obstacles
 *
 * Solution 1) DP top down
 * Solution 2) DP bottom up
 * Solution 3) Backtracking
 *
 * Lesson here: Top down is harder to code than bottom up here
 * Backtracking is one way, but it is much slower
 */
public class Q17_3 {

    public static class Count {
        int count = 0;
        public Count() {
            this.count = 0;
        }
    }

    public static int numAllPaths(int[][] matrix) {
        Count res = new Count();
        numAllPaths(matrix, 0, 0, res);
        return res.count;
    }

    public static void numAllPaths(int[][] matrix, int r, int c, Count res) {
        if (r >= matrix.length || c >= matrix[0].length || matrix[r][c] == 0) {
            return;
        }

        if (r == matrix.length - 1 && c == matrix[0].length - 1) {
            res.count++;
            return;
        } else {
            numAllPaths(matrix, r + 1, c, res);
            numAllPaths(matrix, r, c + 1, res);
        }
    }

    public static int numAllPathsDP(int[][] matrix) {
        int[][] memo = new int[matrix.length][matrix[0].length];


        // fill the first col with 1 until there's a 0
        for (int r = 0; r < memo.length; r++) {
            if (matrix[0][r] == 1) {
                memo[0][r] = 1;
            } else {
                break;
            }
        }

        // fill the first row with 1 until there's a 0
        for (int c = 0; c < memo[0].length; c++) {
            if (matrix[c][0] == 1) {
                memo[c][0] = 1;
            } else {
                break;
            }
        }

        for (int r = 1; r < memo.length; r++) {
            for (int c = 1; c < memo[0].length; c++) {
                if (matrix[r][c] != 0) {
                    memo[r][c] = memo[r - 1][c] + memo[r][c - 1];
                }
            }
        }

        System.out.println(Arrays.deepToString(memo));

        return memo[memo.length - 1][memo[0].length - 1];

    }

    public static int numAllPathsDPTopDown(int[][] matrix) {
        int[][] memo = new int[matrix.length][matrix[0].length];

        for (int[] col : memo) {
            Arrays.fill(col, -1);
        }

        // fill the first col with 1 until there's a 0
        boolean setToZero = false;
        for (int r = 0; r < memo.length; r++) {
            if (setToZero || matrix[0][r] == 0) {
                setToZero = true;
                memo[0][r] = 0;
            } else {
                memo[0][r] = 1;
            }
        }

        // fill the first row with 1 until there's a 0
        setToZero = false;
        for (int c = 0; c < memo[0].length; c++) {
            if (setToZero || matrix[c][0] == 0) {
                setToZero = true;
                memo[c][0] = 0;
            } else {
                memo[c][0] = 1;
            }
        }

        numAllPathsDPTopDown(matrix, memo, memo.length - 1, memo[0].length - 1);
        return memo[memo.length - 1][memo[0].length - 1];
    }

    public static int numAllPathsDPTopDown(int[][] matrix, int[][] memo, int r, int c) {
        if (r < 1 || c < 1) {
            return memo[r][c];
        }

        if (matrix[r][c] == 0) {
            memo[r][c] = 0;
            return memo[r][c];
        }

        if (memo[r][c] != -1) {
            return memo[r][c];
        }

        int left = numAllPathsDPTopDown(matrix, memo, r, c - 1);
        int right = numAllPathsDPTopDown(matrix, memo, r - 1, c);

        memo[r][c] = left + right;

        return memo[r][c];
    }


    public static void main(String[] args) {
        int size = 1000;
        boolean[][] maze = AssortedMethods.randomBooleanMatrix(size, size, 70);

        int[][] intMaze = new int[size][size];

        for (int j = 0; j < maze.length; j++) {
            for (int i = 0; i < maze[0].length; i++) {
                if (maze[j][i]) {
                    intMaze[j][i] = 1;
                }
            }
        }

        intMaze[0][0] = 1;
        intMaze[intMaze.length - 1][intMaze[0].length - 1] = 1;

//        AssortedMethods.printMatrix(intMaze);
//        double startTime = System.currentTimeMillis();
//        System.out.println(numAllPaths(intMaze));
//        double endTime = System.currentTimeMillis();
//        System.out.println("Total execution time: " + (endTime - startTime) );


        double startTime = System.currentTimeMillis();
        System.out.println(numAllPathsDP(intMaze));
        double endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime));

        startTime = System.currentTimeMillis();
        System.out.println(numAllPathsDPTopDown(intMaze));
        endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime));
    }
}
