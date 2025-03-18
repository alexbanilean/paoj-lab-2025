# Introducere in limbajul Java

Java este un limbaj de programare cunoscut pentru portabilitatea, securitatea și robustețea sa.

## Caracteristici principale

- **Orientat pe obiecte**
  - Java folosește paradigma orientată pe obiecte, unde totul este organizat în clase și obiecte. Definiția unui obiect se numește clasă. Obiectul este o instanță a unei clase.
  - Principiile fundamentale ale OOP (incapsulare, moștenire, polimorfism și abstractizare) sunt implementate și încurajate.
  - Spre deosebire de C++, Java nu oferă manipulare directă a memoriei prin pointeri și nu suportă moștenirea multiplă (se folosește interfața pentru a atinge un efect similar).

- **Case Sensitive**
  - Java distinge între majuscule și minuscule, ceea ce înseamnă că `Hello` și `hello` sunt considerate entități diferite.

- **Portabilitate**
  - Codul Java este compilat într-un format intermediar numit **bytecode**, care poate rula pe orice platformă ce are instalată un JVM (Java Virtual Machine).
  - Aceasta permite aplicațiilor Java să fie „scrie o dată, rulează oriunde” (WORA).

- **Securitate**
  - Java are un model de securitate robust, inclusiv gestionarea automată a memoriei (garbage collection), ceea ce reduce riscurile de scurgeri de memorie și acces neautorizat.

- **Multithreading**
  - Suport nativ pentru programare concurentă și multithreading, permițând dezvoltarea de aplicații performante și responsive.

- **Rich Standard Library**
  - Java oferă un set extins de biblioteci standard (java.lang, java.util, java.io, etc.), care facilitează dezvoltarea rapidă a aplicațiilor.

## Procesul de dezvoltare în Java

1. **Scrierea codului sursă (.java)**
  - Fiecare clasă Java este definită într-un fișier cu extensia `.java`.
  - Exemplu de program "Hello, World!":
    ```java
    public class HelloWorld {
        public static void main(String[] args) {
            System.out.println("Hello, World!");
        }
    }
    ```

2. **Compilarea**
  - Codul sursă este compilat cu ajutorul compilatorului `javac`:
    ```
    javac HelloWorld.java
    ```
  - Rezultatul este un fișier `.class` ce conține bytecode-ul.

3. **Executarea**
  - Bytecode-ul este interpretat și executat de JVM:
    ```
    java HelloWorld
    ```

## Etapele realizării unei aplicații Java

- .java (fisier cod sursa) => javac (compilator) => .class (fisier bytecode) => java (interpretor, emulator de cod bytecode = JVM) => OS (sistem de operare)
- JVM = Java Virtual Machine - Interpretează bytecode-ul și asigură portabilitatea aplicațiilor.
- JRE = Java Runtime Environment - Conține JVM, biblioteci de clase și alte componente necesare pentru rularea aplicațiilor Java.
- JDK = Java Development Kit - Include JRE plus unelte de dezvoltare (compilatorul, debugger, documentație, etc.) și este necesar pentru dezvoltarea aplicațiilor Java.

## Pregătire mediu de lucru

- instalare Java 21: https://jdk.java.net/archive/ -> Java 21 GA
- instalare IDE - IntelliJ IDEA: https://www.jetbrains.com/idea/download/
- în IntelliJ IDEA creați un proiect Java nou și alegeți să folosiți JDK 21

## Tutoriale

- https://docs.oracle.com/javase/tutorial/index.html
- https://www.tutorialspoint.com/java/