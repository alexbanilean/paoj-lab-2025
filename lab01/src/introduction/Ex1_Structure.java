// Pachetul în care se află clasa – este o convenție să folosim pachete pentru organizare
package introduction;

import java.util.Random; // Import pentru Random

/**
 * Definiția unei clase în Java:
 *
 * - Cuvântul cheie "class" indică începutul definiției unei clase.
 * - În mod convențional, numele clasei corespunde cu numele fișierului sursă (deși nu este obligatoriu).
 *   Exemplu: Dacă clasa se numește Ex1_Structure, de regulă fișierul se va numi Ex1_Structure.java.
 *
 * Reguli de denumire:
 * - Numele clasei nu poate începe cu o cifră, dar poate conține cifre ulterior.
 * - Numele clasei nu trebuie să coincidă cu cuvinte cheie din Java (de exemplu, "package").
 * - Numele clasei nu poate conține spații sau caractere speciale, cu excepția caracterelor "$" și "_".
 *
 * Convenții de denumire:
 * - Se utilizează stilul "CamelCase": prima literă a fiecărui cuvânt este scrisă cu majusculă.
 * - Numele clasei este, de regulă, un substantiv.
 *
 * Organizarea claselor:
 * - Clasele în Java sunt organizate în pachete (packages).
 * - Convenția este ca denumirile pachetelor să fie scrise cu litere mici, de exemplu: com.exemplu.proiect
 * - Cuvântul cheie "package", plasat de obicei pe prima linie a fișierului, specifică pachetul în care se află clasa.
 * - Dacă un fișier nu declară un pachet, acesta face parte din pachetul implicit, denumit "default".
 * - Ca bună practică, se recomandă întotdeauna declararea explicită a pachetelor pentru o organizare mai clară.
 *
 * Importul claselor:
 * - Pentru a folosi clase din alte pachete decât cel curent, se utilizează cuvântul cheie "import".
 * - Pachetul "java.lang" este special și este importat implicit (de exemplu, clasa System, utilizată pentru a afișa mesaje în consolă, aparține acestui pachet).
 */
public class Ex1_Structure {

    /**
     * Metoda main este punctul de intrare al aplicației.
     */
    public static void main(String[] args) {
        Random randomNumberGenerator = new Random();
        System.out.println(randomNumberGenerator.nextInt());
    }
}