# Java Server Pages (JSP)

- **Java Server Pages** (JSP) este o altă tehnologie Java care poate fi utilizată în aplicațiile de tip server pentru 
  a genera pagini Web având atât un conținut dinamic, cât și unul static.
- Spre deosebire de un servlet, care generează o pagină Web cu un conținut dinamic prin cod Java, tehnologia 
  JSP extinde limbajul HTML oferind posibilitatea de a însera cod Java sub forma unor scripturi.
- Practic, o pagină JSP are structura unui fișier HTML, dar cu extensia `.jsp`.
- Un avantaj important al unei pagini JSP față de un servlet este dat de faptul că are loc o separare clară a
  conținutului HTML static față de cel dinamic, astfel orice modificare referitoare la estetica paginii nu
  conduce la recompilare, așa cum se întâmplă în cazul unui servlet.

Codul Java (scriptlet) se inserează într-o pagină HTML folosind tag-uri dedicate, cele mai utilizate fiind următoarele:
- `<!--comentariu HTML -->`
- `<%--comentariu JSP --%>`
- `<%! declarare Java %>`
- `<%= expresie Java %>`
- `<% cod Java %>`
- `<%@ directivă Java %>`

Exemplu:
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %> <!-- directivă JSP: importă clasa Date -->

<!--comentariu HTML: Acesta este un comentariu vizibil în sursa paginii HTML-->

<%--comentariu JSP: Acesta NU va apărea în codul sursă trimis clientului--%>

