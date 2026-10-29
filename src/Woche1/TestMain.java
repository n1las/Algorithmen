package Woche1;

public class TestMain {
    public static void main(String[] args) {
        int n = 5; // Kleine Größe zum Testen

        System.out.println("--- Test der Generatoren ---");

        druckeArray("1. Binär (Vorne):", RandomArray.erstelleBinaerVorne(n));
        druckeArray("2. Binär (Hinten):", RandomArray.erstelleBinaerHinten(n));
        druckeArray("3. Int (Vorne, gesucht: 42):", RandomArray.erstelleIntVorne(n, 42));
        druckeArray("4. Int (Hinten, gesucht: 42):", RandomArray.erstelleIntHinten(n, 42));
        druckeArray("5. Zufällig (Unsortiert):", RandomArray.erstelleZufaelligesArray(n));
    }

    // Kleine Hilfsmethode zur sauberen Ausgabe auf der Konsole
    private static void druckeArray(String beschreibung, int[] arr) {
        System.out.print(beschreibung + " [ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("]");
    }
}