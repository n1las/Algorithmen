# Auswertung QuickSort Benchmark (Woche 3)

## 1. Gemessene Laufzeiten (in Millisekunden)

| Elemente $n$ | 1. Middle (ms) | 2. Median-of-3 (ms) | 3. Linear Median (ms) |
| :--- | :--- | :--- | :--- |
| **1.000** | ~0,85 ms | ~0,18 ms | ~8,53 ms |
| **5.000** | ~0,47 ms | ~0,42 ms | ~9,71 ms |
| **10.000** | ~0,59 ms | ~0,63 ms | ~11,13 ms |
| **50.000** | ~3,26 ms | ~3,56 ms | ~50,71 ms |
| **100.000** | ~6,97 ms | ~7,52 ms | ~107,48 ms |

> **Hinweis zur Einheit:** Die Zahlen sind **Kommazahlen** in Millisekunden! `7,523` bedeutet **ca. 7,5 Millisekunden** (~0,0075 Sekunden), nicht 7523 Millisekunden.

---

## 2. Vergleich und $\mathcal{O}$-Notation

| Variante | Pivot-Wahl | Best Case | Average Case | Worst Case |
| :--- | :--- | :--- | :--- | :--- |
| **1. Middle** | $\mathcal{O}(1)$ | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n^2)$ |
| **2. Median-of-3** | $\mathcal{O}(1)$ | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n^2)$ |
| **3. Linear Time Median** | $\mathcal{O}(n)$ | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n \log n)$ |

---

## 3. Erklärung (Woran liegt das?)

### 1. Middle & Median-of-3 (Die Schnellsten)
- **Laufzeit:** Sehr schnell (nur ca. 7 ms bei 100.000 Elementen).
- **Grund:** Die Auswahl des Pivots kostet praktisch keine Zeit ($\mathcal{O}(1)$), da nur 1 Element bzw. 3 Elemente verglichen werden. Bei Zufallsdaten reicht das völlig aus, um das Array gut und gleichmäßig aufzuteilen.

### 2. Linear Time Median (Der Langsamste)
- **Laufzeit:** Deutlich am langsamsten (über 100 ms bei 100.000 Elementen, ca. 15-mal langsamer).
- **Grund:**
  - **Theorie vs. Praxis:** Garantiert theoretisch sogar im Worst Case $\mathcal{O}(n \log n)$, weil das Pivot immer gut balanciert ist.
  - **In der Praxis jedoch:** Die Pivot-Wahl selbst benötigt in jedem Rekursionsschritt $\mathcal{O}(n)$ Zeit. Das Erstellen von 5er-Gruppen, `ArrayList`s und Array-Kopien erzeugt einen riesigen konstanten Overhead, weshalb er in der Praxis viel langsamer ist.
