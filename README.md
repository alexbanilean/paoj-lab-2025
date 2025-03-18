# PAOJ 2025 (Grupa 234)

Programare avansată pe obiecte în Java

### Punctaj
- 50% examen final (curs)
  - condiție: minim nota 5 (fără rotunjire) (4.75 != 5)
- 50% laborator
  - condiție: minim nota 5 (fără rotunjire) (4.75 != 5)
  - 25% prezență și activitate
  - 25% proiect
  - nota la laborator va fi de la 1 la 10 astfel:
    - 1 punct din oficiu
    - 5 puncte proiect (2.5 puncte etapa I + 2.5 puncte etapa II)
    - 4 puncte prezență și activitate
      - min(4, 2.4 puncte prezențe (12 laboratoare x 0.2 puncte) + 1.6 puncte teme (4 teme x 0.4 puncte) + bonus Kahoot)
      - bonusul Kahoot este opțional și este reprezentat de punctajul total obținut în urma celor 3 quiz-uri din săptămânile S07 (08 aprilie), S10 (06 mai), S13 (27 mai)
      - pentru fiecare quiz, prima treime din clasament obține 0.3 puncte, a doua treime 0.2 puncte, a treia 0.1 puncte

### Cerințe Proiect

Fiecare student va lucra la un proiect individual. Proiectul este structurat în mai multe etape.

Condiția de punctare a proiectelor:
- să nu prezinte erori de compilare
- să se implementeze cerințele date

Termene de predare:
- Etapa I: săptămâna 14 – 18 aprilie
- Etapa II: săptămâna 2 – 6 iunie

#### Etapa I

1) Definirea sistemului
   - Să se creeze o listă pe baza temei alese cu cel puțin 10 acțiuni/interogări care se pot face în cadrul sistemului și o listă cu cel puțin 8 tipuri de obiecte.
2) Implementare
   - Să se implementeze în limbajul Java o aplicație pe baza celor definite la primul punct.
   - Aplicația va conține:
     - clase simple cu atribute private / protected și metode de acces;
     - cel puțin 2 colecții diferite capabile să gestioneze obiectele definite anterior (eg: List, Set, Map, etc.) dintre care cel puțin una sa fie sortată – se vor folosi array-uri uni/bidimensionale în cazul în care nu se parcurg colecțiile până la data checkpoint-ului;
     - utilizare moștenire pentru crearea de clase adiționale și utilizarea lor în cadrul colecțiilor;
     - cel puțin o clasă serviciu care să expună operațiile sistemului;
     - o clasa Main din care sunt făcute apeluri către servicii;

#### Etapa II

1) Extindeți proiectul din prima etapă prin realizarea persistenței utilizând o bază de date relațională și JDBC.
   - Să se realizeze servicii care sa expună operații de tip create, read, update și delete pentru cel puțin 4 dintre clasele definite. Se vor realiza servicii singleton generice pentru scrierea și citirea din baza de date.
2) Realizarea unui serviciu de audit
   - Se va realiza un serviciu care să scrie într-un fișier de tip CSV de fiecare dată când este executată una dintre acțiunile descrise în prima etapă. Structura fișierului: nume_actiune, timestamp

#### Teme sugerate
1) catalog (student, materie, profesor)
2) biblioteca (sectiuni, carti, autori, cititori)
3) programare cabinet medical (client, medic, programare)
4) gestiune stocuri magazin (categorii, produse, distribuitori)
5) aplicatie bancara (conturi,extras de cont, tranzactii, carduri, servicii)
6) platfora e-learning(cursuri, utilizatori, cursanti, quizuri)
7) sistem licitatii (licitatii, bids, produse, utilizatori)
8) platforma food delivery(localuri, comenzi, soferi, useri)
9) platforma imprumuturi carti - tip bookster (companii afiliate, utilizatori, carti)
10) platforma e-ticketing (evenimente, locatii, clienti)

#### Contact

Bănilean Alexandru-Ioan

- Teams
- email: alexandru-ioan.banilean@s.unibuc.ro
