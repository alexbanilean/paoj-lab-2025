package practice;

/**
 * Cerință:
 * 1. Definiți un `enum TipVehicul` cu valori AUTO, CAMION, AUTOBUZ.
 * 2. Creați o interfață `Vehicul` cu metodele:
 *    - `String getNumarInmatriculare();`
 *    - `TipVehicul getTip();`
 * 3. Creați o clasă `Autovehicul` care implementează interfața `Vehicul`.
 * 4. Creați o colecție `Map<TipVehicul, List<Vehicul>>` pentru a organiza vehiculele după tip.
 * 5. Afișați vehiculele pe categorii.
 * 6. Implementați o metodă care filtrează vehiculele cu număr de înmatriculare care începe cu "B".
 */

import java.util.*;

enum TipVehicul {
    AUTO, CAMION, AUTOBUZ;
}

interface Vehicul {
    String getNumarInmatriculare();
    TipVehicul getTip();
}

class Autovehicul implements Vehicul {
    private String numarInmatriculare;
    private TipVehicul tip;

    public Autovehicul(String numarInmatriculare, TipVehicul tip) {
        this.numarInmatriculare = numarInmatriculare;
        this.tip = tip;
    }

    @Override
    public String getNumarInmatriculare() {
        return numarInmatriculare;
    }

    @Override
    public TipVehicul getTip() {
        return tip;
    }

    @Override
    public String toString() {
        return tip + " - " + numarInmatriculare;
    }
}

public class Ex4_Vehicles {
    // Metodă pentru adăugarea unui vehicul într-o colecție Map
    private static void adaugaVehicul(Map<TipVehicul, List<Vehicul>> flota, Vehicul vehicul) {
        flota.computeIfAbsent(vehicul.getTip(), k -> new ArrayList<>()).add(vehicul);
    }

    // Metodă pentru afișarea vehiculelor pe categorii
    private static void afiseazaVehicule(Map<TipVehicul, List<Vehicul>> flota) {
        for (var entry : flota.entrySet()) {
            System.out.println("\n" + entry.getKey() + ":");
            for (Vehicul v : entry.getValue()) {
                System.out.println(v);
            }
        }
    }

    // Metodă pentru filtrarea vehiculelor cu număr care începe cu "B"
    private static List<Vehicul> filtreazaVehiculeB(Map<TipVehicul, List<Vehicul>> flota) {
        List<Vehicul> rezultat = new ArrayList<>();
        for (List<Vehicul> lista : flota.values()) {
            for (Vehicul v : lista) {
                if (v.getNumarInmatriculare().startsWith("B")) {
                    rezultat.add(v);
                }
            }
        }
        return rezultat;
    }

    public static void main(String[] args) {
        Map<TipVehicul, List<Vehicul>> flota = new HashMap<>();

        adaugaVehicul(flota, new Autovehicul("B123XYZ", TipVehicul.AUTO));
        adaugaVehicul(flota, new Autovehicul("CJ456ABC", TipVehicul.CAMION));
        adaugaVehicul(flota, new Autovehicul("B789DEF", TipVehicul.AUTOBUZ));

        afiseazaVehicule(flota);

        // Filtrăm vehiculele cu număr de înmatriculare care începe cu "B"
        List<Vehicul> vehiculeB = filtreazaVehiculeB(flota);
        System.out.println("\nVehicule cu număr de înmatriculare care începe cu 'B':");
        for (Vehicul v : vehiculeB) {
            System.out.println(v);
        }
    }
}

