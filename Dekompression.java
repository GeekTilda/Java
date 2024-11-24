package Java.Dekompression;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

// Formats input of sequences into sentence(s)

// Examples:

// Input 1:     sju: 0 7; sjö: 4 13; ka: 10; män: 16
// Output 1:    sju sjösjuka sjömän 

// Input 2:     h,: 18 24 51 57 84 90 96; h!: 30 63 102; She loves you,: 0 33 66; yea: 15 21 27 48 54 60 81 87 93 99;
// Output 2:    She loves you, yeah, yeah, yeah! She loves you, yeah, yeah, yeah! She loves you, yeah, yeah, yeah, yeah!

public class Dekompression {

    private static final int MAX_INPUT_SIZE = 100_000_000; // Maximum allowed input size

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim();
        scanner.close();

        if (input.length() > MAX_INPUT_SIZE) {  // Validate input size
            System.err.println("");
            return;
        }

        String[] wordListIndex = input.split(";");  // Split at each word
        TreeMap<Integer, String> positionMap = new TreeMap<>(); // Initialize map consisting of position(s) followed by text

        int maxPosition = 0;

        for (String wordSection : wordListIndex) {
            String[] temp = wordSection.split(":");  // Split the word from the position(s)
            if (temp.length != 2) { // If the input does'nt match expected input formatting
                System.err.println("");
                return;
            }

            String word = temp[0].trim();   // Initializing the word and removing spaces (at the start and end)
            String[] positionsStr = temp[1].trim().split(" ");   // Splitting the string of positions to get each position of the word

            for (String posStr : positionsStr) {
                try {
                    int pos = Integer.parseInt(posStr);

                    if (pos < 0) {  // Cannot have negative placements
                        System.err.println("");
                        return;
                    }

                    positionMap.put(pos, word); // Updating map
                    if (pos + word.length() > maxPosition) {
                        maxPosition = pos + word.length();  // Update max-position
                    }
                } catch (NumberFormatException e) {
                    System.err.println("");
                    return;
                }
            }
        }


        char[] result = new char[maxPosition]; // Initializing results

        for (int i = 0; i < maxPosition; i++) { // Putting spaces at every position.
            result[i] = ' ';
        }

        for (Map.Entry<Integer, String> entry : positionMap.entrySet()) {   // Loop through each word and its position
            int startPos = entry.getKey();  // The position is the key
            String word = entry.getValue(); // The word is the value
            for (int i = 0; i < word.length(); i++) {
                result[startPos + i] = word.charAt(i);  // Insert letter at each position
            }
        }

        System.out.println(new String(result).trim()); // Print the result
    }
}
