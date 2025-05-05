# Thread-uri în Java

## Introducere

- Un fir de executare este o succesiune secvențială de instrucțiuni care se execută în cadrul unui proces. 
- Orice proces conține cel puțin un fir de executare principal, din care pot fi create alte fire, care, la rândul lor, pot crea alte fire. 
- Un fir de executare nu poate fi rulat independent, ci doar în cadrul unui proces. 
- Unui fir de executare îi sunt alocate o secvență de instrucțiuni, un set de regiștri și o stivă, proprii acestuia. 
- Firele de executare din cadrul aceluiași proces pot accesa, simultan, resursele procesului părinte (memoria heap și sistemul de fișiere).

Pentru a efectua mai multe sarcini simultan (paralelism sau concurență), putem crea și porni fire suplimentare.

Programarea concurentă devine esențială în:
- aplicații cu interfețe grafice (pentru a evita blocarea UI-ului),
- procesare paralelă, 
- aplicații server-side (servirea mai multor cereri simultan), 
- procesare I/O intensă.

## Crearea firelor de execuție

Clasele și interfețele necesare utilizării firelor de executare în limbajul Java sunt incluse în pachetul `java.lang.Thread`.
Un fir de executare poate fi creat prin două metode:
- extinderea clasei `Thread`
- implementarea interfeței `Runnable`
În ambele variante, trebuie redefinită/implementată metoda `void run()`, scriind în cadrul său secvența de cod pe care dorim să o executăm pe un fir separat.

Exemplu cu `Thread`:
```java
class Fir1 extends Thread {
    @Override
    public void run() {
        System.out.println("Execut firul " + Thread.currentThread().getName());
    }
}
```

Exemplu cu `Runnable`:
```java
class Fir2 implements Runnable {
    @Override
    public void run() {
        System.out.println("Execut firul " + Thread.currentThread().getName());
    }
}
```

Observații:
- `Thread` este o clasă, iar în Java putem extinde doar o singură clasă, deci folosirea `Runnable` este mai flexibilă.
- `Runnable` este preferat pentru separarea logicii de execuție de infrastructura thread-ului.

Exemplu:
```java
public class ExempluThreaduri {
    public static void main(String[] args) {
        Fir1 f1 = new Fir1();
        f1.start(); // NU run()

        Thread f2 = new Thread(new Fir2());
        f2.start();

        System.out.println("Execut firul principal: " + Thread.currentThread().getName());
    }
}
```

## Metode principale

- `start()` - Pornește execuția firului apelând intern metoda `run()` pe un thread separat.
- `join()` - Permite unui fir să aștepte finalizarea altui fir.

Exemplu cu `join()`:
```java
public class ExempluJoin {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Fir finalizat.");
            } catch (InterruptedException e) {}
        });

        t.start();
        t.join();  // main se suspendă până când t se finalizează
        System.out.println("Continuăm în main");
    }
}
```

## Ciclul de viață al unui thread

Un fir de executare se poate afla într-una dintre următoarele stări:
- **NEW** – firul a fost creat, dar nu a fost pornit
- **RUNNABLE** – firul este pregătit pentru execuție (dar nu rulează încă)
- **RUNNING** – firul este activ
- **BLOCKED/WAITING/SLEEPING** – firul este temporar inactiv
- **TERMINATED** – firul a terminat execuția

## Oprirea firelor de execuție

### Prin variabilă de control

Exemplu:
```java
class FirControlabil extends Thread {
    private volatile boolean running = true;

    public void stopRunning() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            System.out.println("Execut...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Oprire controlată.");
    }
}

public class ExempluFC {
    public static void main(String[] args) {
        FirControlabil t1 = new FirControlabil();
        
        t1.start();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        t1.stopRunning();
    }
}
```

Observație foarte importantă: 
- Dacă variabila care controlează executarea codului din interiorul metodei `run()` nu este locală, atunci ea trebuie declarată (în exteriorul firului) ca fiind volatilă (de exemplu, `volatile boolean stop`) pentru ca valoarea sa să fie actualizată din memoria principală la fiecare accesare. 
- Altfel, deoarece fiecare fir de executare are propria sa stivă, este posibil să se utilizeze o copie locală a variabilei externe respective, deși, între timp, valoarea variabilei respective a fost modificată de alt fir

### Cu ajutorul metodei `void interrupt()`

