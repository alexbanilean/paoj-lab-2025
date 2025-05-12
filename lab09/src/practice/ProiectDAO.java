package practice;

import model.Proiect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProiectDAO {

    public void inserare(Proiect p) throws Exception {
        String sql = "INSERT INTO Proiect(denumire, buget) VALUES (?, ?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getDenumire());
            ps.setBigDecimal(2, p.getBuget());
            ps.executeUpdate();
        }
    }

    public List<Proiect> findAll() throws Exception {
        String sql = "SELECT id, denumire, buget FROM Proiect";
        List<Proiect> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Proiect(
                        rs.getInt("id"),
                        rs.getString("denumire"),
                        rs.getBigDecimal("buget")
                ));
            }
        }
        return list;
    }
}

