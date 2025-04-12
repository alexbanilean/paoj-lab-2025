package practice;

/**
 * Cerință:
 * 1. Creați o clasă care primește din argumentele de comandă calea către un fișier.
 * 2. Folosiți clasa File pentru a verifica:
 *      - Dacă fișierul există.
 *      - Dacă este fișier sau director.
 *      - Mărimea fișierului.
 *      - Lista fișierelor (dacă este director).
 * 3. Afișați informațiile într-un format clar.
 */

import java.io.File;

public class Ex1_File_Properties {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Ex1_File_Properties <file-or-directory>");
            return;
        }
        File f = new File(args[0]);
        if (!f.exists()) {
            System.out.println("Fișierul/directorul nu există.");
        } else {
            System.out.println("Nume: " + f.getName());
            System.out.println("Cale completă: " + f.getAbsolutePath());
            System.out.println("Mărime: " + f.length() + " octeți");
            System.out.println("Este director: " + f.isDirectory());
            if (f.isDirectory()) {
                String[] list = f.list();
                System.out.println("Conținut director:");
                if (list != null) {
                    for (String s : list) {
                        System.out.println("  " + s);
                    }
                }
            }
        }
    }
}

