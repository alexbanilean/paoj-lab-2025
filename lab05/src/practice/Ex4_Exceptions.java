package practice;

/**
 * Cerință:
 * 1. Creați o excepție custom checked `LipsaDateException` și una unchecked `FormatInvalidException`.
 * 2. Creați o clasă `Utilizator` cu atribute `nume`, `email`.
 *    - Dacă numele e gol => aruncă `LipsaDateException`
 *    - Dacă email-ul nu conține `@` => aruncă `FormatInvalidException`
 * 3. Creați o metodă care citește datele de la utilizator (folosește Scanner).
 * 4. Tratați separat:
 *    - întâi excepția `FormatInvalidException`
 *    - apoi pe cea generală `Exception`
 * 5. În blocul `finally`, afișați mesajul „Validare finalizată”.
 */

import java.util.Scanner;

class LipsaDateException extends Exception {
    public LipsaDateException(String msg) {
        super(msg);
    }
}

class FormatInvalidException extends RuntimeException {
    public FormatInvalidException(String msg) {
        super(msg);
    }
}

class Utilizator {
    String nume;
    String email;

    public Utilizator(String nume, String email) throws LipsaDateException {
        if (nume == null || nume.isEmpty()) {
            throw new LipsaDateException("Numele nu poate fi gol.");
        }
        if (email == null || !email.contains("@")) {
            throw new FormatInvalidException("Email invalid.");
        }
        this.nume = nume;
        this.email = email;
    }
}

class Ex4_Exceptions {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Nume: ");
            String nume = scanner.nextLine();
            System.out.print("Email: ");
            String email = scanner.nextLine();

            Utilizator u = new Utilizator(nume, email);
            System.out.println("Utilizator valid!");

        } catch (FormatInvalidException ex) {
            System.err.println("Eroare format email: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Eroare generală: " + ex.getMessage());
        } finally {
            System.out.println("Validare finalizată.");
        }
    }
}
