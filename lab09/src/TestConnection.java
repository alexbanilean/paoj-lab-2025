import java.sql.*;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/firma_it";
        String user = "admin";
        String password = "admin";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conectare reușită!");
        } catch (SQLException e) {
            System.err.println("Eroare la conectare: " + e.getMessage());
        }
    }
}
