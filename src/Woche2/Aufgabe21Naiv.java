package Woche2;

import java.util.Scanner;

public class Aufgabe21Naiv {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, x, y, z;

        do {
            System.out.print("Bitte eine Zahl n >= 0 eingeben: ");
            n = scanner.nextInt();
        } while (n < 0);

        x = 1;
        y = n;

        long startTime = System.nanoTime();
        while (y > 0) {
            x = x * 2;
            y--;
        }
        z = x;
        long endTime = System.nanoTime();

        System.out.println("Ergebnis z = " + z);
        System.out.println("Benötigte Zeit: " + (endTime - startTime) + " ns");
    }
}
