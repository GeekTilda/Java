import java.util.Scanner;

// Calculates the minimum circumference of a given area. 

public class MinstaOmkrets {
    public static void main(String[] args){
        long area = 1;
        Scanner reader = new Scanner(System.in);
        Boolean tf = true;

        while (tf) {
            System.out.print("");
            try {
                area = Long.parseLong(reader.next());

                if (area <= 0) {    // If the user puts in a negative value (or zero)
                    throw new Exception();
                }

                tf = false; // Break loop
            } catch (Exception e) {
                System.out.println("Måste vara ett positivt heltal mellan 1 och 10^12.");
            }
        }
        reader.close();

        // Initiating values
        long x = 1;
        long s = 1; // circumference
        long smin = Long.MAX_VALUE;
        double sqrt = Math.sqrt(area);

        while (x <= sqrt) {
            if (area % x == 0) {
                long y = area/x;    // area = x*y
                s = 2 * (y + x);    // circumference = 2*(x+y)
                if (s < smin) {
                    smin = s;   // Update minimum
                }
            }
            x++;
        }
        System.out.println(smin);
    }
}
