# Agregare și compoziție. Șiruri de caractere

## Agregare și compoziție

### Agregare
Agregarea este o relație de tip „has-a” în care obiectul agregat poate exista independent de obiectul care îl agregă.

Exemplu de agregare (O clasă `Universitate` poate avea o listă de obiecte `Student`. Dacă universitatea dispare, studenții pot exista în alt context.):
```java
class Student {
    private String nume;

    Student(String nume) {
        this.nume = nume;
    }
}

class Universitate {
    private List<Student> studenti;

    Universitate(List<Student> studenti) {
        this.studenti = studenti; // Agregare: lista de studenți este transmisă (existența lor nu depinde de Universitate)
    }
}
```

### Compoziție
Compoziția este o relație de tip „parte-întreg”, în care obiectul compus nu poate exista independent de obiectul care compune.

Exemplu de compoziție (O clasă `Casa` care conține obiecte `Camera`. O cameră nu poate exista fără casa care o conține.):
```java
class Camera {
    private String nume;

    Camera(String nume) {
        this.nume = nume;
    }
}

class Casa {
    private List<Camera> camere;

    Casa() {
        // Compoziție: camerele sunt create și gestionate intern
        camere = new ArrayList<>();
        camere.add(new Camera("Living"));
        camere.add(new Camera("Dormitor"));
    }
}
```

## Upcasting, Downcasting și Operatorul `instanceof`

### Upcasting
Upcasting-ul este conversia implicită a unui obiect dintr-o subclasă la tipul unei superclase.

Particularități:
- Se realizează automat, fără cast explicit.
- Permite tratamentul unui obiect într-un mod generic, folosind metoda și atributele definite în superclasă.

Exemplu:
```java
class Animal {
    void mananca() {
        System.out.println("Animalul mănâncă");
    }
}

class Caine extends Animal {
    @Override
    void mananca() {
        System.out.println("Câinele mănâncă hrană pentru câini");
    }

    void latra() {
        System.out.println("Câinele latră: Ham Ham!");
    }
}

public class UpcastingDemo {
    public static void main(String[] args) {
        // Upcasting: Caine este tratat ca Animal
        Animal a = new Caine();
        a.mananca(); // Se apelează metoda suprascrisă din Caine
        // a.latra(); // Eroare de compilare: metoda latra() nu este definită în Animal
    }
}
```

### Downcasting și Operatorul `instanceof`
Downcasting-ul este conversia explicită a unui obiect dintr-o superclasă la tipul unei subclase.

Particularități:
- Nu este implicit și necesită un cast explicit.
- Dacă obiectul nu este de fapt o instanță a subclasei, se aruncă o excepție `ClassCastException`.

Exemplu:
```java
public class DowncastingDemo {
    public static void main(String[] args) {
        Animal a = new Caine();  // Upcasting implicit
        if (a instanceof Caine) { // Verificăm dacă 'a' este de tipul Caine
            Caine c = (Caine) a; // Downcasting explicit
            c.latra(); // Acum putem apela metoda specifică Caine
        }
    }
}
```

---
Operatorul `instanceof` este un operator binar folosit pentru a verifica dacă un obiect este o instanță a unei clase, a unei subclase sau a unei interfețe. 
În esență, acesta determină dacă relația de tip „este de tipul” (is-a) este adevărată.

Operatorul `instanceof` returnează întotdeauna `false` dacă operandul este `null`.
De asemenea, acesta nu poate fi folosit dacă nu există o relație între obiect comparat și tipul cu care se compară.

În versiunile mai recente de Java, operatorul `instanceof` a primit o extensie sub forma pattern matching. 
Această caracteristică permite ca operatorul să nu mai necesite un cast explicit după verificare.

Exemplu:
```java
public class PatternMatchingExample {
    public static void main(String[] args) {
        Animal a = new Caine();

        // Folosind pattern matching, cast-ul se face implicit dacă verificarea este adevărată
        if (a instanceof Caine c) {
            // Direct se poate folosi variabila 'c' de tip Caine
            System.out.println("Obiectul este de tip Caine: " + c);
        }
    }
}

```

