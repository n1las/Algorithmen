# Auswertung Benchmark (Woche 2)

## 1. Insertion Sort (Best-Case, Worst-Case, Average-Case)

**Ergebnisse:**
- **Best-Case (aufsteigend sortiert):** Sehr schnell. Die Laufzeit wächst nur linear O(n), weil die innere while-Schleife sofort beim ersten Vergleich abbricht und keine Zahlen verschoben werden müssen.
- **Worst-Case (rückwärts sortiert):** Sehr langsam. Die Laufzeit wächst quadratisch O(n^2), weil jede neue Zahl mit allen bisherigen Zahlen verglichen und komplett nach ganz vorne geschoben werden muss.
- **Average-Case (Zufallszahlen):** Ebenfalls O(n^2), aber ungefähr doppelt so schnell wie der Worst-Case, weil die Zahlen im Schnitt nur bis zur Mitte verschoben werden müssen.

**Entspricht das den Erwartungen?**
Ja, die Messwerte passen zur theoretischen Laufzeit.

---

## 2. Aufgabe 2.4: Vergleich Insertion Sort, Selection Sort und Bubble Sort (bis n = 5000)

**Ergebnisse:**
Alle drei Algorithmen haben eine quadratische Laufzeit O(n^2). Wenn man die Werte plottet, sieht man drei Kurven (Parabeln), die nach oben hin immer steiler ansteigen. Trotzdem sind sie unterschiedlich schnell:
1. **Insertion Sort ist am schnellsten:** Die innere Schleife bricht ab, sobald die richtige Stelle gefunden ist. Außerdem werden Werte nur verschoben statt komplett getauscht.
2. **Selection Sort ist im Mittelfeld:** Läuft die innere Schleife zwar immer komplett durch (immer gleich viele Vergleiche), macht aber pro Durchlauf nur einen einzigen Tausch.
3. **Bubble Sort ist am langsamsten:** Macht sowohl sehr viele Vergleiche als auch extrem viele Tausch-Operationen (Dreieckstausch).

**Entspricht das den Erwartungen?**
Ja, der quadratische Anstieg und die Reihenfolge der Geschwindigkeiten sind genau wie erwartet.

---

## 3. Auffälligkeiten / Komisches Verhalten bei den Messwerten

1. **Plötzlicher Sprung nach unten (z.B. ab n = 160 oder bei n = 5000 im Best-Case):**
   Manchmal ist ein größeres Array plötzlich deutlich schneller fertig als ein kleineres davor. Das liegt am **JIT-Compiler (Just-In-Time)** von Java. Am Anfang führt Java den Code noch unoptimiert aus. Nach ein paar Durchläufen merkt Java, dass die Methode oft genutzt wird, und optimiert sie im Hintergrund. Danach läuft der Code auf einmal viel schneller.
2. **Kleine Ausreißer / Zacken im Plot:**
   Manche einzelnen Messwerte fallen kurz nach oben aus. Das liegt daran, dass wir in Nanosekunden messen und Hintergrundprozesse vom PC oder der Java Garbage Collector (Speicherbereinigung) das Programm manchmal minimal ausbremsen.
