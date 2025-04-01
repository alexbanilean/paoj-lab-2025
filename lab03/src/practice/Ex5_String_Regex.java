package practice;

/**
 * Creați o clasă helper UtilitarString care să conțină metode statice pentru:
 *      Verificarea dacă un șir de caractere conține doar litere și spații (regex).
 *      Inversarea unui șir de caractere folosind StringBuilder.
 *      Concatenarea a două stringuri folosind StringBuffer.
 * Testați metodele în main().
 */
import java.util.regex.Pattern;

class UtilitarString {

    // 1. Verificare Regex: doar litere și spații
    public static boolean esteValid(String text) {
        return Pattern.matches("[a-zA-Z ]+", text);
    }

    // 2. Inversarea unui șir folosind StringBuilder
    public static String inverseaza(String text) {
        return new StringBuilder(text).reverse().toString();
    }

    // 3. Concatenare folosind StringBuffer
    public static String concateneaza(String s1, String s2) {
        StringBuffer sb = new StringBuffer(s1);
        return sb.append(s2).toString();
    }
}

public class Ex5_String_Regex {
    public static void main(String[] args) {
        String text1 = "Buna ziua";
        String text2 = "Java#";

        // Testare regex
        System.out.println("Este valid '" + text1 + "' ? " + UtilitarString.esteValid(text1)); // true
        System.out.println("Este valid '" + text2 + "' ? " + UtilitarString.esteValid(text2)); // false

        // Testare inversare
        System.out.println("Inversare 'Java': " + UtilitarString.inverseaza("Java")); // avaJ

        // Testare concatenare
        System.out.println("Concatenare 'Hello' + ' World': " + UtilitarString.concateneaza("Hello", " World")); // Hello World
    }
}
