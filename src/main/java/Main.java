import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.Map;

public class Main {
    public static void main(String[] args) {

        DatabaseSetup.inicializarBD();

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public");
        }).start(7000);

        System.out.println("AirTortilla corriendo en http://localhost:7000");


        app.post("/api/login", ctx -> {
            String username = ctx.formParam("username");
            String password = ctx.formParam("password");

            String rol = AuthService.login(username, password);
            if (rol != null) {
                ctx.json(Map.of("ok", true, "username", username, "rol", rol));
            } else {
                ctx.status(401).json(Map.of("ok", false, "mensaje", "Credenciales incorrectas"));
            }
        });

        app.post("/api/reservas/procesar", ctx -> {
            String usuario = ctx.queryParam("usuario");

            GestorReservas gestor = new GestorReservas();
            gestor.leerReservas("reservas_mockaroo_iso.csv");

            if (gestor.hayReservas()) {
                gestor.generarLocalizadores();
                gestor.migrarReservasABD();
                AuditoriaService.registrar(usuario, "MIGRAR_CSV", "Migración CSV a BD completada");
                ctx.json(Map.of("ok", true, "mensaje", "Reservas procesadas y migradas a BD"));
            } else {
                ctx.status(500).json(Map.of("ok", false, "mensaje", "No se pudieron leer las reservas"));
            }
        });

        app.get("/api/reservas", ctx -> {
            String usuario = ctx.queryParam("usuario");
            AuditoriaService.registrar(usuario, "CONSULTA_RESERVAS", "Consulta de reservas desde BD");
            ctx.json(GestorReservas.obtenerReservasDesdeBD());
        });

        app.post("/api/usuarios", ctx -> {
            String adminActual = ctx.formParam("adminActual");
            String username = ctx.formParam("username");
            String password = ctx.formParam("password");
            String rol = ctx.formParam("rol");

            boolean ok = UsuarioService.crearUsuario(username, password, rol, adminActual);
            if (ok) {
                ctx.json(Map.of("ok", true, "mensaje", "Usuario '" + username + "' creado"));
            } else {
                ctx.status(400).json(Map.of("ok", false, "mensaje", "Error al crear usuario (¿ya existe?)"));
            }
        });

        app.get("/api/usuarios", ctx -> {
            ctx.json(UsuarioService.listarUsuarios());
        });

        app.get("/api/auditoria", ctx -> {
            String usuario = ctx.queryParam("usuario");
            AuditoriaService.registrar(usuario, "CONSULTA_AUDITORIA", "Consulta del log de auditoría");
            ctx.json(AuditoriaService.obtenerTodas());
        });
    }
}