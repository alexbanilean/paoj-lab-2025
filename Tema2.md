# Tema 2 - Programare Avansată pe Obiecte în Java

## 1. Gestionarea permisiunilor cu interfețe marker și verificare prin instanceof

### Cerințe specifice:

- Definiți o ierarhie de utilizatori: `Utilizator` (clasă de bază), cu clasele derivate `Administrator`, `Editor` și `Vizitator`.
- Creați interfețe marker precum `PoateEdita`, `PoateSterge`, `PoateVizualiza`, pe care le veți implementa în funcție de permisiunile fiecărui utilizator.
- Într-un serviciu de gestionare a acțiunilor (`ActiuneService`), folosiți `instanceof` pentru a verifica dacă un utilizator are permisiunea să efectueze o acțiune.
- Afișați dinamic ce acțiuni sunt permise fiecărui tip de utilizator.

## 2. Utilizarea claselor sealed pentru validarea tipurilor într-un sistem de plăți

### Cerințe specifice:

- Creați clasa `MetodaPlata` definită ca sealed, care permite doar extensii de tip `Card`, `Cash` și `TransferBancar`.
- Fiecare subclasă trebuie să aibă comportamente specifice:
  - `Card`: validare CVV și dată expirare. 
  - `Cash`: afișează că tranzacția este instantă. 
  - `TransferBancar`: verifică codul IBAN. 
- Creați o metodă care primește un obiect `MetodaPlata` și efectuează logica de validare în funcție de subtip (folosiți pattern matching cu `instanceof`).
- Afișați mesaje relevante în funcție de metoda de plată.

## 3. Gestionarea excepțiilor custom și tratarea erorilor multiple

### Cerințe specifice:

- Creați o aplicație care simulează un sistem de rezervări. Introduceți clase de excepții personalizate:
  - `RezervareException` (clasă de bază, checked).
  - `LocIndisponibilException`, `DateInvalideException` (derivate din cea de bază). 
- Implementați o metodă `rezervaLoc(String data, int loc)` care poate arunca multiple excepții în funcție de datele furnizate.
- În metoda `main`, gestionați excepțiile folosind blocuri try-catch separate și un catch final cu `Exception` pentru cazuri generale.
- Afișați un raport final indiferent dacă rezervarea a reușit sau nu (`finally`).

## 4. Implementarea unei clase adaptor pentru integrarea cu o bibliotecă terță

### Cerințe specifice:

- Presupuneți existența unei clase externe `SistemExistent` cu metoda `void afiseazaXML(String xml)`. (oferiți o implementare simplistă).
- Creați un sistem propriu care generează date în format JSON (`String genereazaJSON()`).
- Implementați o clasă adaptor `AdaptorJsonToXml` care transformă JSON-ul în XML și îl trimite către `SistemExistent`. (oferiți o implementare simplistă).
- Demonstrați utilizarea adaptorului pentru a integra cele două sisteme fără a modifica codul existent.
- Explicați într-un comentariu de ce acest pattern este util în aplicații reale.

## 5. Moștenire controlată și abstractizare avansată cu clase finale și protected

### Cerințe specifice:

- Creați clasa abstractă `OrganismViu` cu metode abstracte `respira()` și `seHrănește()`.
- Creați clasa `Animal` care extinde `OrganismViu`, dar marcați anumite metode ca `final` (ex: `respira()`).
- Creați o altă clasă abstractă `Mamifer` care derivă din `Animal` și adăugați comportamente specifice (ex: `arePăr()`).
- Creați clasele concrete `Urs` și `Delfin`, fiecare implementând metodele rămase.
- În metoda `main`, creați o listă de `OrganismViu` și demonstrați apelul polimorfic.

## 6. Interfețe extinse și metode default/statice/private

### Cerințe specifice:

- Definiți o interfață `Dispozitiv` cu metode:
  - `porneste()`, `opreste()`, o metodă default `status()` și o metodă statică `descriereGenerala()`.
  - Adăugați o metodă `private` în interfață care este utilizată intern de `status()`.
- Creați interfețele `Smart` și `Conectabil`, care extind `Dispozitiv`.
- Creați clasele `Telefon`, `Smartwatch`, `Televizor` care implementează aceste interfețe.
- Demonstrați în metoda `main` toate tipurile de apeluri posibile asupra metodelor interfețelor.

## 7. Gestionarea unei colecții cu HashSet, HashMap și suprascrierea metodelor equals() și hashCode()

### Cerințe specifice:

- Creați clasa `Produs` cu atribute precum `cod`, `nume`, `pret`.
- Suprascrieți metodele `equals()` și `hashCode()` astfel încât două produse cu același cod să fie considerate egale.
- Adăugați produse într-un `HashSet<Produs>` și demonstrați că duplicatele nu se adaugă.
- Creați un `HashMap<Produs, Integer>` care mapează un produs la stocul disponibil.
- Afișați produsele și stocurile, evidențiind utilizarea metodelor de tip `entrySet()` și `forEach()`.

## 8. Enum-uri avansate cu metode și constructori personalizați

### Cerințe specifice:

- Creați un `enum` numit `NivelAcces` cu valorile: `ADMIN`, `EDITOR`, `USER`, `GUEST`.
- Fiecare valoare trebuie să aibă un cod numeric și o descriere (ex: ADMIN(1, "Acces complet")).
- Creați metode personalizate în enum:
  - `getDescriere()`, `getCod()`, și `NivelAcces dinCod(int cod)` care returnează enum-ul corespunzător.
- Creați clasa `ContUtilizator` care are ca atribut un `NivelAcces`.
- În metoda `main`, creați mai multe conturi și filtrați doar cele cu acces superior unui anumit nivel (comparați enum-urile după ordinea lor naturală).