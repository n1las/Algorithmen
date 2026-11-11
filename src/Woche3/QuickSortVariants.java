package Woche3;

import java.util.Arrays;

public class QuickSortVariants {

    public enum Strategy {
        MIDDLE,
        MEDIAN_OF_THREE,
        LINEAR_TIME_MEDIAN
    }

    public static void sort(int[] a, Strategy strategy) {
        if (a == null || a.length <= 1) return;
        quicksort(a, 0, a.length - 1, strategy);
    }

    private static void quicksort(int[] a, int left, int right, Strategy strategy) {
        int i = left;
        int j = right;

        // Hier wählen wir das Pivot nach der gewählten Strategie:
        int pivot = pickPivot(a, left, right, strategy);

        // Original-Partitionierung aus Vorlesungsfolie 95
        do {
            while (a[i] < pivot) i++;
            while (a[j] > pivot) j--;
            if (i <= j) {
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
                i++;
                j--;
            }
        } while (i <= j);

        // Rekursive Aufrufe für linke und rechte Teilhälfte
        if (left < j) quicksort(a, left, j, strategy);
        if (i < right) quicksort(a, i, right, strategy);
    }

    private static int pickPivot(int[] a, int left, int right, Strategy strategy) {
        switch (strategy) {
            case MIDDLE:
                // Variante 1: Einfach die Mitte nehmen (Folie 95)
                return a[(left + right) / 2];

            case MEDIAN_OF_THREE:
                // Variante 2: Median aus erstem, mittlerem und letztem Element (Folie 100)
                int mid = (left + right) / 2;
                int first = a[left];
                int middle = a[mid];
                int last = a[right];

                if ((first <= middle && middle <= last) || (last <= middle && middle <= first)) return middle;
                if ((middle <= first && first <= last) || (last <= first && first <= middle)) return first;
                return last;

            case LINEAR_TIME_MEDIAN:
                // Variante 3: Den echten Median mit Aufgabe 3.1 berechnen
                int length = right - left + 1;
                // Für winzige Teilarrays lohnt sich der Aufruf gar nicht
                if (length < 10) {
                    return a[(left + right) / 2];
                }
                int[] subArray = Arrays.copyOfRange(a, left, right + 1); // start inklusiv ende exklusiv
                return LinearTimeMedian.getMedian(subArray);

            default:
                return a[(left + right) / 2];
        }
    }
}
