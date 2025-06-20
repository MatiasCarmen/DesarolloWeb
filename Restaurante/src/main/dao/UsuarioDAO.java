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

        String call = "{ call sp_validar_usuario(?, ?) }";
        try (Connection conn = BDConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(call)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(rs.getInt("id"), rs.getString("username"), rs.getString("rol"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ Insertar nuevo usuario en la BD
    /**
     * Inserta un nuevo usuario validando previamente los datos únicos mediante
     * el procedimiento almacenado {@code sp_validar_datos_unicos}.
     *
     * @return código de error devuelto por el procedimiento. 0 significa éxito.
     */
    public int insertarUsuario(String username, String password, String rol,
                               String dni, String telefono, String correo) {
        if (username == null || password == null || rol == null ||
                username.isEmpty() || password.isEmpty() || rol.isEmpty()) {
            return -1; // datos incompletos
        }

        String validateCall = "{ call sp_validar_datos_unicos(?, ?, ?, ?) }";
        String insertSql = "INSERT INTO usuarios (username, password, rol, dni, telefono, correo) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = BDConnection.getConnection();
             CallableStatement validar = conn.prepareCall(validateCall)) {
            validar.setString(1, dni);
            validar.setString(2, telefono);
            validar.setString(3, correo);
            validar.registerOutParameter(4, Types.INTEGER);
            validar.execute();
            int codigo = validar.getInt(4);
            if (codigo != 0) {
                return codigo; // error por duplicados
            }

            try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.setString(3, rol);
                stmt.setString(4, dni);
                stmt.setString(5, telefono);
                stmt.setString(6, correo);
                stmt.executeUpdate();
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -2; // error inesperado
        }
    }
}