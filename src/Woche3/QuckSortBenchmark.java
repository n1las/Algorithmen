package Woche3;

import java.util.Random;

public class QuckSortBenchmark {

    public static void main(String[] args) {
        // Testgrößen: von 1.000 bis 50.000 Zahlen
        int[] testSizes = { 1000, 5000, 10000, 50000,100000 };

        System.out.println("==========================================================================");
        System.out.printf("%-10s | %-15s | %-18s | %-20s%n",
                "Elemente n", "1. Middle (ms)", "2. Median-of-3 (ms)", "3. Linear Median (ms)");
        System.out.println("==========================================================================");

        Random rnd = new Random(42);

        for (int n : testSizes) {
            int[] original = new int[n];
            for (int i = 0; i < n; i++) original[i] = rnd.nextInt(n * 10);

            // Test 1: Middle
            int[] test1 = original.clone();
            long start = System.nanoTime();
            QuickSortVariants.sort(test1, QuickSortVariants.Strategy.MIDDLE);
            long end = System.nanoTime();
            double timeMiddle = (end - start) / 1_000_000.0;

            // Test 2: Median-of-3
            int[] test2 = original.clone();
            start = System.nanoTime();
            QuickSortVariants.sort(test2, QuickSortVariants.Strategy.MEDIAN_OF_THREE);
            end = System.nanoTime();
            double timeM3 = (end - start) / 1_000_000.0;

            // Test 3: Linear Time Median
            int[] test3 = original.clone();
            start = System.nanoTime();
            QuickSortVariants.sort(test3, QuickSortVariants.Strategy.LINEAR_TIME_MEDIAN);
            end = System.nanoTime();
            double timeLinear = (end - start) / 1_000_000.0;

            System.out.printf("%-10d | %-15.3f | %-18.3f | %-20.3f%n",
                    n, timeMiddle, timeM3, timeLinear);
        }
        System.out.println("==========================================================================");
    }
}
