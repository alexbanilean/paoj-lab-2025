package theory;

import java.util.Arrays;

/**
 * TABLOURI
 * - În Java, tablourile sunt obiecte de tip referință.
 * - Se inițializează înainte de utilizare și au lungime fixă.
 */
class Ex2_Arrays {
    public static void main(String[] args) {
        int[] numbers = {5, 2, 9, 1, 5, 6};
        Arrays.sort(numbers);
        System.out.println(Arrays.toString(numbers)); // [1, 2, 5, 5, 6, 9]
    }
}
