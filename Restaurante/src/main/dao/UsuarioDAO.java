package main.dao;

import main.modelo.Usuario;
import main.util.BDConnection;
import java.sql.*;

public class UsuarioDAO {

    // ✅ Validar usuario en la BD
    public Usuario validarUsuario(String username, String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            return null; // Evita errores si los valores están vacíos
        }

        String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
        try (Connection conn = BDConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("rol"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ Insertar nuevo usuario en la BD
    public boolean insertarUsuario(String username, String password, String rol) {
        if (username == null || password == null || rol == null || username.isEmpty() || password.isEmpty() || rol.isEmpty()) {
            return false; // Evita insertar datos vacíos
        }

        String sql = "INSERT INTO usuarios (username, password, rol) VALUES (?, ?, ?)";
        try (Connection conn = BDConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, rol);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}