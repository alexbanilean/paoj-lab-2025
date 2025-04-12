package practice;

import java.util.List;

/**
 * Cerință:
 * 1. Definiți o interfață `Actiune` cu metodele: `executa()` și `revoca()`.
 * 2. Creați o clasă abstractă `AdaptorActiune` care oferă implementări goale (do-nothing) pentru metodele din interfață.
 * 3. Creați o clasă `StergereEveniment` care extinde `AdaptorActiune`, și implementează doar metoda `executa()`.
 * 4. Creați o interfață marker `Critica`, fără metode.
 * 5. Marcați `StergereEveniment` cu această interfață.
 * 6. Scrieți o metodă care primește o listă de obiecte `Actiune` și execută doar cele care sunt și `instanceof Critica`.
 */

interface Actiune {
    void executa();
    void revoca();
}

abstract class AdaptorActiune implements Actiune {
    @Override
    public void executa() {
        // implementare goală
    }

    @Override
    public void revoca() {
        // implementare goală
    }
}

// Interfață marker
interface Critica {}

class StergereEveniment extends AdaptorActiune implements Critica {
    @Override
    public void executa() {
        System.out.println("Eveniment critic șters.");
    }
}

class StergereEvenimentNormal extends AdaptorActiune {
    @Override
    public void executa() {
        System.out.println("Eveniment normal șters.");
    }
}

class Ex1_Adapter_Marker {
    public static void executaActiuniCritice(List<Actiune> actiuni) {
        for (Actiune actiune : actiuni) {
            if (actiune instanceof Critica) {
                actiune.executa();
            }
        }
    }

    public static void main(String[] args) {
        List<Actiune> lista = List.of(new StergereEveniment(), new StergereEvenimentNormal());
        executaActiuniCritice(lista);
    }
}
