package practice;

/**
 * Cerință:
 * 1. Creați o clasă Angajat cu atribute:
 *  - String nume
 *  - String departament
 *  - double salariu
 *  - List<String> skilluri
 *  - Optional<Integer> vechimeAni (care poate lipsi pentru angajații noi)
 *
 * 2. Inițializați o listă de cel puțin 10 angajați, din departamente diferite (HR, IT, FINANTE, MARKETING), cu skill-uri diverse.
 *
 * 3. Cerințe funcționale:
 *  - Găsiți toți angajații care:
 *      - Au salariul peste media companiei.
 *      - Au skillul "Java".
 *  - Calculați:
 *      - Salariul mediu pe departament (groupingBy() + averagingDouble()).
 *      - Numărul de angajați fără vechime folosind filter() + Optional.isEmpty().
 *  - Folosiți flatMap() pentru:
 *      - Lista completă a skill-urilor distincte din companie.
 *  - Folosiți collect(Collectors.toMap()) pentru:
 *      - Maparea numelui angajatului la salariul său.
 *  - Găsiți:
 *      - Angajatul cu cel mai mare salariu (folosind max(Comparator)).
 *      - Angajatul cu cele mai multe skill-uri.
 *  - Folosiți Optional.orElseGet() pentru:
 *      - A afișa un mesaj default "Fără vechime" dacă vechimea lipsește.
 *  - Sortați angajații în funcție de:
 *      - Numărul de skilluri în mod descrescător.
 *  - Creați un Predicate<Angajat> pentru a selecta angajații "elite" (salariu > 8000 și skill "Leadership") și folosiți-l în filter().
 */

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.*;

class Angajat {
    private String nume;
    private String departament;
    private double salariu;
    private List<String> skilluri;
    private Optional<Integer> vechimeAni;

    public Angajat(String nume, String departament, double salariu, List<String> skilluri, Optional<Integer> vechimeAni) {
        this.nume = nume;
        this.departament = departament;
        this.salariu = salariu;
        this.skilluri = skilluri;
        this.vechimeAni = vechimeAni;
    }

    public String getNume() { return nume; }
    public String getDepartament() { return departament; }
    public double getSalariu() { return salariu; }
    public List<String> getSkilluri() { return skilluri; }
    public Optional<Integer> getVechimeAni() { return vechimeAni; }

    @Override
    public String toString() {
        return nume + " (" + departament + ", " + salariu + " RON, " + skilluri + ")";
    }
}

public class Ex2_EmployeeAnalysis {
    public static void main(String[] args) {
        List<Angajat> angajati = List.of(
                new Angajat("Ana", "IT", 9000, List.of("Java", "Spring"), Optional.of(5)),
                new Angajat("Mihai", "FINANTE", 6500, List.of("Excel", "Analiza"), Optional.empty()),
                new Angajat("Dana", "HR", 5000, List.of("Recrutare", "Comunicare"), Optional.of(2)),
                new Angajat("Cosmin", "IT", 12000, List.of("Java", "AWS", "Docker"), Optional.of(7)),
                new Angajat("Irina", "MARKETING", 4500, List.of("Social Media", "SEO"), Optional.of(3)),
                new Angajat("Bogdan", "FINANTE", 7200, List.of("Contabilitate"), Optional.empty()),
                new Angajat("Elena", "HR", 5300, List.of("Training"), Optional.of(1)),
                new Angajat("Diana", "MARKETING", 4700, List.of("Copywriting", "Creativitate"), Optional.empty()),
                new Angajat("Lucian", "IT", 10500, List.of("Java", "Microservices", "Leadership"), Optional.of(4)),
                new Angajat("Adrian", "IT", 9900, List.of("Security", "Networking"), Optional.empty())
        );

        // Salariul mediu
        double medie = angajati.stream()
                .mapToDouble(Angajat::getSalariu)
                .average()
                .orElse(0.0);
        System.out.println("Salariu mediu companie: " + medie);

        // Angajati peste medie
        System.out.println("\n== Angajati peste medie ==");
        angajati.stream()
                .filter(a -> a.getSalariu() > medie)
                .forEach(System.out::println);

        // Angajati cu Java
        System.out.println("\n== Angajati cu skill Java ==");
        angajati.stream()
                .filter(a -> a.getSkilluri().contains("Java"))
                .forEach(System.out::println);

        // Salariu mediu per departament
        System.out.println("\n== Salariu mediu pe departament ==");
        Map<String, Double> mediiDep = angajati.stream()
                .collect(Collectors.groupingBy(Angajat::getDepartament, Collectors.averagingDouble(Angajat::getSalariu)));
        System.out.println(mediiDep);

        // Fara vechime
        System.out.println("\n== Angajati fara vechime ==");
        angajati.stream()
                .filter(a -> a.getVechimeAni().isEmpty())
                .forEach(System.out::println);

        // Skilluri distincte
        System.out.println("\n== Skilluri distincte ==");
        angajati.stream()
                .flatMap(a -> a.getSkilluri().stream())
                .distinct()
                .forEach(System.out::println);

        // Mapare nume-salariu
        System.out.println("\n== Mapare nume -> salariu ==");
        Map<String, Double> numeSalariu = angajati.stream()
                .collect(Collectors.toMap(Angajat::getNume, Angajat::getSalariu));
        System.out.println(numeSalariu);

        // Angajat cu salariul maxim
        angajati.stream()
                .max(Comparator.comparingDouble(Angajat::getSalariu))
                .ifPresent(a -> System.out.println("\nSalariu maxim: " + a));

        // Angajat cu cele mai multe skilluri
        angajati.stream()
                .max(Comparator.comparingInt(a -> a.getSkilluri().size()))
                .ifPresent(a -> System.out.println("\nCel mai multi skilluri: " + a));

        // Bonus Predicate elite
        Predicate<Angajat> elite = a -> a.getSalariu() > 8000 && a.getSkilluri().contains("Leadership");
        System.out.println("\n== Angajati elite ==");
        angajati.stream()
                .filter(elite)
                .forEach(System.out::println); // în acest caz, probabil niciunul
    }
}

