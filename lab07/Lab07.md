# Programare Funcțională în Java

## Lambda Expresii

O lambda expresie este o funcție anonimă care nu aparține niciunei clase. O lambda expresie are
următoarea sintaxă:

`(lista parametrilor) -> {expresie sau instrucțiuni}`

Se observă faptul că pentru o lambda expresie nu se precizează tipul rezultatului returnat, acesta fiind
dedus automat de compilator!

Definirea unei lambda expresii se realizează ținând cont de următoarele reguli de sintaxă:
- lista parametrilor poate fi vidă:
  - `() –> System.out.println("Hello lambdas!")`
- tipul unui parametru poate fi indicat explicit sau poate fi ignorant, fiind dedus din context:
  - `(a, b) -> {return a+b;}`
- dacă lambda expresia are un singur parametru fără tip, atunci se pot omite parantezele:
  - `a -> {return a*a;}`
- dacă lambda expresia nu conține instrucțiuni, ci doar o expresie, atunci acoladele și instrucțiunea
return pot fi omise:
  - `a -> a*a`
  - `(a, b) -> a+b`
  - `(x, y) -> {if(x>y) return x; else return y;}`

Observații:
- O expresie lambda poate fi asignată unui tip țintă care este o interfață funcțională (SAM – Single Abstract Method) sau descriptor.
- O interfață funcțională este o interfață care conține o singură metodă abstractă.
- O lambda expresie nu este de sine stătătoare, ci ea trebuie apelată într-un context care implică o interfață
funcțională. Practic, signatura metodei din interfață precizează forma lambda expresiei.
- O expresie lambda poate accesa doar variabile effectively final din contextul înconjurător.
- În interiorul unei lambda expresii, `this` se referă la instanța în care a fost definită, nu la funcție.

Exemplu:
```java
import java.util.function.*;

public class LambdaDemo {
    public static void main(String[] args) {
        // 1. Predicate<T>: returnează boolean
        Predicate<String> isEmpty = s -> s == null || s.isEmpty();
        System.out.println(isEmpty.test(""));    // true
        System.out.println(isEmpty.test("abc")); // false

        // 2. Consumer<T>: primește un T, nu returnează
        Consumer<String> printer = s -> System.out.println("Value: " + s);
        printer.accept("Hello"); // Value: Hello

        // 3. Function<T,R>: primește T, returnează R
        Function<String,Integer> toLength = String::length;
        System.out.println(toLength.apply("Lambda")); // 6

        // 4. Supplier<R>: nu primește, returnează R
        Supplier<Double> random = () -> Math.random();
        System.out.println("Random: " + random.get());

        // Exemple cu 0 sau >1 parametri
        Runnable r = () -> System.out.println("No args");
        r.run();
        BiFunction<Integer,Integer,Integer> sum = (a,b) -> a + b;
        System.out.println("Sum: " + sum.apply(2,3));
    }
}
```

### Principalele Interfețe Funcționale


| Interfață funcțională | Signatură            | Descriere                           |
|-----------------------|----------------------|-------------------------------------|
| `Predicate<T>`        | `boolean test(T t)`  | Verifică o condiție                 |
| `Consumer<T>`         | `void accept(T t)`   | Primește și procesează un element   |
| `Function<T,R>`       | `R apply(T t)`       | Convertește un tip în altul         |
| `Supplier<R>`         | `R get()`            | Furnizează un obiect fără parametri |


Exemplu:
```java
import java.util.function.*;

public class CoreFunctionals {
    public static void main(String[] args) {
        Predicate<String> p1 = s -> s.length() > 3;
        Predicate<String> p2 = s -> s.contains("a");
        Predicate<String> combo = p1.and(p2.negate());

        System.out.println(combo.test("Java"));  // false (conține 'a')
        System.out.println(combo.test("Code"));  // true

        Function<Integer,Integer> f1 = x -> x * 2;
        Function<Integer,Integer> f2 = x -> x + 5;
        Function<Integer,Integer> pipeline = f1.andThen(f2); // (x*2)+5
        System.out.println(pipeline.apply(3));  // 11

        Consumer<String> c1 = s -> System.out.print(s);
        Consumer<String> c2 = s -> System.out.println("!");
        c1.andThen(c2).accept("Hello");        // Hello!
    }
}
```

### Alte Interfețe Funcționale

| Interfață funcțională  | Signatură                | Descriere                          |
|------------------------|--------------------------|------------------------------------|
| `BiPredicate<T,U>`     | `boolean test(T t, U u)` | două argumente, returnează boolean |
| `BiFunction<T,U,R>`    | `R apply(T t, U u)`      | două argumente, returnează R       |
| `BiConsumer<T,U>`      | `void accept(T t, U u)`  | două argumente, fără retur         |
| `UnaryOperator<T>`     | `T apply(T t)`           | operator unar pe același tip       |
| `BinaryOperator<T>`    | `T apply(T t1, T t2)`    | operator binar pe același tip      |

