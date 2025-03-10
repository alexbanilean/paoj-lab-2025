package introduction;

/**
 * Instrucțiuni de control al fluxului în Java
 *
 * Java oferă mai multe tipuri de instrucțiuni pentru controlul fluxului de execuție, incluzând:
 *
 * 1. Instrucțiuni condiționale:
 *    - if-else if-else
 *    - switch (suportă pattern matching începând cu Java 17+)
 *
 * 2. Structuri repetitive:
 *    - for loop (clasic și for-each)
 *    - while loop
 *    - do-while loop
 *
 * 3. Instrucțiuni de ramificare:
 *    - break (întrerupe o buclă sau un switch)
 *    - continue (sare la următoarea iterație a unei bucle)
 *    - return (termină execuția unei metode)
 *
 * Pentru mai multe detalii, consultați:
 * https://www.baeldung.com/java-control-structures
 */

public class Ex5_FlowControl {

    public static void main(String[] args) {
        // ---------------------------------------------------------
        // 1. Instrucțiuni condiționale
        // ---------------------------------------------------------

        int a = 10;
        int b = 20;

        // IF - ELSE IF - ELSE
        if (a > b) {
            System.out.println("a este mai mare decât b");
        } else if (a < b) {
            System.out.println("b este mai mare decât a");
        } else {
            System.out.println("a este egal cu b");
        }

        // SWITCH STATEMENT
        int zi = 3;
        switch (zi) {
            case 1:
                System.out.println("Luni");
                break;
            case 2:
                System.out.println("Marți");
                break;
            case 3:
                System.out.println("Miercuri");
                break;
            default:
                System.out.println("Altă zi");
        }

        // SWITCH CU PATTERN MATCHING (Java 17+)
        Object obj = "42";
        switch (obj) {
            case Integer integer -> System.out.println("Valoare Integer: " + integer);
            case String str -> System.out.println("String convertit în număr: " + Integer.parseInt(str));
            default -> System.out.println("Tip necunoscut");
        }

        System.out.println();

        // ---------------------------------------------------------
        // 2. Structuri repetitive
        // ---------------------------------------------------------

        // FOR LOOP (Clasic)
        for (int i = 0; i < 3; i++) {
            System.out.println("For loop, iterația: " + i);
        }
        System.out.println();

        // FOR-EACH LOOP (Enhanced for)
        int[] numere = {1, 2, 3, 4};
        for (int num : numere) {
            System.out.println("Enhanced for loop, num = " + num);
        }
        System.out.println();

        // WHILE LOOP
        int i = 0;
        while (i < 3) {
            System.out.println("While loop, i = " + i);
            i++;
        }
        System.out.println();

        // DO-WHILE LOOP
        i = 0;
        do {
            System.out.println("Do-while loop, i = " + i);
            i++;
        } while (i < 3);
        System.out.println();

        // ---------------------------------------------------------
        // 3. Instrucțiuni de ramificare
        // ---------------------------------------------------------

        // BREAK și CONTINUE
        for (int j = 0; j < 5; j++) {
            if (j == 2) {
                System.out.println("Se sare peste iterația 2 cu 'continue'");
                continue;
            }
            if (j == 4) {
                System.out.println("Se întrerupe bucla la 4 cu 'break'");
                break;
            }
            System.out.println("Iterația: " + j);
        }
        System.out.println();

        // RETURN STATEMENT
        System.out.println("Apelarea unei metode care se oprește devreme...");
        earlyStopMethod();
        System.out.println("Această linie nu va fi atinsă.");
    }

    /**
     * Metodă care folosește return pentru a ieși devreme din execuție.
     */
    public static void earlyStopMethod() {
        System.out.println("Această metodă se termină devreme folosind return.");
        return; // Termină execuția metodei
    }
}
