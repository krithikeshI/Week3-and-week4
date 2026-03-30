import java.util.*;

class Client {
    String name;
    int riskScore;
    double balance;

    Client(String name, int riskScore, double balance) {
        this.name = name;
        this.riskScore = riskScore;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return name + "(" + riskScore + ", ₹" + balance + ")";
    }
}

public class RiskSort {

    // -------- Bubble Sort (Ascending by riskScore) --------
    public static void bubbleSort(Client[] arr) {
        int n = arr.length;
        int swaps = 0;

        System.out.println("\nBubble Sort (Step-by-step swaps):");

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].riskScore > arr[j + 1].riskScore) {
                    // Swap
                    Client temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swaps++;
                    swapped = true;

                    // Visualization
                    printArray(arr);
                }
            }

            if (!swapped) break; // Optimization
        }

        System.out.println("Total Swaps: " + swaps);
    }

    // -------- Insertion Sort (Descending by riskScore + balance) --------
    public static void insertionSort(Client[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            Client key = arr[i];
            int j = i - 1;

            // Sort by riskScore DESC, then balance DESC
            while (j >= 0 && compare(arr[j], key) < 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }

        System.out.println("\nInsertion Sort Result (DESC risk + balance):");
        printArray(arr);
    }

    // Comparator for DESC sorting
    private static int compare(Client c1, Client c2) {
        if (c1.riskScore != c2.riskScore) {
            return Integer.compare(c1.riskScore, c2.riskScore);
        }
        return Double.compare(c1.balance, c2.balance);
    }

    // -------- Top 10 High Risk --------
    public static void topHighRisk(Client[] arr, int k) {
        System.out.println("\nTop " + k + " High Risk Clients:");
        for (int i = 0; i < Math.min(k, arr.length); i++) {
            System.out.println(arr[i]);
        }
    }

    // -------- Utility --------
    public static void printArray(Client[] arr) {
        for (Client c : arr) {
            System.out.print(c + "  ");
        }
        System.out.println();
    }

    // -------- Main --------
    public static void main(String[] args) {

        Client[] clients = {
                new Client("clientC", 80, 50000),
                new Client("clientA", 20, 20000),
                new Client("clientB", 50, 30000)
        };

        System.out.println("Original Clients:");
        printArray(clients);

        // Bubble Sort (Ascending)
        bubbleSort(clients);

        System.out.println("\nAfter Bubble Sort (Ascending):");
        printArray(clients);

        // Insertion Sort (Descending)
        insertionSort(clients);

        // Top 3 (can change to 10)
        topHighRisk(clients, 10);
    }
}