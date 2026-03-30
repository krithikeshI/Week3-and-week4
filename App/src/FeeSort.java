import java.util.*;

class Transaction {
    String id;
    double fee;
    String timestamp;

    Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class FeeSort {

    // -------- Bubble Sort (by fee) --------
    public static void bubbleSort(List<Transaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    // Swap
                    Transaction temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);

                    swaps++;
                    swapped = true;
                }
            }

            // Early termination (optimized)
            if (!swapped) break;
        }

        System.out.println("\nBubble Sort Result:");
        printList(list);
        System.out.println("Passes: " + passes + ", Swaps: " + swaps);
    }

    // -------- Insertion Sort (by fee + timestamp) --------
    public static void insertionSort(List<Transaction> list) {
        int n = list.size();

        for (int i = 1; i < n; i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            // Compare by fee first, then timestamp
            while (j >= 0 && compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }

        System.out.println("\nInsertion Sort Result (fee + timestamp):");
        printList(list);
    }

    // Comparator: fee → timestamp
    private static int compare(Transaction t1, Transaction t2) {
        if (t1.fee != t2.fee) {
            return Double.compare(t1.fee, t2.fee);
        }
        return t1.timestamp.compareTo(t2.timestamp);
    }

    // -------- High-fee Outliers --------
    public static void findOutliers(List<Transaction> list) {
        System.out.println("\nHigh-fee Outliers (> $50):");
        boolean found = false;

        for (Transaction t : list) {
            if (t.fee > 50) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("None");
        }
    }

    // -------- Utility --------
    public static void printList(List<Transaction> list) {
        for (Transaction t : list) {
            System.out.print(t + "  ");
        }
        System.out.println();
    }

    // -------- Main --------
    public static void main(String[] args) {

        List<Transaction> transactions = new ArrayList<>();

        // Sample Input
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        System.out.println("Original Transactions:");
        printList(transactions);

        int size = transactions.size();

        // Choose sorting method
        if (size <= 100) {
            bubbleSort(transactions);
        } else if (size <= 1000) {
            insertionSort(transactions);
        }

        // Outlier detection
        findOutliers(transactions);
    }
}