Există de asemenea și interfețe funcționale specializate precum `IntPredicate`, `IntConsumer`, `IntFunction<R>` etc.

Exemplu:
```java
import java.util.function.*;

public class LambdaDemo {
    public static void main(String[] args) {
        BiPredicate<String,Integer> longerThan = (s, n) -> s != null && s.length() > n;
        System.out.println(longerThan.test("Java", 3)); // true

        BiFunction<String,String,String> concat = String::concat;
        System.out.println(concat.apply("Hello, ", "world!")); // Hello, world!

        UnaryOperator<Integer> square = x -> x * x;
        System.out.println(square.apply(5)); // 25

        BinaryOperator<Integer> max = Integer::max;
        System.out.println(max.apply(10, 20)); // 20

        IntUnaryOperator square = x -> x *x ;
        System.out.println(square.applyAsInt(5)); // 25

        DoubleBinaryOperator hypot = (x,y) -> Math.hypot(x,y);
        System.out.println(hypot.applyAsDouble(3,4)); // 5.0

        IntConsumer printer = i -> System.out.println("Val: " + i);
        IntStream.range(1,4).forEach(printer); // Val:1... Val:3
    }
}
```

### Interfețe Funcționale Custom

Exemplu:
```java
@FunctionalInterface
interface TriFunction<A,B,C,R> {
    R apply(A a, B b, C c);
    // dacă se adaugă o altă metodă abstractă compilatorul semnalează eroare
}

public class CustomFunc {
    public static void main(String[] args) {
        TriFunction<Integer,Integer,Integer,Integer> sum3 = (a,b,c) -> a + b + c;
        System.out.println("Sum3: " + sum3.apply(1,2,3)); // 6
    }
}
```

## Referințe către metode

Referințele către metode pot fi utilizate în locul lambda expresiilor care conțin doar apelul standard al
unei anumite metode.

Tipuri:
- Statică: `Class::staticMethod` ~ `(args) -> Class.staticMethod(args)`
- Instanță: `instance::instanceMethod`
  - obiect arbitrar: `ObjectClass::instanceMethod` ~ `(obj, args) -> obj.instanceMethod(args)`
  - obiect particular: `obj::instanceMethod` ~ `(args) -> obj.instanceMethod(args)`
    - Atenție, în acest caz obiectul particular `obj` trebuie să existe și să fie accesibil din lambda expresie! 
    - O lambda expresie poate accesa și variabile locale, dar acestea trebuie să fie efectiv finale, adică fie sunt declarate cu
      final, fie nu sunt declarate cu final, dar sunt inițializate și apoi nu mai sunt modificate!
- Constructor: `ClassName::new` ~ `(args) -> new Class(args)`

Exemplu:
```java
public class RefsDemo {
    public static void main(String[] args) {
      ArrayList<String> list = new ArrayList<>(Arrays.asList("a", "bb", "ccc"));

      // 1. statică
      list.sort(Comparator.comparingInt(String::length).reversed());
      System.out.println(list);

      // 2. instanță - obiect particular
      StringBuilder sb = new StringBuilder("Hello");
      Consumer<String> adauga = sb::append;
      adauga.accept(", world!");
      System.out.println(sb);

      // 3. instanță - obiect arbitrar
      BiFunction<String,Integer,String> subsir1 = (a, b) -> a.substring(b);
      BiFunction<String,Integer,String> subsir2 = String::substring;
      System.out.println(subsir1.apply("test123", 0));
      System.out.println(subsir2.apply("test123", 0));

      // 4. constructor String
      Function<char[],String> fromChars = String::new;
      System.out.println(fromChars.apply(new char[]{'x','y'})); // "xy"
    }
}
```

### Metoda forEach

Exemplu:
```java
public class ForEachDemo {
    public static void main(String[] args) {
        ArrayList<String> listaOrase = new ArrayList<>(Arrays.asList("București",
                "Paris", "Londra", "Berlin", "Roma"));
        
        // accesând direct fiecare element
        for (int i = 0; i < listaOrase.size(); i++)
            System.out.println(listaOrase.get(i));
        
        // folosind un iterator
        Iterator it = listaOrase.iterator();
        while (it.hasNext())
            System.out.println(it.next());
        
        // folosind instrucțiunea enhanced for
        for (String oras : listaOrase)
            System.out.println(oras);

        // folosind metoda forEach și lambda expresii
        listaOrase.forEach((oras) -> System.out.println(oras + " "));
        
        // folosind metoda forEach și referințe spre metode
        listaOrase.forEach(System.out::println);
    }
}
```

## Stream-uri

Un stream, așa cum îi spune numele, este un flux de date, respectiv o secvență de elemente
preluate dintr-o sursă care suportă operații de procesare (parcurgere, modificare, ștergere etc.).

