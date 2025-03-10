package introduction;

/**
 * Clasele Wrapper sunt folosite pentru a lucra cu valorile primitive ca și obiecte.
 *
 * Tipuri de date primitive  |  Clasă Wrapper asociată
 * -------------------------|------------------------
 * byte                     -> Byte
 * short                    -> Short
 * int                      -> Integer
 * long                     -> Long
 * float                    -> Float
 * double                   -> Double
 * boolean                  -> Boolean
 * char                     -> Character
 *
 * Aceste clase oferă metode utile pentru conversii, operații matematice, validări și manipulări de stringuri.
 * De asemenea, autoboxing-ul (convertirea automată a valorilor primitive în obiecte Wrapper)
 * și unboxing-ul (convertirea automată a obiectelor Wrapper în primitive) facilitează manipularea datelor.
 *
 * Documentație suplimentară: https://www.baeldung.com/java-wrapper-classes
 */
public class Ex4_Wrappers {

    public static void main(String[] args) {
        // ------------------------------
        // Exemplu pentru Integer
        // ------------------------------
        // Autoboxing: conversia automată a unui int la Integer
        Integer intWrapped = 5;
        // Unboxing: conversia automată a unui Integer la int
        int intPrimitive = intWrapped;
        System.out.println("Integer example:");
        System.out.println("Valoare: " + intWrapped);
        System.out.println("Metoda intValue(): " + intWrapped.intValue());
        // Conversie din String la int folosind metoda statică parseInt
        System.out.println("Integer.parseInt(\"123\"): " + Integer.parseInt("123"));
        System.out.println();

        // ------------------------------
        // Exemplu pentru Double
        // ------------------------------
        Double doubleWrapped = 5.99;  // Autoboxing de la double la Double
        double doublePrimitive = doubleWrapped; // Unboxing
        System.out.println("Double example:");
        System.out.println("Valoare: " + doubleWrapped);
        System.out.println("Metoda doubleValue(): " + doubleWrapped.doubleValue());
        System.out.println("Double.parseDouble(\"3.14159\"): " + Double.parseDouble("3.14159"));
        System.out.println();

        // ------------------------------
        // Exemplu pentru Character
        // ------------------------------
        Character charWrapped = 'A'; // Autoboxing de la char la Character
        char charPrimitive = charWrapped; // Unboxing
        System.out.println("Character example:");
        System.out.println("Valoare: " + charWrapped);
        System.out.println("Metoda charValue(): " + charWrapped.charValue());
        // Conversie: obținerea unei versiuni majuscule a unui caracter
        System.out.println("Character.toLowerCase('Z'): " + Character.toLowerCase('Z'));
        System.out.println();

        // ------------------------------
        // Exemplu pentru Boolean
        // ------------------------------
        Boolean boolWrapped = true;  // Autoboxing
        boolean boolPrimitive = boolWrapped; // Unboxing
        System.out.println("Boolean example:");
        System.out.println("Valoare: " + boolWrapped);
        // Conversie din String la boolean
        System.out.println("Boolean.parseBoolean(\"false\"): " + Boolean.parseBoolean("false"));
        System.out.println();

        // ------------------------------
        // Exemplu pentru Byte
        // ------------------------------
        Byte byteWrapped = 127; // Autoboxing de la byte la Byte (interval: -128 până la 127)
        byte bytePrimitive = byteWrapped; // Unboxing
        System.out.println("Byte example:");
        System.out.println("Valoare: " + byteWrapped);
        // Convertirea din String la byte
        System.out.println("Byte.parseByte(\"100\"): " + Byte.parseByte("100"));
        System.out.println();

        // ------------------------------
        // Exemplu pentru Short
        // ------------------------------
        Short shortWrapped = 1000; // Autoboxing de la short la Short
        short shortPrimitive = shortWrapped; // Unboxing
        System.out.println("Short example:");
        System.out.println("Valoare: " + shortWrapped);
        // Convertirea din String la short
        System.out.println("Short.parseShort(\"2000\"): " + Short.parseShort("2000"));
        System.out.println();

        // ------------------------------
        // Exemplu pentru Long
        // ------------------------------
        Long longWrapped = 100000L; // Autoboxing de la long la Long; sufixul L indică literalul de tip long
        long longPrimitive = longWrapped; // Unboxing
        System.out.println("Long example:");
        System.out.println("Valoare: " + longWrapped);
        // Convertirea din String la long
        System.out.println("Long.parseLong(\"123456789\"): " + Long.parseLong("123456789"));
        System.out.println();

        // ------------------------------
        // Exemplu pentru Float
        // ------------------------------
        Float floatWrapped = 3.14F; // Autoboxing de la float la Float; sufixul F indică literalul de tip float
        float floatPrimitive = floatWrapped; // Unboxing
        System.out.println("Float example:");
        System.out.println("Valoare: " + floatWrapped);
        // Convertirea din String la float
        System.out.println("Float.parseFloat(\"2.718\"): " + Float.parseFloat("2.718"));
        System.out.println();
    }
}
