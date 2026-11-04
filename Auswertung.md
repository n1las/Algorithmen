# Auswertung: Übungsblatt 1 – Algorithmen und Datenstrukturen

In diesem Dokument werden die gemessenen Laufzeiten der Implementierungen aus Woche 1 ausgewertet und mit den theoretischen Erwartungen verglichen.

---

## 1. Messwerte aus der Konsole

Die Messungen wurden mit der Klasse `LinearSearch` für die Eingabegrößen $n \in \{10, 100, 1000, 10000, 100000\}$ durchgeführt:

| Eingabegröße $n$ | Int Vorne (Best Case) | Int Hinten (Worst Case) | Int Random (Average Case) | Int Nicht da | Binär Vorne | Binär Hinten | BubbleSort | SelectionSort |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **$n = 10$** | 2.864 ns | 3.283 ns | 2.864 ns | 2.794 ns | 2.375 ns | 2.374 ns | 7.613 ns | 8.241 ns |
| **$n = 100$** | 2.374 ns | 6.356 ns | 5.168 ns | 8.242 ns | 1.885 ns | 7.124 ns | 428.976 ns | 225.872 ns |
| **$n = 1.000$** | 1.886 ns | 47.703 ns | 12.641 ns | 45.677 ns | 2.444 ns | 38.553 ns | 7.872.221 ns (~7,9 ms) | 4.709.727 ns (~4,7 ms) |
| **$n = 10.000$** | 2.794 ns | 339.158 ns | 36.528 ns | 344.885 ns | 2.445 ns | 341.463 ns | 143.474.547 ns (~143,5 ms) | 50.286.095 ns (~50,3 ms) |
| **$n = 100.000$** | 3.283 ns | 2.939.625 ns (~2,9 ms) | 875.343 ns (~0,88 ms) | 611.616 ns (~0,61 ms) | 2.864 ns | 409.140 ns (~0,41 ms) | 12.966.398.250 ns (~13,0 s) | 1.779.814.191 ns (~1,8 s) |

---

## 2. Bewertung der Ergebnisse

### Aufgabe 1.1: Lineare Suche

> **Leitfrage:** *Entspricht das Ergebnis Ihren Erwartungen?*

**Ja, die Ergebnisse entsprechen vollkommen den theoretischen Erwartungen:**

1. **Element ganz vorne (Best Case):**
   - **Theorie:** Komplexität $\mathcal{O}(1)$ (konstanter Aufwand), da das Element direkt im ersten Durchlauf gefunden und die Methode beendet wird.
   - **Praxis:** Die gemessene Zeit bleibt über alle Eingabegrößen ($n=10$ bis $n=100.000$) nahezu konstant im Bereich von ca. 1,8 bis 3,3 µs.

2. **Element ganz hinten / nicht vorhanden (Worst Case):**
   - **Theorie:** Komplexität $\mathcal{O}(n)$ (linearer Aufwand). Bei einer Verzehnfachung der Eingabegröße $n$ sollte sich auch die benötigte Zeit etwa verzehnfachen.
   - **Praxis:** 
     - Von $n=100$ (~6,4 µs) auf $n=1.000$ (~47,7 µs) und weiter auf $n=10.000$ (~339 µs) sowie $n=100.000$ (~2,94 ms bei `Int Hinten`) steigt die Zeit jeweils annähernd um den Faktor 7 bis 9.
     - Die lineare Skalierung ist deutlich sichtbar. Dass bei $n=100.000$ die nachfolgenden Durchläufe (`Int Nicht da` und `Binär Hinten`) mit ~0,61 ms bzw. ~0,41 ms plötzlich schneller ablaufen als `Int Hinten`, liegt an der **JIT-Kompilierung (Just-In-Time)** der JVM, die die Schleife nach vielen Iterationen während der Laufzeit optimiert.

3. **Element an zufälliger Position (Average Case):**
   - **Theorie:** Im Durchschnitt müssen $\frac{n}{2}$ Elemente betrachtet werden, was asymptotisch ebenfalls $\mathcal{O}(n)$ entspricht, in der Praxis aber im Mittel unterhalb des Worst Case liegt.
   - **Praxis:** Bei allen größeren $n$ ($100$ bis $100.000$) liegt die Laufzeit von `Int Random` wie erwartet zwischen dem Best Case (`Int Vorne`) und dem Worst Case (`Int Hinten`), z. B. bei $n=100.000$ mit ~0,88 ms gegenüber ~2,94 ms im Worst Case (abhängig von der konkret gezogenen Zufallsposition).

