package examples;

import java.io.*;
import java.net.*;
import java.util.Scanner;

// Serverul TCP multiclient
class MultiClientServer {
    public static void main(String[] args) {
        int port = 12345;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server multiclient pornit pe portul " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new Worker(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Worker implements Runnable {
        private Socket socket;

        public Worker(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
            ) {
                out.println("Salut, ești conectat la server.");
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println("[" + socket.getInetAddress() + "]: " + line);
                    out.println("Server: Am primit '" + line + "'");
                }
            } catch (IOException e) {
                System.out.println("Client deconectat.");
            }
        }
    }
}

class MultiClientClient implements Runnable {
    private final String name;

    public MultiClientClient(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        String host = "localhost";
        int port = 12345;

        try (
                Socket socket = new Socket(host, port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            System.out.println("[" + name + "] Conectat la server: " + in.readLine());

            for (int i = 0; i < 3; i++) {
                String msg = name + " spune mesajul " + (i + 1);
                out.println(msg);
                String response = in.readLine();
                System.out.println("[" + name + "] Server a răspuns: " + response);
                Thread.sleep(1000); // Pauză între mesaje
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("[" + name + "] Eroare: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Thread(new MultiClientClient("Client1")).start();
    }
}

class Exemplu {
    public static void main(String[] args) {
        System.out.println("Pornim mai mulți clienți...");

        for (int i = 1; i <= 3; i++) {
            String clientName = "Client" + i;
            new Thread(new MultiClientClient(clientName)).start();
        }
    }
}
