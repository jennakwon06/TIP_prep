package Q19_9;

import java.util.*;

/**
 * Find longest path in a DAG
 *
 * For any arbitrary graph,
 * SP - Dijkstra, Bellman Ford
 * LP - NP hard
 *
 * For a DAG, SP and LP can be solved efficiently by processing vertices in a topo order
 * Any DAG has at least one topo sort ordering
 *
 *
 * Solution
 * 1) Find one valid topological sort ordering
 * 2) Find longest path in the DAG by processing vertices in the topo sort ordering (any is fine, unlike my first instinct)
 *
 * How to compute longest path when edges do not have a cost?
 *
 * http://www.geeksforgeeks.org/find-longest-path-directed-acyclic-graph/
 *
 */


public class TeamPhotoDay2 {

    private static class Team {
        public Integer id;
        public List<Team> neighbours;

        public Team(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object other){
            if (other == null || !(other instanceof Team)) return false;
            Team obj = (Team) other;
            return obj.id == this.id;
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }

    /**
     * Find the longest path in a DAG
     */
    public static int findLargestNumberOfTeams(List<Team> teams) {
        // Output one valid topo ordering
        List<Team> topoOrder = topologicalSort(teams);

        // pick the longest topo ordering
        return longestPath(topoOrder);

    }

    /**
     * Returns all valid topological sort ordering of vertices starting at root vertex
     */
    public static List<Team> topologicalSort(List<Team> teams) {
        List<Team> result = new LinkedList<>();
        return result;
    }

    public static int longestPath(List<Team> topoOrdering) {
        return 0;
    }

    public static void main(String[] args) {

    }

}
