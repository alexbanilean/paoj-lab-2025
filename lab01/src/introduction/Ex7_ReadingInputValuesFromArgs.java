package introduction;

/**
 * Exemplu de citire a valorilor de intrare din argumentele liniei de comandă.
 *
 * Acest program demonstrează:
 * 1. Cum se accesează argumentele din linia de comandă prin parametrul "args"
 *    al metodei main.
 * 2. Iterarea prin argumente folosind un enhanced-for loop.
 * 3. Conversia argumentelor din String la alte tipuri de date (exemplu: conversie la int).
 *
 * Pentru a rula acest program din linia de comandă:
 *     cd lab01/src/
 *     javac introduction/Ex7_ReadingInputValuesFromArgs.java
 *     java introduction.Ex7_ReadingInputValuesFromArgs arg1 arg2 arg3 ...
 *
 * Exemplu:
 *     java introduction.Ex7_ReadingInputValuesFromArgs "Salut" 123 true
 */
public class Ex7_ReadingInputValuesFromArgs {

    public static void main(String[] args) {
        // Verificăm dacă s-au furnizat argumente
        if (args.length == 0) {
            System.out.println("Nu s-au furnizat argumente.");
            return;
        }

        System.out.println("Argumentele primite:");
        // Iterare prin argumente folosind un enhanced-for loop
        for (String arg : args) {
            System.out.println(arg);
        }

        // Exemplu de conversie: încercăm să convertim primul argument într-un int
        try {
            int num = Integer.parseInt(args[0]);
            System.out.println("Primul argument convertit la int: " + num);
        } catch (NumberFormatException e) {
            System.out.println("Primul argument nu este un număr întreg valid.");
        }
    }
}