Caracteristici:
- Un stream nu stochează elementele unei colecției, ci doar le prelucrează!
- Majoritatea operațiilor efectuate de un stream furnizează un alt stream, care la rândul său
  poate fi prelucrat. În concluzie, se poate crea un lanț de prelucrări succesive.
- Un stream nu poate fi reutilizat!
- Lazy evaluation: Operațiile intermediare nu sunt efectuate decât în momentul în care este invocată o operație de închidere.
- Stateless vs stateful:
  - Stateless: `map, filter` 
  - Stateful: `distinct`, `sorted`, `limit`
- Scurtcircuitare: `anyMatch`, `limit`, `findFirst` pot opri execuția stream-ului mai devreme.
- Paralelizare (doar pentru cele stateless): `stream().parallel()`

Etape necesare:
- Crearea stream-ului 
- Aplicarea unor operații de prelucrare succesive asupra stream-ului (operații intermediare)
- Aplicarea unei operații de închidere a stream-ului respectiv

### Crearea unui Stream

Exemplu:
```java
import java.util.*;
import java.util.stream.Stream;

public class StreamCreation {
    public static void main(String[] args) {
        // 1. Din colecții
        List<String> words = Arrays.asList("hello", "hola", "hallo");
        Stream<String> stream = words.stream();
        stream.forEach(System.out::println);
        
        // 2. Din constante (Stream.of)
        Stream<String> constantStream = Stream.of("hello", "hola", "hallo", "ciao");
        constantStream.forEach(System.out::println);

        // 3. Din array de obiecte
        String[] wordsArray = {"hello", "hola", "hallo", "ciao"};
        Stream<String> arrayStream = Stream.of(wordsArray);
        arrayStream.forEach(System.out::println);

        // 4. Din array de primitive
        int[] nums = {1,2,3,4,5};

        // Greșit (un singur element array)
        Stream<int[]> wrongStream = Stream.of(nums);
        System.out.println(wrongStream.count()); // 1

        // Corect
        Arrays.stream(nums).forEach(System.out::println); // 1 2 3 4 5

        // 5. Generare de elemente infinite (generate și iterate)
        // generate() → creează elemente folosind Supplier
        Stream.generate(() -> Math.random())
                .limit(5)
                .forEach(System.out::println);

        // iterate() → creează elemente în lanț
        Stream.iterate(1, i -> i * 2)
                .limit(5)
                .forEach(System.out::println); // 1, 2, 4, 8, 16
    }
}
```

### Operații Intermediare

Exemplu:
```java
import java.util.*;
import java.util.stream.Stream;

public class StreamIntermediate {
    public static void main(String[] args) {
        // 1. filter(Predicate)
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");
        words.stream()
                .filter(w -> w.length() > 5)
                .forEach(System.out::println); // banana, cherry

        // 2. sorted(Comparator)
        words.stream()
                .sorted((w1, w2) -> w1.length() - w2.length())
                .forEach(System.out::println);

        // Folosind Comparator.comparing
        words.stream()
                .sorted(Comparator.comparing(String::length))
                .forEach(System.out::println);

        // Sortare descrescătoare
        words.stream()
                .sorted(Comparator.comparing(String::length).reversed())
                .forEach(System.out::println);
        
        // 3. distinct() - Elimină duplicatele (folosind equals() și hashCode()).
        List<String> names = Arrays.asList("Ana", "Bob", "Ana", "Charlie");
        names.stream()
                .distinct()
                .forEach(System.out::println); // Ana, Bob, Charlie

        // 4. limit(long maxSize) - Oprește stream-ul la un număr dat de elemente.
        words.stream()
                .limit(2)
                .forEach(System.out::println); // apple, banana

        // 5. map(Function)
        words.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println); // APPLE, BANANA, CHERRY, DATE


        // 6. flatMap(Function<T, Stream<R>>)
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("c", "d")
        );

        listOfLists.stream()
                .flatMap(Collection::stream)
                .forEach(System.out::println); // a, b, c, d
        
    }
}
```

### Operații Terminale

Exemplu:
```java
import java.util.*;
import java.util.stream.Stream;

public class StreamFinal {
    public static void main(String[] args) {
        // 1. forEach(Consumer)
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");
        words.stream()
                .forEach(System.out::println);

        // 2. max(Comparator) / min(Comparator)
        Optional<String> longest = words.stream()
                .max(Comparator.comparing(String::length));
        System.out.println(longest.orElse("No elements"));
        
        // 3. reduce(BinaryOperator)
        int sum = Arrays.stream(new int[]{1,2,3,4,5})
                .reduce(0, Integer::sum);
        System.out.println("Sum: " + sum); // 15

        // 4. collect(Collector)
        List<String> uppercased = words.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(uppercased);

        Map<Integer, List<String>> byLength = words.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println(byLength);


        // 5. Colectori Speciali
        // joining
        String namesJoined = words.stream()
                .collect(Collectors.joining(", "));
        System.out.println(namesJoined); // apple, banana, cherry, date

        // counting
        long count = words.stream().filter(w -> w.length() > 5)
                .collect(Collectors.counting());
        System.out.println(count); // 2

        // averaging
        double avgLength = words.stream()
                .collect(Collectors.averagingInt(String::length));
        System.out.println(avgLength);

        // grouping by length
        Map<Integer, List<String>> grouped = words.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println(grouped);
        
    }
}
```

