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
                letterList = letters.replaceAll("[^a-zA-Z]","").toCharArray(); // Removes all chars that are'nt letters (A-Z) and makes a list with each letter in it.
                if (100000000 < letterList.length) {
                    throw new Exception("Wtf för lång");
                }
                else {
                    tf = false;
                }
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        scanner.close();
        TreeMap<Character, Integer> letterCounts = new TreeMap<>();
        for (char c : letterList) {
            letterCounts.put(c, letterCounts.getOrDefault(c, 0) + 1);
        }
        String result = "";
        for (Map.Entry<Character, Integer> letter : letterCounts.entrySet()) {
            Character thisletter = letter.getKey();
            Integer count = letter.getValue();
            result = result + (thisletter + ":" + count + " ");
        }
        if (result == "") {
            System.out.println("0");
        }
        else {
            System.out.println(result);
        }
    }
}
