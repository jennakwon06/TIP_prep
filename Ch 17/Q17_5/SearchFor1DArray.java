package Q17_5;

import java.util.*;

public class SearchFor1DArray {


    private static class Attempt {
        public Integer x;
        public Integer y;
        public Integer offset;

        public Attempt(Integer x, Integer y, Integer offset) {
            this.x = x;
            this.y = y;
            this.offset = offset;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Attempt cacheEntry = (Attempt)o;

            if (x != null ? !x.equals(cacheEntry.x) : cacheEntry.x != null) {
                return false;
            }
            if (y != null ? !y.equals(cacheEntry.y) : cacheEntry.y != null) {
                return false;
            }
            if (offset != null ? !offset.equals(cacheEntry.offset)
                    : cacheEntry.offset != null) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() { return Objects.hash(x, y, offset); }
    }

    public enum Direction {UP, DOWN, RIGHT, LEFT}

    public static boolean isPatternContainedInGrid(List<List<Integer>> grid,
                                                   List<Integer> pattern) {
        // Each entry in previousAttempts is a point in the grid and suffix of
        // pattern (identified by its offset). Presence in previousAttempts
        // indicates the suffix is not contained in the grid starting from that
        // point.
        Set<Attempt> previousAttempts = new HashSet<>();
        for (int i = 0; i < grid.size(); ++i) {
            for (int j = 0; j < grid.get(i).size(); ++j) {
                if (helper(grid, i, j, pattern, 0,
                        previousAttempts, Direction.DOWN)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean helper(
            List<List<Integer>> grid, int x, int y, List<Integer> pattern, int offset,
            Set<Attempt> previousAttempts, Direction origin) {
        if (pattern.size() == offset) {
            // Nothing left to complete.
            return true;
        }
        // Check if (x, y) lies outside the grid.
        if (x < 0 || x >= grid.size() || y < 0 || y >= grid.get(x).size()
                || previousAttempts.contains(new Attempt(x, y, offset))) {
            return false;
        }

        if (grid.get(x).get(y).equals(pattern.get(offset))
                && (helper(grid, x - 1, y, pattern, offset + 1, previousAttempts, Direction.UP) && origin != Direction.DOWN
                || helper(grid, x + 1, y, pattern, offset + 1, previousAttempts, Direction.DOWN) && origin != Direction.UP
                || helper(grid, x, y - 1, pattern, offset + 1, previousAttempts, Direction.RIGHT) && origin != Direction.LEFT
                || helper(grid, x, y + 1, pattern, offset + 1, previousAttempts, Direction.LEFT) && origin != Direction.RIGHT )) {
            return true;
        }
        previousAttempts.add(new Attempt(x, y, offset));
        return false;
    }
    // @exclude

    public static void main(String[] args) {
        int n = 4;

        // construct matrix
        List<List<Integer>> A = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            A.add(new ArrayList<>(n));
            for (int j = 0; j < n; ++j) {
                A.get(i).add(i*n + j);
            }
        }
        System.out.println("A = " + A);

        //  construct 1D sequence
        List<Integer> S = new ArrayList<>();
        S.add(1);
        S.add(2);
        S.add(1);
        System.out.println("S = " + S);

        System.out.println(isPatternContainedInGrid(A, S));
    }
}