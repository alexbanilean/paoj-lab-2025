package practice;

/**
 * Cerință:
 * 1. Definiți o interfață `Publicatie` cu metodele:
 *    - `String getTitlu();`
 *    - `int getAnPublicatie();`
 * 2. Creați un enum `TipPublicatie` cu valori (CARTE, REVISTA, ZIAR).
 * 3. Implementați o clasă `Carte` cu atributele `titlu`, `autor`, `anPublicatie` și `tip` (sub formă de enum).
 * 4. Creați o colecție `TreeSet<Carte>` care sortează cărțile în ordine cronologică.
 * 5. Definiți un `Comparator` care să sorteze cărțile alfabetic după titlu.
 * 6. Afișați cărțile sortate cronologic și alfabetic.
 */

import java.util.*;

interface Publicatie {
    String getTitlu();
    int getAnPublicatie();
}

enum TipPublicatie {
    FICTIUNE, NON_FICTIUNE, STIINTIFIC, ROMAN, ISTORIC;
}

class Carte implements Publicatie, Comparable<Carte> {
    private String titlu;
    private String autor;
    private int anPublicatie;
    private TipPublicatie tip;

    public Carte(String titlu, String autor, int anPublicatie, TipPublicatie tip) {
        this.titlu = titlu;
        this.autor = autor;
        this.anPublicatie = anPublicatie;
        this.tip = tip;
    }

    @Override
    public String getTitlu() {
        return titlu;
    }

    @Override
    public int getAnPublicatie() {
        return anPublicatie;
    }

    @Override
    public int compareTo(Carte c) {
        return Integer.compare(this.anPublicatie, c.anPublicatie);
    }

    @Override
    public String toString() {
        return titlu + " - " + autor + " (" + anPublicatie + ") [" + tip + "]";
    }
}

class ComparatorTitlu implements Comparator<Carte> {
    @Override
    public int compare(Carte c1, Carte c2) {
        return c1.getTitlu().compareToIgnoreCase(c2.getTitlu());
    }
}

public class Ex3_Library_Management {
    public static void main(String[] args) {
        // Colecție TreeSet sortată cronologic
        TreeSet<Carte> biblioteca = new TreeSet<>();
        biblioteca.add(new Carte("Java Fundamentals", "John Doe", 2015, TipPublicatie.STIINTIFIC));
        biblioteca.add(new Carte("Misterele Dunării", "Ion Popescu", 2010, TipPublicatie.ROMAN));
        biblioteca.add(new Carte("O istorie a lumii", "Maria Ionescu", 2000, TipPublicatie.ISTORIC));

        System.out.println("Cărți sortate cronologic:");
        for (Carte c : biblioteca) {
            System.out.println(c);
        }

        // Sortare alfabetică folosind Comparator
        List<Carte> cartiAlfabetic = new ArrayList<>(biblioteca);
        cartiAlfabetic.sort(new ComparatorTitlu());

        System.out.println("\nCărți sortate alfabetic:");
        for (Carte c : cartiAlfabetic) {
            System.out.println(c);
        }
    }
}

