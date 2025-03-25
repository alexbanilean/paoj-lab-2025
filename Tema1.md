# Tema 1 - Programare Avansată pe Obiecte în Java

## 1. Calculul statisticilor folosind primitive și wrappers
   
### Cerințe specifice:

- Aplicația va primi din linia de comandă (args) cel puțin trei argumente: două numere întregi și unul real.
- Utilizați tipurile primitive (int, double) pentru calcule și wrapper-urile (ex. Integer, Double) pentru a demonstra conversia între tipuri.
- Calculați:
  - Suma celor trei numere.
  - Media aritmetică.
  - Produsul numerelor.
- Implementați instrucțiuni de control (if-else și switch) pentru a trata situații de eroare, cum ar fi diviziunea cu zero sau input-uri invalide.
- Afișați rezultatele într-un format clar, de exemplu: 
  - "Suma: X, Media: Y, Produsul: Z"

## 2. Manipularea unui array de numere și folosirea metodelor din clasa Arrays

### Cerințe specifice:

- Creați o clasă care citește de la utilizator o listă de cel puțin 5 numere întregi separate prin spațiu.
- Stocați aceste numere într-un array de tip int[].
- Folosiți clasele wrapper sau conversii pentru a demonstra trecerea între tipuri.
- Utilizați metoda Arrays.sort() pentru a sorta array-ul și Arrays.binarySearch() pentru a căuta un element specific (de exemplu, primul număr introdus).
- Afișați array-ul inițial și cel sortat, demonstrând modificarea array-ului prin referință.

## 3. Ierarhie OOP pentru vehicule (abstractizare, moștenire și polimorfism)
   
### Cerințe specifice:

- Creați o clasă abstractă Vehicul care include:
  - Atribute comune, ex. marca, model, anFabricatie.
  - O metodă abstractă descriere() ce returnează un șir de caractere cu descrierea vehiculului.
- Creați cel puțin două clase derivate, ex. Mașina și Motocicleta, care extind Vehicul și implementează metoda descriere(), adăugând detalii specifice fiecărui tip (ex. tip transmisie pentru Mașina, clasă putere pentru Motocicleta).
- În metoda main, creați o listă (sau array) de obiecte de tip Vehicul și demonstrați upcasting-ul (stocarea obiectelor derivate în referințe de tipul Vehicul).
- Apelați metoda descriere() pentru fiecare obiect, evidențiind polimorfismul.

## 4. Implementarea unui Singleton pentru gestionarea logurilor

### Cerințe specifice:

- Creați o clasă Logger implementată ca Singleton (asigurați-vă că există doar o instanță).
- Adăugați metode statice precum logInfo(String mesaj), logWarning(String mesaj) și logError(String mesaj) pentru a loga mesaje la consolă, prefixate cu tipul mesajului și timpul logării.
- În metoda main, apelați aceste metode și demonstrați că toate mesajele sunt logate prin aceeași instanță a clasei Logger.

## 5. Supraîncărcarea, suprascrierea și conceptul de copie superficială vs. profundă

### Cerințe specifice:

- Creați clasa Carte cu atribute private: titlu, autor și anAparitie.
- Implementați mai multe variante ale metodei afiseazaDetalii() (supraîncărcare):
  - Una care afișează detaliile complete ale cărții.
  - Alta care afișează doar titlul și autorul.
- Suprascrieți metoda toString() pentru a returna o reprezentare a obiectului în formatul "Carte{titlu='...', autor='...', anAparitie=...}".
- Adăugați o metodă de clonare care să furnizeze atât o copie superficială, cât și una profundă a obiectului Carte.
- Utilizați comentarii pentru a explica clar diferențele între shallow copy (unde referințele la obiecte sunt copiate) și deep copy (unde se creează noi instanțe pentru obiectele referite).
- Adăugați un constructor de copiere.
- Exemplificați shallow/deep copy folosind ambele metode.

## 6. Acces modificatori, agregare și compoziție

### Cerințe specifice:

- Creați clasa Profesor cu atribute private: nume și specializare.
- Declarați un atribut final (ex. codIdentificare) pentru a ilustra imutabilitatea.
- Furnizați metode getter și setter (doar pentru cele care nu sunt finale).
- Creați clasa Curs care conține:
  - Un obiect de tip Profesor (relație de compoziție: dacă obiectul Curs dispare, profesorul asociat nu mai este relevant pentru acel curs).
  - Un array de nume de studenți, ilustrând relația de agregare (studenții pot exista independent de curs).
- Demonstrați în main cum se accesează atributele și cum se modifică starea obiectelor, respectând regulile de encapsulare (folosind private și protected).

## 7. Upcasting, Downcasting și verificarea tipurilor cu instanceof

### Cerințe specifice:

- Creați o ierarhie de clase pentru reprezentarea animalelor:
  - Clasa de bază Animal cu o metodă sunet().
  - Clase derivate: Mamifer (a intermediar) și clase concrete Caine și Pisica care implementează metoda sunet() (ex. Caine latră, Pisica miaună).
- În main:
  - Creați obiecte de tip Caine și Pisica, dar stocați-le în variabile de tip Animal (upcasting).
  - Parcurgeți o colecție de animale și, folosind instanceof, verificați tipul real al fiecărui obiect.
  - Dacă obiectul este de tip Caine, efectuați downcasting-ul și apelați o metodă specifică (ex. latra()), iar dacă este Pisica, apelați metoda specifică acesteia.

## 8. Clasă imutabilă, Comparable și Generics pentru gestionarea studenților + manipularea șirurilor

### Cerințe specifice:

- Creați clasa Student:
  - Atribute private și finale: nume (String), varsta (int) și medie (double).
  - Constructor care inițializează toate atributele.
  - Implementați interfața Comparable<Student> pentru a ordona studenții în funcție de medie (descrescător sau crescător, la alegere).
  - Suprascrieți metodele equals(), hashCode() și toString() din clasa Object.
- Creați o clasă generică Grup<T extends Student>:
  - Gestionează un array de studenți.
  - Oferiți metode pentru adăugarea unui student, căutarea unui student după nume și sortarea listei folosind metoda Arrays.sort().
  - Folosiți clasele String, StringBuffer și StringBuilder:
    - Creați un raport textual detaliat despre studenți, folosind StringBuilder pentru concatenare eficientă.
    - Utilizați StringBuffer pentru un raport sincronizat (dacă doriți să demonstrați diferența, deși în contextul unui singur fir de execuție poate fi doar ilustrativ).
    - Evidențiați situațiile în care este preferabil să folosiți fiecare dintre aceste clase.