## Optional<T>

- `Optional<T>` este o clasă container din pachetul `java.util` introdusă în Java 8, care reprezintă o valoare care poate fi prezentă sau absentă (adică poate conține fie un obiect valid de tip T, fie nimic — null intern).
- Scopul său principal este de a reduce erorile de tip `NullPointerException` și de a forța programatorul să trateze explicit cazurile în care datele pot lipsi. 
- În loc de verificări manuale `if (x != null)`, utilizăm metode elegante precum `isPresent()`, `ifPresent()`, `orElse()`, `orElseThrow()`, etc.

Observații:
- `orElse()` evaluează mereu expresia sa (cost suplimentar!). 
- `orElseGet()` evaluează expresia doar dacă `Optional`-ul e gol (lazy evaluation). 
- `map()` păstrează `Optional`-ul, `flatMap()` lucrează cu `Optional<Optional>`.

Creare:
```java
import java.util.Optional;

public class OptionalCreateDemo {
    public static void main(String[] args) {
        Optional<String> opt1 = Optional.of("foo");
        Optional<String> opt2 = Optional.ofNullable(null);
        Optional<String> empty = Optional.empty();
    }
}
```

Exemplu:
```java
import java.util.Optional;

public class OptionalExample {
    public static void main(String[] args) {
        Optional<String> opt = Optional.ofNullable(getValue(false));

        String result = opt
                .filter(s -> s.length() > 3)
                .map(String::toUpperCase)
                .orElseGet(() -> "EMPTY");
        System.out.println(result);

        opt.ifPresentOrElse(
                s -> System.out.println("Val: " + s),
                () -> System.out.println("Nu există valoare")
        );
    }

    static String getValue(boolean ok) {
        return ok ? "java" : null;
    }
}
```

Exemplu generic:
```java
import java.util.Optional;

public class OptionalExample2 {
  public static void main(String[] args) {
    // Exemplu: obținem un nume de utilizator dintr-o bază de date simulată
    Optional<String> optionalName = findUserNameById(5);

    // 1. Verificare existență cu isPresent()
    if (optionalName.isPresent()) {
      System.out.println("Utilizator găsit: " + optionalName.get());
    } else {
      System.out.println("Utilizator inexistent.");
    }

    // 2. Echivalent elegant cu ifPresent()
    optionalName.ifPresent(name ->
            System.out.println("Salut, " + name + "!")
    );

    // 3. Furnizarea unei valori implicite cu orElse()
    String nameOrDefault = optionalName.orElse("Anonim");
    System.out.println("Nume utilizator: " + nameOrDefault);

    // 4. Furnizarea unei valori implicite lazy cu orElseGet()
    String nameLazy = optionalName.orElseGet(() -> getDefaultUserName());
    System.out.println("Nume (lazy): " + nameLazy);

    // 5. Aruncarea unei excepții dacă valoarea lipsește cu orElseThrow()
    try {
      String mustExist = optionalName.orElseThrow(() -> new RuntimeException("Utilizatorul nu a fost găsit!"));
      System.out.println("Utilizator validat: " + mustExist);
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
    }

    // 6. Transformare cu map()
    Optional<Integer> nameLength = optionalName.map(String::length);
    nameLength.ifPresent(len ->
            System.out.println("Lungimea numelui: " + len)
    );

    // 7. Transformare în lanț cu flatMap()
    Optional<String> upperName = optionalName.flatMap(name -> Optional.of(name.toUpperCase()));
    upperName.ifPresent(System.out::println);

    // 8. Filtrare cu filter()
    optionalName.filter(name -> name.startsWith("A"))
            .ifPresentOrElse(
                    name -> System.out.println("Nume începe cu A: " + name),
                    () -> System.out.println("Numele nu începe cu A.")
            );
  }

  // Simulare funcție ce poate returna Optional.empty()
  public static Optional<String> findUserNameById(int id) {
    if (id == 5) {
      return Optional.of("Alexandru");
    } else {
      return Optional.empty();
    }
  }

  // Furnizare nume default
  public static String getDefaultUserName() {
    System.out.println("(Calculare nume implicit...)");
    return "UtilizatorImplicit";
  }
}
```