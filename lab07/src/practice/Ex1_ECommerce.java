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
public class Ex1_ECommerce {
}
