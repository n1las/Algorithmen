package Woche1;


public class LinearSearch {
    public static void main(String[] args) {
        int[] groessen = {10, 100, 1000, 10000};

        for (int n : groessen) {
            System.out.println("--- Messungen fuer n = " + n + " ---");

            // A) Allgemeine Integer-Arrays (Suche nach einer ganzen Zahl, z. B. 42)
            int[] arrVorne = RandomArray.erstelleIntVorne(n, 42);
            int[] arrHinten = RandomArray.erstelleIntHinten(n, 42);
            int[] arrNichtDa = RandomArray.erstelleArrayOne(n);

            // B) Binär-Arrays (Nur Nullen und Einsen, Suche nach der ersten 0)
            int[] binVorne = RandomArray.erstelleBinaerVorne(n);
            int[] binHinten = RandomArray.erstelleBinaerHinten(n);

            System.out.print("Int Vorne: ");
            linearSearch(arrVorne, 42);
            System.out.print("Int Hinten: ");
            linearSearch(arrHinten, 42);
            System.out.print("Int Nicht da: ");
            linearSearch(arrNichtDa, 42);

            System.out.print("Binaer Vorne: ");
            linearSearch(binVorne, 0);
            System.out.print("Binaer Hinten: ");
            linearSearch(binHinten, 0);

            // C) Sortieren auf Zufallsarrays
            int[] sortArr1 = RandomArray.erstelleZufaelligesArray(n);
            int[] sortArr2 = RandomArray.erstelleZufaelligesArray(n);

            System.out.print("BubbleSort: ");
            bubbleSort(sortArr1);
            System.out.print("SelectionSort: ");
            selectionSort(sortArr2);
            System.out.println();
        }
    }

    public static void linearSearch(int[] arr, int target) {
        long startTime = System.nanoTime();
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == target) {
                long searchTime = System.nanoTime() - startTime;
                printResult(searchTime, true, target);
                return;
            }
        }
        long searchTime = System.nanoTime() - startTime;
        printResult(searchTime, false, target);
    }

    public static void bubbleSort(int[] arr) {
        int temp;
        long startTime = System.nanoTime();
        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = 0; j < arr.length - 1 - i; j++) {
               if(arr[j] > arr[j+1]) {
                   temp = arr[j];
                   arr[j] = arr[j+1];
                   arr[j+1] = temp;
               }
            }
        }
        long endTime = System.nanoTime();
        printSortResult(endTime - startTime, arr);
    }

    public static void selectionSort(int[] arr) {
        int temp;

        long startTime = System.nanoTime();

        for(int i = 0; i < arr.length - 1; i++){
            int min = i;
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[min] > arr[j]) {
                    min = j;
                }
            }
            temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
        long endTime = System.nanoTime();
        printSortResult(endTime - startTime, arr);
    }

    // Die neue Methode für die Ausgabe
    public static void printResult(long time, boolean bestanden, int target) {
        if (bestanden) {
            System.out.println("Zeit bis " + target + " gefunden wurde: " + time + "ns");
        } else {
            System.out.println("Zahl " + target + " wurde nach " + time + "ns nicht gefunden");
        }
    }

    public static void printSortResult(long time, int[] sorted) {
        System.out.println("Array wurde in " + time + "ns" + " sortiert");
        // Nur bei kleinen Arrays ausgeben, damit die Konsole lesbar bleibt
        if (sorted.length <= 20) {
            for(int i: sorted) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}