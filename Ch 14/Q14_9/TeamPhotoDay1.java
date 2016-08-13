package Q14_9;

import java.util.*;


public class TeamPhotoDay1 {

    private static class Player implements Comparable<Player> {
        public Integer height;

        public Player(Integer h) { height = h; }

        @Override
        public int compareTo(Player that) {
            return Integer.compare(height, that.height);
        }

        @Override
        public boolean equals(Object that) {
            if (this == that) {return true;} // same object
            if (that == null || !(that instanceof Player)) return false; // null and type check
            Player player = (Player) that;
            return height.equals(player.height);
        }

        @Override
        public int hashCode() {
            return height.hashCode();
        }
    }

    private static class Team {
        private List<Player> players;

        public Team(List<Integer> height) {
            players = new ArrayList<>(height.size());
            for (int i = 0; i < height.size(); ++i) {
                players.add(new Player(height.get(i)));
            }
        }

        private List<Player> sortPlayersByHeight() {
            List<Player> sortedPlayers = new ArrayList<>(players);
            Collections.sort(sortedPlayers);
            return sortedPlayers;
        }
    }

    // Checks if A can be placed in front of B.
    public static boolean validPlacementExists(Team A, Team B) {
        List<Player> ASorted = A.sortPlayersByHeight();
        List<Player> BSorted = B.sortPlayersByHeight();
        for (int i = 0; i < ASorted.size() && i < BSorted.size(); ++i) {
            if (ASorted.get(i).compareTo(BSorted.get(i)) >= 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<Integer> height = Arrays.asList(1, 5, 4);
        Team t1 = new Team(height);
        height = Arrays.asList(2, 3, 4);
        Team t2 = new Team(height);
        assert(!validPlacementExists(t1, t2)
                && !validPlacementExists(t2, t1));
        height = Arrays.asList(0, 3, 2);
        Team t3 = new Team(height);
        assert(validPlacementExists(t3, t1)
                && !validPlacementExists(t1, t3)
                && validPlacementExists(t3, t2)
                && !validPlacementExists(t1, t2));
    }
}
