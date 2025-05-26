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
