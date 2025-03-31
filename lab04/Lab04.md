# Interfețe. Colecții

## Interfețe în Java

### Sintaxă

- O interfață este un tip de date abstract utilizat pentru a specifica un comportament pe care trebuie să îl implementeze o clasă.
- O interfață se definește cu cuvântul cheie `interface`.
- Implicit, toate metodele declarate în interfață sunt `public`, iar cele fără implementare `abstract`. 
- De asemenea, datele membre sunt implicit `public static final`.
- Când nu se specifică un modificator, se consideră că metodele sunt publice.

Exemplu:
```java
// Interfața Drawable - toate metodele sunt public abstract implicit.
interface Drawable {
    void draw();
}
```

### Implementare

- O clasă poate implementa una sau mai multe interfețe folosind cuvântul cheie `implements`.
- Dacă o clasă implementează două interfețe ce conțin metode abstracte cu aceeași denumire, apărând un conflict de nume:
  - A: dacă metodele au signaturi diferite, clasa trebuie să implementeze ambele metode;
  - B: dacă metodele au aceeași signatură și același tip pentru valoarea returnată, clasa implementează o singură metodă;
  - C: dacă metodele au aceeași signatură și tipuri diferite pentru valoarea returnată => implementarea nu va fi posibilă, deoarece se va obține eroare de compilare.

Exemplu caz A:
```java
interface M {
    void show();
}

interface H {
    void show(int x);
}

class Example implements M, H {
    @Override
    public void show() {
        System.out.println("Show() from M");
    }

    @Override
    public void show(int x) {
        System.out.println("Show(int) from H with value: " + x);
    }
}

public class TestInterfaces {
    public static void main(String[] args) {
        Example ex = new Example();
        ex.show();  // Se apelează metoda din M
        ex.show(10); // Se apelează metoda din H
    }
}
```

Exemplu caz B:
```java
interface M {
    void show();
}

interface H {
    void show();
}

class Example implements M, H {
    @Override
    public void show() {
        System.out.println("Common implementation of show()");
    }
}

public class TestInterfaces {
    public static void main(String[] args) {
        Example ex = new Example();
        ex.show();  // O singură implementare a metodei show()
    }
}
```

Exemplu caz C:
```java
interface M {
    int getValue();
}

interface H {
    double getValue();
}

// Va cauza o eroare de compilare
class Example implements M, H {
    @Override
    public int getValue() {  // Nu poate exista o implementare corectă
        return 10;
    }
}
```

- O interfață nu se poate instanția, însă un obiect de tipul clasei care o implementează poate fi accesat printr-o referință de tipul interfeței.
  - În acest caz, comportamentul obiectului este redus la cel oferit de interfață, alături de cel din clasa `Object`.

Exemplu:
```java
interface Drawable {
    void draw();
}

class Circle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing a circle");
    }

    public void extraMethod() {
        System.out.println("This method is not accessible via the interface reference.");
    }
}

public class TestInterfaces {
    public static void main(String[] args) {
        Drawable d = new Circle();  // Referință de tip interfață
        d.draw();  // Se poate apela metoda definită în interfață

        // d.extraMethod(); Eroare de compilare: metoda nu este vizibilă prin referința de tip Drawable

        // Conversie la tipul concret pentru a accesa metode specifice clasei
        if (d instanceof Circle) {
            Circle c = (Circle) d;
            c.extraMethod(); // Acum putem accesa metoda specifică clasei Circle
        }
    }
}
```

### Extindere

- Interfețele pot extinde alte interfețe folosind cuvântul cheie `extends`.
- Utilitatea interfețelor constă în definirea unor contracte care pot fi implementate în diverse clase, facilitând polimorfismul și implementarea mecanismelor de callback.

Exemplu:
```java
// Interfață de bază
interface Callback {
    void onSuccess(String message);
}

// Interfață extinsă
interface AdvancedCallback extends Callback {
    void onFailure(Exception ex);
}

class Operation {
    // Metodă care primește un callback pentru a notifica rezultatul unei operații
    public void performOperation(AdvancedCallback callback) {
        try {
            // Simulăm o operație
            int result = 42; // rezultatul operației
            // throw new Exception("Erorr!");
            callback.onSuccess("Operation succeeded with result: " + result);
        } catch (Exception ex) {
            callback.onFailure(ex);
        }
    }
}

public class CallbackDemo {
    public static void main(String[] args) {
        Operation op = new Operation();

        // Utilizare a unui callback implementat prin lambda function
        op.performOperation(new AdvancedCallback() {
            @Override
            public void onSuccess(String message) {
                System.out.println("Success: " + message);
            }

            @Override
            public void onFailure(Exception ex) {
                System.err.println("Failure: " + ex.getMessage());
            }
        });
    }
}
```

## Enum

- Enum-urile reprezintă un tip special de clasă care mapează o listă de constante.
- Enum-urile sunt imutabile.
- Sunt utile pentru a reprezenta seturi de valori predefinite (ex.: zilele săptămânii, stările unui obiect etc.).

