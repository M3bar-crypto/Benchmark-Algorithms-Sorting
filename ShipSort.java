package vscode;

import java.util.Arrays;
import java.util.Scanner;

public class ShipSort {

    public static <T extends Comparable<T>> void selectionSort(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[min]) < 0) {
                    min = j;
                }
            }
            T tmp = arr[i];
            arr[i] = arr[min];
            arr[min] = tmp;
        }
    }

    public static <T extends Comparable<T>> void insertionSort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            T key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].compareTo(key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public static <T> void printArray(T[] arr) {
        for (T element : arr) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    private static <T extends Comparable<T>> void runSession(Scanner scanner, T[] data) {
        System.out.println("\nUnsorted:");
        printArray(data);

        System.out.println("\nWhich algorithm?");
        System.out.println("  1) Selection Sort");
        System.out.println("  2) Insertion Sort");
        System.out.println("  3) Bubble Sort");
        System.out.println("  4) All three (with timing)");
        System.out.print("Choice (1-4): ");

        String alg = scanner.nextLine().trim();

        System.out.println();
        switch (alg) {
            case "1":
                runBenchmark("Selection Sort", ShipSort::selectionSort, data);
                break;
            case "2":
                runBenchmark("Insertion Sort", ShipSort::insertionSort, data);
                break;
            case "3":
                runBenchmark("Bubble Sort", ShipSort::bubbleSort, data);
                break;
            case "4":
                runBenchmark("Selection Sort", ShipSort::selectionSort, data);
                runBenchmark("Insertion Sort", ShipSort::insertionSort, data);
                runBenchmark("Bubble Sort", ShipSort::bubbleSort, data);
                break;
            default:
                System.out.println("Invalid choice, please enter 1, 2, 3 or 4.");
                break;
        }
    }

    private static <T extends Comparable<T>> void runBenchmark(
            String name, java.util.function.Consumer<T[]> sort, T[] data) {
        T[] copy = Arrays.copyOf(data, data.length);
        long start = System.nanoTime();
        sort.accept(copy);
        long time = System.nanoTime() - start;
        System.out.printf("%s: %d ns (%.3f ms)%n", name, time, time / 1e6);
        if (copy.length <= 20) {
            System.out.print("Result: ");
            printArray(copy);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("      Welcome to ShipSort Terminal");
        System.out.println("==========================================");

        boolean running = true;
        while (running) {
            System.out.println("\nWhat do you want to sort?");
            System.out.println("  1) Shipping costs (Double)");
            System.out.println("  2) Delivery days (Integer)");
            System.out.println("  3) Exit");
            System.out.print("Choice (1-3): ");

            String choice = scanner.nextLine().trim();

            if (choice.equals("3")) {
                System.out.println("Goodbye!");
                running = false;
                continue;
            }

            if (!choice.equals("1") && !choice.equals("2")) {
                System.out.println("Invalid choice, please enter 1, 2 or 3.");
                continue;
            }

            int size = readInt(scanner, "How many shipments? ");
            if (choice.equals("1")) {
                Double[] costs = new Double[size];
                System.out.println("Enter " + size + " shipping costs (e.g. 12.50):");
                for (int i = 0; i < size; i++) {
                    costs[i] = readDouble(scanner, "Cost #" + (i + 1) + ": ");
                }
                runSession(scanner, costs);
            } else {
                Integer[] days = new Integer[size];
                System.out.println("Enter " + size + " delivery days (whole numbers):");
                for (int i = 0; i < size; i++) {
                    days[i] = readInt(scanner, "Days #" + (i + 1) + ": ");
                }
                runSession(scanner, days);
            }
        }

        scanner.close();
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value > 0) {
                    return value;
                }
                System.out.println("Please enter a positive number.");
            } catch (NumberFormatException e) {
                System.out.println("Not a valid number, try again.");
            }
        }
    }

    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value >= 0) {
                    return value;
                }
                System.out.println("Please enter a non-negative number.");
            } catch (NumberFormatException e) {
                System.out.println("Not a valid number, try again.");
            }
        }
    }
}
