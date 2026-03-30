import java.util.*;

public class RiskBinary {

    // -------- LINEAR SEARCH --------
    public static void linearSearch(int[] arr, int target) {
        int comparisons = 0;
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Found at index: " + i);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Not found");
        }

        System.out.println("Linear Comparisons: " + comparisons);
    }

    // -------- BINARY SEARCH FLOOR --------
    public static int floor(int[] arr, int target, Counter c) {
        int low = 0, high = arr.length - 1;
        int ans = -1;

        while (low <= high) {
            c.count++;
            int mid = low + (high - low) / 2;

            if (arr[mid] <= target) {
                ans = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    // -------- BINARY SEARCH CEILING --------
    public static int ceiling(int[] arr, int target, Counter c) {
        int low = 0, high = arr.length - 1;
        int ans = -1;

        while (low <= high) {
            c.count++;
            int mid = low + (high - low) / 2;

            if (arr[mid] >= target) {
                ans = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    // -------- INSERTION INDEX (lower_bound) --------
    public static int insertionPoint(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low; // insertion index
    }

    // -------- COUNTER --------
    static class Counter {
        int count = 0;
    }

    // -------- MAIN --------
    public static void main(String[] args) {

        int[] unsorted = {50, 10, 100, 25};
        int target = 30;

        System.out.println("Unsorted Risk Bands: " + Arrays.toString(unsorted));
        linearSearch(unsorted, target);

        int[] sorted = {10, 25, 50, 100};
        System.out.println("\nSorted Risk Bands: " + Arrays.toString(sorted));

        Counter c = new Counter();

        int fl = floor(sorted, target, c);
        int ce = ceiling(sorted, target, c);

        System.out.println("\nBinary Search:");
        System.out.println("Floor(" + target + ") = " + fl);
        System.out.println("Ceiling(" + target + ") = " + ce);
        System.out.println("Comparisons: " + c.count);

        int idx = insertionPoint(sorted, target);
        System.out.println("Insertion Index for " + target + ": " + idx);
    }
}