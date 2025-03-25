package practice;

/**
 * Creați o ierarhie de clase pornind de la o superclasă Vehicul.
 * Derivați două clase: Masina și Motocicleta, fiecare cu atribute și metode proprii.
 * În main(), creați un array de tip Vehicul, adăugați instanțe de Masina și Motocicleta și:
 *      Afișați tipul real al fiecărui obiect folosind getClass().
 *      Verificați tipul fiecărui obiect folosind instanceof.
 *      Folosiți downcasting pentru a apela metode specifice subclaselor.
 * Suprascrieți metoda toString() pentru fiecare clasă pentru a oferi o reprezentare clară.
 */

abstract class Vehicul {
    protected String marca;

    public Vehicul(String marca) {
        this.marca = marca;
    }

    public abstract void porneste();

    @Override
    public String toString() {
        return "Vehicul marca: " + marca;
    }
}

class Masina extends Vehicul {
    private int nrLocuri;

    public Masina(String marca, int nrLocuri) {
        super(marca);
        this.nrLocuri = nrLocuri;
    }

    @Override
    public void porneste() {
        System.out.println("Mașina " + marca + " pornește...");
    }

    public void deschidePortbagaj() {
        System.out.println("Portbagaj deschis!");
    }

    @Override
    public String toString() {
        return "Masina{" + "marca='" + marca + "', nrLocuri=" + nrLocuri + '}';
    }
}

class Motocicleta extends Vehicul {
    private boolean areCasca;

    public Motocicleta(String marca, boolean areCasca) {
        super(marca);
        this.areCasca = areCasca;
    }

    @Override
    public void porneste() {
        System.out.println("Motocicleta " + marca + " pornește...");
    }

    public void puneCasca() {
        System.out.println("Casca pusă!");
    }

    @Override
    public String toString() {
        return "Motocicleta{" + "marca='" + marca + "', areCasca=" + areCasca + '}';
    }
}

public class Ex1_Casting {
    public static void main(String[] args) {
        Vehicul[] vehicule = {
                new Masina("BMW", 5),
                new Motocicleta("Yamaha", true)
        };

        for (Vehicul v : vehicule) {
            System.out.println("Tip: " + v.getClass().getSimpleName());
            v.porneste();

            if (v instanceof Masina) {
                ((Masina) v).deschidePortbagaj(); // Downcasting
            } else if (v instanceof Motocicleta) {
                ((Motocicleta) v).puneCasca(); // Downcasting
            }

            System.out.println(v); // toString() suprascris
            System.out.println();
        }
    }
}
