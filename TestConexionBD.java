import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConexionBD {

    private static final String URL = "jdbc:mysql://localhost:3306/base_inscripciones";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        // Intentar conectar a la base de datos
        try (Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (conexion != null) {
                System.out.println("¡Conexión exitosa a la base de datos!");
            }
        } catch (SQLException e) {
            System.out.println("Error de conexion malo: " + e.getMessage());
        }
    }
}
