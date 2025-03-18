package practice;

/**
 * Creați o clasă abstractă Angajat cu:
 *
 * Atribute: nume (String), salariuBaza (double).
 * Constructor care inițializează toate atributele.
 * O metodă abstractă calculeazaVenit() și o metodă afiseazaInformatii().
 * Creați două clase care extind Angajat:
 *
 * Inginer – are un atribut suplimentar bonus (double) și suprascrie calculeazaVenit() pentru a adăuga bonusul la salariul de bază.
 * Economist – are un atribut suplimentar treapta (int) și un vector static cu sporurile procentuale aferente fiecărei trepte (ex. {5, 10, 15, 20, 25, 30}). calculeazaVenit() va crește salariul cu procentul corespunzător treptei.
 *
 * În metoda main():
 *
 * Creați un tablou de angajați (cu referință de tip Angajat), unde adăugați obiecte de tip Inginer și Economist.
 * Parcurgeți tabloul și apelați calculeazaVenit() și afiseazaInformatii(), demonstrând polimorfismul.
 */

abstract class Angajat {
    String nume;
    double salariuBaza;

    public Angajat(String nume, double salariuBaza) {
        this.nume = nume;
        this.salariuBaza = salariuBaza;
    }

    abstract double calculeazaVenit();

    public void afiseazaInformatii() {
        System.out.println("Nume: " + nume);
        System.out.println("Salariu: " + salariuBaza);
    }
}

class Inginer extends Angajat {
    double bonus;

    public Inginer(String nume, double salariuBaza, double bonus) {
        super(nume, salariuBaza);
        this.bonus = bonus;
    }

    @Override
    double calculeazaVenit() {
        return salariuBaza + bonus;
    }
}

class Economist extends Angajat {
    int treapta;
    static double[] sporuri = {5, 10, 15, 20, 25, 30};

    public Economist(String nume, double salariuBaza, int treapta) {
        super(nume, salariuBaza);
        this.treapta = treapta;
    }

    @Override
    double calculeazaVenit() {
        return salariuBaza * (1 + sporuri[treapta] / 100);
    }
}

public class Ex5 {
    public static void main(String[] args) {
        Angajat[] angajati = new Angajat[2];
        angajati[0] = new Inginer("Ion Popescu", 3000, 500);
        angajati[1] = new Economist("Maria Ionescu", 2500, 2);

        for (Angajat angajat : angajati) {
            angajat.afiseazaInformatii();
            System.out.println("Venit total: " + angajat.calculeazaVenit());
            System.out.println();
        }
    }
}
