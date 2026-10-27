package vscode;

import java.util.Random;
import java.util.Scanner;

public class DataStru2_HomeWork_1 {
    

    public static void main(String[] args) {
        
        int n = 100_000;
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("=== Merge Sort Performance Benchmark (100,000 Elements) ===");
        System.out.println("Press Enter to start running the benchmarks ...");
        scanner.nextLine();

        int[] originalRandom = new int[n];
        int[] originalReverse = new int[n];
        int[] originalFully = new int[n];

        for (int i = 0; i < n; i++) {
            originalRandom[i] = random.nextInt();
            originalReverse[i] = n - i;
            originalFully[i] = i + 1;
        }

        int[] arrRandom = originalRandom.clone();
        long start1 = System.nanoTime();
        mergeSort(arrRandom, 0, n - 1);
        long end1 = System.nanoTime();
        double timeRandom = (end1 - start1) / 1000.0;

        int[] arrReverse = originalReverse.clone();
        long start2 = System.nanoTime();
        mergeSort(arrReverse, 0, n - 1);
        long end2 = System.nanoTime();
        double timeReverse = (end2 - start2) / 1000.0;

        int[] arrFully = originalFully.clone();
        long start3 = System.nanoTime();
        mergeSort(arrFully, 0, n - 1);
        long end3 = System.nanoTime();
        double timeFully = (end3 - start3) / 1000.0;

        System.out.println("\n+---------------+--------------------+--------------------+--------------------+");
        System.out.printf("| %-13s | %-18s | %-18s | %-18s |\n", "Algorithm", "Random", "Reversely Sorted", "Fully Sorted");
        System.out.println("+---------------+--------------------+--------------------+--------------------+");
        System.out.printf("| %-13s | %-11.3f ms | %-11.3f ms | %-11.3f ms |\n", "Merge Sort", timeRandom, timeReverse, timeFully);
        System.out.println("+---------------+--------------------+--------------------+--------------------+");

        scanner.close();
    }

    public static void mergeSort(int[] a, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;
            mergeSort(a, l, m);
            mergeSort(a, m + 1, r);
            merge(a, l, m, r);
        }
    }

    public static void merge(int[] a, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = a[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = a[m + 1 + j];

        int i = 0, j = 0;
        int k = l;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                a[k] = L[i];
                i++;
            } else {
                a[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            a[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            a[k] = R[j];
            j++;
            k++;
        }
    }
}
