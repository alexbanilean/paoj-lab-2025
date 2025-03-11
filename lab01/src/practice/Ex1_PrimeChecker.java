package practice;

/**
 * Scrieți o metodă care verifică dacă un număr este număr prim.
 * Exemplificați, apelând metoda din clasa main.
 *
 * Exemplu:
 * isPrime(7) → true
 * isPrime(10) → false
 */
public class Ex1_PrimeChecker {

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] testNumbers = {7, 10, 13, 20, 23};

        for (int num : testNumbers) {
            System.out.println("isPrime(" + num + ") → " + isPrime(num));
        }
    }
}
