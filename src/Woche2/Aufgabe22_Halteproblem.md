# Aufgabe 2.2: Das Halteproblem (Terminierung von Programmen)

## Frage
Kann man ein Programm schreiben, das für jedes beliebige andere Programm erkennt, ob es irgendwann anhält (terminiert) oder in einer Endlosschleife unendlich weiterläuft?

## Antwort
**Nein, das ist unmöglich.** Man kann so ein Programm nicht programmieren. Das lässt sich durch einen einfachen Widerspruchsbeweis zeigen:

---

## Beweis (Widerspruch)

### 1. Annahme
Wir nehmen an, es gäbe eine fertige Methode `terminiert(programm, eingabe)`.
- Sie gibt `true` zurück, wenn `programm` bei der `eingabe` irgendwann anhält.
- Sie gibt `false` zurück, wenn `programm` unendlich lange weiterläuft.

### 2. Konstruktion eines Gegen-Programms (Pseudocode)
Mit dieser Methode schreiben wir uns ein kleines Programm `paradox`, das immer genau das Gegenteil macht:

```java
void paradox(String programm) {
    if (terminiert(programm, programm) == true) {
        // Wenn vorhergesagt wird, dass es anhält -> Endlosschleife starten
        while (true) {
        }
    } else {
        // Wenn vorhergesagt wird, dass es ewig läuft -> sofort anhalten
        return;
    }
}
```

### 3. Selbstaufruf führt zum Widerspruch
Jetzt rufen wir `paradox(paradox)` auf (wir übergeben dem Programm seinen eigenen Code als Eingabe):

- **Fall 1: `terminiert` gibt `true` zurück** (behauptet also, `paradox` hält an).
  Dadurch springt `paradox` in den `if`-Teil in die `while(true)`-Schleife und **läuft unendlich weiter**. Die Vorhersage war also falsch.

- **Fall 2: `terminiert` gibt `false` zurück** (behauptet also, `paradox` läuft unendlich).
  Dadurch springt `paradox` in den `else`-Teil zum `return` und **hält sofort an**. Die Vorhersage war also ebenfalls falsch.

## Fazit
Egal was die Methode `terminiert` ausgibt, das Ergebnis ist immer falsch. Deshalb kann so ein Prüf-Programm gar nicht erst existieren (das Halteproblem ist unentscheidbar).
