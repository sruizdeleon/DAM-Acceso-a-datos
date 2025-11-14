// Importamos las librerías y dependencias
import org.json.*;
import java.io.*;
import java.util.*;

public class Ejemplo01 {
    public static void main(String[] args) {

        // Preparación de datos
        List<Prenda> prendas = Arrays.asList(
            new Prenda("Camiseta", "Azul", 52),
            new Prenda("Pantalón", "Negro", 46)
        );

        // Creamos el objeto JSON raíz
        // Equivalencia { }
        JSONObject root = new JSONObject();

        // Añadimos los campos
        // Equivalencia { propietario: juan, idArmario: 1229 }
        root.put("propietario", "Juan");
        root.put("idArmario", 1229);

        // Creamos el array de prendas
        // Equivalencia []
        JSONArray arrayPrendas = new JSONArray();

        for(Prenda prenda : prendas) {
            // Añadimos las prendas
            JSONObject p = new JSONObject();
            p.put("nombre", prenda.getNombre());
            p.put("color", prenda.getColor());
            p.put("talla", prenda.getTalla());
            // Equivalencia { nombre: nombre, color: color, talla: talla }

            // Añadimos el objeto de prenda creada al array de prendas
            arrayPrendas.put(p);
        }

        root.put("prendas", arrayPrendas);

        try (FileWriter fw = new FileWriter("Clases\\251105\\armario.json")){
            fw.write(root.toString(4));
            

        } catch(Exception e) {
            System.out.println(e);
        }

    }
}
