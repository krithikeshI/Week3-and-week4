import java.util.*;

public class TransactionLog {

    // -------- LINEAR SEARCH --------
    public static void linearSearch(String[] arr, String target) {
        int first = -1, last = -1;
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;

            if (arr[i].equals(target)) {
                if (first == -1) first = i;
                last = i;
            }
        }

        System.out.println("\nLinear Search:");
        System.out.println("First Occurrence: " + first);
        System.out.println("Last Occurrence: " + last);
        System.out.println("Comparisons: " + comparisons);
    }

    // -------- BINARY SEARCH (First Occurrence) --------
    public static int binaryFirst(String[] arr, String target, Counter counter) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            counter.count++;
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                high = mid - 1; // search left
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // -------- BINARY SEARCH (Last Occurrence) --------
    public static int binaryLast(String[] arr, String target, Counter counter) {
        int low = 0, high = arr.length - 1;
        int result = -1;

        while (low <= high) {
            counter.count++;
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                low = mid + 1; // search right
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    // -------- COUNT OCCURRENCES --------
    public static void binarySearch(String[] arr, String target) {
        Counter counter = new Counter();

        int first = binaryFirst(arr, target, counter);
        int last = binaryLast(arr, target, counter);

        int count = (first == -1) ? 0 : (last - first + 1);

        System.out.println("\nBinary Search:");
        System.out.println("First Occurrence: " + first);
        System.out.println("Last Occurrence: " + last);
        System.out.println("Total Count: " + count);
        System.out.println("Comparisons: " + counter.count);
    }

    // -------- Helper Counter Class --------
    static class Counter {
        int count = 0;
    }

    // -------- MAIN --------
    public static void main(String[] args) {

        String[] logs = {"accB", "accA", "accB", "accC"};

        System.out.println("Original Logs:");
        System.out.println(Arrays.toString(logs));

        // Linear Search
        linearSearch(logs, "accB");

        // Sort for Binary Search
        Arrays.sort(logs);
        System.out.println("\nSorted Logs:");
        System.out.println(Arrays.toString(logs));

        // Binary Search
        binarySearch(logs, "accB");
    }
}