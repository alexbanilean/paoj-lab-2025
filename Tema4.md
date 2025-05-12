# Tema 4 - Programare Avansată pe Obiecte în Java

## 1. Manager de Task-uri Concurente cu Monitorizare și Timeout

### Context

Doriți să simulați un manager de task-uri care rulează în paralel, fiecare având o durată de execuție specificată. 
Fiecare task trebuie monitorizat, cu posibilitate de timeout, iar starea sa trebuie afișată periodic de un thread de tip watchdog.

### Cerințe specifice:

- Implementați fiecare task ca o clasă care extinde `Runnable`, primește un `id` și o durată simulată în milisecunde.
- La pornire, afișați faptul că task-ul a început și rulați logica într-o secțiune sincronizată pe un obiect comun `monitor`.
- După execuție, dacă task-ul nu a fost întrerupt, afișați că a fost finalizat și actualizați starea în `ConcurrentHashMap<id, Status>`.
- Dacă timpul maxim de execuție (`Tmax`) este depășit, întrerupeți task-ul și marcați-l ca `TIMED_OUT`.
- Porniți un pool de `N` thread-uri și aplicați manual timeout-ul pentru fiecare task.
- Implementați un thread watchdog care afișează starea fiecărui task la fiecare 500 ms, într-un format tabelar.
- Folosiți `volatile` și `interrupt()` pentru timeout și nu folosiți `ExecutorService`.
- Marcați secțiunea în care modificați starea în `Status.COMPLETED` cu `synchronized`.

## 2. Simulare Producer–Consumer cu Deadlock Intenționat

### Context

Doriți să simulați un sistem de tip producer-consumer folosind două buffere partajate și să demonstrați apariția unui deadlock. 
Ulterior, veți modifica codul pentru a elimina această problemă.

### Cerințe specifice:

- Declarați două resurse partajate: `BufferA` și `BufferB`, fiecare fiind o listă sincronizată.
- Creați două thread-uri producătoare (`P1` și `P2`) care blochează resursele în ordine inversă pentru a provoca deadlock.
- Creați două thread-uri consumatoare (`C1` și `C2`) care citesc din ambele buffere, blocând într-o ordine variabilă.
- Demonstrați apariția deadlock-ului prin mesaje de log corespunzătoare.
- Rezolvați problema deadlock-ului fie prin impunerea unei ordini unice de achiziție a lock-urilor, fie folosind `tryLock(timeout)` cu `ReentrantLock`.
- Verificați prin loguri că deadlock-ul nu mai apare în implementarea corectă.

## 3. Aplicație JDBC Concurentă cu Pool Simplu de Conexiuni

### Context

Doriți să implementați o aplicație multithreaded care utilizează un pool propriu de conexiuni pentru a insera date într-o bază de date PostgreSQL.

### Cerințe specifice:

- Creați o clasă `SimpleConnectionPool` care pre-creează M conexiuni și oferă metode sincronizate `getConnection()` și `releaseConnection(Connection)` folosind `wait()` și `notifyAll()`.
- Porniți `K` thread-uri lucrătoare care obțin conexiuni, inserează mesaje în baza de date, așteaptă un timp aleator între 100–500 ms, apoi eliberează conexiunea.
- La finalul execuției, afișați câte înregistrări au fost adăugate în tabelul `Log`.
- Tratați toate excepțiile `SQLException` și închideți resursele în blocuri `finally`.
- Apelați o procedură stocată care șterge intrările mai vechi de o oră, folosind `CallableStatement`.

## 4. Chat Simplu P2P folosind Socket-uri și Thread per Client

### Context

Doriți să construiți un sistem de chat simplu de tip client-server folosind socket-uri, în care fiecare client este gestionat de un thread separat.

### Cerințe specifice:

- Deschideți un `ServerSocket` pe portul 8888 și, la fiecare conexiune, porniți un `ClientHandler` într-un nou thread.
- Sincronizați accesul la lista clienților activi pentru a permite accesul concurent sigur.
- Implementați în client două thread-uri: unul pentru citirea de la tastatură și unul pentru ascultarea mesajelor de la server.
- Implementați comanda `/quit` pentru deconectarea unui client.
- Implementați comanda `/shutdown`, disponibilă doar pentru un client cu drepturi de administrator, care va opri toți clienții și va închide serverul.
- Utilizați `ThreadLocal<Socket>` în handler pentru a reține socket-ul curent al fiecărui client.

# Observații
- Fiecare exercițiu trebuie implementat într-un fișier/folder separat.
- Codul trebuie să compileze.
- Structura codului trebuie să fie clară, lizibilă, folosind indentare și nume sugestive.
