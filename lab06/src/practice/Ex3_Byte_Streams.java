package practice;

/**
 * Cerință:
 * 1. Creați o clasă care scrie un set de date (ex: un int, un double și un String) într-un fișier binar folosind DataOutputStream.
 * 2. Citiți datele înapoi folosind DataInputStream și afișați-le.
 * 3. Tratați excepțiile de tip IOException corespunzător.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ex3_Byte_Streams {
    public static void main(String[] args) {
        String filename = "lab06/src/data.bin";

        // Scrierea datelor binare
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename))) {
            dos.writeInt(100);
            dos.writeDouble(3.1415);
            dos.writeUTF("Hello, binary world!");
        } catch (IOException e) {
            System.err.println("Eroare la scrierea datelor: " + e.getMessage());
        }

        // Citirea datelor binare
        try (DataInputStream dis = new DataInputStream(new FileInputStream(filename))) {
            int i = dis.readInt();
            double d = dis.readDouble();
            String s = dis.readUTF();
            System.out.println("Citit: int=" + i + ", double=" + d + ", String=" + s);
        } catch (IOException e) {
            System.err.println("Eroare la citirea datelor: " + e.getMessage());
        }
    }
}
