package main.dao;

import main.modelo.Reserva;
import main.util.BDConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaDao {

    // ✅ Método para insertar una reserva en la BD
    public boolean insertarReserva(Reserva reserva) {
        String sql = "INSERT INTO reservas (nombre_cliente, fecha, hora, numero_mesa, estado) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = BDConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, reserva.getNombreCliente());
            stmt.setDate(2, reserva.getFecha());
            stmt.setTime(3, reserva.getHora());
            stmt.setInt(4, reserva.getNumeroMesa());
            stmt.setString(5, reserva.getEstado());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Método para listar todas las reservas
    public List<Reserva> listarReservas() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reservas";

        try (Connection conn = BDConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                reservas.add(new Reserva(
                        rs.getString("nombre_cliente"),
                        rs.getDate("fecha"),
                        rs.getTime("hora"),
                        rs.getInt("numero_mesa"),
                        rs.getString("estado")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }
}