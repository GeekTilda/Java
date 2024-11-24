package Java.StörstaÖn;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Finds the biggest island! 

// @ = island
// ~ = sea

// Input: Two numbers representing the width and height of the map
// Output: The area of the largest island (does not count diagonals)

// Examples:

// Input 1:     10 10
//              ~~~~~~~~~~
//              ~@~~~~~~~~
//              ~~~~~~~~~~
//              ~~~@@~~~~~
//              ~~~@~~~~~~
//              ~~~~~~~~~~
//              ~~~~~~~~~~
//              ~@@@@@@@@@
//              ~~~~~~~~~~
//              ~~~~~~~~~~
// Output 1:    9

// Input 2:     3 3
//              ~~~
//              ~@~
//              ~~~
// Output 2:    1

public class StorstaOn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] rowXcol = scanner.nextLine().split(" ");

        // Initiating row and column sizes 
        int totalRows = Integer.parseInt(rowXcol[0]);
        int totalCols = Integer.parseInt(rowXcol[1]);

        char[][] map = new char[totalRows][totalCols];  // Creating a 2D grid to store the map

        for (int r = 0; r < totalRows; r++) {   // Read through every row
            map[r] = scanner.nextLine().toCharArray();
        }

        scanner.close();

        int maxSize = 0;    // Variable to keep track of the largest island

        for (int r = 0; r < totalRows; r++) {
            for (int c = 0; c < totalCols; c++) {
                if (map[r][c] == '@') {
                    int islandSize = BFS(map, r, c, totalRows, totalCols);  // When we find land, start a BFS (breadth-first search)
                    if (islandSize > maxSize) { // Update largest island
                        maxSize = islandSize;
                    }
                }
            }
        }

        System.out.println(maxSize);
    }

    public static int BFS(char[][] map, int startRow, int startCol, int totalRows, int totalCols) { // BFS (breadth-first search) method to explore the island (Visual: https://en.wikipedia.org/wiki/Breadth-first_search#/media/File:BFS-Algorithm_Search_Way.gif )
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};    // "Matrix" of directions to explore (up, down, left, right)

        Queue<int[]> queue = new LinkedList<>();    // Queue for BFS
        queue.add(new int[]{startRow, startCol});   // Adding starting-values

        int size = 1;

        map[startRow][startCol] = '.';  // Mark the starting position as visited

        while (!queue.isEmpty()) {  // BFS loop
            int[] point = queue.poll(); // Dequeues the first element in the queue (which will be both a row and a column)
            int row = point[0];
            int col = point[1];

            for (int[] direction : directions) {    // Explore all 4 possible directions (up, down, left, right)
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (newRow >= 0 && newRow < totalRows && newCol >= 0 && newCol < totalCols) {   // Check if the new position is within bounds
                    if (map[newRow][newCol] == '@') {   // If the new position is land, add the coordinates to our queue
                        queue.add(new int[]{newRow, newCol});
                        size++;  // Increase the island size

                        map[newRow][newCol] = '.';  // Mark the cell as visited (so we don't count the same piece of land multiple times)
                    }
                }
            }
        }

        return size;
    }
}
