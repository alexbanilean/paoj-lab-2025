package examples;

import java.io.*;
import java.net.*;

class HttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("HTTP Server pornit pe http://localhost:8080");

        while (true) {
            Socket client = serverSocket.accept();

            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream());

            // Citim prima linie pentru a identifica ruta
            String requestLine = in.readLine();
            if (requestLine == null || requestLine.isEmpty()) {
                client.close();
                continue;
            }

            // Exemplu: "GET /salut HTTP/1.1"
            System.out.println("Cerere: " + requestLine);

            // Extragem ruta din cerere
            String[] tokens = requestLine.split(" ");
            String path = "/";
            if (tokens.length >= 2) {
                path = tokens[1];
            }

            // Continuăm să citim restul header-ului (ignorat pentru acum)
            String line;
            while ((line = in.readLine()) != null && !line.isEmpty()) {
                // doar consumăm cererea
            }

            // Rutare simplă
            String responseBody;
            if (path.equals("/salut")) {
                responseBody = "<html><body><h1>Salutare! Acesta este un răspuns personalizat.</h1></body></html>";
            } else {
                responseBody = "<html><body><h1>Hello from Java HTTP Server!</h1></body></html>";
            }

            // Trimitem răspuns HTTP complet
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html; charset=UTF-8");
            out.println("Content-Length: " + responseBody.getBytes().length);
            out.println(); // linie goală => final header
            out.println(responseBody);
            out.flush();

            client.close();
        }
    }
}
