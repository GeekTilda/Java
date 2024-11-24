package Java.FaktoradiskaTal;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FaktoradiskaTal {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        String facInput = scanner.nextLine().toUpperCase();  // First line: factorial
        long decInput = Long.parseLong(scanner.nextLine());  // Second line: decimal
        scanner.close();

        List<String> facSplittedInput = Arrays.asList(facInput.split(""));
        Collections.reverse(facSplittedInput); // Reversing our factorial list :)

        System.out.println(facSum(facSplittedInput)); 
        System.out.println(decSum(decInput)); 
    }

    public static long facSum(List<String> facSplittedInput) {   // Calculates from factorial to decimal. 
        
        long factorialSum = 0;
        
        for (int i = 1; i <= facSplittedInput.size(); i++) { 

            if (Character.isLetter((facSplittedInput.get(i-1)).charAt(0))) {
                int value = (int) (facSplittedInput.get(i-1)).charAt(0);
                facSplittedInput.set((i-1), String.valueOf(value-55));
            }

            factorialSum = factorialSum + factorial(i) * Long.valueOf(facSplittedInput.get(i-1));
        }
        return factorialSum;
    }

    public static String decSum(long decInput) {
        StringBuilder decimalSum = new StringBuilder();
        int n = 1;
    
        while (factorial(n) <= decInput) {  // Finds biggest n, where n! <= decInput
            n++;
        }
        n--;
    
        while (n > 0) { // Start with biggest factorial and work down
            long currentFactorial = factorial(n);
            long coefficient = decInput / currentFactorial;  // Find coefficient
            
            if (coefficient > 9) {
                // If coefficient is bigger than 9, convert to letter. 
                decimalSum.append((char) ('A' + (coefficient - 10)));
            } else {
                decimalSum.append(coefficient);
            }
    
            // Updating decInput by substituting what we have found
            decInput -= coefficient * currentFactorial;
    
            n--;  // Go to next, aka smaller factorial. 
        }
    
        return decimalSum.toString();
    }
    

    public static long factorial(int n) {
        long fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
    
}