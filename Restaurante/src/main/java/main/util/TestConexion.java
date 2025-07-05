package main.util;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConexion {
    public static void main(String[] args) {
        try {
            Connection conn = BDConnection.getConnection();
            if (conn != null) {
                System.out.println("¡Conexión exitosa!");
            } else {
                System.out.println("No se pudo establecer la conexión.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}