package Java.Dekompression;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Dekompression {

    private static final int MAX_INPUT_SIZE = 100_000_000; // Maximum allowed input size

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        scanner.close();

        // Validate input size
        if (input.length() > MAX_INPUT_SIZE) {
            System.err.println("");
            return;
        }

        String[] wordListIndex = input.split(";");
        TreeMap<Integer, String> positionMap = new TreeMap<>();

        int maxPosition = 0;

        // Parse input and populate position map
        for (String wordSection : wordListIndex) {
            String[] temp = wordSection.trim().split(":");
            if (temp.length != 2) {
                System.err.println("");
                return;
            }

            String word = temp[0].trim();
            String[] positionsStr = temp[1].trim().split("\\s+");

            for (String posStr : positionsStr) {
                try {
                    int pos = Integer.parseInt(posStr);
                    if (pos < 0) {
                        System.err.println("");
                        return;
                    }
                    // Ensure we place the word at the correct position
                    positionMap.put(pos, word);
                    maxPosition = Math.max(maxPosition, pos + word.length());
                } catch (NumberFormatException e) {
                    System.err.println("");
                    return;
                }
            }
        }

        // Use a char array to construct the result
        char[] result = new char[maxPosition];
        for (int i = 0; i < maxPosition; i++) {
            result[i] = ' ';
        }

        // Populate the result based on the position map
        for (Map.Entry<Integer, String> entry : positionMap.entrySet()) {
            int startPos = entry.getKey();
            String word = entry.getValue();
            for (int i = 0; i < word.length(); i++) {
                if (startPos + i < maxPosition) {
                    result[startPos + i] = word.charAt(i);
                }
            }
        }

        // Print the result
        System.out.println(new String(result).trim());
    }
}
