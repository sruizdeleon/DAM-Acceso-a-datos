import java.io.File;

public class InfoFichero {
    public static void main(String[] args) {
        String rutaFichero = "D:\\Documentos\\Estudios\\DAM\\03. Segundo curso\\AAD - Acceso a Datos\\00 - Repositorio\\DAM - Acceso a Datos\\Clases\\251022";
        
        File f = new File(rutaFichero);
        
        if(f.exists()) {
            System.out.println("El fichero o directorio existe.");
            if(f.isFile()) {
                System.out.println("Es un fichero.");
                System.out.println("Nombre: " + f.getName());
                System.out.println("Ruta absoluta: " + f.getAbsolutePath());
                System.out.println("Tamaño en bytes: " + f.length() + " bytes");
            }
            if(f.isDirectory()) {
                System.out.println("Es un directorio.");
                String[] ficheros = f.list();
                for (String fichero : ficheros) {
                    System.out.println(" - " + fichero);
                }
            }
        } else {
            System.out.println("El fichero o directorio no existe.");
        }

    }
}
