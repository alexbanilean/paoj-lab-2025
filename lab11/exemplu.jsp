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