<%! 
    // declarare Java: membrii clasei servletului generat
    private String autor = "Prof. Popescu";
    
    public String getSalut() {
        return "Salutări de la " + autor;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Exemplu JSP Didactic</title>
</head>
<body>

    <h1>Exemplu JSP cu toate elementele</h1>

    <p><strong>Expresie Java:</strong> Astăzi este: <%= new Date() %></p>

    <p><strong>Apel metodă declarată:</strong> <%= getSalut() %></p>

    <%
        // cod Java: cod executabil într-un scriptlet
        int anCurent = new Date().getYear() + 1900;
    %>

    <p><strong>Anul curent este:</strong> <%= anCurent %></p>

</body>
</html>
```

## Ciclul de viață al unei pagini JSP

Ciclul de viață al unei pagini JSP este următorul:
- **translatarea** presupune transformarea paginii `JSP` (.jsp) într-un servlet (.java);
- **compilarea** presupune generarea fișierului `.class` corespunzător servlet-ului, astfel:
  - în momentul în care serverul Web primește o cerere pentru o pagină JSP, motorul JSP verifică dacă este necesară compilarea sa, respectiv dacă nu a
    mai fost compilată sau dacă a fost modificată de la ultima compilare;
  - compilarea presupune parsarea paginii JSP, translatarea sa într-un servlet și compilarea servlet-ului;
- **încărcarea** presupune salvarea în memorie a fișierul `.class` corespunzător servlet-ului;
- **instanțierea** presupune instanțierea unui obiect servlet de către containerul Web
- **inițializarea** presupune apelarea metodei `jspInit()` a servlet-ului;
- **procesarea cererilor** presupune crearea, pentru fiecare client în parte, a unui un fir de executare, apelarea metodei `_jspService()` și generarea unui răspuns `HTML`;
- **distrugerea** presupune eliberarea zonei de memorie alocată servlet-ului și se realizează prin apelarea metodei `jspDestroy()`.

## Obiecte predefinite

Într-o pagină JSP sunt predefinite mai multe obiecte care sunt, de fapt, preluate din servlet-ul obținut prin translatarea paginii JSP:
- **request** – obiect de tip `HttpServletRequest` asociat paginii, echivalent cu primul parametru al
  metodelor `doGet()` și `doPost()` dintr-un servlet;
- **response** – obiect de tip `HttpServletResponse` asociat paginii, echivalent cu al doilea
  parametru al metodelor `doGet()` și `doPost()` dintr-un servlet;
- **out** – obiect de tip `PrintWriter` folosit pentru generarea răspunsului către client;
- **page** – sinonim pentru referința `this`;
- **Exception** – obiect de tip `Exception` asociat paginii JSP.

În afara obiectelor predefinite menționate anterior, unei pagini JSP îi este asociat, pe parcursul întregului
său ciclu de viață, un obiect application. Practic, obiectul application este creat în momentul
instanțierii servlet-ului asociat paginii JSP (apelarea metodei jspInit()) și este distrus în momentul
distrugerii servlet-ului asociat (apelarea metodei jspDestroy()). Folosind acest obiect predefinit, se pot
accesa valorile unor parametri impliciți de configurare din fișierul web.xml sau se pot crea și manipula
parametri dedicați. Manipularea acestor parametri se realizează folosind următoarele două metode:
- `application.setAttribute(String Key, Object Value)`;
- `application.getAttribute(String Key)`;

Exemplu:
```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Date" %>  <!-- Import de clasă Java, util în jspInit/jspDestroy -->
<%-- Acesta este un comentariu JSP care nu apare în codul sursă trimis clientului --%>
<!-- Acesta este un comentariu HTML care apare în sursa trimisă browserului -->

<%! 
    // Declarație Java - variabile și metode la nivel de clasă (apelate în jspInit sau jspDestroy)
    private String dataInitializarii;

    public void jspInit() {
        dataInitializarii = new Date().toString();
        application.setAttribute("dataServerStart", dataInitializarii);
        System.out.println("Pagina JSP a fost inițializată la: " + dataInitializarii);
    }

    public void jspDestroy() {
        System.out.println("Pagina JSP este distrusă. Se închide aplicația.");
    }
%>

<%
    // Cod Java executat la fiecare cerere - corespunde metodei _jspService()
    String nume = request.getParameter("nume");
    if (nume == null || nume.trim().isEmpty()) {
        nume = "Vizitator";
    }

    // Setăm o valoare în contextul aplicației (application scope)
    application.setAttribute("lastVisitor", nume);

    // Obținem o valoare salvată în contextul aplicației
    String serverStart = (String)application.getAttribute("dataServerStart");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Exemplu Ciclu de Viață JSP</title>
</head>
<body>
    <h1>Bun venit, <%= nume %>!</h1> <!-- Expresie JSP pentru afișare -->
    
    <p>Data de pornire a serverului (din jspInit): <strong><%= serverStart %></strong></p>
    <p>Ultimul vizitator care a accesat pagina: <strong><%= application.getAttribute("lastVisitor") %></strong></p>

    <!-- Demonstrăm folosirea obiectelor predefinite -->
    <h3>Informații din obiectele predefinite:</h3>
    <ul>
        <li><strong>request.getMethod():</strong> <%= request.getMethod() %></li>
        <li><strong>request.getRequestURI():</strong> <%= request.getRequestURI() %></li>
        <li><strong>response contentType:</strong> <%= response.getContentType() %></li>
        <li><strong>page (this):</strong> <%= page.getClass().getName() %></li>
        <li><strong>out:</strong> este instanță de <%= out.getClass().getName() %></li>
    </ul>

    <hr>
    <form method="get">
        <label>Introdu numele tău:</label>
        <input type="text" name="nume" />
        <button type="submit">Trimite</button>
    </form>

    <%-- Exemplu de folosire a obiectului exception (într-o pagină declarată cu isErrorPage="true") --%>
    <%-- 
        if (true) {
            throw new RuntimeException("Eroare de test.");
        }
    --%>

</body>
</html>
```

# Proiectarea Interfețelor Grafice în Java

În Java, interfețele grafice se pot realiza folosind bibliotecile `AWT` (Abstract Window Toolkit) și Swing.

## Abstract Window Toolkit (AWT)

- pachet de clase dedicat proiectării unei interfețe grafice
- bazat pe tratarea evenimentelor generate de interacțiunea utilizatorului cu interfața grafică
- crearea unei componente grafice va fi delegată către sistemul de operare
- avantaje: 
  - viteză bună de executare
  - flexibilitate din punct de vedere al sistemului de operare utilizat
- dezavantaje:
  - set redus de componente grafice
  - lipsa portabilității

### Arhitectura AWT

- AWT oferă componente grafice de bază precum butoane, etichete, câmpuri de text, meniuri, liste și containere.
  Aceste componente sunt clase care extind clasa de bază Component (ex: `Button`, `Label`, `TextField`, `TextArea`, `List`, `Checkbox`, `Choice`).
- AWT utilizează _peer classes_, adică pentru fiecare componentă Java există o implementare echivalentă la nivel de sistem de operare. 
  De exemplu, un Button AWT corespunde unui buton nativ Windows sau macOS. Acest mecanism se numește delegare la platformă.
- Principalele clase AWT: `Component`, `Container`, `Toolkit`
- AWT definește ferestre de nivel superior, care pot fi independente sau modale:
  - `Frame` – fereastră principală de aplicație.
  - `Dialog` – fereastră de dialog, opțional modală.
  - `Window` – fereastră generică, fără bordură sau titlu.
- AWT oferă suport pentru grafică bidimensională prin clasa `Graphics`, care este utilizată pentru a desena linii, cercuri, dreptunghiuri, text și imagini. 
  Componentele care doresc să personalizeze afișarea trebuie să suprascrie metoda `paint(Graphics g)`.
- AWT utilizează **modelul de delegare a evenimentelor**, ce stă și la baza Swing.

## Java Foundation Classes (JFC)

**Java Foundation Classes** (JFC) este o arhitectură complexă de API-uri care pune la dispoziție o serie de
facilități pentru proiectarea unei interfețe grafice performante. Arhitectura JFC este structurată pe mai
multe module:
- **Swing**: un API dedicat realizării unei interfețe grafice format din numeroase pachete de clase și interfețe
performante, atât din punct de vedere funcțional, cât și din punct de vedere estetic;
- **Look-and-Feel**: un API care permite modificarea aspectului unei interfețe grafice în raport cu un anumit
model, cum ar fi cele standard `Windows`, `Mac`, `Java`, `Motif` sau altele oferite de diverși dezvoltatori,
acestea putând fi modificate de către utilizator chiar în momentul executării aplicației;
- **Accessibility**: un API care conține facilități dedicate persoanelor cu dizabilități, cum ar fi comenzi
vocale, cititoare de ecran, dispozitive de recunoaștere a vocii etc.);
- **Java2D**: un API care conține facilități dedicate pentru crearea de desene complexe, efectuarea unor
operații geometrice (rotiri, scalări, translații etc.), prelucrarea imaginilor etc.;
- **Internalization**: un API care permite dezvoltarea unor aplicații care pot fi configurate în raport cu
diferite zone geografice, utilizând limba și particularitățile legate de formatarea datei, a numerelor sau
a monedei din zona respectivă

### Arhitectura Swing

- **Swing** este o bibliotecă mai avansată, complet scrisă în Java, care extinde funcționalitățile **AWT** și oferă o gamă largă de componente și un grad ridicat de personalizare.
- Spre deosebire de componentele grafice din AWT, componentele Swing nu depind de sistemul de operare, fiind implementate direct în Java.
- Swing se bazează pe o arhitectură de tip **Model-View-Controller (MVC)**:
  - componenta **Model** gestionează datele, care pot preluate dintr-o bază de date, fișier etc. și înștiințează
    componenta **Controller** în momentul în care acestea sunt modificate;
  - componenta **View** are rolul de a reprezenta grafic datele din model și de a facilita interacțiunea cu
    utilizatorul, prin intermediul componentelor grafice;
  - componenta **Controller** este cea care conectează componentele **Model** și **View**, definind modul în care
    interfața reacționează la acțiunile utilizatorului prin intermediul evenimentelor (click, apăsarea unei taste,
    închiderea unei ferestre etc.), recepționând mesajele primite de la componenta **View** după apariția unui
    anumit eveniment și trimițând mesaje componentei **Model** pentru a actualiza datele afișate de către
    componenta **View**.
- În modelul Swing componentele **View** și **Controller** au fost unificate într-o arhitectură cu model separabil,
  respectiv datele sunt separate de reprezentarea lor vizuală. Pentru a realiza această separare, pentru fiecare componentă Swing este
  definită o clasă care gestionează datele și modul în care sunt tratate evenimentele asociate componentei grafice
  respective.
- Swing se bazează pe modelul delegării evenimentelor, în care o componentă sursă declanșează un eveniment ce este preluat de un listener. 
- Principalele interfețe listener sunt:
  - `ActionListener` – pentru butoane și alte acțiuni (click, ENTER).
  - `MouseListener`, MouseMotionListener – pentru evenimente ale mouse-ului.
  - `KeyListener` – pentru evenimente de tastatură.
  - `WindowListener` – pentru evenimente asociate ferestrelor (deschidere, închidere).
  - `ChangeListener`, `ItemListener`, `FocusListener` – pentru alte tipuri de interacțiuni.
- Swing nu este thread-safe, ceea ce înseamnă că actualizările interfeței grafice trebuie să se facă doar pe `Event Dispatch Thread (EDT)`.

### Componente uzuale

- Containerele sunt elemente care pot conține alte componente sau containere și sunt responsabile pentru afișare și organizarea componentelor.
- Cele mai importante containere Swing sunt:
    - `JFrame` – fereastră de nivel superior, folosită pentru aplicații standalone.
    - `JDialog` – fereastră secundară, folosită pentru mesaje sau confirmări.
    - `JPanel` – container general pentru organizarea altor componente.
    - `JApplet` – folosit pentru aplicații în browser (învechit în prezent).
- Componente de bază în Swing:
  - Componente text: `JLabel`, `JTextField`, `JTextArea`, `JPasswordField`.
  - Componente buton: `JButton`, `JCheckBox`, `JRadioButton`, `JToggleButton`.
  - Liste și selecții: `JList`, `JComboBox`, `JTable`, `JTree`.
  - Componente de meniu: `JMenu`, `JMenuItem`, `JMenuBar`, `JPopupMenu`.
  - Componente de afișare și control: `JScrollPane`, `JTabbedPane`, `JToolBar`, `JProgressBar`, `JSlider`
- Fiecare componentă este o instanță a unei clase care extinde `JComponent`, având metode de configurare, evenimente asociate și posibilitatea de personalizare.
- În plus, o serie de componente Swing au asociate mai multe modele prin care pot fi gestionate datele:
  - **ButtonModel** (`JButton`, `JRadioButton`, `JMenu`, `JMenuItem`, `JCheckBox`)
  - **Document** (`JTextArea`, `JTextField`, `JEditorPane`)
- **Layout Managers** sunt folosiți pentru a determina poziționarea și dimensiunea componentelor într-un container, în funcție de reguli predefinite.
- Tipuri principale de layout manageri:
  - `FlowLayout` – dispune componentele pe orizontală, trecând pe rândul următor când nu mai este spațiu.
  - `BorderLayout` – împarte containerul în 5 zone: North, South, East, West, Center.
  - `GridLayout` – dispune componentele într-o grilă cu un număr fix de rânduri și coloane.
  - `BoxLayout` – dispune componentele pe orizontală sau verticală.
  - `GridBagLayout` – cel mai flexibil și complex, permite dispunerea într-o grilă neregulată, cu control asupra alinierii și întinderii.

### Etapele realizării unei interfețe grafice:
1. crearea unui container rădăcină;
2. adăugarea unor containere intermediare în containerul rădăcină;
3. adăugarea unor componente grafice în containerele intermediare;
4. poziționarea/alinierea componentelor în containerele intermediare folosind gestionari de poziționare;
5. specificarea acțiunilor care trebuie efectuate în momentul apariției unui anumit eveniment lansat în urma
   interacțiunii utilizatorului cu o anumită componentă grafică.

### 1. Crearea unui container rădăcină

Containerul rădăcină este componenta principală a interfeței grafice, care stă la baza structurii aplicației. 
Acesta reprezintă fereastra de nivel superior în care vor fi plasate toate celelalte componente și containere secundare.
Exemple: `JFrame` în Swing, `Frame` în AWT.

Roluri principale:
- Servește ca punct de intrare vizual al aplicației.
- Este responsabil pentru gestionarea întregii ierarhii de componente.
- Reprezintă cadrul în care se definește logica grafică a aplicației.

Considerații de proiectare:
- Se setează titlul ferestrei, dimensiunile și comportamentul la închidere.
- Este recomandată separarea logicii de inițializare a ferestrei de restul funcționalității aplicației.

### 2. Adăugarea unor containere intermediare în containerul rădăcină

Containerele intermediare sunt componente specializate pentru gruparea și organizarea altor componente sau containere. 
Ele facilitează structurarea logică și vizuală a interfeței, precum și reutilizarea de secțiuni în cadrul aplicației.
Exemple: `JPanel` în Swing, `Panel` în AWT.

Scopuri principale:
- Organizarea ierarhică a componentelor pentru o interfață clară și modulară.
- Aplicarea de layout-uri distincte pe secțiuni ale interfeței.
- Îmbunătățirea lizibilității și întreținerii codului prin separarea pe zone funcționale (ex: meniu, formular, zonă de rezultate).

Considerații de proiectare:
- Fiecare container intermediar trebuie să aibă un scop clar.
- Se pot utiliza containere intermediare imbricate pentru layout-uri complexe.
- Arhitectura pe layere sau panels permite scalabilitate în dezvoltare.

### 3. Adăugarea unor componente grafice în containerele intermediare

Componentele grafice (widgets sau controale) sunt elementele de interacțiune directă cu utilizatorul – cum ar fi butoane, etichete, câmpuri de text, liste, etc.
Exemple: `JButton`, `JLabel`, `JTextField`, `JComboBox`, `JList`, `JTable` în Swing.

Roluri esențiale:
- Reprezintă interfața dintre utilizator și aplicație.
- Permit introducerea, afișarea și manipularea datelor.
- Trimit semnale (evenimente) către aplicație în funcție de acțiunile utilizatorului.

Considerații de proiectare:
- Fiecare componentă trebuie să aibă un scop precis în contextul interfeței.
- Se evită aglomerarea vizuală – componenta trebuie să fie clar delimitată și accesibilă.
- Se pot utiliza grupuri de componente pentru funcționalități similare (ex: butoane pentru operații CRUD).

### 4. Poziționarea/alinierea componentelor în containerele intermediare folosind Layout Managers

Layout managers sunt obiecte care controlează modul în care componentele sunt aranjate spațial într-un container. 
Acest lucru permite o interfață scalabilă, adaptabilă la diferite rezoluții și dimensiuni de fereastră.

Layout-uri frecvent utilizate:
- `FlowLayout`: poziționare în linie, pe rânduri succesive.
- `BorderLayout`: împărțirea în cinci regiuni (N, S, E, W, C).
- `GridLayout`: matrice de dimensiuni fixe.
- `BoxLayout`: poziționare verticală sau orizontală.
- `GridBagLayout`: layout flexibil pe grilă, cu restricții detaliate.

Avantaje ale folosirii layout managerilor:
- Interfețe responsive, care se redimensionează corect.
- Separarea clară între logică și prezentare.
- Eliminarea poziționării absolute, dificil de menținut și scalat.

Considerații de proiectare:
- Se alege layout-ul în funcție de tipul de interfață dorit (simplitate vs. control detaliat).
- Se pot combina layout-uri prin utilizarea de containere intermediare.
- Se evită suprapunerea manuală a componentelor (`setBounds`), în favoarea layout-urilor dinamice.

### 5. Specificarea acțiunilor care trebuie efectuate în momentul apariției unui anumit eveniment lansat în urma interacțiunii utilizatorului cu o anumită componentă grafică

Acțiunile sunt comportamentele definite de dezvoltator ca răspuns la interacțiunile utilizatorului cu componentele GUI. 
Aceste interacțiuni sunt gestionate prin mecanismul de evenimente și listeners.

Mecanismul de delegare a evenimentelor:
- Componenta (ex: un buton) generează un eveniment.
- Evenimentul este transmis unui listener, adică unei clase care implementează o interfață de tip listener (ex: `ActionListener`, `MouseListener`).
- Listener-ul prelucrează evenimentul și execută logica asociată.

Exemple de evenimente:
- Apăsarea unui buton (`ActionEvent`).
- Mutarea cursorului (`MouseEvent`).
- Închiderea unei ferestre (`WindowEvent`).

Rolul acestei etape:
- Este esențială pentru definirea funcționalității aplicației.
- Leagă interfața vizuală de logica de business.
- Asigură dinamismul aplicației – răspunsuri rapide la interacțiunea utilizatorului.

Considerații de proiectare:
- Se recomandă separarea logicii de tratare a evenimentelor într-o clasă proprie (MVC).
- Se evită blocarea firului principal de execuție cu operații lungi.
- Se poate folosi programarea funcțională (lambdas în Java 8+) pentru listeners mai simpli.

