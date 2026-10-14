import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorReservas {
    private ArrayList<Reserva> reservas = new ArrayList<>();
    private String rutaFicheroGenerado = null;

    public void leerReservas(String nombreFichero) {
        reservas.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreFichero))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length >= 4) {
                    reservas.add(new Reserva(partes[0].trim(), partes[1].trim(), partes[2].trim(), partes[3].trim()));
                }
            }
            System.out.println("Se han leido " + reservas.size() + " reservas.");
        } catch (Exception e) {
            System.out.println("Error al leer el fichero: " + nombreFichero);
        }
    }

    public void generarLocalizadores() {
        int contador = 1;
        System.out.println("\nBuscando coincidencias...");
        for (int i = 0; i < reservas.size(); i++) {
            Reserva r1 = reservas.get(i);
            if (!r1.getLocalizador().equals("-")) continue;

            String loc = "LOC-" + String.format("%03d", contador);
            boolean hayCoincidencia = false;

            for (int j = i + 1; j < reservas.size(); j++) {
                Reserva r2 = reservas.get(j);
                if (r1.coincidePasajero(r2)) {
                    r2.setLocalizador(loc);
                    hayCoincidencia = true;
                }
            }
            if (hayCoincidencia) {
                r1.setLocalizador(loc);
                contador++;
            }
        }
    }

    public void generarFicheroUnico() {
        System.out.println("\nGenerando fichero...");
        new File("salida").mkdir();

        if (reservas.isEmpty()) {
            System.out.println("No hay reservas para guardar.");
            return;
        }

        Reserva primera = reservas.get(0);
        String iso = primera.getDestino().toUpperCase();
        String fecha = primera.getFecha().replace("-", "_");
        rutaFicheroGenerado = "salida/AirTortilla_" + iso + "_" + fecha + ".csv";

        try (PrintWriter pw = new PrintWriter(new FileWriter(rutaFicheroGenerado))) {
            for (Reserva r : reservas) {
                pw.println(r.toString());
            }
            System.out.println("  Generado: " + rutaFicheroGenerado);
        } catch (IOException e) {
            System.out.println("Error al crear el fichero.");
        }
    }

    public void consultarCompleto() {
        if (rutaFicheroGenerado == null) {
            System.out.println("\nPrimero debes procesar las reservas (Opción 1).");
            return;
        }
        System.out.println("\n--- CONTENIDO COMPLETO (" + rutaFicheroGenerado + ") ---");
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFicheroGenerado))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
    }
    public void consultarLineaALinea(Scanner sc) {
        if (rutaFicheroGenerado == null) {
            System.out.println("\nPrimero debes procesar las reservas (Opción 1).");
            return;
        }
        System.out.println("\n--- CONSULTA LINEA A LINEA (" + rutaFicheroGenerado + ") ---");
        try (BufferedReader br = new BufferedReader(new FileReader(rutaFicheroGenerado))) {
            String linea;
            int numLinea = 1;
            while ((linea = br.readLine()) != null) {
                System.out.println("Linea " + numLinea + ": " + linea);
                System.out.print("  (Pulsa Enter para continuar...)");
                sc.nextLine();
                numLinea++;
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero.");
        }
    }

    public boolean hayReservas() {
        return !reservas.isEmpty();
    }
}