Exemplu:
```java
class FirIntreruptibil extends Thread {
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            System.out.println("Execut...");
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Am fost întrerupt.");
    }
}

public class ExempluFI {
    public static void main(String[] args) {
        FirIntreruptibil t1 = new FirIntreruptibil();

        t1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        t1.interrupt();
    }
}
```

## Accesarea concurentă a resurselor

- O secțiune critică este o secvență de cod care gestionează o resursă comună mai multor de fire de executare care acționează simultan.
- Controlul accesului într-o secțiune critică (la o resursă comună) se face folosind cuvântul cheie `synchronized`.

Exemplu:
```java
class ContBancar {
    private int sold = 100;

    public synchronized void retrage(int suma) {
        if (sold >= suma) {
            sold -= suma;
            System.out.println("Retras " + suma + ", sold ramas: " + sold);
        } else {
            System.out.println("Fonduri insuficiente");
        }
    }
}

public class DemoContBancar {
    public static void main(String[] args) {
        ContBancar cont = new ContBancar();

        Runnable task = () -> cont.retrage(70);

        Thread t1 = new Thread(task, "Client-1");
        Thread t2 = new Thread(task, "Client-2");

        t1.start();
        t2.start();
    }
}
```

## Metodele `wait()`, `notify()`, `notifyAll()`

Exemplu:
```java
class Coada {
    private int valoare;
    private boolean disponibila = false;

    public synchronized void produce(int val) throws InterruptedException {
        while (disponibila) wait();
        valoare = val;
        disponibila = true;
        notify();
    }

    public synchronized int consume() throws InterruptedException {
        while (!disponibila) wait();
        disponibila = false;
        notify();
        return valoare;
    }
}

public class ExempluPC {
    public static void main(String[] args) {
        Coada coada = new Coada();

        Thread producator = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    coada.produce(i);
                    System.out.println("Produs: " + i);
                } catch (InterruptedException e) {}
            }
        });

        Thread consumator = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    int val = coada.consume();
                    System.out.println("Consum: " + val);
                } catch (InterruptedException e) {}
            }
        });

        producator.start();
        consumator.start();
    }
}
```

## Deadlock si Starvation

### Deadlock

Deadlock-ul este o situație în programarea concurentă în care două sau mai multe fire de execuție (threads) așteaptă la infinit resurse pe care le deține celălalt, blocând astfel progresul tuturor.

Condițiile necesare pentru apariția deadlock-ului (conform teoriei lui Coffman):
- Excludere mutuală (Mutual Exclusion) – cel puțin o resursă trebuie să fie deținută exclusiv de un fir.
- Păstrare și așteptare (Hold and Wait) – un fir deține o resursă și așteaptă alta.
- Non-preempție (No Preemption) – resursele nu pot fi forțat eliberate.
- Așteptare circulară (Circular Wait) – un lanț circular de fire există, fiecare așteptând o resursă de la următorul.

Dacă toate aceste condiții sunt îndeplinite, atunci un deadlock poate apărea.

Exemplu de deadlock:
```java
class Resource {
    private final String name;

    public Resource(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Task implements Runnable {
    private final Resource resource1;
    private final Resource resource2;

    public Task(Resource resource1, Resource resource2) {
        this.resource1 = resource1;
        this.resource2 = resource2;
    }

    @Override
    public void run() {
        synchronized (resource1) {
            System.out.println(Thread.currentThread().getName() + " a blocat " + resource1.getName());

            try { Thread.sleep(50); } catch (InterruptedException ignored) {}

            synchronized (resource2) {
                System.out.println(Thread.currentThread().getName() + " a blocat " + resource2.getName());
            }
        }
    }
}

public class DeadlockDemo {
    public static void main(String[] args) {
        Resource r1 = new Resource("Resursa 1");
        Resource r2 = new Resource("Resursa 2");

        Thread t1 = new Thread(new Task(r1, r2), "Fir-1");
        Thread t2 = new Thread(new Task(r2, r1), "Fir-2");

        t1.start();
        t2.start();
    }
}
```

Explicație:
- Firul 1 blochează `r1` și așteaptă `r2`.
- Firul 2 blochează `r2` și așteaptă `r1`.
- Niciun fir nu poate progresa — DEADLOCK.

