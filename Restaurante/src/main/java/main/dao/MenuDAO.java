package main.dao;

import main.modelo.Plato;
import main.util.BDConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuDAO {
    private static final Logger LOGGER = Logger.getLogger(MenuDAO.class.getName());

    // LISTAR TODOS LOS PLATOS DEL MENÚ
    public List<Plato> listarPlatos() {
        List<Plato> platos = new ArrayList<>();
        String sql = "SELECT * FROM menu";
        try (Connection conn = BDConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Plato plato = new Plato(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getString("imagen")
                );
                platos.add(plato);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error obteniendo platos: {0}", e.getMessage());
        }
        return platos;
    }
}