package practice;

/**
 * Cerință:
 * 1. Creați un fișier binar care conține 10 numere întregi.
 * 2. Folosiți RandomAccessFile pentru a citi și modifica direct numărul de la poziția 5 (înlocuiți-l cu o altă valoare).
 * 3. Tratați excepțiile specifice (ex: EOFException, IOException).
 */

import java.io.IOException;
import java.io.RandomAccessFile;

public class Ex4_Random_Access {
    public static void main(String[] args) {
        String filename = "randomData.bin";
        // Inițializare fișier cu 10 numere întregi
        try (RandomAccessFile raf = new RandomAccessFile(filename, "rw")) {
            for (int i = 0; i < 10; i++) {
                raf.writeInt(i * 10); // 0, 10, 20, ...
            }
        } catch (IOException e) {
            System.err.println("Eroare la inițializare: " + e.getMessage());
        }

        // Modificarea numărului de la poziția 5
        try (RandomAccessFile raf = new RandomAccessFile(filename, "rw")) {
            raf.seek(5 * Integer.BYTES); // poziția celui de-al 6-lea număr (index 5)
            raf.writeInt(555); // înlocuim cu 555
            // Resetăm la început pentru a citi toate valorile
            raf.seek(0);
            for (int i = 0; i < 10; i++) {
                System.out.println("Număr " + i + ": " + raf.readInt());
            }
        } catch (IOException e) {
            System.err.println("Eroare la accesul aleatoriu: " + e.getMessage());
        }
    }
}
