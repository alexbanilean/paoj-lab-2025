package examples;

import java.io.*;
import java.net.*;
import java.util.Scanner;

// Serverul Echo (TCP)
class EchoServer {
    public static void main(String[] args) {
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serverul TCP a pornit pe portul " + port + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client conectat: " + clientSocket.getInetAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String message = in.readLine();
                System.out.println("Primit: " + message);

                out.println("ECHO: " + message);
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Clientul Echo (TCP)
class EchoClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 12345;

        try (Socket socket = new Socket(host, port)) {
            System.out.println("Conectat la serverul TCP.");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Scrie un mesaj: ");
            String msg = scanner.nextLine();

            out.println(msg);
            String response = in.readLine();
            System.out.println("Răspuns de la server: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
