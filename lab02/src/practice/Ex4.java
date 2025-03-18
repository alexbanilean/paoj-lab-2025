package practice;

/**
 * Creați o clasă Investitie care să conțină:
 *
 * Atribute private: nume (String), valoare (double).
 * Constructor, metode gettere și metoda toString().
 * Creați o clasă Portofoliu care să conțină:
 *
 * Un tablou de obiecte Investitie.
 * Un constructor care primește un tablou de investiții și îl asignează.
 * Implementați două metode în Portofoliu:
 *
 * shallowCopy() – returnează o copie unde referința tabloului este aceeași.
 * deepCopy() – returnează o copie unde fiecare obiect Investitie este copiat individual.
 *
 * În metoda main(), demonstrați diferența dintre shallow copy și deep copy prin modificarea unei investiții și observarea efectelor.
 */

import java.util.Arrays;

class Investitie {
    private String nume;
    private double valoare;

    public Investitie(String nume, double valoare) {
        this.nume = nume;
        this.valoare = valoare;
    }

    public String getNume() {
        return nume;
    }

    public double getValoare() {
        return valoare;
    }

    @Override
    public String toString() {
        return "Investitie: " + nume + ", Valoare: " + valoare;
    }
}

class Portofoliu {
    private Investitie[] investitii;

    public Portofoliu(Investitie[] investitii) {
        this.investitii = investitii;
    }

    public Portofoliu shallowCopy() {
        return new Portofoliu(this.investitii);
    }

    public Portofoliu deepCopy() {
        Investitie[] copy = Arrays.copyOf(this.investitii, this.investitii.length);
        for (int i = 0; i < copy.length; i++) {
            copy[i] = new Investitie(copy[i].getNume(), copy[i].getValoare());
        }
        return new Portofoliu(copy);
    }

    public void afiseazaInvestitii() {
        for (Investitie investitie : investitii) {
            System.out.println(investitie);
        }
    }
}

public class Ex4 {
    public static void main(String[] args) {
        Investitie[] investitii = {
                new Investitie("Gold", 1000),
                new Investitie("Stocks", 500)
        };

        Portofoliu portofoliu1 = new Portofoliu(investitii);
        Portofoliu shallowCopy = portofoliu1.shallowCopy();
        Portofoliu deepCopy = portofoliu1.deepCopy();


        System.out.println("Portofoliu original inainte de modificare:");
        portofoliu1.afiseazaInvestitii();

        investitii[0] = new Investitie("Real Estate", 1500);

        System.out.println("Portofoliu original dupa modificare:");
        portofoliu1.afiseazaInvestitii();

        System.out.println("Shallow copy:");
        shallowCopy.afiseazaInvestitii();

        System.out.println("Deep copy:");
        deepCopy.afiseazaInvestitii();
    }
}

