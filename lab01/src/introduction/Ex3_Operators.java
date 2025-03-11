package introduction;

/**
 * Exemplu de utilizare a operatorilor în Java.
 *
 * Această clasă demonstrează diferite categorii de operatori:
 *
 * 1. Operatorii aritmetici:
 *    - +, -, *, /, %
 *    - Folosiți pentru operații matematice între valori numerice.
 *
 * 2. Operatorii relaționali:
 *    - <, >, <=, >=, ==, !=
 *    - Compară două valori și returnează un boolean.
 *
 * 3. Operatorii logici:
 *    - && (AND), || (OR), ! (NOT)
 *    - Combină expresii booleene pentru a forma condiții complexe.
 *
 * 4. Operatorii bitwise:
 *    - &, |, ^, ~, <<, >>, >>>
 *    - Operații pe nivelul biților (folosiți de obicei cu tipuri integrale).
 *
 * 5. Operatorii de atribuire:
 *    - =, +=, -=, *=, /=, %=, etc.
 *    - Atribuie o valoare unei variabile și, în combinație, efectuează o operație.
 *
 * 6. Operatorul ternar:
 *    - ? :
 *    - O formă concisă de expresie if-else care returnează o valoare.
 *
 * 7. Operatorii de incrementare și decrementare:
 *    - ++, --
 *    - Folosiți pentru a mări sau micșora o valoare numerică cu 1.
 *
 * Pentru mai multe detalii, consultați:
 * https://www.baeldung.com/java-operators
 */
public class Ex3_Operators {

    public static void main(String[] args) {
        // ---------------------------------------------------------
        // 1. Operatorii aritmetici
        // ---------------------------------------------------------
        int a = 10;
        int b = 3;
        int sum = a + b;           // Adunare: 10 + 3 = 13
        int diff = a - b;          // Scădere: 10 - 3 = 7
        int prod = a * b;          // Înmulțire: 10 * 3 = 30
        int quot = a / b;          // Împărțire: 10 / 3 = 3 (împărțire între int)
        int mod = a % b;           // Modul: restul împărțirii 10 % 3 = 1

        System.out.println("Operatori aritmetici:");
        System.out.println("Suma: " + sum);
        System.out.println("Diferența: " + diff);
        System.out.println("Produsul: " + prod);
        System.out.println("Împărțirea: " + quot);
        System.out.println("Modul: " + mod);
        System.out.println();

        // ---------------------------------------------------------
        // 2. Operatorii relaționali
        // ---------------------------------------------------------
        boolean eq = (a == b);     // Egalitate: false
        boolean ne = (a != b);     // Diferență: true
        boolean gt = (a > b);      // Mai mare: true
        boolean lt = (a < b);      // Mai mic: false
        boolean ge = (a >= b);     // Mai mare sau egal: true
        boolean le = (a <= b);     // Mai mic sau egal: false

        System.out.println("Operatori relaționali:");
        System.out.println("a == b: " + eq);
        System.out.println("a != b: " + ne);
        System.out.println("a > b: " + gt);
        System.out.println("a < b: " + lt);
        System.out.println("a >= b: " + ge);
        System.out.println("a <= b: " + le);
        System.out.println();

        // ---------------------------------------------------------
        // 3. Operatorii logici
        // ---------------------------------------------------------
        boolean logicAnd = (true && false);  // AND: false
        boolean logicOr  = (true || false);   // OR: true
        boolean logicNot = !true;             // NOT: false

        System.out.println("Operatori logici:");
        System.out.println("true && false: " + logicAnd);
        System.out.println("true || false: " + logicOr);
        System.out.println("!true: " + logicNot);
        System.out.println();

        // ---------------------------------------------------------
        // 4. Operatorii bitwise
        // ---------------------------------------------------------
        // Folosim numerele a = 5 (0101) și b = 3 (0011) în reprezentare binară
        int x = -5;   // 0101 în binar
        int y = 3;   // 0011 în binar
        int bitAnd = x & y;      // Bitwise AND: 0101 & 0011 = 0001 (1)
        int bitOr = x | y;       // Bitwise OR:  0101 | 0011 = 0111 (7)
        int bitXor = x ^ y;      // Bitwise XOR: 0101 ^ 0011 = 0110 (6)
        int bitNot = ~x;         // Bitwise NOT: ~0101 (depinde de reprezentare pe 32 biți)
        int leftShift = x << 1;  // Shift la stânga: 0101 devine 1010 (10)
        int rightShift = x >> 1; // Shift la dreapta: 0101 devine 0010 (2)
        int unsignedRightShift = x >>> 1; // Shift la dreapta fără semn: 0101 devine 0010 (2)

        System.out.println("Operatori bitwise:");
        System.out.println("x & y: " + bitAnd);
        System.out.println("x | y: " + bitOr);
        System.out.println("x ^ y: " + bitXor);
        System.out.println("~x: " + bitNot);
        System.out.println("x << 1: " + leftShift);
        System.out.println("x >> 1: " + rightShift);
        System.out.println("x >>> 1: " + unsignedRightShift);
        System.out.println();

        // ---------------------------------------------------------
        // 5. Operatorii de atribuire
        // ---------------------------------------------------------
        int c = 5;
        c += 2;  // Echivalent cu: c = c + 2; c devine 7
        c -= 1;  // c = c - 1; c devine 6
        c *= 3;  // c = c * 3; c devine 18
        c /= 2;  // c = c / 2; c devine 9 (împărțire între int)
        c %= 4;  // c = c % 4; c devine 1

        System.out.println("Operatori de atribuire:");
        System.out.println("Valoarea finală a lui c: " + c);
        System.out.println();

        // ---------------------------------------------------------
        // 6. Operatorul ternar
        // ---------------------------------------------------------
        // Forma scurtă a unei instrucțiuni if-else
        int max = (a > b) ? a : b;  // Dacă a > b, max = a; altfel, max = b
        System.out.println("Operator ternar:");
        System.out.println("Maximul dintre a și b: " + max);
        System.out.println();

        // ---------------------------------------------------------
        // 7. Operatorii de incrementare și decrementare
        // ---------------------------------------------------------
        int d = 10;
        int postIncrement = d++;  // Valoarea este utilizată înainte de incrementare (10), d devine apoi 11
        int preIncrement = ++d;   // d este incrementat mai întâi (devine 12), apoi este folosit (12)
        int postDecrement = d--;  // d este folosit înainte de decrementare (12), apoi devine 11
        int preDecrement = --d;   // d este decrementat înainte de utilizare (devine 10), apoi este folosit (10)

        System.out.println("Operatori de incrementare/decrementare:");
        System.out.println("Post-increment: " + postIncrement);
        System.out.println("Pre-increment: " + preIncrement);
        System.out.println("Post-decrement: " + postDecrement);
        System.out.println("Pre-decrement: " + preDecrement);
    }
}
