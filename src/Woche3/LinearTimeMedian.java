package Woche3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinearTimeMedian {
    /**
     * Startmethode: Sucht den Median eines Arrays.
     */
    public static int getMedian(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array darf nicht leer sein!");
        }
        // Der Median ist das Element in der Mitte (1-basierter Rang k)
        int k = (array.length + 1) / 2;
        return select(array, k);
    }

    /**
     * Berechnet das k-kleinste Element aus S (exakt wie auf Vorlesungsfolie 102).
     * k ist 1-basiert (1 = kleinstes Element, S.length = größtes Element).
     */
    public static int select(int[] S, int k) {
        int n = S.length;

        // Schritt 0: Basisfall - Wenn das Array klein ist (< 50), direkt sortieren!
        if (n < 50) {
            int[] copy = S.clone();
            Arrays.sort(copy);
            return copy[k - 1]; // k - 1 wegen 0-basierter Array-Indizierung in Java
        }

        // Schritt 1 & 2: In 5er-Gruppen teilen und von jeder den Median holen
        int numGroups = (int) Math.ceil((double) n / 5.0);
        int[] medians = new int[numGroups];

        for (int i = 0; i < numGroups; i++) {
            int start = i * 5; // start index im array S
            int end = Math.min(start + 5, n); // Absicherung falls die letzte gruppe nicht 5 Elemente hat
            int groupSize = end - start; // ebenfalls absicherung falls gruopSize < 5

            int[] group = new int[groupSize];
            System.arraycopy(S, start, group, 0, groupSize); // Quellsarr, quellStartIndex, zielarr, startindexZielarr, anzahl an elmenten
            Arrays.sort(group); // 5 Zahlen sortieren dauert quasi 0 ms

            // Die Mitte der 5er-Gruppe einsammeln
            medians[i] = group[groupSize / 2];
        }

        // Schritt 3: Rekursiv den Median der Mediane m berechnen
        int m = select(medians, (numGroups + 1) / 2);

        // Schritt 4: Aufteilen in Kiste A (< m), Kiste B (== m), Kiste C (> m)
        // Vorgabe aus dem Übungsblatt: ArrayList verwenden!
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();
        List<Integer> listC = new ArrayList<>();

        for (int x : S) {
            if (x < m) {
                listA.add(x);
            } else if (x == m) {
                listB.add(x);
            } else {
                listC.add(x);
            }
        }

        // Schritt 5: Prüfen, in welcher Kiste das k-kleinste Element liegt
        if (listA.size() >= k) {
            // Es liegt in Kiste A
            return select(toIntArray(listA), k);
        } else if (listA.size() + listB.size() >= k) {
            // Treffer! Es ist genau unser Pivot m
            return m;
        } else {
            // Es liegt in Kiste C -> Rang k um die übersprungenen Elemente reduzieren
            int newK = k - listA.size() - listB.size();
            return select(toIntArray(listC), newK);
        }
    }

    /**
     * Hilfsmethode: Wandelt List<Integer> via toArray() wieder in ein int[] um.
     * (Genau wie auf dem Übungsblatt gefordert!)
     */
    private static int[] toIntArray(List<Integer> list) {
        Integer[] boxed = list.toArray(new Integer[0]);
        int[] result = new int[boxed.length];
        for (int i = 0; i < boxed.length; i++) {
            result[i] = boxed[i];
        }
        return result;
    }
}
