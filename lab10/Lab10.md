# Socket-uri în Java

- Un socket reprezintă un punct final pentru comunicația bidirecțională între două procese într-o rețea, identificat prin adresa IP și port.
- Prin intermediul socket-urilor, aplicațiile pot trimite și primi date folosind protocoale precum `TCP` sau `UDP`.
- `TCP` oferă o comunicare fiabilă, orientată pe conexiune, cu retransmisii și control al fluxului, ideal pentru aplicații care necesită integritate (ex. `HTTP`, `FTP`).
- `UDP` este neorientat pe conexiune, fără garanții de livrare sau ordonare, cu latență redusă, potrivit pentru streaming audio/video sau jocuri online.

- Modelul utilizat pe scară largă în sistemele distribuite este sistemul examples.Client-Server.
- Serverele sunt cele care își încep primele activitatea, oferind clienților posibilitatea de a se conecta la ele (spunem că acceptă conexiuni de la clienți).
- Un client își manifestă dorința de a se conecta și, dacă serverul este gata să accepte conexiunea, aceasta se
  realizează efectiv. În continuare, informațiile (datele) sunt transmise bidirecțional. Teoretic, activitatea unui
  server se desfășoară la infinit.
- În pachetul `java.net` sunt definite două clase care pot fi utilizate pentru comunicarea bazată pe socket-uri:
  - `ServerSocket` – pentru partea de server; 
  - `Socket` – pentru partea de client.
- Oricărui socket îi sunt atașate două fluxuri: unul de intrare și unul de ieșire. Astfel, comunicarea folosind
  socket-uri se reduce la operații de scriere/citire în/din fluxurile atașate.
- I/O vs NIO (Non-blocking I/O): I/O tradițional (Socket/ServerSocket) blochează firul de execuție în așteptarea datelor, 
  pe când NIO utilizează canale și selectori pentru a gestiona mai eficient resursele, permițând unui singur fir să monitorizeze multiple conexiuni.

## API-ul Java pentru socket-uri

- `java.net.Socket` – client TCP
- `java.net.ServerSocket` – server TCP
- `java.net.DatagramSocket` și `DatagramPacket` – comunicație UDP
- `java.nio.channels.SocketChannel`, `ServerSocketChannel`, `Selector` – I/O non-blocking NIO

# Servlet-uri

- Servlet-urile sunt aplicații Java gestionate și executate de un server Web specializat, precum Apache Tomcat, GlassFish etc.
- Practic, un servlet este o clasă scrisă în limbajul Java al cărei scop este generarea dinamică de date într-un
server HTTP. O astfel de clasă poate crea atât conținut HTML, cât și documente XML, PDF, imagini,
fișiere etc. Totuși, de obicei, un servlet este folosit împreună cu protocolul HTTP pentru a genera pagini
web dinamice.
- Un container web gestionează ciclul de viață al unui servlet, asociază un URL cu un anumit
  servlet, asigură securitatea servlet-ului etc. Practic, comunicarea dintre un client (de obicei un browser) și
  un servlet se realizează astfel:
  - pentru a răspunde unei cereri de la un client către un anumit servlet, serverul trimite mai departe cererea
    către container, care are rolul de a instanția obiectului respectiv și de a apela metodele necesare;
  - serverul Web, după executarea servlet-ului, trimite înapoi browser-ului un fișier HTML, pe care acesta
    îl afișează în mod corespunzător.

Caracteristici:
- Rulează într-un container web (Tomcat, Jetty).
- Răspunde la cereri HTTP.
- Poate manipula datele cererii și poate genera HTML dinamic.

Definirea unui servlet se poate realiza prin extinderea clasei abstracte HttpServlet din pachetul javax.servlet, care conține următoarele metode:
- metoda init() se apelează o singură dată, atunci când este încărcat servlet-ul (similar unui
constructor). Practic, servlet-ul este creat în momentul în care un client emite o cerere, dar se poate opta
și pentru a-l crea în momentul pornirii server-ului;
- metoda service() este automat apelată ca răspuns la cererea fiecărui client și poate fi suprascrisă
pentru a furniza o funcționalitate implicită (servlet-urile care extind HttpServlet pot să nu
suprascrie această metodă);
- metoda destroy() este apelată când servlet-ul este oprit de către server-ul Web.

Ciclul de viață al unui servlet este următorul:
- serverul încarcă servlet-ul când acesta este cerut de către client sau la pornirea server-ului;
- serverul creează o instanță a clasei servlet-ului pentru deservirea tuturor clienților, pentru fiecare client fiind alocat automat un fir de executare separat;
- serverul apelează metoda init() a servlet-ului; 
- în momentul primirii unei cereri pentru servlet, serverul instanțiază:
  - un obiect HttpServletRequest folosind datele incluse în cererea clientului 
  - un obiect HttpServletResponse care furnizează metode pentru returnarea răspunsului
- servlet-ul apelează metoda service(), astfel:
  - metoda service() primește ca parametrii obiectele construite la pasul anterior și, la rândul său,
  apelează metodele specifice protocolului HTTP pentru transmiterea datelor de la/către client,
  respectiv metodele doGet() sau doPost();
  - metoda service() procesează cererea clientului prin intermediul obiectului de tip
  HttpServletRequest și furnizează un răspuns prin obiectul HttpServletResponse;
- se apelează metoda destroy() în cazul în care containerul de servlet-uri al server-ului Web inițiază oprirea sa.

Observații:
- Datele transmise prin metoda GET sunt restricționate la maxim 1024 caractere și nu pot conține date în
  format binar. Deoarece valorile parametrilor sunt vizibile în link, nu se poate folosi metoda GET pentru a
  transmite date confidențiale! Mai mult, datele transmise folosind metoda GET rămân în cache-ul browserului și sunt salvate în istoricul navigării (History)!
- Spre deosebire de metoda GET, metoda POST transmite informații către server prin intermediul header-ului
  HTTP. Astfel, nu există restricții referitoare la dimensiunea datelor trimise, iar datele pot fi și în format binar.
- Parametrii unei cereri fie sunt incluși în URL, fie sunt încapsulați în corpul unei cereri HTTP.
  Indiferent de varianta utilizată, valorile parametrilor pot fi preluate în cadrul celor două metode, sub forma
  unor șiruri de caractere, folosind metoda String getParameter(String nume_parametru).
