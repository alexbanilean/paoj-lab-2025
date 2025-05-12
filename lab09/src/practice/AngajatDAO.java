package practice;

import model.Angajat;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AngajatDAO {

    public void inserare(Angajat a) throws Exception {
        String sql = "INSERT INTO Angajat(cnp, nume, salariu) VALUES (?, ?, ?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, a.getCnp());
            ps.setString(2, a.getNume());
            ps.setBigDecimal(3, a.getSalariu());
            ps.executeUpdate();
        }
    }

    public void inserareSauActualizareProc(String cnp, String nume,
                                           java.math.BigDecimal salariu) throws Exception {
        String call = "{ CALL inserareSauActualizareAngajat(?, ?, ?, ?) }";
        try (Connection c = DBConnection.getConnection();
             CallableStatement cs = c.prepareCall(call)) {
            cs.setString(1, cnp);
            cs.setString(2, nume);
            cs.setBigDecimal(3, salariu);
            cs.registerOutParameter(4, Types.INTEGER);
            cs.execute();
            int rezultat = cs.getInt(4);
            if (rezultat == 1)
                System.out.println("Angajat inserat.");
            else
                System.out.println("Angajat actualizat.");
        }
    }

    public List<Angajat> findAll() throws Exception {
        String sql = "SELECT cnp, nume, salariu FROM Angajat";
        List<Angajat> list = new ArrayList<>();
        try (Connection c = DBConnection.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Angajat(
                        rs.getString("cnp"),
                        rs.getString("nume"),
                        rs.getBigDecimal("salariu")
                ));
            }
        }
        return list;
    }
}

