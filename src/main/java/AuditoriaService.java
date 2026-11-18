import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuditoriaService {

    public static void registrar(String usuario, String accion, String detalle) {
        String sql = "INSERT INTO auditoria (usuario, accion, detalle) VALUES (?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario);
            ps.setString(2, accion);
            ps.setString(3, detalle);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error auditoría: " + e.getMessage());
        }
    }

    public static List<Map<String, String>> obtenerTodas() {
        List<Map<String, String>> lista = new ArrayList<>();
        String sql = "SELECT * FROM auditoria ORDER BY fecha_hora DESC";
        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Map<String, String> fila = new HashMap<>();
                fila.put("id", String.valueOf(rs.getInt("id")));
                fila.put("usuario", rs.getString("usuario"));
                fila.put("accion", rs.getString("accion"));
                fila.put("detalle", rs.getString("detalle"));
                fila.put("fechaHora", rs.getTimestamp("fecha_hora").toString());
                lista.add(fila);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return lista;
    }
}