## Clasa `Object`
Clasa `Object` este fundamentul ierarhiei de clase în Java – practic, toate clasele din Java moștenesc implicit clasa `Object`, chiar dacă nu specificăm acest lucru în mod explicit. 
Aceasta înseamnă că orice obiect creat în Java beneficiază de metodele și comportamentul definit în clasa `Object`, care se găsește în pachetul `java.lang`.

### Principalele Metode ale Clasei `Object`

#### `getClass()`
Metoda `getClass()` returnează un obiect de tip `Class` care conține informații despre tipul real al obiectului la momentul execuției.
Această metodă este utilă pentru reflecție și pentru a obține numele clasei sau alte detalii despre structura clasei.

Exemplu:
```java
public class TestGetClass {
    public static void main(String[] args) {
        String text = "Exemplu";
        // Afișează numele clasei, de ex. "java.lang.String"
        System.out.println("Clasa obiectului: " + text.getClass().getName());
    }
}
```

---
#### `toString()`
Metoda `toString()` returnează o reprezentare textuală a obiectului. 
Implicit, implementarea din clasa `Object` returnează o valoare care include numele clasei și codul hash, însă majoritatea claselor suprascriu această metodă pentru a oferi informații semnificative despre obiect.
Este foarte utilă la debugging sau când dorim să afișăm rapid informații despre obiect.

Exemplu:
```java
class Persoana {
    private String nume;
    private int varsta;

    Persoana(String nume, int varsta) {
        this.nume = nume;
        this.varsta = varsta;
    }

    @Override
    public String toString() {
        return "Persoana{" + "nume='" + nume + '\'' + ", varsta=" + varsta + '}';
    }
}

public class TestToString {
    public static void main(String[] args) {
        Persoana p = new Persoana("Ion", 30);
        // Apel automat la toString() prin intermediul System.out.println()
        System.out.println(p);
    }
}
```

---
#### `equals(Object obj)`
Metoda `equals()` este folosită pentru a compara două obiecte din punct de vedere al conținutului. 
Implementarea implicită din clasa `Object` compară referințele (adică, dacă două obiecte sunt identice în memorie), dar este adesea suprascrisă pentru a compara stările interne ale obiectelor.
Este esențială atunci când dorim să verificăm egalitatea semantică între obiecte (de ex., două obiecte de tip `Persoana` care au aceleași valori pentru atribute).

Exemplu:
```java
class Persoana {
    private String nume;
    private int varsta;

    Persoana(String nume, int varsta) {
        this.nume = nume;
        this.varsta = varsta;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // compara referințele
        if (obj == null || getClass() != obj.getClass()) return false;
        Persoana other = (Persoana) obj;
        return varsta == other.varsta && nume.equals(other.nume);
    }
}

public class TestEquals {
    public static void main(String[] args) {
        Persoana p1 = new Persoana("Ana", 25);
        Persoana p2 = new Persoana("Ana", 25);
        // Chiar dacă p1 și p2 nu sunt același obiect în memorie, ele sunt considerate egale.
        System.out.println("Egale: " + p1.equals(p2));
    }
}
```

---
#### `hashCode()`
Metoda `hashCode()` returnează un cod hash (un număr întreg) pentru obiect. Este folosită în colecții precum `HashMap`, `HashSet` etc. 
Regula importantă este că dacă două obiecte sunt considerate egale prin metoda `equals()`, atunci ele trebuie să returneze același hash code.
Suprascrierea metodei `hashCode()` este esențială atunci când suprascriem `equals()`, pentru a menține contractul dintre aceste metode.

Exemplu:
```java
import java.util.Objects;

class Persoana {
    private String nume;
    private int varsta;

    Persoana(String nume, int varsta) {
        this.nume = nume;
        this.varsta = varsta;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Persoana other = (Persoana) obj;
        return varsta == other.varsta && nume.equals(other.nume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, varsta);
    }
}

public class TestHashCode {
    public static void main(String[] args) {
        Persoana p1 = new Persoana("Maria", 28);
        Persoana p2 = new Persoana("Maria", 28);
        System.out.println("HashCode p1: " + p1.hashCode());
        System.out.println("HashCode p2: " + p2.hashCode());
    }
}
```

### Alte Metode ale Clasei `Object`

#### `clone()`
Metoda `clone()` permite crearea unei copii a obiectului. 
Implicit, aceasta efectuează o clonare superficială (shallow copy). 
Pentru a utiliza `clone()`, o clasă trebuie să implementeze interfața `Cloneable` și, de regulă, este nevoie de suprascrierea metodei.
Este foarte rar folosită în practică, preferându-se folosirea constructorului de copiere.

