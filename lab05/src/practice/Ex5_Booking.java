package practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Cerință:
 * 1. Creați o clasă sealed `Rezervare` cu două subclase permise: `RezervareHotel` și `RezervareAvion`.
 * 2. Creați o interfață `Validabil` cu o metodă `valideaza()`, implementată diferit în fiecare subclasă:
 *    - dacă numărul de persoane e negativ => aruncă `IllegalArgumentException`
 *    - dacă datele nu sunt completate => aruncă `CheckedCustomException`
 * 3. Creați o interfață `Persistenta` cu o metodă `salveaza()`, dar oferă o clasă adaptor `PersistentaAdaptor`
 *    care implementează metodele goale.
 * 4. Creați o clasă `ManagerRezervari` care stochează o listă de `Rezervare`, apelează `valideaza()` și tratează erorile.
 * 5. Adăugați o clasă `RezervareInterna` care încearcă să extindă `Rezervare` și observați eroarea.
 */

class CheckedCustomException extends Exception {
    public CheckedCustomException(String msg) {
        super(msg);
    }
}

interface Validabil {
    void valideaza() throws CheckedCustomException;
}

sealed abstract class Rezervare permits RezervareHotel, RezervareAvion {
    String client;
    int persoane;

    public Rezervare(String client, int persoane) {
        this.client = client;
        this.persoane = persoane;
    }
}

final class RezervareHotel extends Rezervare implements Validabil {
    String locatie;

    public RezervareHotel(String client, int persoane, String locatie) {
        super(client, persoane);
        this.locatie = locatie;
    }

    public void valideaza() throws CheckedCustomException {
        if (persoane < 0) {
            throw new IllegalArgumentException("Număr persoane invalid.");
        }
        if (client == null || locatie == null) {
            throw new CheckedCustomException("Date lipsă pentru hotel.");
        }
    }
}

final class RezervareAvion extends Rezervare implements Validabil {
    String zbor;

    public RezervareAvion(String client, int persoane, String zbor) {
        super(client, persoane);
        this.zbor = zbor;
    }

    public void valideaza() throws CheckedCustomException {
        if (persoane < 0) {
            throw new IllegalArgumentException("Număr pasageri negativi.");
        }
        if (client == null || zbor == null) {
            throw new CheckedCustomException("Date lipsă pentru zbor.");
        }
    }
}

interface Persistenta {
    void salveaza();
}

abstract class PersistentaAdaptor {
    public void salveaza() {
        // do-nothing
    }
}

// Nu putem face asta:
/*
class RezervareInterna extends Rezervare {
    RezervareInterna(String client, int persoane) {
        super(client, persoane);
    }
}
*/

class ManagerRezervari {
    List<Rezervare> rezervari = new ArrayList<>();

    void adaugaRezervare(Rezervare r) {
        rezervari.add(r);
    }

    void valideazaToate() {
        for (Rezervare r : rezervari) {
            if (r instanceof Validabil v) {
                try {
                    v.valideaza();
                } catch (IllegalArgumentException | CheckedCustomException e) {
                    System.err.println("Eroare validare: " + e.getMessage());
                }
            }
        }
    }

    public static void main(String[] args) {
        ManagerRezervari m = new ManagerRezervari();
        m.adaugaRezervare(new RezervareHotel("Ion", 2, "Bucuresti"));
        m.adaugaRezervare(new RezervareAvion(null, 3, "RO512")); // client null
        m.adaugaRezervare(new RezervareAvion("George", -1, "RO512")); // numar persoane negativ

        m.valideazaToate();
    }
}
