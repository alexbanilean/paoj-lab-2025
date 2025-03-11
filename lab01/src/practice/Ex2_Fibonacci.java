package practice;

import java.util.Scanner;

/**
 * Scrieți o metodă care returnează elementul N al secvenței Fibonacci.
 * Exemplificați, citind N de la tastatură și apelând metoda din clasa main.
 *
 * Exemplu:
 * fibonacci(4) → 2
 * fibonacci(8) → 13
 */
public class Ex2_Fibonacci {

    public static int fibonacci(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Indexul Fibonacci trebuie să fie ≥ 1");
        }
        if (n == 1) return 0;
        if (n == 2) return 1;

        int a = 0, b = 1;
        for (int i = 3; i <= n; i++) {
            int next = a + b;
            a = b;
            b = next;
        }
        return b;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduceți un număr N: ");
        int n = scanner.nextInt();
        scanner.close();

        System.out.println("fibonacci(" + n + ") → " + fibonacci(n));
    }
}