---
#### `finalize()`
Aceasta metodă este apelată de Garbage Collector înainte ca obiectul să fie distrus. 
Deși nu este recomandată utilizarea ei în aplicațiile moderne, poate fi menționată pentru înțelegerea mecanismului de gestionare a memoriei în Java.


## Clase Imutabile
O clasă imutabilă este o clasă al cărei obiect, odată creat, nu mai poate fi modificat. 
Toate atributele sale sunt finale și se stabilesc o singură dată, de regulă prin constructor.

Avantaje:
- Sunt thread-safe fără sincronizare suplimentară.
- Se pot utiliza ca chei în colecții de tip hash.

Reguli pentru a crea o clasă imutabilă:
- Declararea clasei ca `final` pentru a preveni extinderea acesteia.
- Declararea tuturor atributelor ca `final`.
- Inițializarea atributelor doar în constructor.
- Furnizarea de metode `getter` fără `setter`.
- Returnarea de copii ale obiectelor mutabile în locul referințelor către acestea.

Exemplu:
```java
public final class PersoanaImutabila {
    private final String nume;
    private final int varsta;
    private final Date dataNasterii; // Date este mutable, deci folosim copie defensivă

    public PersoanaImutabila(String nume, int varsta, Date dataNasterii) {
        this.nume = nume;
        this.varsta = varsta;
        // Copie defensivă pentru obiectul mutable
        this.dataNasterii = new Date(dataNasterii.getTime());
    }

    public String getNume() {
        return nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public Date getDataNasterii() {
        // Returnează o copie defensivă
        return new Date(dataNasterii.getTime());
    }

    // Nu sunt definite metode setter!

    @Override
    public String toString() {
        return "PersoanaImutabila{" +
                "nume='" + nume + '\'' +
                ", varsta=" + varsta +
                ", dataNasterii=" + dataNasterii +
                '}';
    }
}
```


## Clase de Tip Records (înregistrare)
O înregistrare este o nouă modalitate de a declara clase imutabile, introdusă în Java 15, concepută pentru a reduce boilerplate-ul. 
Aceasta generează automat constructorul, gettere, equals(), hashCode() și toString().

Caracteristici:
- Sunt imutabile prin definiție.
- Sunt declarate folosind cuvântul cheie record.

Exemplu:
```java
public record PersoanaRecord(String nume, int varsta) { }

public class TestRecord {
    public static void main(String[] args) {
        PersoanaRecord pr = new PersoanaRecord("Alex", 30);
        System.out.println(pr); // Se afișează automat, de exemplu: PersoanaRecord[nume=Alex, varsta=30]
    }
}
```

## Comparable și Comparator

### Comparable
Comparable definește ordinea naturală a obiectelor. 
O clasă care implementează interfața `Comparable<T>` trebuie să suprascrie metoda `compareTo(T o)`.
Permite sortarea obiectelor cu metode din `Collections.sort()` sau `Arrays.sort()`.

Exemplu:
```java
class Persoana implements Comparable<Persoana> {
    private String nume;
    private int varsta;

    public Persoana(String nume, int varsta) {
        this.nume = nume;
        this.varsta = varsta;
    }

    @Override
    public int compareTo(Persoana other) {
        // Ordine naturală: comparare după nume, apoi după vârstă
        int cmp = this.nume.compareTo(other.nume);
        return (cmp != 0) ? cmp : Integer.compare(this.varsta, other.varsta);
    }

    @Override
    public String toString() {
        return nume + " (" + varsta + ")";
    }
}

public class TestComparable {
    public static void main(String[] args) {
        Persoana p1 = new Persoana("Ana", 25);
        Persoana p2 = new Persoana("Ion", 30);
        Persoana p3 = new Persoana("Ana", 22);

        Persoana[] persoane = {p1, p2, p3};
        Arrays.sort(persoane);
        System.out.println(Arrays.toString(persoane));
    }
}
```

### Comparator
Comparator oferă o modalitate alternativă de sortare, fără a modifica clasa originală. Se implementează interfața `Comparator<T>`.
Permite sortarea obiectelor în ordini diferite de cea naturală.

