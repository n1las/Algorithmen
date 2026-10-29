package Woche1;

import java.util.Arrays;
import java.util.Random;

public class RandomArray {

    // 1. Binär-Array: Die erste 0 sitzt ganz am Anfang
    public static int[] erstelleBinaerVorne(int n) {
        int[] arr = new int[n];
        arr[0] = 0; // Erste Null direkt vorne
        for (int i = 1; i < n; i++) {
            arr[i] = 1; // Der Rest sind Einsen
        }
        return arr;
    }

    // 2. Binär-Array: Die erste 0 sitzt ganz am Ende
    public static int[] erstelleBinaerHinten(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n - 1; i++) {
            arr[i] = 1; // Überall Einsen
        }
        arr[n - 1] = 0; // Nur das letzte Element ist eine Null
        return arr;
    }

    // 3. Normales Array: Gesuchte Zahl (gesucht) sitzt ganz vorne
    public static int[] erstelleIntVorne(int n, int gesucht) {
        int[] arr = new int[n];
        arr[0] = gesucht;
        for (int i = 1; i < n; i++) {
            arr[i] = i; // Einfache Füllzahlen
        }
        return arr;
    }

    // 4. Normales Array: Gesuchte Zahl (gesucht) sitzt ganz hinten
    public static int[] erstelleIntHinten(int n, int gesucht) {
        int[] arr = new int[n];
        for (int i = 0; i < n - 1; i++) {
            arr[i] = i;
        }
        arr[n - 1] = gesucht; // Letztes Element ist der Treffer
        return arr;
    }

    public static int[] erstelleZufaelligesArray(int n) {
        int[] arr = new int[n];
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            // Generiert eine Zufallszahl zwischen 0 und 999 (können Sie anpassen)
            arr[i] = rand.nextInt(1000);
        }
        return arr;
    }

    public static int[] erstelleArrayOne(int n) {
        int arr[] = new int[n];
        Arrays.fill(arr, 7);
        return arr;
    }


}