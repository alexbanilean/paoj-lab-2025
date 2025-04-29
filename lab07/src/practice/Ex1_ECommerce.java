package practice;

/**
 * Cerință:
 * 1. Creați o clasă Produs cu atribute:
 *    - String nume
 *    - double pret
 *    - Categoria categorie (Enum: ELECTRONICE, IMBRACAMINTE, ALIMENTE, CARTI)
 *
 * 2. Creați o clasă Comanda:
 *    - List<Produs> produse
 *    - String numeClient
 *    - boolean livrata
 *    - Optional<String> adresaLivrare (care poate lipsi)
 *
 * 3. Inițializați o listă de cel puțin 8 comenzi diferite, unele livrate, unele nelivrate, unele fără adresa de livrare (folosiți Optional.empty()).
 *
 * 4. Cerințe funcționale:
 *    - Folosiți stream() și filter() pentru a găsi toate comenzile nelivrate.
 *    - Folosiți map() și flatMap() pentru a obține toate produsele unice comandate (fără duplicate).
 *    - Calculați:
 *      - Numărul total de comenzi livrate (folosind count()).
 *      - Suma totală a valorii comenzilor livrate.
 *    - Folosiți groupingBy() pentru a grupa comenzile pe categorii de produs.
 *    - Folosiți Optional pentru:
 *      - A extrage și afișa adresele de livrare doar pentru comenzile unde există o adresă.
 *    - Folosiți sorted() pentru a afișa comenzile după:
 *      - Numărul de produse (descrescător).
 *    - Folosiți reduce() pentru a calcula:
 *      - Cel mai scump produs din toate comenzile.
 *    - Afișați raportul final:
 *      - total comenzi,
 *      - total produse livrate,
 *      - produsul cu cel mai mare preț,
 *      - comenzile fără adresă.
 */

import java.util.*;
import java.util.stream.*;

enum Categoria {
    ELECTRONICE, IMBRACAMINTE, ALIMENTE, CARTI
}

class Produs {
    private String nume;
    private double pret;
    private Categoria categorie;

    public Produs(String nume, double pret, Categoria categorie) {
        this.nume = nume;
        this.pret = pret;
        this.categorie = categorie;
    }

    public String getNume() { return nume; }
    public double getPret() { return pret; }
    public Categoria getCategorie() { return categorie; }

    @Override
    public String toString() {
        return nume + " (" + categorie + ", " + pret + " RON)";
    }
}

class Comanda {
    private List<Produs> produse;
    private String numeClient;
    private boolean livrata;
    private Optional<String> adresaLivrare;

    public Comanda(String numeClient, List<Produs> produse, boolean livrata, Optional<String> adresaLivrare) {
        this.numeClient = numeClient;
        this.produse = produse;
        this.livrata = livrata;
        this.adresaLivrare = adresaLivrare;
    }

    public List<Produs> getProduse() { return produse; }
    public boolean isLivrata() { return livrata; }
    public Optional<String> getAdresaLivrare() { return adresaLivrare; }
    public String getNumeClient() { return numeClient; }

    @Override
    public String toString() {
        return "Comanda{" +
                "numeClient='" + numeClient + '\'' +
                ", produse=" + produse +
                ", livrata=" + livrata +
                ", adresaLivrare=" + adresaLivrare.orElse("Fara adresa") +
                '}';
    }
}

public class Ex1_ECommerce {
    public static void main(String[] args) {
        List<Produs> catalog = List.of(
                new Produs("Laptop", 4000, Categoria.ELECTRONICE),
                new Produs("Tricou", 80, Categoria.IMBRACAMINTE),
                new Produs("Paine", 5, Categoria.ALIMENTE),
                new Produs("Carte Java", 150, Categoria.CARTI),
                new Produs("Telefon", 2500, Categoria.ELECTRONICE),
                new Produs("Blugi", 200, Categoria.IMBRACAMINTE)
        );

        List<Comanda> comenzi = List.of(
                new Comanda("Andrei", List.of(catalog.get(0), catalog.get(1)), true, Optional.of("Strada A")),
                new Comanda("Maria", List.of(catalog.get(2), catalog.get(3)), false, Optional.empty()),
                new Comanda("George", List.of(catalog.get(1), catalog.get(5)), true, Optional.of("Strada B")),
                new Comanda("Elena", List.of(catalog.get(4)), false, Optional.of("Strada C")),
                new Comanda("Ioana", List.of(catalog.get(3), catalog.get(5)), true, Optional.empty()),
                new Comanda("Radu", List.of(catalog.get(2), catalog.get(2)), true, Optional.of("Strada D"))
        );

        // Comenzi nelivrate
        System.out.println("== Comenzi nelivrate ==");
        comenzi.stream()
                .filter(c -> !c.isLivrata())
                .forEach(System.out::println);

        // Produse unice
        System.out.println("\n== Produse unice comandate ==");
        comenzi.stream()
                .flatMap(c -> c.getProduse().stream())
                .distinct()
                .forEach(System.out::println);

        // Numar comenzi livrate
        long nrLivrate = comenzi.stream()
                .filter(Comanda::isLivrata)
                .count();
        System.out.println("\nNumar comenzi livrate: " + nrLivrate);

        // Suma valorii comenzilor livrate
        double totalLivrari = comenzi.stream()
                .filter(Comanda::isLivrata)
                .flatMap(c -> c.getProduse().stream())
                .mapToDouble(Produs::getPret)
                .sum();
        System.out.println("Valoare totala comenzi livrate: " + totalLivrari + " RON");

        // Grupare pe categorii
        System.out.println("\n== Grupare produse pe categorii ==");
        Map<Categoria, List<Produs>> grupare = comenzi.stream()
                .flatMap(c -> c.getProduse().stream())
                .collect(Collectors.groupingBy(Produs::getCategorie));
        System.out.println(grupare);

        // Adrese livrare (Optional)
        System.out.println("\n== Adrese existente ==");
        comenzi.stream()
                .map(Comanda::getAdresaLivrare)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .forEach(System.out::println);

        // Sortare dupa numar produse
        System.out.println("\n== Comenzi sortate dupa numar de produse ==");
        comenzi.stream()
                .sorted(Comparator.comparingInt((Comanda c) -> c.getProduse().size()).reversed())
                .forEach(System.out::println);

        // Cel mai scump produs
        Optional<Produs> celMaiScump = comenzi.stream()
                .flatMap(c -> c.getProduse().stream())
                .reduce((p1, p2) -> p1.getPret() > p2.getPret() ? p1 : p2);
        celMaiScump.ifPresent(p -> System.out.println("\nCel mai scump produs: " + p));
    }
}
