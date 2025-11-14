
public class Prenda {
    private String nombre;
    private String color;
    private int talla;
    
    // Constructor de la clase
    public Prenda(String nombre, String color, int talla) {
        this.nombre = nombre;
        this.color = color;
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }
    public String getNombre() {
        return nombre;
    }
    public int getTalla() {
        return talla;
    }
}
