package Woche2;
import java.util.Scanner;
public class Aufgabe21 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, x, y, z;

        do {
            System.out.print("Bitte eine Zahl n >= 0 eingeben: ");
            n = scanner.nextInt();
        } while (n < 0);

        x = 2; y = n; z = 1;

        long startTime = System.nanoTime();
        // Schleifeninvariante: z * x^y = 2^n und y >= 0
        while (y > 0) {
            if (y % 2 == 1) {
                z = z * x;
                y = y - 1;
            } else {
                x = x * x;
                y = y / 2;
            }
        }
        long endTime = System.nanoTime();

        System.out.println("Ergebnis z = " + z);
        System.out.println("Benötigte Zeit: " + (endTime - startTime) + " ns");
    }
}
