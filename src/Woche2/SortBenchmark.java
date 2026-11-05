package Woche2;

import Woche1.RandomArray;
import java.util.Random;

public class SortBenchmark {

    public static void main(String[] args) {
        InsertionSort sorter = new InsertionSort();

        // 1. Insertion Sort: Best-Case, Worst-Case und Average-Case
        System.out.println("--- Insertion Sort Benchmark ---");
        int[] groessen = {10, 100, 1000, 5000, 10000};

        for (int n : groessen) {
            int[] bestCase = erstelleBestCase(n);
            int[] worstCase = erstelleWorstCase(n);

            long startBest = System.nanoTime();
            sorter.insertionSort(bestCase);
            long zeitBest = System.nanoTime() - startBest;

            long startWorst = System.nanoTime();
            sorter.insertionSort(worstCase);
            long zeitWorst = System.nanoTime() - startWorst;

            System.out.println("n = " + n + " | Best-Case: " + zeitBest + " ns | Worst-Case: " + zeitWorst + " ns");
        }

        // Average-Case mit zufälliger Array-Länge
        int[] avgCase = erstelleAverageCase();
        long startAvg = System.nanoTime();
        sorter.insertionSort(avgCase);
        long zeitAvg = System.nanoTime() - startAvg;
        System.out.println("Average-Case (Länge " + avgCase.length + "): " + zeitAvg + " ns");

        System.out.println();

        // 2. Aufgabe 2.4: Benchmark bis 5000 in 10er-Schritten
        System.out.println("--- Aufgabe 2.4: Benchmark (10 bis 5000) ---");
        System.out.println("n\tInsertionSort\tSelectionSort\tBubbleSort");

        for (int n = 10; n <= 5000; n += 10) {
            int[] arrInsertion = RandomArray.erstelleZufaelligesArray(n);
            int[] arrSelection = RandomArray.erstelleZufaelligesArray(n);
            int[] arrBubble = RandomArray.erstelleZufaelligesArray(n);

            long startIns = System.nanoTime();
            sorter.insertionSort(arrInsertion);
            long zeitIns = System.nanoTime() - startIns;

            long startSel = System.nanoTime();
            selectionSort(arrSelection);
            long zeitSel = System.nanoTime() - startSel;

            long startBub = System.nanoTime();
            bubbleSort(arrBubble);
            long zeitBub = System.nanoTime() - startBub;

            System.out.println(n + "\t" + zeitIns + "\t" + zeitSel + "\t" + zeitBub);
        }
    }

    // Best-Case: Array ist schon sortiert
    public static int[] erstelleBestCase(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }

    // Worst-Case: Array ist rückwärts sortiert
    public static int[] erstelleWorstCase(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = n - i;
        }
        return arr;
    }

    // Average-Case: Zufälliges Array mit zufälliger Länge
    public static int[] erstelleAverageCase() {
        Random rand = new Random();
        int laenge = rand.nextInt(5000) + 1;
        return RandomArray.erstelleZufaelligesArray(laenge);
    }

    public static void selectionSort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    public static void bubbleSort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}
