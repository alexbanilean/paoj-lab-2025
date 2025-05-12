# Java Database Connectivity (JDBC)

`Java DataBase Connectivity` (JDBC) este un API dedicat accesării bazelor de date din cadrul unei aplicații
Java, care permite conectarea la un server de baze de date, precum și executarea unor instrucțiuni `SQL`.

JDBC dispune de clasa `DriverManager` care administrează
încărcarea driverelor, precum și obținerea conexiunilor către baza de date.

Fluxul tipic al unei aplicații JDBC include: 
- (1) încărcarea driver-ului; 
- (2) crearea conexiunii; 
- (3) crearea și executarea de Statement (simple, parametrizate sau pentru proceduri stocate); 
- (4) procesarea ResultSet;
- (5) închiderea corectă a resurselor

## Arhitectura JDBC

### Straturi principale

- Driver Layer: module platform-specific care implementează interfața JDBC.
- DriverManager: selectează driver-ul potrivit și inițiază conexiunea
- Application Layer: codul Java care folosește API-ul JDBC.

### Componente API

- `java.sql`: clase de bază (`Connection`, `Statement`, `ResultSet` etc.)
- `javax.sql`: suport avansat (pooling, distribuție, rowsets)

### Clase și interfețe comune

- Clasa `DriverManager`: gestionează driver-ele JDBC instalate și alege driver-ul potrivit pentru
  realizarea unei conexiuni la o bază de date;
- Interfața `Connection`: gestionează o conexiune cu o bază de date (orice comandă SQL este executată în
  contextul unei conexiuni);
- Interfețele `Statement` / `PreparedStatement` / `CallableStatement`: sunt utilizate pentru
  a executa comenzi SQL în SGBD sau pentru a apela proceduri stocate;
- Interfața `ResultSet`: stochează sub forma tabelară datele obținute în urma executării unei comenzi SQL;
- Clasa `SQLException`: utilizată pentru tratarea erorilor specifice JDBC

## Etapele realizării unei aplicații Java folosind JDBC

### (1) Încărcarea driver-ului specific

Prima etapă constă în înregistrarea, pe mașina virtuală unde rulează aplicația, a driver-ului JDBC necesar pentru comunicarea cu baza de date respectivă.

Acest lucru presupune încărcarea în memorie a clasei care implementează driver-ul și poate fi realizată prin apelul metodei statice `void forName(String driver)` din clasa `Class`.

Începând cu versiunea JDBC 4.0, inclusă în Java SE 6, acest pas nu mai este obligatoriu, deoarece, la prima încercare de conectare la o bază de date, mașina virtuală Java va încărca automat toate driver-ele disponibile (pe care le găsește în `class path`).

Exemplu:
```
Class.forName("com.mysql.cj.jdbc.Driver");
```

### (2) Stabilirea conexiunii cu baza de date

După înregistrarea unui driver JDBC, acesta poate fi utilizat pentru a stabili o conexiune cu o bază de date de tipul respectiv. 
O conexiune (sesiune) la o bază de date reprezintă un context prin care sunt trimise secvențe SQL din cadrul aplicației către SGBD și sunt primite înapoi rezultatele obținute.

Având în vedere faptul că pot exista mai multe drivere încărcate în memorie, se va specifica, pe lângă un identificator al bazei de date, și driverul care trebuie utilizat. 
Acest lucru se realizează prin intermediul unei adrese specifice, numită JDBC URL, având formatul `jdbc:sub-protocol:identificator`, unde:
- câmpul `sub-protocol` specifică tipul de driver care va fi utilizat (de exemplu `sqlserver`, `mysql`, `postgresql` etc.);
- câmpul `identificator` specifică adresa unei mașini gazdă (inclusiv un număr de port), numele bazei de date și, eventual, numele utilizatorului și parola sa.

La primirea unui URL de tip JDBC, DriverManager-ul va parcurge lista driverelor încărcate în memorie, până când unul dintre ele va recunoaște URL-ul respectiv. 
Dacă nu există nici un driver potrivit, atunci va fi lansată o excepție de tipul `SQLException`, cu mesajul "No suitable driver found for ...".

Exemplu:
```java
String url = "jdbc:mysql://localhost:3306/school";
String user = "root", pass = "secret";
Connection conn = DriverManager.getConnection(url, user, pass);
```

### (3) Crearea unui obiect de tip `Statement`

După realizarea unei conexiuni cu o bază de date, acesta poate fi folosită pentru executarea unor comenzi SQL, precum și pentru extragerea unor informații referitoare la baza de date (meta-date).
Obiectele de tip `Statement` sunt utilizate pentru a executa instrucțiuni SQL (interogări, actualizări ale datelor sau modificări ale structurii) în cadrul unei conexiuni, precum și pentru prelucrarea datelor obișnuite.

