package practice;

/**
 * Cerință:
 * 1. Citiți un fișier text linie cu linie folosind BufferedReader.
 * 2. Folosiți try-with-resources pentru a gestiona închiderea automată.
 * 3. Afișați fiecare linie numerotată.
 * 4. Tratați excepțiile specifice (ex: IOException, FileNotFoundException).
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ex5_Line_Reading {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("text.txt"))) {
            String line;
            int count = 1;
            while ((line = br.readLine()) != null) {
                System.out.println(count + ": " + line);
                count++;
            }
        } catch (IOException e) {
            System.err.println("Eroare: " + e.getMessage());
        }
    }
}
