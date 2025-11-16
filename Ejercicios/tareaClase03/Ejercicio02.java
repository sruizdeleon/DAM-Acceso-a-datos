package ejercicios.tareaClase03;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Ejercicio02 {
    public static void main(String[] args) {
        try {
            // Guardamos el string del archivo son
            String contenido = new String(Files.readAllBytes(Paths.get(".\\Ejercicios\\tareaClase03\\peliculas.json")));

            // Parseamos el contenido a json
            JSONArray jsonArray = new JSONArray(contenido);

            // Leemos el json nodo a nodo y lo vamos imprimiendo por pantalla
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                System.out.println("Título: " + obj.getString("titulo"));
                System.out.println("Director: " + obj.getString("director"));
                System.out.println("Año: " + obj.getInt("anio"));
                System.out.println("-------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
