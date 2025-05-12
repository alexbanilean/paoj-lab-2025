package practice;

/**
 * Cerință:
 * Se dorește realizarea unei aplicații Java care să gestioneze următoarele entități dintr-o companie IT:
 *      Angajat (CNP, Nume, Salariu)
 *      Proiect (ID, Denumire, Buget)
 *      Participare (CNP_angajat, ID_proiect, Rol, Nr_ore)
 *
 * Creați o bază de date PostgreSQL numită firma_it și tabelele corespunzătoare de mai sus folosind un script SQL.
 * Datele de conectare la baza de date trebuie să se afle într-un fișier `db.properties`.
 *
 * Operații ce trebuie implementate:
 *
 * 1. Inserarea de angajați și proiecte utilizând PreparedStatement.
 * 2. Atribuirea unui angajat la un proiect cu un anumit rol și număr de ore.
 * 3. Afișarea tuturor proiectelor cu angajații asociați, rolul și orele lucrate,
 *    folosind JOIN și Statement.
 * 4. Crearea și apelarea din Java a unei proceduri stocate:
 *    - inserareSauActualizareAngajat(CNP, Nume, Salariu, OUT rezultat)
 *    - Inseră angajatul dacă nu există, altfel îl actualizează.
 *    - Afișează în Java un mesaj diferit în funcție de rezultat (1 – inserat, 2 – actualizat).
 * 5. Crearea și apelarea din Java a unei funcții stocate:
 *    - bugetAlocat(CNP)
 *    - Calculează bugetul total alocat unui angajat, pe baza orelor și bugetelor proiectelor.
 *    - Rezultatul este afișat în consola Java.
 *
 * De asemenea, în clasa Main trebuie să se implementeze un meniu cu toate operațiile de mai sus.
 *
 * Structură recomandată Java:
 *   - DBConnection.java – gestionarea conexiunii la DB
 *   - AngajatDAO.java – logica pentru angajați
 *   - ProiectDAO.java – logica pentru proiecte
 *   - ParticipareDAO.java – logica pentru participări
 *   - Main.java
 **/

public class Ex1_IT_Management {
}
