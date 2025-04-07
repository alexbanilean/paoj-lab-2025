# Clase Adaptor. Sealed Classes. Interfețe Marker. Excepții în Java

## Clase Adapter

- În multe situații, interfețele Java pot conține multe metode abstracte (ex.: MouseListener din AWT/Swing). 
- Dacă o clasă nu este interesată de toate metodele, poate deveni incomod să le implementăm pe toate. 
- Pentru a simplifica acest proces, se folosesc clase adapter – clase abstracte care implementează interfața și oferă implementări "goale" (empty bodies) pentru toate metodele. 
- Clasa noastră poate extinde adaptorul și suprascrie doar metodele de care are nevoie.

Observații:
- Dacă interfața se modifică (de exemplu, se adaugă o metodă nouă), adaptorul trebuie actualizat, altfel clasele care îl extind vor produce o eroare la compilare.
- Dacă adaptorul nu oferă implementare, atunci nu se evită complet necesitatea implementării.

Exemplu:
```java
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Clasa adaptor este furnizată de Java: MouseAdapter
// Extindem adaptorul pentru a implementa doar metoda mouseClicked
class CustomMouseAdapter extends MouseAdapter {
    @Override
    public void mouseClicked(MouseEvent e) {
        // Aici implementăm doar comportamentul pentru mouseClicked
        System.out.println("Mouse clicked at (" + e.getX() + ", " + e.getY() + ")");
    }

    // Alte metode, cum ar fi mousePressed, mouseReleased etc., rămân cu implementări goale.
}

public class TestAdapter {
    public static void main(String[] args) {
        // Demonstrație: instanțiem adaptorul și simulăm un eveniment (în aplicații GUI, evenimentele sunt generate de sistem)
        CustomMouseAdapter adapter = new CustomMouseAdapter();
        // Simulăm un eveniment de mouse click (de obicei, e generat automat de GUI)
        adapter.mouseClicked(new MouseEvent(new java.awt.Button(), 0, System.currentTimeMillis(), 0, 100, 200, 1, false));
    }
}
```

## Clase Sealed și Controlul Moștenirii

- Java 17 introduce cuvintele cheie `sealed`, `non-sealed` și `permits` pentru controlul extensibilității claselor.
- `sealed` permite doar claselor specificate prin `permits` să extindă.

- Începând cu Java 17, putem controla moștenirea folosind cuvântul cheie `sealed`. 
- Clasele sealed permit specificarea exactă a subclaselor care pot extinde clasa de bază. 
- Acest mecanism previne extinderea neautorizată, oferind o securitate suplimentară și claritate a ierarhiei de clase.
- Sealed classes: Specifică explicit care clase pot extinde clasa respectivă, folosind `permits`.
- Non-sealed classes: Pot extinde o clasă `sealed` și pot fi ulterior extinse fără restricții suplimentare.

Observații:
- Dacă o clasă `sealed` este extinsă de o clasă care nu este listată în `permits`, compilatorul va semnala o eroare.
- Nu putem marca o clasă sealed ca fiind `final`, deoarece finalul oprește extinderea.

Exemplu:
```java
// Definim o clasă sealed, care permite doar extinderea de către clasele specificate.
sealed class Vehicul permits Masina, Bicicleta {
    protected String marca;
    public Vehicul(String marca) {
        this.marca = marca;
    }
    public abstract void porneste();
}

// Clasă final – nu se poate extinde
final class Masina extends Vehicul {
    public Masina(String marca) {
        super(marca);
    }
    @Override
    public void porneste() {
        System.out.println("Masina " + marca + " pornește.");
    }
}

// Clasă non-sealed – poate fi extinsă ulterior
non-sealed class Bicicleta extends Vehicul {
    public Bicicleta(String marca) {
        super(marca);
    }
    @Override
    public void porneste() {
        System.out.println("Bicicleta " + marca + " pornește.");
    }
}

public class TestSealed {
    public static void main(String[] args) {
        Vehicul v1 = new Masina("BMW");
        Vehicul v2 = new Bicicleta("Giant");

        v1.porneste();
        v2.porneste();
    }
}
```

## Interfețe Marker

- O interfață marker nu conține constante și metode – este folosită doar pentru a marca o clasă.
- Se pot folosi în reflecție sau validare logică.

Exemplu:
```java
interface Auditable {} // marker

class User implements Auditable {
    String name;
}
```

## Extinderea Interfețelor cu Metode Default

- Interfețele pot conține metode default care oferă implementări implicite. 
- În caz de conflict (problema rombului), clasa care implementează interfețele trebuie să rezolve ambiguitatea.

În momentul extinderii unei interfețe care conține o metodă default pot să apară următoarele situații:
- sub-interfața nu are nicio metodă cu același nume => sub-interfața va moșteni metoda default din super-interfață;
- sub-interfața conține o metodă abstractă cu același nume => metoda redevine abstractă în sub-interfață (i.e., metoda nu mai este default);
- sub-interfața redefinește metoda default tot printr-o metodă default;
- sub-interfața extinde două super-interfețe care conțin două metode default cu aceeași signatură și același tip returnat => sub-interfața trebuie să redefinească metoda (nu neapărat tot de tip default) și, eventual, poate să apeleze în implementarea sa metodele din super-interfețe folosind sintaxa `SuperInterfata.super.metoda()`;
- sub-interfața extinde două super-interfețe care conțin două metode default cu aceeași signatură și tipuri returnate diferite => moștenirea nu este posibilă.

