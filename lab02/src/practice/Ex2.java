package practice;

/**
 *
 * Creați o clasă ContBancar care să conțină:
 *
 * Atribute private:
 * IBAN (String) - trebuie inițializat prin constructor și să nu mai poată fi modificat.
 * sold (double) - are o valoare inițială de 0.
 * Implementați următoarele metode:
 *
 * Constructor: primește ca parametru IBAN-ul și îl setează.
 * depunere(double suma) - adaugă suma la sold.
 * retrage(double suma) - scade suma din sold, dar doar dacă există suficient sold (altfel afișează un mesaj de eroare).
 * afiseazaSold() - afișează IBAN-ul și soldul curent.
 *
 * În metoda main(), creați mai multe obiecte ContBancar, efectuați depuneri și retrageri, verificând validarea corectă a soldului.
 */

class ContBancar {
    private final String IBAN;
    private double sold;

    public ContBancar(String IBAN) {
        this.IBAN = IBAN;
        this.sold = 0;
    }

    public void depunere(double suma) {
        if (suma > 0) {
            sold += suma;
        }
    }

    public void retrage(double suma) {
        if (suma <= sold && suma > 0) {
            sold -= suma;
        } else {
            System.out.println("Eroare: Sold insuficient sau suma invalida.\n");
        }
    }

    public void afiseazaSold() {
        System.out.println("IBAN: " + IBAN);
        System.out.println("Sold: " + sold + " RON\n");
    }
}

public class Ex2 {
    public static void main(String[] args) {
        ContBancar cont1 = new ContBancar("RO49AAAA1B31007593840000");
        cont1.depunere(500);
        cont1.retrage(200);
        cont1.afiseazaSold();

        ContBancar cont2 = new ContBancar("RO49AAAA1B31007593840001");
        cont2.depunere(1000);
        cont2.retrage(1200);
        cont2.afiseazaSold();
    }
}

