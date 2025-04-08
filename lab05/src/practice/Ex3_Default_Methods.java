package practice;

/**
 * Cerință:
 * 1. Creați două interfețe: `A` și `B`, fiecare cu o metodă `afiseaza()`, cu implementare default diferită.
 * 2. Creați o interfață `C` care extinde `A` și `B`. Suprascrieți metoda `afiseaza()` pentru a rezolva conflictul.
 * 3. Adăugați o metodă `static` în interfața `A` și una `private` în interfața `B`.
 * 4. Creați o clasă `Implementare` care implementează `C` și testează toate metodele.
 * 5. Explicați motivul pentru care este necesară suprascrierea metodei `afiseaza()` în `C`.
 */

interface A {
    default void afiseaza() {
        System.out.println("Din A");
    }

    static void metodaStatica() {
        System.out.println("Static din A");
    }
}

interface B {
    default void afiseaza() {
        System.out.println("Din B");
    }

    private void metodaPrivata() {
        System.out.println("Privat în B");
    }

    default void apelPrivat() {
        metodaPrivata();
    }
}

// C moștenește două interfețe care conțin aceeași metodă default -> trebuie să o redeclare explicit
interface C extends A, B {
    @Override
    default void afiseaza() {
        A.super.afiseaza();
        B.super.afiseaza();
        System.out.println("Conflict rezolvat în C");
    }
}

class Implementare implements C {
    public static void main(String[] args) {
        Implementare imp = new Implementare();
        imp.afiseaza();       // testează suprascrierea
        A.metodaStatica();    // apel metodă statică
        imp.apelPrivat();     // apel metodă private din B prin default method
    }
}