Exemplu:
```java
import java.util.Comparator;

class PersoanaVarstaComparator implements Comparator<Persoana> {
    @Override
    public int compare(Persoana p1, Persoana p2) {
        return Integer.compare(p1.varsta, p2.varsta);
    }
}

public class TestComparator {
    public static void main(String[] args) {
        Persoana p1 = new Persoana("Ana", 25);
        Persoana p2 = new Persoana("Ion", 30);
        Persoana p3 = new Persoana("Ana", 22);

        Persoana[] persoane = {p1, p2, p3};
        // Sortare după vârstă folosind comparatorul definit
        Arrays.sort(persoane, new PersoanaVarstaComparator());
        System.out.println(Arrays.toString(persoane));
    }
}
```

## Clase și Metode Generice. Bounded Types

### Clase generice
Clasele generice permit definirea de clase care pot funcționa cu orice tip de date, specificând tipul ulterior, la momentul instanțierii. 
Acestea oferă siguranță la compilare și reducerea conversiilor explicite (cast-uri).

Exemplu:
```java
// Clasa generică Pereche cu două tipuri parametrizate
class Pereche<K, V> {
    private K cheie;
    private V valoare;

    public Pereche(K cheie, V valoare) {
        this.cheie = cheie;
        this.valoare = valoare;
    }

    public K getCheie() { return cheie; }
    public V getValoare() { return valoare; }

    @Override
    public String toString() {
        return "(" + cheie + ", " + valoare + ")";
    }
}

public class TestGenerice {
    public static void main(String[] args) {
        Pereche<String, Integer> pereche = new Pereche<>("Clasa", 101);
        System.out.println(pereche);
    }
}
```

### Metode generice
Metodele generice permit definirea de metode care operează pe tipuri parametrizate, fără a depinde de tipul specific al clasei.
Acestea permit reutilizarea codului pentru diferite tipuri și cresc flexibilitatea și siguranța la compilare.

Exemplu:
```java
public class Utilitare {
    // Metodă generică care afișează elementele unui array
    public static <T> void afiseazaArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    // Metodă generică care returnează primul element dintr-un array
    public static <T> T primulElement(T[] array) {
        return array.length > 0 ? array[0] : null;
    }

    public static void main(String[] args) {
        Integer[] numere = {1, 2, 3, 4, 5};
        String[] cuvinte = {"Java", "Generice", "Sunt", "Utilizate"};

        afiseazaArray(numere);
        afiseazaArray(cuvinte);

        System.out.println("Primul element al array-ului de numere: " + primulElement(numere));
        System.out.println("Primul element al array-ului de cuvinte: " + primulElement(cuvinte));
    }
}
```

### Bounded Types
Tipurile generice pot fi restrânse (bound) pentru a permite numai tipuri care extind o anumită clasă sau implementează o anumită interfață.
Se folosesc cu keyword-ul `extends` în declarațiile generice pentru a impune constrângeri.

Exemplu:
```java
// Clasa generică care acceptă doar tipuri care extind Number
class Container<T extends Number> {
    private T valoare;

    public Container(T valoare) {
        this.valoare = valoare;
    }

    public T getValoare() {
        return valoare;
    }

    @Override
    public String toString() {
        return "Container{" + "valoare=" + valoare + '}';
    }
}

public class TestBoundedTypes {
    public static void main(String[] args) {
        Container<Integer> intContainer = new Container<>(100);
        Container<Double> doubleContainer = new Container<>(99.99);
        System.out.println(intContainer);
        System.out.println(doubleContainer);

        // Container<String> stringContainer = new Container<>("Test"); // Eroare de compilare!
    }
}
```

## Șiruri de Caractere în Java
În Java, șirurile de caractere (strings) sunt utilizate extensiv pentru manipularea și procesarea textului. 
Java oferă mai multe clase pentru gestionarea șirurilor de caractere, fiecare având particularități proprii:
- Clasa `String` – imutabilă, utilizată pentru manipulare standard a textului.
- Clasa `StringBuilder` – mutabilă, folosită pentru manipulări eficiente de șiruri în single-threading.
- Clasa `StringBuffer` – similară cu `StringBuilder`, dar thread-safe și sincronizată.

