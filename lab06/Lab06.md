# Fluxuri de Intrare / Ieșire în Java

Operațiile de intrare/ieșire sunt realizate, în general, cu ajutorul claselor din pachetul java.io, folosind
conceptul de flux (stream).

Un flux reprezintă o modalitate de transfer al unor informații în format binar de la o sursă către o destinație.

În funcție de modalitatea de prelucrare a informației, precum și a direcției canalului de comunicație, fluxurile se
pot clasifica astfel:
- după direcția canalului de comunicație:
  - de intrare
  - de ieșire
- după modul de operare asupra datelor:
  - la nivel de octet (flux pe 8 biți )
  - la nivel de caracter (flux pe 16 biți)
- după modul în care acționează asupra datelor:
  - primitive (doar operațiile de citire/scriere)
  - procesare (adaugă la cele primitive operații suplimentare: procesare la nivel de buffer, serializare etc.)

În concluzie, pentru a deschide orice flux se instanțiază o clasă dedicată, care poate conține mai mulți constructori:
- un constructor cu un argument prin care se specifică path-ul fișierului sub forma unui șir de caractere;
- un constructor care primește ca argument un obiect de tip File;
- un constructor care primește ca argument un alt flux.

## Clasa File

- Clasa `java.io.File` reprezintă o abstracție pentru fișiere și directoare din sistemul de fișiere. 
- Aceasta nu reprezintă conținutul unui fișier, ci o cale (path) și proprietăți ale acestuia (ex. existență, permisiuni).

Metode uzuale ale clasei `File`:
- `exists()`: Verifică dacă fișierul/directorul există.
- `isDirectory()`: Determină dacă path-ul reprezintă un director.
- `isFile()`: Determină dacă path-ul reprezintă un fișier.
- `getName()`: Returnează numele fișierului/directorului.
- `length()`: Returnează mărimea fișierului (în octeți).
- `listFiles()`: Returnează un tablou de obiecte `File` asociate fișierelor dintr-un director (dacă este director).
- `getAbsolutePath()`: Returnează calea absolută a unui fișier.
- `createNewFile()`: Creează un nou fișier, iar dacă fișierul există deja metoda returnează `false`.

Exemplu:
```java
import java.io.File;

public class FileExample {
    public static void main(String[] args) {
        File file = new File("exemplu.txt"); // Calea către fișier
        if (file.exists()) {
            System.out.println("Nume: " + file.getName());
            System.out.println("Mărime: " + file.length() + " octeți");
            System.out.println("Este director: " + file.isDirectory());
        } else {
            System.out.println("Fișierul nu există.");
        }
    }
}
```

## Fluxuri primitive

Fluxurile primitive permit doar operații de intrare/ieșire. După modul de operare asupra datelor, fluxurile
primitive se împart în două categorii:
- prelucrare la nivel de caracter (fișiere text)
- prelucrare la nivel de octet(fișiere binare)

### Fluxuri primitive de tip caracter (character streams)

- Sunt folosite clasele `FileReader` și `FileWriter`.
- `BufferedReader` adaugă un buffer intern pentru a reduce numărul de accesări pe disc și oferă metoda convenabilă readLine().
- Deschiderea unui fișier impune tratarea excepției `FileNotFoundException`.
- Scrierea informației într-un fișier impune tratarea excepției `IOException`.
- `BufferedWriter` adaugă un buffer și permite scrierea mai eficientă.

Exemplu de citire cu `FileReader`:
```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CitireFisierText {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("exemplu.txt"))) {
            String linie;
            while ((linie = reader.readLine()) != null) {
                System.out.println("Linie citită: " + linie);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Fișierul nu a fost găsit: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Eroare la citirea fișierului: " + e.getMessage());
        }
    }
}
```

Exemplu de scriere cu `FileWriter`:
```java
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ScriereFisierText {
    public static void main(String[] args) {
        // Deschide fișierul în modul "append" = false (va suprascrie fișierul dacă există)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("scris.txt", false))) {
            writer.write("Prima linie din fișier.");
            writer.newLine(); // scrie un newline (caracter de linie nouă)
            writer.write("A doua linie.");
            System.out.println("Fișierul a fost scris cu succes.");
        } catch (IOException e) {
            System.err.println("Eroare la scrierea în fișier: " + e.getMessage());
        }
    }
}
```

### Fluxuri primitive de tip octet (byte streams)

- Sunt folosite clasele `FileInputStream` și `FileOutputStream`.
- Blocul `try-with-resources` asigură închiderea automată a fluxului.
- `fis.read()` citește câte un byte (0–255) și returnează -1 când ajunge la sfârșitul fișierului.
- `FileNotFoundException` este aruncată dacă fișierul nu există sau nu poate fi accesat.
- `FileOutputStream` scrie date în format binar (octeți).
- `text.getBytes()` convertește șirul într-un array de octeți folosind codificarea platformei (sau specificând explicit `"UTF-8"`).
- Fișierul este suprascris implicit. Pentru adăugare, se folosește `new FileOutputStream("scris.bin", true)`.
- `IOException` este aruncată pentru orice eroare în timpul scrierii.
- În cazul în care textul are un encoding special (spre exemplu `Unicode`), este recomandată folosirea `InputStreamReader`.


