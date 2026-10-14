public class Reserva {
    private String destino;
    private String origen;
    private String nombre;
    private String fecha;
    private String localizador;

    public Reserva(String destino, String origen, String nombre, String fecha) {
        this.destino = destino;
        this.origen = origen;
        this.nombre = nombre;
        this.fecha = fecha;
        this.localizador = "-";
    }

    public String getDestino() { return destino; }
    public String getOrigen() { return origen; }
    public String getNombre() { return nombre; }
    public String getFecha() { return fecha; }
    public String getLocalizador() { return localizador; }

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