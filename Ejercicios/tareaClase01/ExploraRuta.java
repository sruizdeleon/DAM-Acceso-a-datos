package ejercicios.tareaClase01;
import java.io.File;

public class ExploraRuta {

    public static void main(String[] args) {

        // Mostramos error si no se proporciona ruta
        if (args.length == 0) {
            System.out.println("ERROR: no has introducido ninguna ruta.");
            return;
        }

        String ruta = args[0];
        File fichero = new File(ruta);

        // Si no existe la ruta
        if (!fichero.exists()) {
            System.out.println("ERROR: La ruta no existe.");
            return;
        }

        // Comprobamos que sea un Fichero
        if (fichero.isFile()) {
            System.out.println("----- RESULTADO -----");
            System.out.println("Es un fichero.");
            System.out.println("Nombre: " + fichero.getName());
            System.out.println("Tamaño (bytes): " + fichero.length());
            System.out.println("Se puede leer: " + (fichero.canRead() ? "Sí" : "No")); // Añadimos un ternario para la lectura
            System.out.println("Se puede escribir: " + (fichero.canWrite() ? "Sí" : "No")); // Añadimos un ternario para la escritura
        } else if (fichero.isDirectory()) { // Comprobamos que sea un Directorio
            System.out.println("Es un directorio.");
            String[] elementos = fichero.list(); // Guardamos el número de ficheros y elementos en el directorio
            if (elementos == null) {
                System.out.println("No se pudo listar el contenido del directorio.");
                return;
            }
            System.out.println("Número de elementos: " + elementos.length);
            System.out.println("Contenido:");
            for (String nombre : elementos) { // Mostramos una lista de nombres de los ficheros
                System.out.println("- " + nombre);
            }
        } else {
            // Si no es ni un fichero ni un archivo mostramos el mensaje
            System.out.println("La ruta existe pero no es un fichero ni un directorio.");
        }
    }
}
