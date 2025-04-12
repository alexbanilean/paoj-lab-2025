package practice;

/**
 * Cerință:
 * 1. Creați o clasă care citește de la tastatură conținutul unui fișier text și îl afișează.
 * 2. Folosiți FileReader și BufferedReader pentru citire și FileWriter pentru scrierea unui alt fișier.
 * 3. Tratați excepțiile de tip IOException corespunzător.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ex2_Chars_Stream {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceți calea fișierului de citit: ");
        String filePath = scanner.nextLine();

        // Citirea fișierului
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String linie;
            System.out.println("Conținutul fișierului:");
            while ((linie = br.readLine()) != null) {
                System.out.println(linie);
            }
        } catch (IOException e) {
            System.err.println("Eroare la citire: " + e.getMessage());
        }

        // Scrierea fișierului
        System.out.print("Introduceți numele fișierului de scris: ");
        String outputFile = scanner.nextLine();
        try (FileWriter fw = new FileWriter(outputFile)) {
            fw.write("Acesta este un text scris automat.\nLinia a doua.");
            System.out.println("Fișierul a fost scris cu succes.");
        } catch (IOException e) {
            System.err.println("Eroare la scriere: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
