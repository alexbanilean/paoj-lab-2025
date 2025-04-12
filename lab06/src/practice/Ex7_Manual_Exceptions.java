package practice;

/**
 * Cerință:
 * 1. Creați o metodă care deschide un fișier folosind FileReader.
 * 2. Simulați situații de eroare:
 *    - Dacă fișierul nu există, se va arunca FileNotFoundException.
 *    - Dacă se citește un caracter invalid, poate fi aruncată o IOException.
 * 3. Folosiți blocurile try-catch pentru a trata excepțiile specifice și un bloc finally pentru a închide resursele manual.
 */

import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Ex7_Manual_Exceptions {
    public static void main(String[] args) {
        FileReader fr = null;
        try {
            fr = new FileReader("nonexistent.txt");  // Probabil va arunca FileNotFoundException
            int data = fr.read();
            // Variabila "data" este accesibilă doar în interiorul blocului try
            System.out.println("Primul caracter: " + (char)data);
        } catch (FileNotFoundException e) {
            System.err.println("Fișierul nu a fost găsit: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Eroare la citire: " + e.getMessage());
        } finally {
            System.out.println("Execută blocul finally pentru a elibera resursele.");
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    System.err.println("Eroare la închiderea fișierului: " + e.getMessage());
                }
            }
        }
    }
}

