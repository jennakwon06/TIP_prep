package Q19_3;

import java.util.*;

/**
 * Fill all the white squares that are enclosed with "Black"
 *
 * Solution 1
 * - code from EPI github
 * - mark all the white squares reachable from boundaries as visited, and turn all the unmarked white squares black
 *
 * Solution 2
 * - my solution
 * - iterate through nonboundary squares and turn to black if is enclosed (check for enclosed with bfs)
 * - also uses visited for not visiting neighbouring cells twice p
 * - faster than solution 1 on average especially for large boards
 */

public class ComputeEnclosedRegions {


    /**
     * Solution 1 implementation
     * @param board
     */
    public static void fillSurroundedRegionsOne(List<List<Character>> board) {
        if (board.isEmpty()) {
            return;
        }

        List<List<Boolean>> visited = new ArrayList<>(board.size());
        for (int i = 0; i < board.size(); ++i) {
            visited.add(new ArrayList(Collections.nCopies(board.get(i).size(), false)));
        }

        // Identifies the regions that are reachable via white path starting from
        // the first or last columns.
        for (int i = 0; i < board.size(); ++i) {
            if (board.get(i).get(0) == 'W' && !visited.get(i).get(0)) {
                markBoundaryRegion(i, 0, board, visited);
            }
            if (board.get(i).get(board.get(i).size() - 1) == 'W'
                    && !visited.get(i).get(board.get(i).size() - 1)) {
                markBoundaryRegion(i, board.get(i).size() - 1, board, visited);
            }
        }
        // Identifies the regions that are reachable via white path starting from
        // the first or last rows.
        for (int j = 0; j < board.get(0).size(); ++j) {
            if (board.get(0).get(j) == 'W' && !visited.get(0).get(j)) {
                markBoundaryRegion(0, j, board, visited);
            }
            if (board.get(board.size() - 1).get(j) == 'W'
                    && !visited.get(board.size() - 1).get(j)) {
                markBoundaryRegion(board.size() - 1, j, board, visited);
            }
        }

        // Marks the surrounded white regions as black.
        for (int i = 1; i < board.size() - 1; ++i) {
            for (int j = 1; j < board.get(i).size() - 1; ++j) {
                if (board.get(i).get(j) == 'W' && !visited.get(i).get(j)) {
                    board.get(i).set(j, 'B');
                }
            }
        }
    }

