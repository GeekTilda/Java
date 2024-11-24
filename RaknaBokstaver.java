package Java.RäknaBokstäver;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class RaknaBokstaver {
    public static void main(String[] args) {
        char[] letterList = new char[]{};
        Scanner scanner = new Scanner(System.in);
        Boolean tf = true;

        while (tf) {
            try {
                System.out.print("");
                String letters = scanner.nextLine().replaceAll("\\s+", "").toUpperCase();  // Makes every single letter uppercase & removes spaces
                letterList = letters.replaceAll("[^A-Z]","").toCharArray(); // Removes all chars that are'nt letters (A-Z) and makes a list with each letter in it.
                
                if (100000000 < letterList.length) {
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
