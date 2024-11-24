package Java.StörstaÖn;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class StorstaOn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] rowXcol = scanner.nextLine().split(" ");

        int totalRows = Integer.parseInt(rowXcol[0]);
        int totalCols = Integer.parseInt(rowXcol[1]);

        // Create a 2D grid to store the map
        char[][] map = new char[totalRows][totalCols];

        // Read the entire map into memory
        for (int r = 0; r < totalRows; r++) {
            map[r] = scanner.nextLine().toCharArray();
        }

        scanner.close();

        // Variable to keep track of the largest island
        int maxSize = 0;

        // Traverse through each cell of the map
        for (int r = 0; r < totalRows; r++) {
            for (int c = 0; c < totalCols; c++) {
                if (map[r][c] == '@') {  // When we find land, start a BFS
                    int islandSize = BFS(map, r, c, totalRows, totalCols);
                    maxSize = Math.max(maxSize, islandSize);  // Update the largest island size
                }
            }
        }

        System.out.println(maxSize);
    }

    // BFS method to explore the island
    public static int BFS(char[][] map, int startRow, int startCol, int totalRows, int totalCols) {
        // Directions to explore (up, down, left, right)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Queue for BFS
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startRow, startCol});

        // Start the size of the island as 1 (since we found an '@' at startRow, startCol)
        int size = 1;

        // Mark the starting position as visited
        map[startRow][startCol] = '.';

        // BFS loop
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int row = point[0];
            int col = point[1];

            // Explore all 4 possible directions (up, down, left, right)
            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                // Check if the new position is within bounds
                if (newRow >= 0 && newRow < totalRows && newCol >= 0 && newCol < totalCols) {
                    // If the new position is land ('@'), continue the BFS
                    if (map[newRow][newCol] == '@') {
                        queue.add(new int[]{newRow, newCol});
                        size++;  // Increase the island size

                        // Mark the cell as visited
                        map[newRow][newCol] = '.';
                    }
                }
            }
        }

        return size;
    }
}
