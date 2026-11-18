import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuarioService {

    public static boolean crearUsuario(String username, String password, String rol, String adminActual) {
        String sql = "INSERT INTO usuarios (username, password, rol) VALUES (?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, rol);
            ps.executeUpdate();
            AuditoriaService.registrar(adminActual, "ALTA_USUARIO",
                    "Creado usuario '" + username + "' con rol " + rol);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static List<Map<String, String>> listarUsuarios() {
        List<Map<String, String>> lista = new ArrayList<>();
        String sql = "SELECT id, username, rol, activo, fecha_creacion FROM usuarios";
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Map<String, String> u = new HashMap<>();
                u.put("id", String.valueOf(rs.getInt("id")));
                u.put("username", rs.getString("username"));
                u.put("rol", rs.getString("rol"));
                u.put("activo", String.valueOf(rs.getBoolean("activo")));
                u.put("fechaCreacion", rs.getTimestamp("fecha_creacion").toString());
                lista.add(u);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }
}
