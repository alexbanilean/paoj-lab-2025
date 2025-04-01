package practice;

/**
 * Cerință:
 * 1. Definiți o interfață `Angajat` cu metodele:
 *    - `double calculeazaSalariu();`
 *    - `String getDetalii();`
 * 2. Creați un enum `Departament` cu valori (ex.: IT, HR, MARKETING).
 * 3. Implementați două clase de angajați: `Programator` și `Manager`.
 * 4. Creați o colecție `HashMap<Departament, List<Angajat>>` pentru a organiza angajații.
 * 5. Afișați angajații pe departamente.
 * 6. Adăugați o metodă pentru a filtra angajații IT cu salariul peste un prag.
 */

import java.util.*;

interface Angajat {
    double calculeazaSalariu();
    String getDetalii();
}

enum Departament {
    IT("Informatica"), HR("Resurse Umane"), MARKETING("Publicitate");

    private final String descriere;

    Departament(String descriere) {
        this.descriere = descriere;
    }

    public String getDescriere() {
        return descriere;
    }
}

class Programator implements Angajat {
    private String nume;
    private double salariu, bonus;

    public Programator(String nume, double salariu, double bonus) {
        this.nume = nume;
        this.salariu = salariu;
        this.bonus = bonus;
    }

    @Override
    public double calculeazaSalariu() {
        return salariu + bonus;
    }

    @Override
    public String getDetalii() {
        return "Programator: " + nume + ", Salariu: " + calculeazaSalariu();
    }
}

public class Ex2_Employees_Management {
    public static void main(String[] args) {
        Map<Departament, List<Angajat>> firma = new HashMap<>();
        firma.put(Departament.IT, new ArrayList<>(List.of(new Programator("Ana", 4000, 500))));
        firma.put(Departament.MARKETING, new ArrayList<>(List.of(new Programator("Maria", 5000, 100))));

        for (var entry : firma.entrySet()) {
            System.out.println("Departament: " + entry.getKey().getDescriere());
            for (Angajat a : entry.getValue()) {
                System.out.println(a.getDetalii());
            }
        }
    }
}