Exemplu:
```java
enum Day {
    LUNI, MARTI, MIERCURI, JOI, VINERI, SAMBATA, DUMINICA;
}

public class EnumDemo {
    public static void main(String[] args) {
        Day today = Day.MARTI;
        System.out.println("Today is " + today);
        
        // Iterarea asupra valorilor enum
        for (Day d : Day.values()) {
            System.out.println(d);
        }
    }
}
```

- Enum-urile pot avea câmpuri, metode și constructori private. Acestea permit atașarea de informații suplimentare fiecărei constante. De exemplu, puteți asocia o valoare numerică sau un șir descriptiv fiecărei constante.

Exemplu:
```java
enum Day {
    LUNI("Weekday"),
    MARTI("Weekday"),
    MIERCURI("Weekday"),
    JOI("Weekday"),
    VINERI("Weekday"),
    SAMBATA("Weekend"),
    DUMINICA("Weekend");

    private final String tip;

    // Constructor privat
    Day(String tip) {
        this.tip = tip;
    }

    public String getTip() {
        return tip;
    }
}

public class EnumExample {
    public static void main(String[] args) {
        for (Day d : Day.values()) {
            System.out.println(d + " is a " + d.getTip());
        }
    }
}
```

- Enum-urile sunt utile pentru a fi folosite într-o instrucțiune `switch`, oferind o sintaxă clară și concisă pentru ramuri multiple de decizie.

Exemplu:
```java
public class SwitchEnumExample {
    public static void main(String[] args) {
        Day today = Day.MARTI;
        switch (today) {
            case SAMBATA, DUMINICA:
                System.out.println("Este weekend!");
                break;
            default:
                System.out.println("Este o zi lucrătoare.");
        }
    }
}
```

## Colecții în Java

- Pachetul `java.util` conține clase si interfețe utile pentru reprezentarea și manipularea colecțiilor. Cele mai populare tipuri de colecții se împart în trei categorii:
  - List: Colecții ordonate care permit elemente duplicate. 
  - Set: Colecții care nu permit duplicate. 
  - Map: Colecții de perechi cheie-valoare, unde fiecare cheie este unică.
- Interfața de bază este `Collection`.
- În clasa Collections găsim exclusiv metode statice utile lucrului cu colecții(e.g. Collections.sort(...), Collections.reverse(...)).

### List

- ArrayList: Implementare bazată pe array-uri dinamice, cunoscută pentru acces rapid (random access), dar cu costuri ridicate la inserare/ștergere (în mijloc).
- LinkedList: Implementare bazată pe listă dublu înlănțuită, utilă pentru inserări și ștergeri frecvente, dar cu acces mai lent pentru elemente de la poziții arbitrare.

Exemplu:
```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {
        // ArrayList
        List<String> arrayList = new ArrayList<>();
        arrayList.add("Java");
        arrayList.add("Python");
        arrayList.add("C++");
        System.out.println("ArrayList: " + arrayList);

        // LinkedList
        List<String> linkedList = new LinkedList<>();
        linkedList.add("Java");
        linkedList.add("Python");
        linkedList.add("C++");
        System.out.println("LinkedList: " + linkedList);
    }
}
```

### Set

- HashSet: Colecție bazată pe tabele de dispersie (hash); nu garantează ordinea elementelor.
- LinkedHashSet: Menține ordinea de inserare a elementelor. 
- TreeSet: Implementează interfața `SortedSet` și stochează elementele într-o ordine sortată (naturală sau definită printr-un comparator).

Exemplu:
```java
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;
import java.util.Set;

public class SetExample {
    public static void main(String[] args) {
        // HashSet
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Java");
        hashSet.add("Python");
        hashSet.add("Java"); // duplicat, nu va fi adăugat
        System.out.println("HashSet: " + hashSet);

        // LinkedHashSet
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("Java");
        linkedHashSet.add("Python");
        linkedHashSet.add("C++");
        System.out.println("LinkedHashSet: " + linkedHashSet);

        // TreeSet
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("Java");
        treeSet.add("Python");
        treeSet.add("C++");
        System.out.println("TreeSet (sortat): " + treeSet);
    }
}
```

### Map

- HashMap: Implementează interfața Map și stochează elementele în baza unui hash; nu garantează ordinea.
- LinkedHashMap: Menține ordinea de inserare.
- TreeMap: Stochează elementele sortate după cheie (naturală sau definită prin comparator).

Exemplu:
```java
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.Map;

public class MapExample {
    public static void main(String[] args) {
        // HashMap
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Java", 1);
        hashMap.put("Python", 2);
        hashMap.put("C++", 3);
        System.out.println("HashMap: " + hashMap);
        
        for (String key : hashMap.keySet()) {
            System.out.println(key + " -> " + hashMap.get(key));
        }

        // LinkedHashMap
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("Java", 1);
        linkedHashMap.put("Python", 2);
        linkedHashMap.put("C++", 3);
        System.out.println("LinkedHashMap: " + linkedHashMap);

        // TreeMap
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("Java", 1);
        treeMap.put("Python", 2);
        treeMap.put("C++", 3);
        System.out.println("TreeMap (sortat): " + treeMap);
    }
}
```