JDBC pune la dispoziția programatorului 3 tipuri de statement-uri, sub forma a 3 interfețe:
- `Statement` – pentru comenzi SQL simple, fără parametri;
- `PreparedStatement` – pentru comenzi SQL parametrizate;
- `CallableStatement` – pentru apelarea funcțiilor sau procedurilor stocate.

#### Interfața `Statement`

Exemplu creare:
```java
Statement stmt = con.createStatement();
```

Executarea unei secvențe SQL poate fi realizată prin intermediul următoarelor metode:
- metoda `ResultSet executeQuery(String sql)` – este folosită pentru executarea interogărilor de tip `SELECT` și returnează un obiect de tip `ResultSet` care va conține rezultatul interogării sub o
  formă tabelară, precum și meta-datele interogării (de exemplu, denumirile coloanelor selectate, numărul lor etc.).

Observații:
- Pentru a parcurge înregistrările rezultate în urma unei interogări de tip `SELECT`, un obiect de tip `ResultSet` utilizează un cursor, poziționat inițial înaintea primei linii. 
- În clasa `ResultSet` sunt definite mai multe metode pentru a muta cursorul în cadrul structurii tabelare, în scopul parcurgerii sale: `boolean first(), boolean last(), boolean next(), boolean previous()`.
- Toate cele 4 metode întorc valoarea true dacă mutarea cursorului a fost efectuată cu succes sau false în caz contrar.
- Pentru a extrage informațiile de pe fiecare linie se utilizează metode de forma `TipData getTipData(int coloană)` sau `TipData getTipData(String coloană)`, unde
  `TipData` reprezintă tipul de dată al unei coloane, iar argumentul `coloană` indică fie numărul de ordine din cadrul tabelului (începând cu 1), fie numele acesteia.

Exemplu executare secvență SQL cu `executeQuery`:
```java
public class ExempluEXCQ {
  public static void main(String[] args) {
    String sql = "SELECT * FROM Angajati";
    ResultSet rs = stmt.executeQuery(sql);
    
    while (rs.next()) {
        System.out.println(rs.getString("Nume") + " " + rs.getInt("Varsta") + " " + rs.getDouble("Salariu"));
    }
  }
}
```

- metoda `int executeUpdate(String sql)` – este folosită pentru executarea unor interogări SQL de tipul Data Manipulation Language (DML), care permit actualizări ale datelor de tipul
  `UPDATE/INSERT/DELETE`, sau de tipul Data Definition Language (DDL) care permit manipularea structurii bazei de date (de exemplu, `CREATE/ALTER/DROP TABLE`). Metoda returnează numărul de
  linii modificate în urma efectuării unor interogări de tip DML sau 0 în cazul interogărilor de tip DDL.

Exemplu executare secvență SQL cu `executeUpdate`:
```java
public class ExempluEXCU {
  public static void main(String[] args) {
    String qrySQL = "INSERT INTO Angajati VALUES('1234567890999','Albu Ioan',3210.10)";
    int n = stmt.executeUpdate(qrySQL);
    
    System.out.println("Au fost modificate " + n + " înregistrări!");
  }
}
```

#### Interfața `PreparedStatement`

Exemplu creare:
```java
String sql = "UPDATE persoane SET nume=? WHERE cod=?";
Statement pstmt = con.prepareStatement(sql);
```

Observații:
- Fiecare parametru este specificat prin intermediul unui semn de întrebare (`?`). 
- Obiectele de tip `PreparedStatement` sunt utilizate în cazul în care este necesară executarea repetată a unei interogări SQL, eventual cu valori diferite ale parametrilor, deoarece aceasta va fi precompilată, deci se va executa mai rapid.
- Obiectul `pstmt` conține o instrucțiune SQL precompilată care este trimisă către baza de date, însă pentru a putea fi executată este necesară stabilirea valorilor pentru fiecare parametru. 
- Setarea valorilor parametrilor se realizează prin metode de tip `void setTipData(int index, TipData valoare)`, unde `TipData` este tipul de date corespunzător parametrului respectiv, iar prin argumentele metodei se specifică indexul parametrului (începând de la 1) și valoarea pe care dorim să i-o atribuim.
- Executarea unei instrucțiuni SQL folosind un obiect de tip `PreparedStatement` se realizează apelând una dintre metodele `ResultSet executeQuery()` sau `int executeUpdate()`, asemănătoare cu cele definite pentru un obiect de tip `Statement`.

