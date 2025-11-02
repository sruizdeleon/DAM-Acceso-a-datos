import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class LeerLinea {
    public static void main(String[] args) {

        String rutaFichero = "D:\\Documentos\\Estudios\\DAM\\03. Segundo curso\\AAD - Acceso a Datos\\00 - Repositorio\\DAM - Acceso a Datos\\Clases\\251022\\ficheroCaracteres.txt";

        File f = new File(rutaFichero);

        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String linea;
            while((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}