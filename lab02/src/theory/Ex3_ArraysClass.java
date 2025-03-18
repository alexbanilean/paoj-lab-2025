package theory;

import java.util.Arrays;

/**
 * CLASA Arrays
 * - Clasa Arrays oferă metode pentru manipularea tablourilor, inclusiv sortare, căutare și copiere.
 */
class Ex3_ArraysClass {
    public static void main(String[] args) {
        int[] t = {1, 2, 3, 4, 5};
        int[] v = {1, 2, 3, 4, 5};
        int[] w = {1, 2, 4, 4, 5};

        System.out.println(Arrays.toString(t)); // [1, 2, 3, 4, 5]
        System.out.println(Arrays.equals(t, v)); // true
        System.out.println(Arrays.equals(w, v)); // false
        System.out.println(Arrays.deepToString(new int[][]{{1,2},{3,4}})); // [[1, 2], [3, 4]]
    }
}
