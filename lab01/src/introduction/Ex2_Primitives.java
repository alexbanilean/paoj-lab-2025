package introduction;

/**
 * Introducere în gestionarea datelor în Java:
 *
 * Pentru a lucra cu date în Java, trebuie să definim zone de memorie (variabile) în care să stocăm
 * informații. Tipul de date specifică ce fel de informații pot fi stocate și modul în care acestea sunt
 * manipulate. Java clasifică datele în două categorii principale:
 *
 * 1. Tipuri primitive:
 *    - Sunt tipuri de date de bază, care nu sunt obiecte.
 *    - Exemple: byte, short, int, long, float, double, char, boolean.
 *    - Avantaje: sunt stocate direct în stivă (stack) și oferă performanțe ridicate datorită simplității lor.
 *
 * 2. Tipuri de referință:
 *    - Reprezintă obiecte (instanțe ale unor clase).
 *    - Exemplu: String, care, deși reprezintă un șir de caractere, nu este un tip primitiv.
 *
 * Observații importante:
 * - Deoarece Java oferă tipuri primitive, acesta nu este considerat un limbaj OOP "pur", însă majoritatea
 *   componentelor și bibliotecilor din Java sunt orientate pe obiecte.
 *
 *
 * Literali în Java
 *
 * Un literal este o valoare constantă scrisă direct în cod. Principalele tipuri de literali:
 *
 * - Literali întregi:
 *   - Pot fi scriși în formă zecimală (ex: 42).
 *   - Octal: prefixul `0` indică un literal octal (ex: 054 reprezintă 44 în baza 10).
 *   - Hexadecimal: prefixul `0x` sau `0X` indică un literal hexadecimal (ex: 0xFF reprezintă 255).
 *   - Binar: prefixul `0b` sau `0B` indică un literal binar (ex: 0b10110 reprezintă 22 în baza 10).
 *
 * - Literali cu virgulă mobilă:
 *   - Reprezintă numere reale. Exemplu: `3.14` sau în notație științifică: `1.0E-3`.
 *   - Prin convenție, numerele cu virgulă mobilă sunt de tip `double` implicit. Pentru a specifica
 *     un literal de tip `float`, se adaugă sufixul `F` sau `f` (ex: `10.5F`).
 *
 * - Literali de tip caracter:
 *   - Se scriu între ghilimele simple. Exemplu: `'a'`.
 *   - Pot include secvențe de escape, cum ar fi `'\n'` (linie nouă) sau coduri Unicode, cum ar fi `'\u1011'`.
 *
 * - Literali boolean:
 *   - Reprezintă valorile logice: `true` și `false`.
 *
 * - Literali de tip String:
 *   - Se scriu între ghilimele duble. Exemplu: `"Hello, World!"`
 *   - Deși literalul de tip String reprezintă o secvență de caractere, String-ul este o clasă din Java și,
 *     prin urmare, un tip de referință.
 *
 * 
 * Reguli de denumire pentru identificatori:
 * - Un identificator (numele variabilei sau al clasei) trebuie să înceapă cu o literă, caracterul '_' sau '$';
 *   nu poate începe cu o cifră.
 * - Poate conține litere, cifre, '_' și '$'. Java permite și caractere Unicode, dar se recomandă utilizarea
 *   literelor latine pentru claritate.
 * - Este o bună practică să se folosească convenția camelCase pentru variabile și PascalCase pentru numele claselor.
 *
 * Un literal în Java este orice valoare pe care o specificăm drept constantă în cod.
 * Mai jos se vor folosi diferite exemple de literali.
 *
 * Pentru mai multe informații, consultați:
 * https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html
 * https://www.baeldung.com/java-primitives
 * https://www.baeldung.com/java-literals
 */
public class Ex2_Primitives {

    public static void main(String[] args) {
        // Exemplificarea tipurilor primitive și a literaliilor

        // 1. Tipuri numerice integrale:
        // byte: 8 biți semnați; interval: -128 până la 127
        byte q1 = 10;

        // short: 16 biți semnați; interval: -32,768 până la 32,767
        short s1 = 10;

        // int: 32 biți semnați; interval: -2,147,483,648 până la 2,147,483,647
        int i = 10;

        // long: 64 biți semnați; interval: -2^63 până la 2^63-1. Literalul 'L' specifică tipul long.
        long a = 10L;

        // Exemplu de literali în diferite baze:
        // Octal: începe cu 0; cifre posibile: 0-7
        int i2 = 054; // valoare octală (54 în baza 8)

        // Hexadecimal: începe cu 0x sau 0X; cifre posibile: 0-9 și A-F/a-f
        int i3 = 0XFF; // 255 în baza 10
        int i4 = 0xFF; // 255 în baza 10

        // Binar: începe cu 0b sau 0B; cifre posibile: 0 și 1
        int i5 = 0b10110; // echivalent decimal: 22

        // Literal long cu valoare mare
        long b = 9999999999999L;

        // 2. Tipuri numerice cu virgulă mobilă:
        // double: număr real pe 64 biți (precizie dublă)
        double d1 = 10.5;

        // float: număr real pe 32 biți (precizie simplă); literalul 'F' sau 'f' specifică tipul float.
        float f1 = 10.5F;
        float f2 = (float) 10.5;  // conversie explicită de la double la float

        // Exemplu de conversie (unboxing): valoarea 10.5 este trunchiată la 10
        int f3 = (int) 10.5;

        // 3. Tipul boolean:
        // Reprezintă valori logice: true sau false
        boolean k1 = false;
        boolean k2 = true;

        // 4. Tipul char:
        // Stochează un caracter Unicode pe 16 biți.
        char w1 = 'a';      // literalu: 'a'
        char w2 = '\n';     // caracter de linie nouă
        char w3 = '\u1011'; // caracter definit prin cod Unicode
        System.out.print(w3);

        // Declarări multiple de variabile:
        // Se pot declara mai multe variabile de același tip, separate prin virgulă.
        int r1, r2, r3;
        int r4, r5 = 10, r6;
    }
}
