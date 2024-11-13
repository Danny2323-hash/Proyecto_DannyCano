import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/base_inscripciones"; // Cambia la URL según tu configuración
    private static final String USER = "root";  // Usuario de MySQL
    private static final String PASSWORD = "root"; // Contraseña de MySQL

    public static Connection getConnection() throws SQLException {
        try {
         
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("Error de conexión a la base de datos: " + e.getMessage());
        }
    }
}
