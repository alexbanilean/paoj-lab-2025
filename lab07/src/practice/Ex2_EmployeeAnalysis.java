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
public class Ex2_EmployeeAnalysis {
}
