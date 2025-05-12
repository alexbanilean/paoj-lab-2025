import db.DBConnection;
import practice.*;
import model.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final AngajatDAO angDAO = new AngajatDAO();
    private static final ProiectDAO projDAO = new ProiectDAO();
    private static final ParticipareDAO partDAO = new ParticipareDAO();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println("\n=== MENIU ===");
            System.out.println("1. Inserare Angajat");
            System.out.println("2. Inserare Proiect");
            System.out.println("3. Atribuire Participare");
            System.out.println("4. Afisare Participari");
            System.out.println("5. Procedura inserare/actualizare Angajat");
            System.out.println("6. Buget alocat Angajat");
            System.out.println("0. Exit");
            System.out.print("Optiune: ");
            int opt = Integer.parseInt(sc.nextLine());
            switch (opt) {
                case 1 -> inserareAngajat();
                case 2 -> inserareProiect();
                case 3 -> atribuireParticipare();
                case 4 -> afisareParticipari();
                case 5 -> procInserareSauActualizare();
                case 6 -> bugetAlocat();
                case 0 -> { System.exit(0); }
                default -> System.out.println("Optiune invalida.");
            }
        }
    }

    private static void inserareAngajat() throws Exception {
        System.out.print("CNP: "); var c = sc.nextLine();
        System.out.print("Nume: "); var n = sc.nextLine();
        System.out.print("Salariu: "); var s = new BigDecimal(sc.nextLine());
        angDAO.inserare(new Angajat(c, n, s));
        System.out.println("Angajat inserat.");
    }

    private static void inserareProiect() throws Exception {
        System.out.print("Denumire: "); var d = sc.nextLine();
        System.out.print("Buget: "); var b = new BigDecimal(sc.nextLine());
        projDAO.inserare(new Proiect(0, d, b));
        System.out.println("Proiect inserat.");
    }

    private static void atribuireParticipare() throws Exception {
        System.out.print("CNP angajat: "); var c = sc.nextLine();
        System.out.print("ID proiect: "); var i = Integer.parseInt(sc.nextLine());
        System.out.print("Rol: "); var r = sc.nextLine();
        System.out.print("Nr ore: "); var o = Integer.parseInt(sc.nextLine());
        partDAO.inserare(new Participare(c, i, r, o));
        System.out.println("Participare inserata.");
    }

    private static void afisareParticipari() throws Exception {
        List<Participare> list = partDAO.findAllJoin();
        System.out.printf("%-20s %-20s %-10s %-8s%n",
                "Angajat", "Proiect", "Rol", "Ore");
        list.forEach(p ->
                System.out.printf("%-20s %-20s %-10s %-8d%n",
                        p.getCnpAngajat(),
                        p.getIdProiect()==-1? p.getCnpAngajat() : "",
                        p.getRol(), p.getNrOre())
        );
    }

    private static void procInserareSauActualizare() throws Exception {
        System.out.print("CNP: "); var c = sc.nextLine();
        System.out.print("Nume: "); var n = sc.nextLine();
        System.out.print("Salariu: "); var s = new BigDecimal(sc.nextLine());
        angDAO.inserareSauActualizareProc(c, n, s);
    }

    private static void bugetAlocat() throws Exception {
        System.out.print("CNP: "); var c = sc.nextLine();
        try (var cdb = DBConnection.getConnection();
             var st = cdb.createStatement();
             var rs = st.executeQuery("SELECT bugetAlocat('" + c + "')")) {
            if (rs.next()) {
                System.out.println("Buget alocat: " + rs.getBigDecimal(1));
            }
        }
    }
}
