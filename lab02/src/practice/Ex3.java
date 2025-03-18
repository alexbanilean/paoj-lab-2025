package practice;

/**
 * Creați o clasă Calcul care să conțină mai multe variante ale metodei suma(), astfel încât să accepte:
 *
 * Două numere întregi (int).
 * Trei numere reale (double).
 * Un tablou de numere (int[]).
 * Toate metodele trebuie să returneze rezultatul și să afișeze ce variantă de metodă a fost apelată.
 *
 * În metoda main(), testați toate variantele metodei suma().
 */

class Calcul {
    public int suma(int a, int b) {
        System.out.println("Suma a doua intregi.");
        return a + b;
    }

    public double suma(double a, double b, double c) {
        System.out.println("Suma a trei numere reale.");
        return a + b + c;
    }

    public int suma(int[] numere) {
        System.out.println("Suma unui tablou de intregi.");
        int sum = 0;
        for (int numar : numere) {
            sum += numar;
        }
        return sum;
    }
}

public class Ex3 {
    public static void main(String[] args) {
        Calcul calc = new Calcul();
        System.out.println("Rezultatul: " + calc.suma(5, 10));
        System.out.println("Rezultatul: " + calc.suma(2.5, 3.5, 4.5));
        System.out.println("Rezultatul: " + calc.suma(new int[]{1, 2, 3, 4, 5}));
    }
}

