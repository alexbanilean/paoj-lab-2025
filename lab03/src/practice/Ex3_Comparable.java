package practice;

/**
 * Creați o clasă Produs care implementează Comparable<Produs> și compară după preț.
 * Creați un comparator pentru sortare alfabetică.
 * Testați codul în metoda main().
 */
import java.util.*;

class Produs implements Comparable<Produs> {
    private String nume;
    private double pret;

    public Produs(String nume, double pret) {
        this.nume = nume;
        this.pret = pret;
    }

    public String getNume() {
        return nume;
    }

    public double getPret() {
        return pret;
    }

    @Override
    public int compareTo(Produs other) {
        return Double.compare(this.pret, other.pret);
    }

    @Override
    public String toString() {
        return nume + " - " + pret + " RON";
    }
}

class ComparatorAlfabetic implements Comparator<Produs> {
    @Override
    public int compare(Produs p1, Produs p2) {
        return p1.getNume().compareTo(p2.getNume());
    }
}

public class Ex3_Comparable {
    public static void main(String[] args) {
        Produs[] produse = {
                new Produs("Laptop", 3500),
                new Produs("Telefon", 2500),
                new Produs("Mouse", 150)
        };

        Arrays.sort(produse); // Sortare implicită după preț
        System.out.println("Sortare după preț:");
        for (Produs p : produse) {
            System.out.println(p);
        }

        System.out.println();

        Arrays.sort(produse, new ComparatorAlfabetic());
        System.out.println("Sortare alfabetică:");
        for (Produs p : produse) {
            System.out.println(p);
        }
    }
}