Reguli pentru extinderea interfețelor și implementarea lor (problema rombului)
1. Clasele au prioritate mai mare decât interfețele (dacă o metodă default dintr-o interfață este rescrisă într-o clasă, atunci se va apela metoda din clasa respectivă);
2. Interfețele "specializate" (sub-interfețele) au prioritate mai mare decât interfețele "generale" (super-interfețe);
3. Nu există regula 3! Dacă în urma aplicării regulilor 1 și 2 nu există o singură interfață câștigătoare, atunci clasele trebuie să rezolve conflictul de nume explicit, respectiv vor implementa metoda default, eventual apelând una dintre metodele default printr-o construcție sintactică de forma `Interfață.super.metoda()`.

Exemplu:
```java
interface A {
    default void salut() {
        System.out.println("Salut din A");
    }
}

interface B {
    default void salut() {
        System.out.println("Salut din B");
    }
}

class C implements A, B {
    // Rezolvarea ambiguității prin apelarea explicită a metodei dintr-o interfață
    @Override
    public void salut() {
        A.super.salut();
        // Alternativ: B.super.salut();
    }
}
```

## Metode statice și private în interfețe

- Metodele statice: Pot fi apelate direct din afara interfeței.
- Metodele private: Pot fi folosite pentru a împărți logica internă a metodelor default, fără a fi expuse în afara interfeței.

Observații:
- Metodele private trebuie să fie definite complet (să nu fie abstracte).
- Metodele private pot fi statice, dar nu pot fi default.

Exemplu:
```java
interface Loggable {
    // Metodă statică
    static void logStatic(String msg) {
        System.out.println("Static log: " + msg);
    }

    // Metodă default care folosește metoda privată
    default void doLog(String msg) {
        logInternal(msg);
    }

    // Metodă privată – nu este vizibilă din afara interfeței
    private void logInternal(String msg) {
        System.out.println("Log internal: " + msg);
    }
}
```

## Excepții în Java

În Java, excepțiile sunt obiecte care descriu situații de eroare sau condiții excepționale în timpul execuției. Ele se împart în două categorii:
- Checked Exceptions: Trebuie fie tratate, fie declarate în semnătura metodei. (Ex.: IOException, SQLException.)
- Unchecked Exceptions: Extind RuntimeException și nu trebuie tratate obligatoriu. (Ex.: NullPointerException, ArithmeticException, ArrayIndexOutOfBoundsException.)

### Exemple comune de excepții

Division By Zero:
```java
public class DivisionExample {
    public static void main(String[] args) {
        try {
            int a = 10, b = 0;
            int result = a / b; // ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("Eroare: Împărțire la zero!");
        }
    }
}
```

Out of Bounds:
```java
public class OutOfBoundsExample {
    public static void main(String[] args) {
        try {
            int[] arr = {1, 2, 3};
            System.out.println(arr[5]); // ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Eroare: Index inexistent!");
        }
    }
}
```

Number Format Exception:
```java
public class NumberFormatExample {
    public static void main(String[] args) {
        try {
            String s = "abc";
            int num = Integer.parseInt(s); // NumberFormatException
        } catch (NumberFormatException e) {
            System.out.println("Eroare: Format numeric incorect!");
        }
    }
}
```

Infinite Recursion:
```java
public class RecursionExample {
    public static void recursiv() {
        recursiv(); // apel recursiv fără condiție de oprire
    }
    public static void main(String[] args) {
        try {
            recursiv();
        } catch (StackOverflowError e) {
            System.out.println("Eroare: Recursie infinită!");
        }
    }
}
```

### Crearea și Tratarea Excepțiilor Custom

- Pentru a crea excepții custom, putem extinde Exception (pentru checked) sau RuntimeException (pentru unchecked).
- În structura try-catch-finally, blocul finally se execută întotdeauna, indiferent dacă excepția este aruncată sau nu.

Exemplu:
```java
// Checked exception: trebuie tratată explicit (try-catch) sau declarată cu throws
class CheckedCustomException extends Exception {
    public CheckedCustomException(String mesaj) {
        super(mesaj);
    }
}

// Unchecked exception: moștenește RuntimeException, nu e obligatoriu să fie tratată
class UncheckedCustomException extends RuntimeException {
    public UncheckedCustomException(String mesaj) {
        super(mesaj);
    }
}

public class CustomExceptionDemo {

    // Aruncă o excepție checked
    public static void metodaChecked(boolean eroare) throws CheckedCustomException {
        if (eroare) {
            throw new CheckedCustomException("Eroare verificată (checked) apărută!");
        }
        System.out.println("Metodă checked executată fără probleme.");
    }

    // Aruncă o excepție unchecked
    public static void metodaUnchecked(boolean eroare) {
        if (eroare) {
            throw new UncheckedCustomException("Eroare neverificată (unchecked) apărută!");
        }
        System.out.println("Metodă unchecked executată fără probleme.");
    }
    
    public static void main(String[] args) {
        try {
            // TEST 1: Checked
            metodaChecked(true); // modifică între true/false pentru a testa

            // TEST 2: Unchecked
            metodaUnchecked(true); // dacă metodaChecked aruncă o excepție, această linie nu se execută
        } catch (CheckedCustomException e) {
            System.out.println("Am prins o excepție specifică: " + e.getMessage());
            
            // Tratarea unei excepții generale (RuntimeException + subclasele sale)
        } catch (RuntimeException e) {
            // Va prinde și UncheckedCustomException, și alte excepții ca NullPointerException etc.
            System.out.println("Excepție generală (RuntimeException): " + e.getMessage());
        } catch (Throwable t) {
            System.out.println("Altă eroare: " + t.getClass().getSimpleName() + ": " + t.getMessage());
        } finally {
            System.out.println("Bloc finally - se execută mereu: resursele pot fi eliberate aici (ex: conexiuni DB, fișiere etc).");
        }

        System.out.println("Programul a continuat normal după tratarea excepțiilor.");
    }
}
```