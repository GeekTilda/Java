import java.util.*;

class SnodjupRekord implements Comparable<SnodjupRekord> {
    String location;
    double snowDepth;
    int year;

    // Constructor.
    public SnodjupRekord(String date, String location, double snowDepth) {
        this.location = location;
        this.snowDepth = snowDepth;
        this.year = Integer.parseInt(date.substring(0, 4)); // Extract year from the date.
    }

    // Compare by snow depth (descending) and then by location (ascending).
    public int compareTo(SnodjupRekord other) {
        if (this.snowDepth != other.snowDepth) {
            return Double.compare(other.snowDepth, this.snowDepth); // Descending order of snow depth.
        }
        return this.location.compareTo(other.location); // Alphabetical order of location.
    }

    public String toString() {
        return this.location + " " + this.snowDepth;
    }
}

public class Snodjup {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // List to store all snow depth records.
        List<SnodjupRekord> records = new ArrayList<>();

        // Read user input until the entire list is read.
        while (scanner.hasNextLine()) {
            String places = scanner.nextLine().trim();
            if (places.isEmpty()) {
                break; // Stop reading input when an empty line is encountered.
            }

            // Split the input (date, location, snow depth).
            String[] parts = places.split(" ");
            String date = parts[0];
            String location = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length - 1));
            double snowDepth = Double.parseDouble(parts[parts.length - 1]);

            // Create and add a new SnodjupRekord to the list.
            records.add(new SnodjupRekord(date, location, snowDepth));
        }

        scanner.close();

        // Group records by year and ensure only the highest snow depth per location is kept.
        Map<Integer, Map<String, SnodjupRekord>> recordsByYear = new HashMap<>();

        for (SnodjupRekord record : records) {
            recordsByYear.putIfAbsent(record.year, new HashMap<>());
            Map<String, SnodjupRekord> yearRecords = recordsByYear.get(record.year);

            // Only keep the record if it's the highest for that location.
            if (!yearRecords.containsKey(record.location) || yearRecords.get(record.location).snowDepth < record.snowDepth) {
                yearRecords.put(record.location, record);
            }
        }

        // Sort the records by year.
        List<Integer> sortedYears = new ArrayList<>(recordsByYear.keySet());
        Collections.sort(sortedYears);

        // Output results for each year.
        for (int year : sortedYears) {
            List<SnodjupRekord> yearRecords = new ArrayList<>(recordsByYear.get(year).values());

            // Sort by snow depth (descending) and location (alphabetical) using compareTo.
            Collections.sort(yearRecords);

            // Print the year.
            System.out.println(year);

            // Print up to 5 records for the current year.
            for (int i = 0; i < Math.min(5, yearRecords.size()); i++) {
                System.out.println(yearRecords.get(i));
            }
        }
    }
}
