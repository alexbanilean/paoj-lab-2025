package practice;

/**
 * Cerință:
 * 1. Definiți o interfață `Forma` cu următoarele metode abstracte:
 *    - `double calculeazaArie();`
 *    - `double calculeazaPerimetru();`
 * 2. Creați un enum `Culoare` care să reprezinte câteva culori (ex.: ROSU, VERDE, ALBASTRU),
 *    fiecare având un câmp descriptiv.
 * 3. Implementați două clase care extind interfața `Forma`:
 *    - `Cerc`: cu atributul `raza` și o proprietate de tip `Culoare`.
 *    - `Dreptunghi`: cu atributele `latime`, `inaltime` și o proprietate de tip `Culoare`.
 * 4. Suprascrieți metoda `toString()` în ambele clase pentru a afișa detaliile formei.
 * 5. Creați o colecție (de tip `ArrayList<Forma>`) pentru a stoca diverse forme.
 *    - Parcurge colecția cu un iterator și afișează detaliile fiecărei forme.
 * 6. Utilizați interfața `Iterator` pentru a elimina din colecție toate formele cu culoarea ROSU.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

interface Forma {
    double calculeazaArie();
    double calculeazaPerimetru();
}

enum Culoare {
    ROSU("Roșu"), VERDE("Verde"), ALBASTRU("Albastru");

    private final String descriere;

    Culoare(String descriere) {
        this.descriere = descriere;
    }

    public String getDescriere() {
        return descriere;
    }
}

class Cerc implements Forma {
    private double raza;
    private Culoare culoare;

    public Cerc(double raza, Culoare culoare) {
        this.raza = raza;
        this.culoare = culoare;
    }

    @Override
    public double calculeazaArie() {
        return Math.PI * raza * raza;
    }

    @Override
    public double calculeazaPerimetru() {
        return 2 * Math.PI * raza;
    }

    @Override
    public String toString() {
        return "Cerc [Raza=" + raza + ", Culoare=" + culoare.getDescriere() + "]";
    }
}

class Dreptunghi implements Forma {
    private double latime, inaltime;
    private Culoare culoare;

    public Dreptunghi(double latime, double inaltime, Culoare culoare) {
        this.latime = latime;
        this.inaltime = inaltime;
        this.culoare = culoare;
    }

    @Override
    public double calculeazaArie() {
        return latime * inaltime;
    }

    @Override
    public double calculeazaPerimetru() {
        return 2 * (latime + inaltime);
    }

    @Override
    public String toString() {
        return "Dreptunghi [Latime=" + latime + ", Inaltime=" + inaltime + ", Culoare=" + culoare.getDescriere() + "]";
    }
}

public class Ex1_Geometry_Colors {
    public static void main(String[] args) {
        List<Forma> forme = new ArrayList<>();

        forme.add(new Cerc(5, Culoare.ROSU));
        forme.add(new Dreptunghi(4, 6, Culoare.VERDE));
        forme.add(new Cerc(3, Culoare.ALBASTRU));

        System.out.println("Forme inițiale:");
        for (Forma forma : forme) {
            System.out.println(forma);
        }

        // Eliminăm formele de culoare ROSU folosind Iterator
        Iterator<Forma> iterator = forme.iterator();
        while (iterator.hasNext()) {
            Forma forma = iterator.next();
            if (forma instanceof Cerc && ((Cerc) forma).toString().contains("Roșu")) {
                iterator.remove();
            }
        }

        System.out.println("\nForme după eliminarea celor roșii:");
        for (Forma forma : forme) {
            System.out.println(forma);
        }
    }
}
