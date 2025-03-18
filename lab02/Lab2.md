# Clase și Obiecte. Extinderea Claselor. Polimorfism

## Principiile Programării Orientate pe Obiecte
Programarea orientată pe obiecte (OOP) este un paradigmă de programare care se bazează pe conceptul de obiecte. Aceasta are la bază patru principii fundamentale:

- **Abstractizarea**: Procesul prin care sunt identificate și reținute doar caracteristicile esențiale ale unui obiect, ascunzând detaliile neesențiale. Abstractizarea permite definirea unor șabloane generale (template-uri) pentru obiecte.
- **Încapsularea**: Gruparea datelor și metodelor care operează asupra acestora într-o singură entitate (clasă). Încapsularea asigură protecția datelor și împiedică accesul neautorizat la acestea.
- **Moștenirea**: Mecanism prin care o clasă (subclasă sau clasă derivată) poate prelua atributele și metodele unei alte clase (superclasă sau clasă de bază), permițând reutilizarea codului și extinderea funcționalității.
- **Polimorfismul**: Capacitatea unui obiect de a fi utilizat în mai multe forme, permițând suprascrierea metodelor (method overriding) și supraincărcarea acestora (method overloading) pentru a adapta comportamentul obiectelor.

## Clase și Obiecte
În Java, tipurile complexe de date sunt modelate folosind **clase**. O **clasă** reprezintă un șablon (template) pentru crearea obiectelor.

Un **obiect** este o instanță a unei clase, iar în momentul instantierii acestuia se alocă spațiu în memorie pentru stocarea atributelor și metodelor sale.

### Structura unei clase în Java
O clasă conține următoarele componente:
- **Atribute** – variabilele care definesc starea obiectului.
- **Metode** – secțiuni de cod executabil care definesc comportamentul obiectului.
- **Constructori** – metode speciale care sunt apelate la crearea unui obiect și care inițializează atributele acestuia.

### Referința `this`
Cuvântul cheie `this` este utilizat pentru a face referire la obiectul curent. Acesta este folosit în special pentru:
- A face distincție între variabilele de instanță și parametrii metodelor/constructorilor cu același nume.
- Apele metode sau constructori ai clasei curente.

## Modificatori de Acces
Modificatorii de acces determină nivelul de vizibilitate și accesibilitate a metodelor și atributelor unei clase.

- **`private`**: Permite accesul doar în interiorul clasei în care sunt definite. Se utilizează pentru a proteja datele interne ale unui obiect.
- **`default`** *(package-private)*: Dacă nu este specificat un modificator, elementele sunt accesibile doar în cadrul pachetului în care sunt definite.
- **`protected`**: Permite accesul în cadrul aceluiași pachet și în subclase, chiar dacă acestea se află în alt pachet.
- **`public`**: Permite accesul la elemente din orice altă clasă sau pachet.

## Moștenirea
Moștenirea este un mecanism prin care o clasă poate extinde o altă clasă, preluând metodele și atributele acesteia.

- Se utilizează cuvântul cheie `extends`.
- Java nu permite moștenirea multiplă (o clasă poate extinde doar o singură clasă).
- Toate clasele din Java sunt, implicit, extensii ale clasei `Object`, care este superclasa tuturor claselor.

Exemplu de moștenire:
```java
class Animal {
    void sunet() {
        System.out.println("Animalul scoate un sunet");
    }
}

class Caine extends Animal {
    @Override
    void sunet() {
        System.out.println("Câinele latră");
    }
}
```

## Cuvântul cheie `static`
Cuvântul cheie `static` este utilizat pentru a marca variabile și metode care aparțin clasei, nu instanțelor acesteia.

- **Câmpuri statice**: Există într-un singur exemplar în memorie, indiferent de numărul instanțelor create.
- **Metode statice**: Pot fi apelate fără a crea o instanță a clasei.

Exemplu:
```java
class Utilitar {
    static int counter = 0;
    static void afisareContor() {
        System.out.println("Contor: " + counter);
    }
}
```

