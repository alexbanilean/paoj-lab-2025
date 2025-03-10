package introduction;

import java.util.Scanner;

/**
 * Exemplu de citire a valorilor de intrare de la utilizator în Java.
 *
 * Acest exemplu demonstrează:
 * 1. Utilizarea clasei Scanner pentru a citi date de la tastatură.
 * 2. Citirea unui șir de caractere folosind nextLine().
 * 3. Citirea numerelor întregi folosind nextInt().
 * 4. Afișarea valorilor introduse și efectuarea unei operații aritmetice.
 *
 * Notă:
 * - Clasa Scanner se găsește în pachetul java.util.
 * - Este recomandat să se utilizeze try-with-resources pentru a închide automat Scanner-ul.
 * - Atunci când se utilizează nextInt(), caracterul de linie rămas (newline) nu este consumat,
 *   ceea ce poate cauza probleme dacă se urmează o citire cu nextLine(). În acest exemplu,
 *   citirile sunt efectuate în mod secvențial fără a alterna cu nextLine() după nextInt().
 */
public class Ex6_ReadingInputValues {

    public static void main(String[] args) {
        // Folosim try-with-resources pentru a închide automat Scanner-ul la final.
        try (Scanner scanner = new Scanner(System.in)) {

            // 1. Citirea unui șir de caractere (String)
            System.out.println("Introduceți un text:");
            String text = scanner.nextLine();
            System.out.println("Textul introdus: " + text);
            System.out.println();

            // 2. Citirea primului număr întreg
            System.out.println("Introduceți primul număr:");
            int a = scanner.nextInt();
            System.out.println("Primul număr: " + a);
            System.out.println();

            // 3. Citirea celui de-al doilea număr întreg
            System.out.println("Introduceți al doilea număr:");
            int b = scanner.nextInt();
            System.out.println("Al doilea număr: " + b);
            System.out.println();

            // 4. Efectuarea unei operații aritmetice: înmulțirea
            int produs = a * b;
            System.out.println("Produsul celor două numere (a * b): " + produs);

        } catch (Exception e) {
            // Tratarea eventualelor excepții, de exemplu InputMismatchException
            System.err.println("Eroare la citirea datelor: " + e.getMessage());
        }
    }
}
