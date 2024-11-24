package Java.FaktoradiskaTal;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// Program that converts factorial to decimal and from decimal to factorial.

// Input: Two numbers on each two rows (first in factorial, second in decimal)
// Output: Two numbers on each two rows (first is made from factorial to decimal, second is made from decimal to factorial)

// Examples:

// Input 1:     654320
//              25
// Output 1:    5038
//              1001

// Input 2:     32A40244706404200
//              1122334455667788
// Output 2:    1122334455667788
//              32A40244706404200

public class FaktoradiskaTal {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        String facInput = scanner.nextLine().toUpperCase();  // First line: factorial (can contain letters)
        long decInput = Long.parseLong(scanner.nextLine());  // Second line: decimal
        scanner.close();

        List<String> facSplittedInput = Arrays.asList(facInput.split(""));  // Split on each letter
        Collections.reverse(facSplittedInput); // Reversing our factorial list. Makes calculating easier. 

        System.out.println(facSum(facSplittedInput)); 
        System.out.println(decSum(decInput)); 
    }

    public static long facSum(List<String> facSplittedInput) {   // Calculates from factorial to decimal
        
        long factorialSum = 0;
        
        for (int i = 1; i <= facSplittedInput.size(); i++) { 

            if (Character.isLetter((facSplittedInput.get(i-1)).charAt(0))) {    // If the coefficient is a letter 
                int value = (int) (facSplittedInput.get(i-1)).charAt(0);    // Make the letter into an int
                facSplittedInput.set((i-1), String.valueOf(value-55));  // Replace the letter with the int-value
            }

            factorialSum = factorialSum + factorial(i) * Long.valueOf(facSplittedInput.get(i-1));   // From the equation Σ(f_i*i!)
        }
        return factorialSum;
    }

    public static String decSum(long decInput) {   // Calculates from decimal to factorial
        String decimalSum = "";
        int n = 1;
    
        while (factorial(n) < decInput) {  // Finds biggest n, where n! <= decInput
            n++;
        }
        n--;    // Otherwise 0! also will be counted
    
        while (n > 0) { // Start with biggest factorial and work down
            long currentFactorial = factorial(n);
            long coefficient = decInput / currentFactorial;  // Find coefficient
            
            if (coefficient > 9) {
                char letter = (char)('A' + coefficient - 10);   // Converts to letter if the coefficient was bigger than 9
                decimalSum = decimalSum + String.valueOf(letter);
            }
            else {
                decimalSum = decimalSum + coefficient;
            }
    
            decInput -= coefficient * currentFactorial; // Updating decInput by substituting what we have found
    
            n--;  // Go to next (smaller factorial)
        }
    
        return decimalSum;
    }
    

    public static long factorial(int n) {   // Turns a number into factorial, for example: factorial(5) = 5*4*3*2*1 = 5!
        long fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
    
}