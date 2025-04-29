# Tema 3 - Programare Avansată pe Obiecte în Java

## 1. Sistem de evidență a produselor

### Context

Gestionarea un fișier binar `produse.dat` care conține obiecte serializate de tip `Produs` (cu atribute: `String nume`, `double pret`, `int stoc`).

### Cerințe specifice:

- Creați clasa `Produs`:
  - Implementați `Serializable`. 
  - Adăugați validări: `pret` și `stoc` nu pot fi negative. Dacă sunt negative, aruncați o excepție custom `InvalidDataException` (checked exception). 
- Scrieți un program care:
  - Scrie 10 produse în fișier folosind `ObjectOutputStream` și `try-with-resources`. 
  - La scriere, tratați orice eroare de tip `IOException` și logați eroarea într-un fișier `erori.log` (folosind `PrintWriter`). 
- Citiți produsele cu `ObjectInputStream`:
  - Tratați excepțiile `EOFException` (terminarea fișierului) și `ClassNotFoundException`. 
- Creați un flux `Stream<Produs>` din datele citite și:
  - Selectați produsele cu stoc zero și salvați-le într-un fișier `epuizate.txt`. 
  - Reduceți stocurile tuturor produselor cu 10% folosind `UnaryOperator<Produs>`. 
  - Afișați produsul cu cel mai mare preț `(folosind max(Comparator)`).

## 2. Prelucrare avansată a comenzilor

### Context

Gestionarea comenzilor clienților în fișierul binar `comenzi.dat`, folosind acces aleatoriu.

### Cerințe specifice:

- Definiți clasa `Comanda`:
  - Atribute: `int id`, `String numeClient`, `double valoare`, `boolean finalizata`.
  - Implementați `Serializable`. 
- Scrieți 15 comenzi folosind `ObjectOutputStream`. 
- Implementați un sistem care:
  - Deschide `comenzi.dat` cu `RandomAccessFile`. 
  - Permite actualizarea câmpului `finalizata` pentru comenzile peste o anumită valoare (ex: 5000 RON). 
  - Tratați toate excepțiile posibile (`EOFException`, `IOException`, `SecurityException`). 
- După actualizare:
  - Citiți din nou comenzile într-un `List<Comanda>`. 
  - Folosiți Stream-uri pentru:
    - Filtrarea comenzilor finalizate. 
    - Calcularea sumei tuturor comenzilor finalizate. 
    - Afișarea comenzilor finalizate grupate pe client (`groupingBy`).

## 3. Sistem de procesare clienți

### Context

Procesarea unei liste de clienți folosind funcții și stream-uri complexe.

### Cerințe specifice:

- Definiți clasa `Client`:
  - Atribute: `String nume`, `int varsta`, `double sumaCont`, `Optional<String> tipClient` (`VIP`, `Standard`, `Nou`). 
- Inițializați o listă cu minim 12 clienți diverși. 
- Folosiți fluxuri funcționale pentru a:
  - Filtra clienții de tip `VIP` care au suma contului peste media tuturor clienților. 
  - Mapare: transformați toți clienții într-un format `"Nume - Varsta ani"`. 
  - Reducere: calculați suma totală a sumelor conturilor. 
  - Colectare:
    - Gruparea clienților în funcție de tipul lor (`VIP`, `Standard`, `Nou`) și numărarea lor. 
    - Colectați toate numele clienților sub 25 ani într-un singur String (`joining(", ")`). 
  - Folosiți `Predicate`, `Function`, `BiFunction`, `Supplier` definite manual, nu doar lambdas inline. 
  - Tratați lipsa tipului de client (`Optional.empty()`) folosind `orElse("Necunoscut")`.

## 4. Managementul fișierelor text

### Context

Se dă un fișier text `date.txt` cu date despre persoane, formatate pe linii `nume;varsta;oras`.
Spre exemplu o linie din fișier poate fi: `Andrei Popescu;35;Bucuresti`.

### Cerințe specifice:

- Citiți datele din `date.txt` folosind `BufferedReader` și procesați fiecare linie. 
- Modelați fiecare linie ca un obiect Persoana cu câmpurile:
  - `String nume` 
  - `int varsta`
  - `String oras`
- Folosiți Stream-uri pentru:
  - Filtrare: persoane peste 30 ani care locuiesc în orașe care încep cu litera "B". 
  - Grupare: persoane grupate după oraș. 
  - Agregare: media de vârstă per oraș. 
  - Sortare: persoanele sortate după nume și apoi după vârstă. 
  - Reducere: găsirea persoanei cu vârsta maximă. 
- Scrieți într-un fișier `rezultat.txt`:
  - Lista persoanelor filtrate și sortate. 
  - Gruparea persoanelor pe orașe și media de vârstă. 
- Tratați toate excepțiile de I/O.

# Observații
- Nu sunt permise soluții imperative clasice pentru cerințele funcționale (for, while fără Stream). 
- Rezolvați cerințele folosind paradigma programării funcționale oriunde este posibil acest lucru.
- Se urmărește utilizarea try-with-resources, a fluxurilor de intrare/ieșire, a stream-urilor și a lambda expresiilor.
- Fiecare exercițiu trebuie implementat într-un fișier separat.
- Codul trebuie să compileze.
- Structura codului trebuie să fie clară, lizibilă, folosind indentare și nume sugestive.
