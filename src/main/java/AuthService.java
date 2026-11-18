import java.sql.*;

public class AuthService {

    public static String login(String username, String password) {
        String sql = "SELECT rol FROM usuarios WHERE username = ? AND password = ? AND activo = TRUE";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                AuditoriaService.registrar(username, "LOGIN", "Inicio de sesión exitoso");
                return rs.getString("rol");
            } else {
                AuditoriaService.registrar(username, "LOGIN_FALLIDO", "Intento de login fallido");
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }
}