Exemplu:
```java
public class ExempluPS {
  public static void main(String[] args) {
    String sql = "UPDATE persoane SET nume=? WHERE cod=?";
    Statement pstmt = con.prepareStatement(sql);
    pstmt.setString(1, "Ionescu");
    pstmt.setInt(2, 45);

    int n = pstmt.executeUpdate();
    
    System.out.println("Au fost modificate " + n + " înregistrări!");
  }
}
```

#### Interfața `CallableStatement`

- Această interfață este utilizată pentru a apela funcții și proceduri stocate în baza de date. 
- Funcțiile returnează o valoare și pot avea doar parametri de intrare. 
- Procedurile pot avea parametri de intrare, ieșire sau intrare-ieșire, dar nu returnează o valoare prin nume.

Exemplu creare:
```java
CallableStatement cstmtfs = con.prepareCall("{? = call functieStocata(?)}");
CallableStatement cstmtps = con.prepareCall("{call proceduraStocata(?,?,?)}");
```

Pași uzuali pentru utilizarea `CallableStatement`:
- Crearea obiectului: Se folosește metoda `prepareCall(String sql)` a unui obiect de tip `Connection`.
- Înregistrarea parametrilor de ieșire (dacă este cazul): Se utilizează metoda `registerOutParameter(int paramIndex, int sqlType)`.
- Setarea parametrilor de intrare: Se utilizează metodele de forma `setTipData(int paramIndex, TipData value)`, la fel ca în `PreparedStatement`.
- Executarea procedurii / functiei: Se apelează metoda `execute()`.
- Obținerea valorilor de ieșire: Se utilizează metodele `getTipData(int paramIndex)`.

Observații:
- Parametrii sunt indexați în ordinea apariției în apelul SQL.
- Dacă se apelează o funcție, valoarea returnată se mapează prin `?=` și este considerată primul parametru (`paramIndex = 1`).
- În cazul unei proceduri, toți parametrii se specifică între paranteze și se numerotează în ordine.

Exemplu apelare funcție stocată:
```sql
CREATE FUNCTION totalSalarii(Tip VARCHAR(1)) RETURNS DOUBLE
BEGIN
  DECLARE total DOUBLE;
  DECLARE aux CHAR;
  IF(Tip = 'B') THEN
    SET aux = '1';
  ELSE
      SET aux = '2';
  END IF;
  SELECT SUM(Salariu) INTO total FROM Angajati WHERE LEFT(CNP,1) = aux;
  RETURN total;
END
```

```java
public class ExempluFS {
    public static void main(String[] args) {
      CallableStatement sfunc = con.prepareCall("{? = call totalSalarii(?)}");
      sfunc.registerOutParameter(1, Types.DOUBLE); // valoarea returnată
      sfunc.setString(2, "B");                     // parametrul de intrare
      sfunc.execute();
      double total = sfunc.getDouble(1);           // extragerea valorii returnate
      System.out.println("Total salarii: " + total);
    }
}
```

Exemplu apelare procedură stocată:
```sql
CREATE PROCEDURE inserareAngajat(
  IN CNP VARCHAR(13),
  IN Nume VARCHAR(45),
  IN Salariu DOUBLE,
  OUT rezultat INT
)
BEGIN
  DECLARE cnt INT;
  SELECT COUNT(*) INTO cnt FROM angajati WHERE CNP = CNP;

  IF(cnt = 0) THEN
      INSERT INTO Angajati VALUES(CNP,Nume,Salariu);
      SET rezultat = 1;
  ELSE
      UPDATE Angajati SET Nume = Nume, Salariu = Salariu WHERE CNP = CNP;
      SET rezultat = 2;
  END IF;
END
```

```java
public class ExempluPS {
    public static void main(String[] args) {
      CallableStatement sproc = con.prepareCall("{call inserareAngajat(?,?,?,?)}");
      sproc.setString(1, "1234567890999");
      sproc.setString(2, "Vasilescu Ion");
      sproc.setDouble(3, 3333.33);
      sproc.registerOutParameter(4, Types.INTEGER);  // rezultatul procedurii
      sproc.execute();
      int rezultat = sproc.getInt(4);
      if (rezultat == 1) {
        System.out.println("Angajat inserat!");
      } else {
        System.out.println("Angajat actualizat!");
      }
    }
}
```

### (4) Închiderea conexiunii

Conexiunea cu o bază de date se închide utilizând metoda `void close()` din clasa `Connection`, dacă nu este utilizat un bloc de tip `try-with-resources`.

Exemplu:
```java
public class ExempluInchidere {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(...);
            PreparedStatement ps = conn.prepareStatement(...);
            ResultSet rs = ps.executeQuery()) {
            // procesare
        }
    }
}
```