4. **Vergleich normales Integer-Array vs. Binär-Array:**
   - **Theorie:** Beide Arrays bestehen aus `int`-Werten und werden mit der gleichen Logik durchsucht. Es ist kein signifikanter Unterschied zu erwarten.
   - **Praxis:** Die gemessenen Zeiten für das Binär-Array (Suche nach 0) und das normale Integer-Array (Suche nach 42) liegen jeweils auf vergleichbarem Niveau.

---

### Aufgabe 1.2 & 1.3: BubbleSort vs. SelectionSort

> **Leitfrage:** *Entspricht das Ergebnis Ihren Erwartungen?*

**Ja, das Verhalten spiegelt die theoretische Komplexität wider:**

1. **Quadratisches Wachstum $\mathcal{O}(n^2)$:**
   - Beide Algorithmen vergleichen in zwei verschachtelten Schleifen Elemente miteinander. Bei einer Verzehnfachung von $n$ wächst die Anzahl der Vergleiche theoretisch um das Hundertfache ($10^2 = 100$).
   - In den Messungen sieht man den enormen Zeitsprung:
     - Bei $n=10$: beide brauchen nur ca. 8 µs.
     - Bei $n=1.000$: ca. 4,7 bis 7,9 ms.
     - Bei $n=10.000$: ca. 50,3 bis 143,5 ms.
     - Bei $n=100.000$: SelectionSort benötigt ~1,78 Sekunden, BubbleSort sogar **~12,97 Sekunden** (fast Faktor 90 gegenüber $n=10.000$).
   - Das starke, quadratische Ansteigen bestätigt die Komplexitätsklasse $\mathcal{O}(n^2)$ eindeutig.

2. **Vergleich BubbleSort vs. SelectionSort:**
   - Bei kleinen Arrays ($n=10$) sind beide nahezu gleich schnell (~8 µs).
   - Bei größeren Arrays ($n \ge 100$) ist **SelectionSort deutlich schneller als BubbleSort** (bei $n=100.000$ sogar über **7-mal schneller**):
     - Bei $n=1.000$: SelectionSort ~4,7 ms vs. BubbleSort ~7,9 ms.
     - Bei $n=10.000$: SelectionSort ~50,3 ms vs. BubbleSort ~143,5 ms.
     - Bei $n=100.000$: SelectionSort ~1,8 s vs. BubbleSort ~13,0 s.
   - **Grund:** Während BubbleSort bei jedem Fehltritt benachbarte Elemente vertauscht (viele Schreibzugriffe im Speicher, $\mathcal{O}(n^2)$ Vertauschungen), sucht SelectionSort pro Durchlauf nur das Minimum und führt pro äußerem Durchlauf nur **einen einzigen Tausch** durch ($\mathcal{O}(n)$ Vertauschungen).

---

### Aufgabe 1.4: Theoretisches Wachstumsverhalten

Ausgabe der Berechnungen aus `Wachstum.java` (unter der Annahme von $1\,\mu\text{s}$ pro Operation):

| Funktion / $n$ | $n = 10$ | $n = 20$ | $n = 30$ | $n = 40$ | $n = 50$ | $n = 60$ |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **$\log_2(n)$** | 3.32 µs | 4.32 µs | 4.91 µs | 5.32 µs | 5.64 µs | 5.91 µs |
| **$n$** | 10.0 µs | 20.0 µs | 30.0 µs | 40.0 µs | 50.0 µs | 60.0 µs |
| **$n \cdot \log_2(n)$** | 33.22 µs | 86.44 µs | 147.21 µs | 212.88 µs | 282.19 µs | 354.41 µs |
| **$n^2$** | 100.0 µs | 400.0 µs | 900.0 µs | 1.6 ms | 2.5 ms | 3.6 ms |
| **$n^3$** | 1.0 ms | 8.0 ms | 27.0 ms | 64.0 ms | 125.0 ms | 216.0 ms |
| **$2^n$** | 1.02 ms | 1.05 s | 17.9 min | 12.73 d | 35.7 J. | 365.59 Jh. |
| **$n!$** | 3.63 s | 771.47 Jh. | $\infty$ | $\infty$ | $\infty$ | $\infty$ |

Die berechneten Werte aus `Wachstum.java` zeigen sehr gut, warum $\mathcal{O}(n^2)$ und insbesondere exponentielles Wachstum $\mathcal{O}(2^n)$ oder $\mathcal{O}(n!)$ für größere Datenmengen unbrauchbar werden:
- Während $\log(n)$ und $n$ selbst bei $n=60$ nur wenige Mikrosekunden benötigen,
- benötigt $2^{60}$ bereits über 365 Jahrhunderte,
- und $n!$ übersteigt bereits ab $n=30$ die Grenze von 1.000 Jahrhunderten ($\infty$).