Exemplu de citire cu `FileInputStream`:
```java
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CitireOcteti {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("exemplu.bin")) {
            int octet;
            while ((octet = fis.read()) != -1) {
                // Conversie la caracter doar pentru fișiere text
                System.out.print((char) octet);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Fișierul nu a fost găsit: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Eroare la citire: " + e.getMessage());
        }
    }
}
```

Exemplu de scriere cu `FileOutputStream`:
```java
import java.io.FileOutputStream;
import java.io.IOException;

public class ScriereOcteti {
    public static void main(String[] args) {
        String text = "Exemplu de text scris în fișierul binar.";

        try (FileOutputStream fos = new FileOutputStream("scris.bin")) {
            byte[] data = text.getBytes(); // conversie String → byte[]
            fos.write(data);
            System.out.println("Datele au fost scrise cu succes în fișier.");
        } catch (IOException e) {
            System.err.println("Eroare la scrierea în fișier: " + e.getMessage());
        }
    }
}
```

Exemplu de citire + scriere (copiere fișier binar):
```java
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopiereFisierBinar {
    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream("imagine.jpg");
             FileOutputStream fos = new FileOutputStream("copie.jpg")) {

            byte[] buffer = new byte[1024]; // buffer de 1KB
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            System.out.println("Fișierul a fost copiat cu succes.");

        } catch (IOException e) {
            System.err.println("Eroare la copierea fișierului: " + e.getMessage());
        }
    }
}
```

## Fluxuri de procesare

- Limbajul Java pune la dispoziție o serie de fluxuri de intrare/ieșire care au o structură stratificată pentru a adăuga
  funcționalități suplimentare pentru fluxurile primitive, într-un mod dinamic și transparent. (Decorator Pattern)
- Printre funcționalitățile suplimentare adăugate se numără:
  - buffering – pentru citire/scriere eficientă; 
  - citire/scriere a tipurilor primitive Java (int, double, String) în format binar; 
  - citire linie cu linie pentru text;
  - citirea/serializarea obiectelor. 
- `readLine()` este specifică `BufferedReader` și returnează `null` atunci când se ajunge la sfârșit de fișier
- `StreamCorruptedException` se aruncă atunci când se încearcă citirea cu `ObjectInputStream` care nu corespunde unui flux valid de obiecte
- Serializarea este procesul de conversie a stării unui obiect întru flux de octeți, care poate fi apoi convertit în sens invers pentru a obține o copie a stării obiectului.
- Cu ajutorul cuvântului cheie `transient`, se pot exclude câmpuri din serializare.

Cele mai comune fluxuri de procesare sunt:
  - `BufferedReader` / `BufferedWriter` pentru citire/scriere buffered
  - `DataInputStream` / `DataOutputStream` pentru citire/scriere eficientă de date primitive
  - `ObjectInputStream` / `ObjectOutputStream` pentru serializare și deserializare
  - `InputStreamReader` / `OutputStreamWriter` pentru conversie între bytes și chars

Exemplu `BufferedReader`:
```java
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CitireBuffered {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("fisier.txt"))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                System.out.println("Linie: " + linie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

Exemplu `DataInputStream` / `DataOutputStream`:
```java
import java.io.*;

