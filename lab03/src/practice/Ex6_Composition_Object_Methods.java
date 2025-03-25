package practice;

/**
 * Creați o clasă Carte care conține:
 *      Atribute titlu și Autor (compoziție).
 *      Suprascrierea metodei equals() pentru a compara cărțile după titlu și autor.
 * Creați clasa Autor cu nume și naționalitate, și suprascrieți metoda equals() pentru a compara autorii corect.
 * Testați egalitatea între două obiecte Carte.
 */
import java.util.Objects;

// Clasa Autor
class Autor {
    private String nume;
    private String natiune;

    public Autor(String nume, String natiune) {
        this.nume = nume;
        this.natiune = natiune;
    }

    // Suprascriere equals()
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Autor autor = (Autor) obj;
        return nume.equals(autor.nume) && natiune.equals(autor.natiune);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, natiune);
    }

    @Override
    public String toString() {
        return nume + " (" + natiune + ")";
    }
}

// Clasa Carte (compoziție cu Autor)
class Carte6 {
    private String titlu;
    private Autor autor;

    public Carte6(String titlu, Autor autor) {
        this.titlu = titlu;
        this.autor = autor;
    }

    public String getTitlu() {
        return titlu;
    }

    public Autor getAutor() {
        return autor;
    }

    // Suprascriere equals() pentru comparație după titlu și autor
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Carte6 carte = (Carte6) obj;
        return titlu.equals(carte.getTitlu()) && autor.equals(carte.getAutor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(titlu, autor);
    }

    @Override
    public String toString() {
        return "Carte{" + "titlu='" + titlu + "', autor=" + autor + '}';
    }
}

public class Ex6_Composition_Object_Methods {
    public static void main(String[] args) {
        Autor autor1 = new Autor("J.K. Rowling", "Marea Britanie");
        Autor autor2 = new Autor("J.K. Rowling", "Marea Britanie");

        Carte6 carte1 = new Carte6("Harry Potter", autor1);
        Carte6 carte2 = new Carte6("Harry Potter", autor2);
        Carte6 carte3 = new Carte6("Luceafărul", new Autor("Mihai Eminescu", "România"));

        System.out.println("carte1 == carte2 ? " + carte1.equals(carte2)); // true (autor și titlu identice)
        System.out.println("carte1 == carte3 ? " + carte1.equals(carte3)); // false (titlu diferit)

        System.out.println(carte1.hashCode() == carte2.hashCode()); // true - trebuie să fie identice
        System.out.println(carte1);
    }
}
