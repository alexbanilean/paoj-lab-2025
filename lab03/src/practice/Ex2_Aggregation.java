package practice;

/**
 * Creați o clasă imutabilă Carte cu atributele titlu și autor.
 * Creați o clasă Biblioteca, care conține o listă de cărți (agregare).
 * Creați un record AutorRecord pentru a reprezenta autorii.
 * Adăugați metode în Biblioteca pentru a adăuga și afișa cărți.
 * Testați codul în metoda main().
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class Carte {
    private final String titlu;
    private final AutorRecord autor;

    public Carte(String titlu, AutorRecord autor) {
        this.titlu = titlu;
        this.autor = autor;
    }

    public String getTitlu() {
        return titlu;
    }

    public AutorRecord getAutor() {
        return autor;
    }

    @Override
    public String toString() {
        return "Carte{" + "titlu='" + titlu + "', autor=" + autor + '}';
    }
}

record AutorRecord(String nume, String nationalitate) {}

class Biblioteca {
    private final List<Carte> carti = new ArrayList<>();

    public void adaugaCarte(Carte carte) {
        carti.add(carte);
    }

    public List<Carte> getCarti() {
        return Collections.unmodifiableList(carti); // Evităm modificări externe
    }
}

public class Ex2_Aggregation {
    public static void main(String[] args) {
        AutorRecord autor1 = new AutorRecord("Mihai Eminescu", "Română");
        AutorRecord autor2 = new AutorRecord("J.K. Rowling", "Britanică");

        Carte carte1 = new Carte("Luceafărul", autor1);
        Carte carte2 = new Carte("Harry Potter", autor2);

        Biblioteca biblioteca = new Biblioteca();
        biblioteca.adaugaCarte(carte1);
        biblioteca.adaugaCarte(carte2);

        biblioteca.getCarti().forEach(System.out::println);
    }
}

