package vscode;

import java.util.Random;
import java.util.Scanner;

public class DataStru2_HomeWork_1 {

    public static void main(String[] args) {
        
        int n = 100_000;
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("=== Sorting Algorithms Performance Benchmark (100,000 Elements) ===");
        System.out.println("Press Enter to start running the benchmarks ...");
        scanner.nextLine();

        int[] originalRandom = new int[n];
        int[] originalReverse = new int[n];
        int[] originalPartially = new int[n];
        int[] originalFully = new int[n];

        for (int i = 0; i < n; i++) {
            originalRandom[i] = random.nextInt();
            originalReverse[i] = n - i;
            originalFully[i] = i + 1;
            
            if (i < (int)(n * 0.8)) {
                originalPartially[i] = i + 1;
            } else {
                originalPartially[i] = random.nextInt();
            }
        }

        System.out.println("+---------------+--------------------+--------------------+--------------------+--------------------+");
        System.out.printf("| %-13s | %-18s | %-18s | %-18s | %-18s |\n", "Algorithm", "Random", "Reversely Sorted", "Partially Sorted", "Fully Sorted");
        System.out.println("+---------------+--------------------+--------------------+--------------------+--------------------+");

        runBenchmark("Selection Sort", originalRandom, originalReverse, originalPartially, originalFully, n, 1);

        runBenchmark("Insertion Sort", originalRandom, originalReverse, originalPartially, originalFully, n, 2);

        runBenchmark("Bubble Sort", originalRandom, originalReverse, originalPartially, originalFully, n, 3);

        runBenchmark("Quick Sort", originalRandom, originalReverse, originalPartially, originalFully, n, 4);

        runBenchmark("Merge Sort", originalRandom, originalReverse, originalPartially, originalFully, n, 5);

        System.out.println("+---------------+--------------------+--------------------+--------------------+--------------------+");

        scanner.close();
    }

    public static void runBenchmark(String name, int[] rand, int[] rev, int[] part, int[] full, int n, int algType) {
        double timeRandom = executeAlg(rand.clone(), n, algType);
        double timeReverse = executeAlg(rev.clone(), n, algType);
        double timePartially = executeAlg(part.clone(), n, algType);
        double timeFully = executeAlg(full.clone(), n, algType);

        System.out.printf("| %-13s | %-8.3f   ms | %-8.3f   ms | %-8.3f   ms | %-8.3f   ms |\n", 
                          name, timeRandom, timeReverse, timePartially, timeFully);
    }

    public static double executeAlg(int[] arr, int n, int algType) {
        long start = System.nanoTime();
        switch (algType) {
            case 1: selectionSort(arr); break;
            case 2: insertionSort(arr); break;
            case 3: bubbleSort(arr); break;
            case 4: quickSort(arr, 0, n - 1); break;
            case 5: mergeSort(arr, 0, n - 1); break;
        }
        long end = System.nanoTime();
        return (end - start) / 1_000_000.0;
        
    }

    public static void selectionSort(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = a[minIdx];
            a[minIdx] = a[i];
            a[i] = temp;
        }
    }

    public static void insertionSort(int[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    public static void bubbleSort(int[] a) {
        int n = a.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    public static void quickSort(int[] a, int low, int high) {
        if (low < high) {
            int pi = randomizedPartition(a, low, high);
            quickSort(a, low, pi - 1);
            quickSort(a, pi + 1, high);
        }
    }

    private static int randomizedPartition(int[] a, int low, int high) {
        Random rand = new Random();
        int pivotIndex = low + rand.nextInt(high - low + 1);
        int temp = a[pivotIndex];
        a[pivotIndex] = a[high];
        a[high] = temp;
        return partition(a, low, high);
    }

    private static int partition(int[] a, int low, int high) {
        int pivot = a[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (a[j] <= pivot) {
                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        int temp = a[i + 1];
        a[i + 1] = a[high];
        a[high] = temp;
        return i + 1;
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