- Interfața `Map.Entry<K, V>` reprezintă o pereche cheie-valoare dintr-o colecție Map. Aceasta oferă metode pentru a obține și modifica perechile cheie-valoare.
- Metodele principale ale `Map.Entry`: 
  - `K getKey()`: returnează cheia perechii. 
  - `V getValue()`: returnează valoarea asociată cheii. 
  - `V setValue(V value)`: setează o nouă valoare pentru cheie.

Exemplu:
```java
import java.util.HashMap;
import java.util.Map;

public class MapEntryExample {
    public static void main(String[] args) {
        Map<String, Integer> students = new HashMap<>();
        students.put("Alice", 90);
        students.put("Bob", 85);
        students.put("Charlie", 88);

        // Parcurgere utilizând Map.Entry
        for (Map.Entry<String, Integer> entry : students.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
```

### Observații

- List permite elemente duplicate și menține ordinea de inserare (în cazul ArrayList și LinkedList). Set nu permite duplicate și, în funcție de implementare, poate sau nu să păstreze ordinea.
- Un map stochează perechi cheie-valoare, ceea ce îl face diferit de celelalte colecții, unde se stochează doar elemente individuale.
- Toate colecțiile din Java oferă un iterator pentru a parcurge elementele, facilitând operații de iterare în mod generic.

Exemplu:
```java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        list.add("C++");

        // Utilizare iterator
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
```

#### Parcurgerea Colecțiilor 

- Se poate realiza prin două metode principale:
  - Utilizarea iteratorilor:
    - Permite traversarea și modificarea colecției în timpul iterării (ex. ștergere de elemente).
    - Se utilizează în special pentru `Set` și `Map`, unde nu există acces direct prin index.
    - Exemplu:
    ```java
    import java.util.ArrayList;
    import java.util.Iterator;
    import java.util.List;
    
    public class IteratorExample {
        public static void main(String[] args) {
            List<String> languages = new ArrayList<>();
            languages.add("Java");
            languages.add("Python");
            languages.add("C++");
    
            Iterator<String> iterator = languages.iterator();
            while (iterator.hasNext()) {
                String language = iterator.next();
                if (language.equals("Python")) {
                    iterator.remove(); // Sterge elementul curent
                }
            }
            System.out.println(languages); // Output: [Java, C++]
        }
    }
    ```
  - Utilizarea For-Each:
    - Este mai simplă decât varianta cu iteratori, dar nu permite modificarea elementelor în timpul parcurgerii.
    - Greșeală comună: Dacă încercăm să ștergem un element în timpul iterării cu for-each, vom obține `ConcurrentModificationException`.
    - Exemplu:
    ```java
    import java.util.HashSet;
    import java.util.Set;
    
    public class ForEachExample {
        public static void main(String[] args) {
            Set<String> cities = new HashSet<>();
            cities.add("București");
            cities.add("Cluj");
            cities.add("Iași");
    
            for (String city : cities) {
                System.out.println(city);
                // cities.remove(city);  // NU ESTE PERMIS – va genera ConcurrentModificationException
            }
        }
    }
    ```
    
#### Compararea Elementelor în Colecții

- Pentru tipuri complexe de date (ex.: obiecte definite de utilizator), trebuie definit comportamentul de comparare. 
- Există două abordări principale:
  - Implementarea `Comparable<E>`:
    - Se implementează direct în clasa obiectului.
    - Se suprascrie metoda `compareTo()`, care definește sortarea naturală.
    - Exemplu:
    ```java
    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.List;
    
    class Student implements Comparable<Student> {
        private String name;
        private int grade;
    
        public Student(String name, int grade) {
            this.name = name;
            this.grade = grade;
        }
    
        @Override
        public int compareTo(Student other) {
            return Integer.compare(this.grade, other.grade); // Comparare după notă
        }
    
        @Override
        public String toString() {
            return name + " - " + grade;
        }
    }
    
    public class ComparableExample {
        public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 90));
        students.add(new Student("Bob", 85));
        students.add(new Student("Charlie", 88));
    
            Collections.sort(students); // Sortează conform lui compareTo()
            System.out.println(students);
        }
    }
    ```
  - Implementarea `Comparator<E>`:
    - Se definește într-o clasă separată.
    - Permite definirea mai multor moduri de sortare.
    - Exemplu:
    ```java
    import java.util.*;

    class NameComparator implements Comparator<Student> {
        @Override
        public int compare(Student s1, Student s2) {
            return s1.name.compareTo(s2.name);
        }
    }
    
    public class ComparatorExample {
        public static void main(String[] args) {
            List<Student> students = new ArrayList<>();
            students.add(new Student("Alice", 90));
            students.add(new Student("Bob", 85));
            students.add(new Student("Charlie", 88));
    
            students.sort(new NameComparator()); // Sortare după nume
            System.out.println(students);
        }
    }
    ```
