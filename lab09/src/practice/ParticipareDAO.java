package practice;

import model.Participare;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipareDAO {

    public void inserare(Participare p) throws Exception {
        String sql = "INSERT INTO Participare(cnp_angajat, id_proiect, rol, nr_ore) VALUES (?, ?, ?, ?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getCnpAngajat());
            ps.setInt(2, p.getIdProiect());
            ps.setString(3, p.getRol());
            ps.setInt(4, p.getNrOre());
            ps.executeUpdate();
        }
    }

    public List<Participare> findAllJoin() throws Exception {
        String sql =
                "SELECT a.nume, p.denumire, par.rol, par.nr_ore " +
                        "FROM Participare par " +
                        "JOIN Angajat a ON par.cnp_angajat = a.cnp " +
                        "JOIN Proiect p ON par.id_proiect = p.id";
        List<Participare> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Participare(
                        rs.getString("nume"),
                        rs.getString("denumire"),
                        rs.getString("rol"),
                        rs.getInt("nr_ore")
                ));
            }
        }
        return list;
    }
}

