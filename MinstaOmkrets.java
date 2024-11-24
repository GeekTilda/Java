import java.util.Scanner;

public class MinstaOmkrets {
    public static void main(String[] args){
        long area = 1;
        Scanner reader = new Scanner(System.in);
        Boolean tf = true;
        while (tf) {
            System.out.print("");
            try {
                area = Long.parseLong(reader.next());
                tf = false;
            } catch (Exception e) {
                System.out.println("Måste vara ett heltal mellan 1 och 10^12.");
            }
        }
        reader.close();
        long x = 1;
        long s = 1;
        long smin = Long.MAX_VALUE;
        double sqrt = Math.sqrt(area);
        while (x <= sqrt) {
            if (area % x == 0) {
                long y = area/x;
                s = 2 * (y + x);
                if (s < smin) {
                    smin = s;
                }
            }
            x++;
        }
        System.out.println(smin);
    }
}
