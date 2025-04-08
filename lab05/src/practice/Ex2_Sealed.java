package practice;

/**
 * Cerință:
 * 1. Definiți o clasă sealed `Eveniment` care permite moștenirea doar pentru clasele `Conferinta` și `Workshop`.
 * 2. Creați clasele `Conferinta` și `Workshop`, fiecare cu un câmp: `nume` și o metodă `descriere()`.
 * 3. Creați o clasă `CursOnline` care încearcă să extindă `Eveniment` – observați eroarea de compilare.
 * 4. Creați o metodă statică care primește un `Eveniment` și apelează `descriere()` folosind pattern matching cu `instanceof`.
 * 5. Adăugați și o clasă `Webinar` care extinde `Workshop` și observă că este permis, pentru că `Workshop` nu e `sealed`.
 */

sealed abstract class Eveniment permits Conferinta, Workshop {
    abstract void descriere();
}

final class Conferinta extends Eveniment {
    String nume;

    Conferinta(String nume) {
        this.nume = nume;
    }

    @Override
    void descriere() {
        System.out.println("Conferinta: " + nume);
    }
}

non-sealed class Workshop extends Eveniment {
    String nume;

    Workshop(String nume) {
        this.nume = nume;
    }

    @Override
    void descriere() {
        System.out.println("Workshop: " + nume);
    }
}

// Această clasă ar produce eroare, deoarece nu este permisă de sealed class
/*
class CursOnline extends Eveniment {
    @Override
    void descriere() {}
}
*/

class Webinar extends Workshop {
    Webinar(String nume) {
        super(nume);
    }
}

class Ex2_Sealed {
    static void afiseazaDescriere(Eveniment e) {
        if (e instanceof Conferinta c) {
            c.descriere();
        } else if (e instanceof Workshop w) {
            w.descriere();
        }
    }

    public static void main(String[] args) {
        afiseazaDescriere(new Conferinta("Java Summit"));
        afiseazaDescriere(new Webinar("Webinar Modern Java"));
    }
}
