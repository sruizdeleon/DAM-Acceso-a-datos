package ejercicios.tareaClase06;

public class Juego {
    private int id;
    private String nombre;
    private String genero;
    private double puntuacion;

    public Juego(String nombre, String genero, double puntuacion) {
        this.nombre = nombre;
        this.genero = genero;
        this.puntuacion = puntuacion;
    }

    public Juego(String nombre, String genero, double puntuacion, int id) {
        this.nombre = nombre;
        this.genero = genero;
        this.puntuacion = puntuacion;
        this.id = id;
    }

    public void print() {
        System.out.println("["+ id + "] "+ nombre + " | "+ genero +" | "+puntuacion);
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public double getPuntuacion() {
        return puntuacion;
    }

    // SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setPuntuacion(double puntuacion) {
        this.puntuacion = puntuacion;
    }
}
