package practice;

import java.util.Arrays;

/**
 * Creați o clasă generică Container<T>.
 * Creați o metodă generică swap() pentru a schimba două valori dintr-un array generic.
 * Testați codul în metoda main().
 */
class Container<T> {
    private T valoare;

    public Container(T valoare) {
        this.valoare = valoare;
    }

    public T getValoare() {
        return valoare;
    }
}

public class Ex4_Generics {
    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        Integer[] numere = {1, 2, 3, 4};
        System.out.println("Original: " + Arrays.toString(numere));
        swap(numere, 1, 3);
        System.out.println("După swap: " + Arrays.toString(numere));

        Container<String> strContainer = new Container<>("Test");
        System.out.println(strContainer.getValoare());
    }
}
