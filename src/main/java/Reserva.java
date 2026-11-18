public class Reserva {
    private String destino;
    private String origen;
    private String nombre;
    private String fecha;
    private String localizador;

    private int id;

    public Reserva() {}

    public Reserva(String destino, String origen, String nombre, String fecha) {
        this.destino = destino;
        this.origen = origen;
        this.nombre = nombre;
        this.fecha = fecha;
        this.localizador = "-";
    }

    public Reserva(int id, String destino, String origen, String nombre, String fecha, String localizador) {
        this.id = id;
        this.destino = destino;
        this.origen = origen;
        this.nombre = nombre;
        this.fecha = fecha;
        this.localizador = localizador;
    }

    public String getDestino() { return destino; }
    public String getOrigen() { return origen; }
    public String getNombre() { return nombre; }
    public String getFecha() { return fecha; }
    public String getLocalizador() { return localizador; }

    public int getId() { return id; }

    public void setLocalizador(String localizador) {
        this.localizador = localizador;
    }

    public boolean coincidePasajero(Reserva otra) {
        return this.nombre.equals(otra.getNombre()) &&
                this.origen.equals(otra.getOrigen()) &&
                this.destino.equals(otra.getDestino());
    }

    @Override
    public String toString() {
        return destino + "," + origen + "," + nombre + "," + fecha + "," + localizador;
    }
}