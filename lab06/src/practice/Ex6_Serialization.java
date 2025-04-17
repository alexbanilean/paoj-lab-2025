package practice;

/**
 * Cerință:
 * 1. Creați o clasă Student care implementează Serializable.
 * 2. Suprascrieți toString() pentru afișare.
 * 3. Scrieți mai multe instanțe de Student într-un fișier folosind ObjectOutputStream.
 * 4. Citiți obiectele folosind ObjectInputStream și afișați-le.
 * 5. Tratați excepțiile specifice.
 */

import java.io.*;
import java.util.*;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nume;
    private int varsta;

    public Student(String nume, int varsta) {
        this.nume = nume;
        this.varsta = varsta;
    }

    @Override
    public String toString() {
        return "Student{nume='" + nume + "', varsta=" + varsta + "}";
    }
}

class Ex6_Serialization {
    public static void main(String[] args) {
        List<Student> studenti = Arrays.asList(
                new Student("Alice", 20),
                new Student("Bob", 22)
        );
        String filename = "lab06/src/students.ser";

        // Serializare
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(studenti);
            System.out.println("Studentii au fost serializați.");
        } catch (IOException e) {
            System.err.println("Eroare la serializare: " + e.getMessage());
        }

        // Deserializare
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            List<Student> cititi = (List<Student>) ois.readObject();
            System.out.println("Studentii deserializați: " + cititi);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Eroare la deserializare: " + e.getMessage());
        }
    }
}

