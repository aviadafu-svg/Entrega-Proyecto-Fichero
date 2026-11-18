import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSetup {

    public static void inicializarBD() {
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS usuarios (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    username VARCHAR(50) NOT NULL UNIQUE,
                    password VARCHAR(255) NOT NULL,
                    rol VARCHAR(20) NOT NULL DEFAULT 'EMPLEADO',
                    activo BOOLEAN DEFAULT TRUE,
                    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS reservas (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    destino VARCHAR(5) NOT NULL,
                    origen VARCHAR(5) NOT NULL,
                    nombre_pasajero VARCHAR(100) NOT NULL,
                    fecha DATE NOT NULL,
                    localizador VARCHAR(20) DEFAULT '-'
                )
            """);

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS auditoria (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    usuario VARCHAR(50) NOT NULL,
                    accion VARCHAR(255) NOT NULL,
                    detalle TEXT,
                    fecha_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
            """);

            stmt.execute("""
                MERGE INTO usuarios (username, password, rol)
                KEY (username)
                VALUES ('admin', 'admin123', 'ADMIN')
            """);

            System.out.println("Base de datos inicializada.");
        } catch (Exception e) {
            System.out.println("Error BD: " + e.getMessage());
        }
    }
}
