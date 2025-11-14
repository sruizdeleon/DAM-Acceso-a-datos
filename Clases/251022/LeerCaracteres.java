import java.io.File;
import java.io.FileReader;

public class LeerCaracteres {
    public static void main(String[] args) {

        String rutaFichero = "D:\\Documentos\\Estudios\\DAM\\03. Segundo curso\\AAD - Acceso a Datos\\00 - Repositorio\\DAM - Acceso a Datos\\Clases\\251022\\ficheroCaracteres.txt";

        File f = new File(rutaFichero);

        try {
            FileReader fr = new FileReader(f);
            int c;
            while((c = fr.read()) != -1) {
                char letra = (char) c;
                System.out.print(letra);
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}