Posibilă soluție:
```java
class SafeTask implements Runnable {
    private final Resource r1;
    private final Resource r2;

    public SafeTask(Resource r1, Resource r2) {
        // asigurare ordine
        if (r1.getName().compareTo(r2.getName()) < 0) {
            this.r1 = r1;
            this.r2 = r2;
        } else {
            this.r1 = r2;
            this.r2 = r1;
        }
    }

    @Override
    public void run() {
        synchronized (r1) {
            System.out.println(Thread.currentThread().getName() + " a blocat " + r1.getName());
            try { Thread.sleep(50); } catch (InterruptedException ignored) {}
            synchronized (r2) {
                System.out.println(Thread.currentThread().getName() + " a blocat " + r2.getName());
            }
        }
    }
}

public class DeadlockPreventionDemo {
    public static void main(String[] args) {
        Resource r1 = new Resource("A");
        Resource r2 = new Resource("B");

        Thread t1 = new Thread(new SafeTask(r1, r2), "Fir-1");
        Thread t2 = new Thread(new SafeTask(r2, r1), "Fir-2");

        t1.start();
        t2.start();
    }
}
```

### Starvation

Starvation apare atunci când un fir de execuție nu primește niciodată acces la o resursă partajată pentru că alte fire monopolizează acea resursă, în special în prezența priorităților.

Este o problemă de echitate (fairness), spre deosebire de deadlock care este o problemă de blocare completă.

Exemplu de starvation:
```java
class StarvationExample {
    public static void main(String[] args) {
        Object lock = new Object();

        Runnable longRunning = () -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " execută mult timp...");
                try { Thread.sleep(10000); } catch (InterruptedException e) { }
            }
        };

        Runnable starving = () -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " a reușit în sfârșit...");
            }
        };

        Thread t1 = new Thread(longRunning, "Fir Prioritar 1");
        Thread t2 = new Thread(longRunning, "Fir Prioritar 2");

        // fir "înfometat"
        Thread lowPriority = new Thread(starving, "Fir Înfometat");

        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        lowPriority.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
        lowPriority.start();
    }
}
```

Explicație:
- Firele cu prioritate mai mare rulează frecvent și țin blocată resursa.
- Firul cu prioritate minimă este întârziat indefinit.
- Nu apare blocaj complet, dar firul cu prioritate mică nu progresează — STARVATION.

Posibilă soluție:
```java
import java.util.concurrent.locks.ReentrantLock;

public class FairLockExample {
    private static final ReentrantLock lock = new ReentrantLock(true); // fairness = true

    public static void main(String[] args) {
        Runnable task = () -> {
            while (true) {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + " a obținut lock-ul.");
                    try { Thread.sleep(500); } catch (InterruptedException ignored) {}
                } finally {
                    lock.unlock();
                }
            }
        };

        Thread t1 = new Thread(task, "Fir-1");
        Thread t2 = new Thread(task, "Fir-2");
        Thread t3 = new Thread(task, "Fir-3");

        t1.start();
        t2.start();
        t3.start();
    }
}
```

### Race Condition

Un race condition apare atunci când două sau mai multe fire de execuție accesează o resursă partajată (ex: o variabilă) concurent, iar rezultatul execuției depinde de ordinea execuțiilor acestor fire.

Exemplu de race condition:
```java
class Counter {
    int count = 0;

    public void increment() {
        count++; // operație NON atomică!
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Runnable task = () -> {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Rezultat final: " + counter.count); // Rezultat așteptat: 20000
    }
}
```

Explicație:
- Operația `count++` se descompune în:
- Citirea valorii curente a `count`
- Incrementează valoarea
- Scrie valoarea înapoi în memorie
- Două fire pot executa această operație în paralel, citind aceeași valoare inițială și suprascriind-o una peste alta → pierdere de incrementări.

Posibile soluții:

#### Synchronized Method

Blochează întregul obiect (this) în momentul apelului metodei.

```java
class SafeCounter1 {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
```

#### Synchronized Block

Permite sincronizarea doar a unei secțiuni din metodă (mai eficient).

```java
class SafeCounter2 {
    private int count = 0;

    public void increment() {
        synchronized (this) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}
```

#### Synchronized Static Method

Blochează clasa (`StaticSafeCounter.class`), util când resursa este statică și comună tuturor instanțelor.

```java
class StaticSafeCounter {
    private static int count = 0;

    public static synchronized void increment() {
        count++;
    }

    public static int getCount() {
        return count;
    }
}
```

#### Synchronized Block pe obiect custom

Permite sincronizarea doar pe obiectul critic, nu pe this sau pe clasă.

```java
class SafeCounter3 {
    private int count = 0;
    private final Object lock = new Object();

    public void increment() {
        synchronized (lock) {
            count++;
        }
    }

    public int getCount() {
        return count;
    }
}
```
