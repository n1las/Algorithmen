# Auswertung: Übungsblatt 1 – Algorithmen und Datenstrukturen

In diesem Dokument werden die gemessenen Laufzeiten der Implementierungen aus Woche 1 ausgewertet und mit den theoretischen Erwartungen verglichen.

---

## 1. Messwerte aus der Konsole

Die Messungen wurden mit der Klasse `LinearSearch` für die Eingabegrößen $n \in \{10, 100, 1000, 10000\}$ durchgeführt:

| Eingabegröße $n$ | Int Vorne (Best Case) | Int Hinten (Worst Case) | Int Nicht da | Binär Vorne | Binär Hinten | BubbleSort | SelectionSort |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **$n = 10$** | ~230 ns | ~360 ns | ~330 ns | ~170 ns | ~290 ns | ~2.550 ns | ~2.520 ns |
| **$n = 100$** | ~230 ns | ~1.830 ns | ~1.810 ns | ~180 ns | ~1.830 ns | ~187.150 ns | ~85.540 ns |
| **$n = 1.000$** | ~190 ns | ~14.170 ns | ~13.510 ns | ~170 ns | ~13.280 ns | ~4.129.546 ns (~4,1 ms) | ~2.120.418 ns (~2,1 ms) |
| **$n = 10.000$** | ~160 ns | ~111.300 ns | ~115.940 ns | ~130 ns | ~111.280 ns | ~90.697.687 ns (~90,7 ms) | ~52.922.692 ns (~52,9 ms) |

---

## 2. Bewertung der Ergebnisse

### Aufgabe 1.1: Lineare Suche

> **Leitfrage:** *Entspricht das Ergebnis Ihren Erwartungen?*

**Ja, die Ergebnisse entsprechen vollkommen den theoretischen Erwartungen:**

1. **Element ganz vorne (Best Case):**
   - **Theorie:** Komplexität $\mathcal{O}(1)$ (konstanter Aufwand), da das Element direkt im ersten Durchlauf gefunden und die Methode beendet wird.
   - **Praxis:** Die gemessene Zeit bleibt über alle Eingabegrößen ($n=10$ bis $n=10.000$) nahezu konstant im Bereich von ca. 150 bis 250 ns.

2. **Element ganz hinten / nicht vorhanden (Worst Case):**
   - **Theorie:** Komplexität $\mathcal{O}(n)$ (linearer Aufwand). Bei einer Verzehnfachung der Eingabegröße $n$ sollte sich auch die benötigte Zeit etwa verzehnfachen.
   - **Praxis:** 
     - Von $n=100$ (~1,8 µs) auf $n=1.000$ (~14 µs) steigt die Zeit um ca. Faktor 8.
     - Von $n=1.000$ (~14 µs) auf $n=10.000$ (~115 µs) steigt die Zeit um ca. Faktor 8.
     - Die lineare Skalierung ist in der echten Zeit sehr deutlich sichtbar. Kleinere Abweichungen vom exakten Faktor 10 sind im Nanosekundenbereich völlig normal (CPU-Caching, Hintergrundprozesse des Betriebssystems).

3. **Vergleich normales Integer-Array vs. Binär-Array:**
   - **Theorie:** Beide Arrays bestehen aus `int`-Werten und werden mit der gleichen Logik durchsucht. Es ist kein signifikanter Unterschied zu erwarten.
   - **Praxis:** Die gemessenen Zeiten für das Binär-Array (Suche nach 0) und das normale Integer-Array (Suche nach 42) liegen jeweils auf demselben Niveau.

---

### Aufgabe 1.2 & 1.3: BubbleSort vs. SelectionSort

> **Leitfrage:** *Entspricht das Ergebnis Ihren Erwartungen?*

**Ja, das Verhalten spiegelt die theoretische Komplexität wider:**

1. **Quadratisches Wachstum $\mathcal{O}(n^2)$:**
   - Beide Algorithmen vergleichen in zwei verschachtelten Schleifen Elemente miteinander. Bei einer Verzehnfachung von $n$ wächst die Anzahl der Vergleiche theoretisch um das Hundertfache ($10^2 = 100$).
   - In den Messungen sieht man den enormen Zeitsprung:
     - Bei $n=10$: beide brauchen nur ca. 2,5 µs.
     - Bei $n=1.000$: ca. 2 bis 4 ms.
     - Bei $n=10.000$: ca. 50 bis 90 ms.
   - Das starke, nicht-lineare Ansteigen bestätigt die Komplexitätsklasse $\mathcal{O}(n^2)$ eindeutig.

2. **Vergleich BubbleSort vs. SelectionSort:**
   - Bei kleinen Arrays ($n=10$) sind beide nahezu gleich schnell (~2,5 µs).
   - Bei größeren Arrays ($n \ge 100$) ist **SelectionSort etwa doppelt so schnell wie BubbleSort**:
     - Bei $n=1.000$: SelectionSort ~2,1 ms vs. BubbleSort ~4,1 ms.
     - Bei $n=10.000$: SelectionSort ~53 ms vs. BubbleSort ~91 ms.
   - **Grund:** Während BubbleSort bei jedem Fehltritt benachbarte Elemente vertauscht (viele Schreibzugriffe im Speicher, $\mathcal{O}(n^2)$ Vertauschungen), sucht SelectionSort pro Durchlauf nur das Minimum und führt pro äußerem Durchlauf nur **einen einzigen Tausch** durch ($\mathcal{O}(n)$ Vertauschungen).

---

### Aufgabe 1.4: Theoretisches Wachstumsverhalten

Die berechneten Werte aus `Wachstum.java` zeigen sehr gut, warum $\mathcal{O}(n^2)$ und insbesondere exponentielles Wachstum $\mathcal{O}(2^n)$ oder $\mathcal{O}(n!)$ für größere Datenmengen unbrauchbar werden:
- Während $\log(n)$ und $n$ selbst bei $n=60$ nur wenige Mikrosekunden benötigen,
- benötigt $2^{60}$ bereits über 365 Jahrhunderte,
- und $60!$ ist praktisch unendlich ($\infty$).
