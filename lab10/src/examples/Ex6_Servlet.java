package examples;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/feedback") // maparea URL-ului înlocuiește web.xml
class FeedbackServlet extends HttpServlet {

    // Metodă apelată o singură dată, la inițializare
    @Override
    public void init() throws ServletException {
        System.out.println("Servletul Feedback a fost inițializat.");
    }

    // Metodă apelată la cereri GET (ex: din browser)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Setăm tipul de conținut al răspunsului (text HTML)
        response.setContentType("text/html");

        // Obținem obiectul PrintWriter pentru a scrie HTML în răspuns
        PrintWriter out = response.getWriter();

        // Generăm dinamic formularul HTML
        out.println("<html><head><title>Feedback</title></head><body>");
        out.println("<h1>Formular de Feedback</h1>");
        out.println("<form method='post' action='feedback'>");
        out.println("Nume: <input type='text' name='nume'/><br/>");
        out.println("Email: <input type='text' name='email'/><br/>");
        out.println("Comentarii:<br/><textarea name='comentarii' rows='5' cols='30'></textarea><br/>");
        out.println("<input type='submit' value='Trimite'/>");
        out.println("</form>");
        out.println("</body></html>");
    }

    // Metodă apelată la cereri POST (ex: submit din formular)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Citim datele trimise prin formular
        String nume = request.getParameter("nume");
        String email = request.getParameter("email");
        String comentarii = request.getParameter("comentarii");

        // Setăm tipul răspunsului
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        // Generăm o pagină HTML care reflectă datele introduse
        out.println("<html><head><title>Mulțumim!</title></head><body>");
        out.println("<h1>Mulțumim pentru feedback, " + escape(nume) + "!</h1>");
        out.println("<p>Emailul tău: " + escape(email) + "</p>");
        out.println("<p>Comentariile tale:</p>");
        out.println("<blockquote>" + escape(comentarii) + "</blockquote>");
        out.println("<a href='feedback'>Trimite alt feedback</a>");
        out.println("</body></html>");
    }

    // Metodă apelată o singură dată, la distrugerea servletului
    @Override
    public void destroy() {
        System.out.println("Servletul Feedback a fost distrus.");
    }

    // Funcție simplă pentru a evita injecții HTML (didactic)
    private String escape(String input) {
        if (input == null) return "";
        return input.replaceAll("&", "&amp;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;");
    }
}
