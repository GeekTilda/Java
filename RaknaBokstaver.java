package Java.RäknaBokstäver;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

// Calculates the amount of times a letter occurs in a given input

// Examples:

// Input 1:     ABCaaa
// Output 1:    A:4 B:1 C:1 

// Input 2:     sju sjsjuka sjmn
// Output 2:    A:1 J:4 K:1 M:1 N:1 S:4 U:2

public class RaknaBokstaver {

    private static final int MAX_INPUT_SIZE = 100_000_000; // Maximum allowed input size
    public static void main(String[] args) {
        char[] letterList = new char[]{};
        Scanner scanner = new Scanner(System.in);
        Boolean tf = true;

        while (tf) {
            try {
                System.out.print("");
                String letters = scanner.nextLine().trim().toUpperCase();  // Makes every single letter uppercase & removes spaces
                letterList = letters.replaceAll("[^A-Z]","").toCharArray(); // Removes all chars that are'nt letters (A-Z) and makes a list with each letter in it.
                
                if (MAX_INPUT_SIZE < letterList.length) {
                    throw new Exception("För lång text!");
                }
                else {
                    tf = false; // Break loop
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        scanner.close();
        
        TreeMap<Character, Integer> letterCount = new TreeMap<>(); // Initiating a "map" consisting of each letter and how many times it occurs. 

        for (char c : letterList) {
            letterCount.put(c, letterCount.getOrDefault(c, 0) + 1);   // Puts in each letter c and its amount of occurences. If it doesnt already exist in the map, we initiate it. If it already exists, we add 1 to "occurences".
        }

        String result = "";

        for (Map.Entry<Character, Integer> letter : letterCount.entrySet()) {  // Creates the string for the output
            Character thisletter = letter.getKey();
            Integer count = letter.getValue();
            result = result + (thisletter + ":" + count + " ");
        }

        if (result == "") { // If we did'nt get any input data :(
            System.out.println("0");
        }
        else {
            System.out.println(result);
        }
    }
}
