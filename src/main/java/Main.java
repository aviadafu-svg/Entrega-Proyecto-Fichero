import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestorReservas gestor = new GestorReservas();
        String ficheroEntrada = "reservas_mockaroo_iso.csv";
        boolean salir = false;

        System.out.println("==================================================");
        System.out.println("   AirTortilla - Gestión de Reservas");
        System.out.println("==================================================");

        while (!salir) {
            System.out.println("\nMENÚ:");
            System.out.println("1. Procesar reservas y generar fichero");
            System.out.println("2. Consultar fichero generado (modo completo)");
            System.out.println("3. Consultar fichero generado (línea a línea)");
            System.out.println("4. Salir");
            System.out.print("\nElige una opción: ");

            String opcion = sc.nextLine().trim();

            switch (opcion) {
                case "1":
                    gestor.leerReservas(ficheroEntrada);
                    if (gestor.hayReservas()) {
                        gestor.generarLocalizadores();
                        // Llama al método que genera un solo archivo sin filtrar
                        gestor.generarFicheroUnico();
                        System.out.println("\n¡Proceso terminado!");
                    } else {
                        System.out.println("\nNo se pudieron procesar las reservas.");
                    }
                    break;


                case "2":
                    gestor.consultarCompleto();
                    break;

                case "3":
                    gestor.consultarLineaALinea(sc);
                    break;

                case "4":
                    System.out.println("¡Hasta luego!");
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        }
        sc.close();
    }
}