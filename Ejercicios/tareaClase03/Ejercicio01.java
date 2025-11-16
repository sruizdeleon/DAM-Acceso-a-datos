package ejercicios.tareaClase03;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Ejercicio01 {
    public static void main(String[] args) {

        // Creamos un array de películas y añadimos unas de pruebas
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        peliculas.add(new Pelicula("Inception", "Christopher Nolan", 2010));
        peliculas.add(new Pelicula("Pulp Fiction", "Quentin Tarantino", 1994));
        peliculas.add(new Pelicula("Interstellar", "Christopher Nolan", 2014));
        peliculas.add(new Pelicula("Jurasic Park", "Steaven Spealver", 1993));

        // Creamos el json
        JSONArray jsonArray = new JSONArray();

        // Añadimos toda la información de mi array en el json
        for (Pelicula p : peliculas) {
            JSONObject obj = new JSONObject();
            obj.put("titulo", p.getTitulo());
            obj.put("director", p.getDirector());
            obj.put("anio", p.getAnio());
            jsonArray.put(obj);
        }
        
        // Creamos el archivo json en la ruta de la carpeta
        try (FileWriter file = new FileWriter(".\\Ejercicios\\tareaClase03\\peliculas.json")) {
            // Escribimos el json en el archivo json
            file.write(jsonArray.toString(4)); // formato bonito
            System.out.println("Archivo peliculas.json creado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}