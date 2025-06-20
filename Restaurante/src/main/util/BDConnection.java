package main.util;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BDConnection {
    private static Properties loadProperties() throws SQLException {
        Properties props = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/resources/config.properties")) {
            props.load(input);

            // Verificar que todas las propiedades necesarias existen
            if (!props.containsKey("db.url") ||
                    !props.containsKey("db.user") ||
                    !props.containsKey("db.password")) {
                throw new SQLException("Faltan propiedades de configuración necesarias");
            }

            return props;
        } catch (IOException e) {
            throw new SQLException("No se pudo cargar el archivo de configuración", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Properties props = loadProperties();
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se pudo cargar el driver de MySQL", e);
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}