public class DataStreamDemo {
    public static void main(String[] args) {
        String numeFisier = "student.bin";

        // Scrierea datelor
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(numeFisier))) {
            dos.writeInt(123);
            dos.writeDouble(9.81);
            dos.writeUTF("Ana");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Citirea datelor
        try (DataInputStream dis = new DataInputStream(new FileInputStream(numeFisier))) {
            int id = dis.readInt();
            double nota = dis.readDouble();
            String nume = dis.readUTF();

            System.out.println("Student: " + id + ", " + nume + ", nota = " + nota);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

Exemplu `EOFException`:
```java
import java.io.*;

public class EOFExceptionExample {
    public static void main(String[] args) {
        String fileName = "numere.dat";

        // Scriem 3 numere întregi în fișier binar
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))) {
            dos.writeInt(10);
            dos.writeInt(20);
            dos.writeInt(30);
        } catch (IOException e) {
            System.err.println("Eroare la scriere: " + e.getMessage());
        }

        // Citim din fișier până ajungem la EOF (și tratăm excepția)
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
            while (true) { // Vom ieși din buclă doar la EOFException
                int numar = dis.readInt();
                System.out.println("Citit: " + numar);
            }
        } catch (EOFException eof) {
            System.out.println("Am ajuns la sfârșitul fișierului (EOF).");
        } catch (IOException e) {
            System.err.println("Eroare la citire: " + e.getMessage());
        }
    }
}
```

Exemplu `ObjectInputStream` / `ObjectOutputStream`:
```java
import java.io.*;
import java.util.*;

class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nume;
    private int varsta;

    public Student(String nume, int varsta) {
        this.nume = nume;
        this.varsta = varsta;
    }

    @Override
    public String toString() {
        return nume + " (" + varsta + ")";
    }
}

public class SerializareDemo {
    public static void main(String[] args) {
        List<Student> lista = List.of(new Student("Maria", 22), new Student("Ion", 24));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("studenti.ser"))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("studenti.ser"))) {
            List<Student> citit = (List<Student>) ois.readObject();
            citit.forEach(System.out::println);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

Exemplu `InputStreamReader` / `OutputStreamWriter`:
```java
import java.io.*;

public class ConversieEncoding {
  public static void main(String[] args) {
    String mesaj = "Șir cu diacritice: ăîșțâ";

    // Scriere cu specificarea encoding-ului
    try (Writer writer = new OutputStreamWriter(new FileOutputStream("diacritice.txt"), "UTF-8")) {
      writer.write(mesaj);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Citire cu același encoding
    try (Reader reader = new InputStreamReader(new FileInputStream("diacritice.txt"), "UTF-8")) {
      int ch;
      while ((ch = reader.read()) != -1) {
        System.out.print((char) ch);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
```

## Fluxuri de procesare cu acces aleatoriu

- Clasa `RandomAccessFile` permite citirea și scrierea la poziții arbitrare dintr-un fișier, oferind posibilitatea de a modifica conținutul fără a citi întreg fișierul.
- `RandomAccessFile(File file, String mode)`
- `RandomAccessFile(String name, String mode)`
- Parametrul `mode` este utilizat pentru a indica modalitatea de deschidere a fișierului: `r`, `rw`, etc.
- Metodele de citire și scriere sunt similare cu cele de la clasele `DataInputStream` / `DataOutputStream`, deoarece clasa implementează interfețele `DataInput` și `DataOutput`.
- Alte metode uzuale:
  - `long getFilePointer()` – furnizează valoarea curentă a cursorului asociat fișierului, raportată la
    începutul fișierului (octetul cu numărul de ordine 0); 
  - `void seek(long pos)` – mută cursorul asociat fișierului pe octetul cu numărul de ordine pos față
    de începutul fișierului (octetul cu numărul de ordine 0); 
  - `int skipBytes(int n)` - mută cursorul asociat fișierului peste n octeți față de poziția curentă.
- În limbajul Java, toate fișierele binare sunt considerate în mod implicit ca fiind de tip `big-endian`.

Exemplu `RandomAccessFile`:
```java
import java.io.RandomAccessFile;
import java.io.IOException;

public class RandomAccessExample {
    public static void main(String[] args) {
        try (RandomAccessFile raf = new RandomAccessFile("random.txt", "rw")) {
            // Scrie un text și apoi modifică la o poziție specifică
            raf.writeUTF("Acesta este un text de test.");
            // Setăm cursorul la poziția 10
            raf.seek(10);
            raf.writeUTF("MODIFICAT");
            // Resetăm poziția pentru a citi întregul fișier
            raf.seek(0);
            String content = raf.readUTF();
            System.out.println("Conținut: " + content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## Serializable

- `Serializable` este o interfață marker din pachetul `java.io` care permite salvarea stării unui obiect într-un flux de octeți (de exemplu, într-un fișier) pentru a fi:
  - stocat permanent (ex: salvare pe disc);
  - transmis prin rețea;
  - transmis între procese;
  - recreat ulterior prin deserializare.

- Java folosește clasele:
  - `ObjectOutputStream` – pentru scrierea (serializarea) obiectelor; 
  - `ObjectInputStream` – pentru citirea (deserializarea) obiectelor; 
- Obiectele scrise trebuie să implementeze `Serializable`.


Condiții esențiale:
- Clasa trebuie să implementeze `Serializable`.
- Toate câmpurile din clasă trebuie să fie și ele serializabile sau să fie marcate ca `transient`. 
- Este recomandată definirea un `serialVersionUID` (identificator unic pentru versiunea clasei serializate).
  - Dacă în timpul deserializării clasa din cod nu se potrivește cu cea din fișier (semnătură diferită), va apărea o excepție de tip `InvalidClassException`.


Exemplu definire:
```java
import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nume;
    private int varsta;

    public Student(String nume, int varsta) {
        this.nume = nume;
        this.varsta = varsta;
    }

    @Override
    public String toString() {
        return "Student{nume='" + nume + "', varsta=" + varsta + "}";
    }
}
```

Exemplu scriere (serializare):
```java
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerializareDemo {
    public static void main(String[] args) {
        Student s = new Student("Maria", 21);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("student.ser"))) {
            out.writeObject(s);
            System.out.println("Obiectul a fost serializat.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

Exemplu citire (deserializare):
```java
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class DeserializareDemo {
    public static void main(String[] args) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("student.ser"))) {
            Student s = (Student) in.readObject();  // downcasting necesar
            System.out.println("Obiectul a fost deserializat: " + s);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```