### Clasa `String`
- `String` este imutabilă – odată creat, conținutul nu poate fi modificat.
- Este stocată în String Constant Pool, o zonă specială din Heap care optimizează utilizarea memoriei.
- Oferă metode puternice pentru manipularea textului.

Există două moduri de a crea un obiect de tip `String`:
```java
// Creare implicită - utilizând String Constant Pool
String s1 = "Salut";

// Creare explicită - utilizând Heap Memory (fără reutilizare în pool)
String s2 = new String("Salut");
```

Diferența dintre aceste metode este că `s1` va fi reutilizat din String Pool, pe când `s2` creează mereu o nouă instanță în Heap.

---

În Java, compararea șirurilor de caractere se face prin:
- Operatorul `==` – verifică referința obiectului în memorie.
- Metoda `equals()` – compară conținutul stringurilor.

Exemplu:
```java
public class StringEquality {
    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = "Hello";
        String s3 = new String("Hello");

        System.out.println(s1 == s2); // true - sunt în String Pool
        System.out.println(s1 == s3); // false - s3 este un obiect nou în Heap
        System.out.println(s1.equals(s3)); // true - compară conținutul
    }
}
```

---

Metode Principale ale Clasei `String`:
```java
public class StringMethods {
    public static void main(String[] args) {
        String text = "Java Programming";

        System.out.println("Lungimea: " + text.length());  // 16
        System.out.println("Caracter la poziția 5: " + text.charAt(5));  // P
        System.out.println("Substring (0-4): " + text.substring(0, 4));  // Java
        System.out.println("Conține 'Pro'? " + text.contains("Pro"));  // true
        System.out.println("Începe cu 'Java'? " + text.startsWith("Java"));  // true
        System.out.println("Se termină cu 'ing'? " + text.endsWith("ing"));  // true
    }
}
```

```java
public class StringModification {
    public static void main(String[] args) {
        String text = "Java";

        System.out.println("UpperCase: " + text.toUpperCase());  // JAVA
        System.out.println("LowerCase: " + text.toLowerCase());  // java
        System.out.println("Înlocuire 'a' -> 'o': " + text.replace('a', 'o'));  // Jovo
        System.out.println("Fără spații: " + "  Java  ".trim());  // "Java"
    }
}
```

```java
public class StringRegex {
    public static void main(String[] args) {
        String text = "Ana are mere, pere și prune";

        // Separare după spațiu
        String[] cuvinte = text.split(" ");
        System.out.println("Cuvinte: " + Arrays.toString(cuvinte));

        // Verificare cu regex
        System.out.println("Este format din litere? " + text.matches("[a-zA-Z ]+"));
    }
}
```

### Clasa `StringBuilder`
- Este mutabilă, spre deosebire de `String`.
- Este mai rapidă decât `String` pentru modificări multiple.
- Nu este thread-safe (nu este sincronizată).

Crearea unui obiect de tip `StringBuilder`:
```java
StringBuilder sb = new StringBuilder("Java");
```

---

Metode Principale ale Clasei `StringBuilder`:
```java
public class StringBuilderExample {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Java");

        sb.append(" Programming");   // Adaugă text
        sb.insert(5, "Awesome ");    // Inserează text
        sb.replace(5, 12, "Great");  // Înlocuiește
        sb.delete(5, 11);            // Șterge caractere
        sb.reverse();                // Inversează textul

        System.out.println(sb);  // Afișează string-ul modificat
    }
}
```

### Clasa `StringBuffer`
- Este mutabilă, spre deosebire de `String`.
- Similară cu `StringBuilder`, dar thread-safe și sincronizată.
- Este mai lentă decât `StringBuilder` din cauza mecanismului de sincronizare.

Crearea unui obiect de tip `StringBuffer`:
```java
StringBuffer sbf = new StringBuffer("Hello");
```

---

Metode Principale ale Clasei `StringBuffer`:
```java
public class StringBufferExample {
    public static void main(String[] args) {
        StringBuffer sbf = new StringBuffer("Java");

        sbf.append(" Programming");   // Adaugă text
        sbf.insert(5, "Awesome ");    // Inserează text
        sbf.replace(5, 12, "Great");  // Înlocuiește
        sbf.delete(5, 11);            // Șterge caractere
        sbf.reverse();                // Inversează textul

        System.out.println(sbf);  // Afișează string-ul modificat
    }
}
```