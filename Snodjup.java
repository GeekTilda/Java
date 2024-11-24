import java.util.*;

// Given a list of snow-depths a sorted list (by depth) will be given back

// Examples:

// Input 1:     20240216 Trysil 2.1
//              20240215 Kiruna 1.1
//              20230215 Kiruna 1.2
//              20240109 Abisko 1.6

// Output 1:    2023
//              Kiruna 1.2
//              2024
//              Trysil 2.1
//              Abisko 1.6
//              Kiruna 1.1

// Input 2:     19720214 Aspen 1.8
//              19730116 Aspen 1.9
//              19721223 Kiruna 1.2
//              19730117 Aspen 2.1
//              19721228 Kiruna 1.2
//              19721113 Boden 1.2
//              19720322 Chamonix 0.4
//              19720104 Val d'Isere 1.4
//              19721230 Kalmar 0.1
//              19721109 Leksand 0.3
//              19721121 Trysil 0.9
//              19721230 Kalmar 0.3
//              19721219 Leksand 0.1
//              19721124 Trysil 0.3

// Output 2:    1972
//              Aspen 1.8
//              Val d'Isere 1.4
//              Boden 1.2
//              Kiruna 1.2
//              Trysil 0.9
//              1973
//              Aspen 2.1

class SnodjupRekord implements Comparable<SnodjupRekord> {
    String location;
    double snowDepth;
    int year;

    // Constructor
    public SnodjupRekord(String date, String location, double snowDepth) {
        this.location = location;
        this.snowDepth = snowDepth;
        this.year = Integer.parseInt(date.substring(0, 4)); // Extract year from the date
    }

    public int compareTo(SnodjupRekord other) { // Compare by snow depth (descending) and then by location (ascending)
        if (this.snowDepth != other.snowDepth) {
            return Double.compare(other.snowDepth, this.snowDepth); // Descending order of snow depth
        }
        return this.location.compareTo(other.location); // Alphabetical order of location (if both has the same depth)
    }

    public String toString() {
        return this.location + " " + this.snowDepth;
    }
}

public class Snodjup {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<SnodjupRekord> records = new ArrayList<>();    // List to store all snow depth records

        while (scanner.hasNextLine()) { // Read user input until the entire list is read
            String places = scanner.nextLine().trim();
            if (places.isEmpty()) {
                break; // Stop reading input when an empty line is encountered
            }

            String[] parts = places.split(" "); // Split the input (date, location, snow depth)
            String date = parts[0];
            String location = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length - 1));
            double snowDepth = Double.parseDouble(parts[parts.length - 1]);

            records.add(new SnodjupRekord(date, location, snowDepth));  // Create and add a new SnodjupRekord to the list.
        }

        scanner.close();

        Map<Integer, Map<String, SnodjupRekord>> recordsByYear = new HashMap<>();   // Group records by year and ensure only the highest snow depth per location is kept

        for (SnodjupRekord record : records) {  // Loop through all records
            recordsByYear.putIfAbsent(record.year, new HashMap<>());    // Create "new year" if records for that year does'nt already exist
            Map<String, SnodjupRekord> yearRecords = recordsByYear.get(record.year);

            if (!yearRecords.containsKey(record.location) || yearRecords.get(record.location).snowDepth < record.snowDepth) {   // Only keep the record if it's the highest for that location.
                yearRecords.put(record.location, record);
            }
        }

        List<Integer> sortedYears = new ArrayList<>(recordsByYear.keySet());    // Sort the records by year
        Collections.sort(sortedYears);

        for (int year : sortedYears) {  // Output results for each year
            List<SnodjupRekord> yearRecords = new ArrayList<>(recordsByYear.get(year).values());
            Collections.sort(yearRecords);  // Sort by snow depth (descending) and location (alphabetical) (if two places has the same depth)
            System.out.println(year);

            for (int i = 0; i < Math.min(5, yearRecords.size()); i++) { // Print up to 5 records for the current year
                System.out.println(yearRecords.get(i));
            }
        }
    }
}