## Cuvântul cheie `final`
- **Atribute**: Odată inițializate, nu mai pot fi modificate (constante).
- **Metode**: Nu pot fi suprascrise în clasele derivate.
- **Clase**: O clasă marcată `final` nu poate fi extinsă.

Exemplu:
```java
final class Constante {
    static final double PI = 3.14159;
}
```

## Enumerări (`enum`)
Enumerările sunt un tip special de clasă care definește un set finit de constante.

Exemplu:
```java
enum ZiuaSaptamanii {
    LUNI, MARTI, MIERCURI, JOI, VINERI, SAMBATA, DUMINICA;
}
```

## Polimorfism
Polimorfismul permite utilizarea unei metode sau a unui obiect în mai multe forme.

1. **Polimorfism prin suprascriere (`overriding`)**: O metodă definită într-o superclasă poate fi redefinită într-o subclasă.
2. **Polimorfism prin supraincărcare (`overloading`)**: Permite definirea mai multor metode cu același nume, dar cu liste diferite de parametri.

Exemplu de polimorfism prin suprascriere:
```java
class Vehicul {
    void porneste() {
        System.out.println("Vehiculul pornește");
    }
}
class Masina extends Vehicul {
    @Override
    void porneste() {
        System.out.println("Mașina pornește");
    }
}
```

Exemplu de polimorfism prin supraincărcare:
```java
class Matematica {
    int aduna(int a, int b) {
        return a + b;
    }
    double aduna(double a, double b) {
        return a + b;
    }
}
```

## Java și Mecanismul de Transmitere a Parametrilor
Java utilizează întotdeauna **pass-by-value**. Acest lucru înseamnă că atunci când se transmit argumente către metode:
- Pentru tipurile primitive (int, double, etc.), valoarea este copiată și transmisă metodei.
- Pentru obiecte, **referința** obiectului este copiată și transmisă metodei (dar nu obiectul în sine). Astfel, modificările asupra referinței nu afectează obiectul original, însă modificările asupra conținutului obiectului sunt vizibile în afara metodei.

## Imutabilitate în Java
Un obiect este considerat **imutabil** dacă, odată creat, conținutul său nu poate fi modificat. Exemple de clase imutabile în Java sunt: `String`, `Integer`, `Boolean`, `Byte`, `Short`.

### Crearea unei clase imutabile
Pentru a defini o clasă imutabilă, trebuie să respectăm următoarele reguli:
- Declararea clasei ca `final` pentru a preveni extinderea acesteia.
- Declararea tuturor atributelor ca `final`.
- Inițializarea atributelor doar în constructor.
- Furnizarea de metode `getter` fără `setter`.
- Returnarea de copii ale obiectelor mutabile în locul referințelor către acestea.

Exemplu:
```java
final class PersoanaImutabila {
    private final String nume;
    private final int varsta;
    
    public PersoanaImutabila(String nume, int varsta) {
        this.nume = nume;
        this.varsta = varsta;
    }
    
    public String getNume() { return nume; }
    public int getVarsta() { return varsta; }
}
```

## Arrays în Java
Un **array** este o colecție ordonată de elemente de același tip, indexate de la 0.

### Caracteristici ale tablourilor în Java:
- Tablourile au o dimensiune fixă, care trebuie specificată la momentul creării.
- Pot conține atât tipuri primitive, cât și obiecte.
- Accesarea elementelor se face prin index.

Exemplu:
```java
int[] numere = new int[5]; // Declarație și alocare de memorie
numere[0] = 10; // Atribuire
System.out.println(numere[0]); // Afișare
```

Tablourile pot fi create și utilizând inițializatori:
```java
int[] valori = {10, 20, 30, 40};
System.out.println("Elementul 2: " + valori[1]);
```

Tablourile de obiecte necesită inițializare explicită:
```java
String[] cuvinte = new String[3];
cuvinte[0] = "Java";
cuvinte[1] = "OOP";
cuvinte[2] = "Arrays";
```