    private static void markBoundaryRegion(int i, int j,
                                           List<List<Character>> board,
                                           List<List<Boolean>> visited) {
        Queue<Coordinate> q = new LinkedList<>();
        q.add(new Coordinate(i, j));
        visited.get(i).set(j, true);
        // Uses BFS to traverse this region.
        while (!q.isEmpty()) {
            Coordinate curr = q.poll();
            final int DIRS[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
            for (int[] dir : DIRS) {
                Coordinate next = new Coordinate(curr.c + dir[0], curr.r + dir[1]);
                if (next.c >= 0 && next.c < board.size() && next.r >= 0
                        && next.r < board.get(next.c).size()
                        && board.get(next.c).get(next.r) == 'W'
                        && !visited.get(next.c).get(next.r)) {
                    visited.get(next.c).set(next.r, true);
                    q.add(next);
                }
            }
        }
    }

    /**
     * Solution 2 implementation
     */
    public static void fillSurroundedRegionsTwo(List<List<Character>> board) {
        int m = board.size();
        int n = board.get(0).size();

        HashSet<Coordinate> visited = new HashSet<>();

        for (int r = 1; r <= m - 2; r++) { // consider non boundary regions
            for (int c = 1; c <= n -2; c++) {
                if (board.get(r).get(c) == 'W') {
                    doBFS(board, visited, r, c);
                }
            }
        }
    }

    public static void doBFS(List<List<Character>> board, HashSet<Coordinate> visited, int r, int c) {
        LinkedList<Coordinate> q = new LinkedList<>();
        LinkedList<Coordinate> path = new LinkedList<>();

        Coordinate root = new Coordinate(r, c);

        q.add(root);
        path.add(root);
                        // left      up    right    down
        int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

        while (!q.isEmpty()) {
            Coordinate curElem = q.pop();

            // add neighbors
            for (int[] dir : dirs) {
                int rowInd = curElem.r + dir[0];
                int colInd = curElem.c + dir[1];

                if (rowInd < board.size() && rowInd >= 0
                        && colInd < board.get(0).size() && colInd >= 0
                        && board.get(rowInd).get(colInd) == 'W') { //square is valid and white

                    Coordinate validNeighbor = new Coordinate(rowInd, colInd);

                    if (!visited.contains(validNeighbor)) {
                        path.add(validNeighbor);
                        if (rowInd == board.size() - 1 || colInd == board.size() - 1
                                || rowInd == 0 || colInd == 0) {
                            return;
                        }
                        q.add(validNeighbor);
                    }
                }

                visited.add(curElem);
            }
        }
        turnSquaresToBlack(path, board);
    }

    public static void turnSquaresToBlack(LinkedList<Coordinate> path, List<List<Character>> board) {
        for (Coordinate c : path) {
            board.get(c.r).set(c.c, 'B');
        }
    }


    private static class Coordinate {
        public Integer r;
        public Integer c;

        public Coordinate(Integer r, Integer c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }

            if (!(o instanceof Coordinate)) {
                return false;
            }

            Coordinate casted = (Coordinate) o;

            return Integer.compare(c, casted.c) == 0
                    && Integer.compare(r, casted.r) == 0;
        }

        public String toString() {
            return "(r = " + r + " c = " + c + ")";
        }

        @Override
        public int hashCode() {
            // bijection algorithm for a point
            int temp = (r + ((c + 1) / 2));
            return c + (temp * temp);
        }
    }

    // @exclude

    private static void simpleTest() {
        List<List<Character>> A = Arrays.asList(
                Arrays.asList('B', 'B', 'B', 'B'), Arrays.asList('W', 'B', 'W', 'B'),
                Arrays.asList('B', 'W', 'W', 'B'), Arrays.asList('B', 'B', 'B', 'B'));
        System.out.println(A.toString());

        fillSurroundedRegionsTwo(A);

        System.out.println(A.toString());

        List<List<Character>> golden = Arrays.asList(
                Arrays.asList('B', 'B', 'B', 'B'), Arrays.asList('W', 'B', 'B', 'B'),
                Arrays.asList('B', 'B', 'B', 'B'), Arrays.asList('B', 'B', 'B', 'B'));

        assert(A.equals(golden));
    }

    public static void main(String[] args) {
        Coordinate c1 = new Coordinate(10, 20); //row, col
        Coordinate c2 = new Coordinate(10, 20);
        System.out.println(c1.equals(c2));
        simpleTest();

        Random r = new Random();
        int n, m;
        if (args.length == 2) {
            n = Integer.parseInt(args[0]);
            m = Integer.parseInt(args[1]);
        } else {
            n = r.nextInt(10000);
            m = r.nextInt(10000);
        }

        System.out.println("m = " + m + " n = " + n);

        List<List<Character>> board = new ArrayList<>();
        List<List<Character>> board2 = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<Character> row = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                row.add(r.nextBoolean() ? 'B' : 'W');
            }
            board.add(row);
            board2.add(row);
        }

        long startTime = System.currentTimeMillis();
        fillSurroundedRegionsOne(board);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

        startTime = System.currentTimeMillis();
        fillSurroundedRegionsTwo(board2);
        endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

//        for (int i = 0; i < board2.size(); i++) {
//            System.out.println(board.get(i));
//            System.out.println(board2.get(i));
//            Boolean result = board.get(i).equals(board2.get(i));
//            System.out.println("are they equal? " + result);
//        }
    }
}