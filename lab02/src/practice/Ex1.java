package practice;

/**
 * Implementați o clasă abstractă "Vehicul" cu metoda abstractă "porneste()"
 * Creați clasele derivate "Masina" și "Bicicleta", fiecare implementând metoda "porneste()".
 */

/**
 * Creați o clasă abstractă Vehicul cu următoarele caracteristici:
 *
 * Un atribut vitezaMaxima (int).
 * Un atribut nrRoti (int).
 * Un constructor care inițializează atributele.
 * O metodă abstractă porneste().
 * O metodă afiseazaDetalii() care afișează numărul de roți și viteza maximă.
 *
 * Extindeți Vehicul prin două clase concrete:
 *
 * Masina
 * Are un atribut suplimentar marca (String).
 * Constructor care primește toți parametrii.
 * Implementarea metodei porneste() pentru a afișa un mesaj specific unei mașini.
 *
 * Bicicleta
 * Are un atribut suplimentar tipBicicleta (String).
 * Constructor care primește toți parametrii.
 * Implementarea metodei porneste() pentru a afișa un mesaj specific unei biciclete.
 *
 * În metoda main(), creați obiecte de tip Masina și Bicicleta și apelați metodele porneste() și afiseazaDetalii().
*/

abstract class Vehicul {
    int vitezaMaxima;
    int nrRoti;

    public Vehicul(int vitezaMaxima, int nrRoti) {
        this.vitezaMaxima = vitezaMaxima;
        this.nrRoti = nrRoti;
    }

    abstract void porneste();

    public void afiseazaDetalii() {
        System.out.println("Numar roti: " + nrRoti);
        System.out.println("Viteza maxima: " + vitezaMaxima + " km/h");
    }
}

class Masina extends Vehicul {
    String marca;

    public Masina(int vitezaMaxima, int nrRoti, String marca) {
        super(vitezaMaxima, nrRoti);
        this.marca = marca;
    }

    @Override
    void porneste() {
        System.out.println("Masina " + marca + " porneste.");
    }
}

class Bicicleta extends Vehicul {
    String tipBicicleta;

    public Bicicleta(int vitezaMaxima, int nrRoti, String tipBicicleta) {
        super(vitezaMaxima, nrRoti);
        this.tipBicicleta = tipBicicleta;
    }

    @Override
    void porneste() {
        System.out.println("Bicicleta de tip " + tipBicicleta + " porneste.");
    }
}

public class Ex1 {
    public static void main(String[] args) {
        Masina masina = new Masina(180, 4, "BMW");
        Bicicleta bicicleta = new Bicicleta(25, 2, "MTB");

        masina.porneste();
        masina.afiseazaDetalii();

        bicicleta.porneste();
        bicicleta.afiseazaDetalii();
    }
}

