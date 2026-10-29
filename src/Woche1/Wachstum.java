package Woche1;

public class Wachstum {


    public static void main(String[] args) {
        int[] zahlen = {10, 20, 30, 40, 50, 60};

        // Kopfzeile
        System.out.print("n =\t\t");
        for (int n : zahlen) {
            System.out.print(n + "\t\t");
        }
        System.out.println("\n---------------------------------------------------------------------------------------------------------");

        // 1. log(n) (Zweierlogarithmus)
        System.out.print("log(n)\t\t");
        for (int i : zahlen) {
            double erg = Math.log(i) / Math.log(2); // Math.log ist der Logarithmus von e
            System.out.print(umrechnen(erg) + "\t");
        }
        System.out.println();

        // 2. n
        System.out.print("n\t\t");
        for (int i : zahlen) {
            System.out.print(umrechnen(i) + "\t");
        }
        System.out.println();

        // 3. n * log(n)
        System.out.print("n*log(n)\t");
        for (int i : zahlen) {
            double erg = i * (Math.log(i) / Math.log(2));
            System.out.print(umrechnen(erg) + "\t");
        }
        System.out.println();

        // 4. n^2
        System.out.print("n^2\t\t");
        for (int i : zahlen) {
            System.out.print(umrechnen(i * i) + "\t");
        }
        System.out.println();

        // 5. n^3
        System.out.print("n^3\t\t");
        for (int i : zahlen) {
            System.out.print(umrechnen(i * i * i) + "\t");
        }
        System.out.println();

        // 6. 2^n
        System.out.print("2^n\t\t");
        for (int i : zahlen) {
            double erg = Math.pow(2.0, i);
            System.out.print(umrechnen(erg) + "\t");
        }
        System.out.println();

        // 7. n!
        System.out.print("n!\t\t");
        for (int i : zahlen) {
            double erg = berechneFakultaet(i);
            System.out.print(umrechnen(erg) + "\t");
        }
        System.out.println();
    }

    // Deine Methode, nur mit 'double' statt 'long' gegen den Zahlenüberlauf
    public static double berechneFakultaet(int n) {
        double ergebnis = 1;
        for (int i = 1; i <= n; i++) {
            ergebnis *= i;
        }
        return ergebnis;
    }

    // Rechnet Mikrosekunden Schritt für Schritt in größere Einheiten um
    public static String umrechnen(double wert) {
        if (wert < 1000) {
            return Math.round(wert * 100.0) / 100.0 + " µs"; // runde auf die 2te Nachkommastelle indem man sie erst vors Komm holt und dann wieder nach hinten schiebt
        }
        wert = wert / 1000; // in Millisekunden
        if (wert < 1000) {
            return Math.round(wert * 100.0) / 100.0 + " ms";
        }
        wert = wert / 1000; // in Sekunden
        if (wert < 60) {
            return Math.round(wert * 100.0) / 100.0 + " s";
        }
        wert = wert / 60; // in Minuten
        if (wert < 60) {
            return Math.round(wert * 100.0) / 100.0 + " min";
        }
        wert = wert / 60; // in Stunden
        if (wert < 24) {
            return Math.round(wert * 100.0) / 100.0 + " h";
        }
        wert = wert / 24; // in Tage
        if (wert < 365) {
            return Math.round(wert * 100.0) / 100.0 + " d";
        }
        wert = wert / 365; // in Jahre
        if (wert < 100) {
            return Math.round(wert * 100.0) / 100.0 + " J.";
        }
        wert = wert / 100; // in Jahrhunderte
        if (wert < 1000) {
            return Math.round(wert * 100.0) / 100.0 + " Jh.";
        }

        // Alles was 1000 Jahrhunderte(100.000 Jahre) oder größer ist (z.B. ab 30!) wird als unendlich ausgegeben
        return "∞\t";
    }
}