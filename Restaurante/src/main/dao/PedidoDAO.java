package main.dao;

import main.modelo.Pedido;
import main.util.BDConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PedidoDAO {
    private static final Logger LOGGER = Logger.getLogger(PedidoDAO.class.getName());

    // INSERTAR UN PEDIDO
    public boolean insertarPedido(Pedido pedido) {
        String sql = "INSERT INTO pedidos (cliente, fecha, estado) VALUES (?, ?, ?)";
        try (Connection conn = BDConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pedido.getCliente());
            stmt.setDate(2, pedido.getFecha());
            stmt.setString(3, pedido.getEstado());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error insertando pedido: {0}", e.getMessage());
            return false;
        }
    }

    // LISTAR TODOS LOS PEDIDOS
    public List<Pedido> listarPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";
        try (Connection conn = BDConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Pedido pedido = new Pedido(
                        rs.getInt("id"),
                        rs.getString("cliente"),
                        rs.getDate("fecha"),
                        rs.getString("estado")
                );
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error obteniendo pedidos: {0}", e.getMessage());
        }
        return pedidos;
    }
}