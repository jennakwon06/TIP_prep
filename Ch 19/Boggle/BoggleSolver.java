package Boggle;

import java.util.*;

/**
 * Java implementation of a boggle solver
 * Problem explained at http://www.geeksforgeeks.org/boggle-find-possible-words-board-characters/
 */

public class BoggleSolver {

    static void boggleSolver(char[][] boggle, HashSet<String> dictionary) {
        int M = boggle.length;
        int N = boggle[0].length;
        boolean[][] visited = new boolean[M][N];
        String str = "";

        for (int i = 0; i < M; i++) {
            for (int j = 0 ;j < N; j++) {
                DFSUtil(boggle, dictionary, visited, i, j, str);
            }
        }
    }

    static void DFSUtil(char[][] boggle, HashSet<String> dictionary, boolean[][] visited, int i, int j, String str) {
        visited[i][j] = true;
        str += boggle[i][j];

        if(isValidWord(str, dictionary))
            System.out.println(str);

        for (int row = i - 1; row <= i + 1 && row < boggle.length; row++) {
            for (int col = j - 1; col <= j + 1 && col < boggle[0].length; col++) {
                if (row >= 0 && col >= 0 && !visited[row][col])
                    DFSUtil(boggle, dictionary, visited, row, col, str);
            }
        }

        visited[i][j] = false;
        str = "";
    }


    static boolean isValidWord(String str, HashSet<String> dictionary) {
        return dictionary.contains(str);
    }

    static void printMatrix(char[][] boggle) {
        for (int i = 0; i <boggle.length; i++) {
            for (int j = 0;j < boggle[0].length; j++) {
                System.out.print(boggle[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        HashSet<String> dictionary = new HashSet<>();

        dictionary.add("GEEKS");
        dictionary.add("FOR");
        dictionary.add("QUIZ");
        dictionary.add("GO");


        char[][] boggle = {{'G','I','Z'},
                {'U','E','K'},
                {'Q','S','E'}};

        System.out.println("Boggle:");
        printMatrix(boggle);

        System.out.println("\nWords:");
        boggleSolver(boggle, dictionary);
    }
}
