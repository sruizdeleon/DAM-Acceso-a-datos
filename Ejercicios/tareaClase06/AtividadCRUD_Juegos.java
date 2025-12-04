package ejercicios.tareaClase06;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Programar en Java una clase que:
 *   * Consulte y muestre todos los juegos de la tabla JUEGO.
 *   * Inserte un nuevo juego.
 *   * Vuelva a consultar los datos para comprobar la inserción.
 *   * Actualice la puntuación de ese nuevo juego.
 *   * Vuelva a consultar para comprobar la actualización.
 *   * Elimine algún juego (por nombre o por id).
 *   * Haga una última consulta para ver el estado final de la tabla.
 */

public class AtividadCRUD_Juegos {

    // Enlace a conexión con la BBDD
    private static final String URL = "jdbc:h2:./ejercicios/tareaClase06/BDJuegos;AUTO_SERVER=TRUE";

    // Usuario y contraseña de la BBDD
    private static final String USER = "sa";
    private static final String PSSWRD = "";

    public static void main(String[] args) {
        
        try {
            // Cargamos el Driver de h2
            Class.forName("org.h2.Driver");

            // Conectamos y guardamos la conexión con BD
            try (Connection conn = DriverManager.getConnection(URL, USER, PSSWRD)) {
                System.out.println("====== Conectado a BD Juegos ======");

                // Métodos a ejecutar
                mostrarTodos(conn);

                // Preparar dos juegos para insertar
                Juego juego1 = new Juego("Call Of Dutty", "Shooter", 9.7);
                Juego juego2 = new Juego("Minecraft", "Farming", 9.2);
                
                // Insertamos dos juegos
                insertar(conn, juego1);
                insertar(conn, juego2);

                // Actualizar puntuacion por el nombre
                actualizarPuntuacionPorNombre(conn, "Call Of Dutty", 7.5);

                // Mostrar juegos por nombre
                mostrarPorNombre(conn, "Call Of Dutty");

                // Borrar juego por nombre
                borrarPorNombre(conn, "Call Of Dutty");

                // Mostrar juegos por nombre
                mostrarPorNombre(conn, "Call Of Dutty");


            } catch (SQLException e) {
                System.out.println("ERROR: Error en la base de datos");
            }


        } catch (ClassNotFoundException e) {
            System.out.println("ERROR: Error en la clase");
        }

    }

    /**
     * mostrarTodos
     * @param conn conexión a BBDD
     * @throws SQLException
     */
    private static void mostrarTodos(Connection conn) throws SQLException {
        // Creamos la consulta
        String query = "SELECT ID, NOMBRE, GENERO, PUNTUACION FROM JUEGO";

        // Creamos el estado
        try(Statement st = conn.createStatement();

            // Ejecutamos y guardamos la consulta
            ResultSet rs = st.executeQuery(query)) {

                System.out.println("====== Mostrar todos ======");
                while (rs.next()) {
                    // Guardamos el resultado
                    Juego juego = new Juego(
                        rs.getString("NOMBRE"),
                        rs.getString("GENERO"),
                        rs.getDouble("PUNTUACION"),
                        rs.getInt("ID")
                    );
                    // Imprimir fila
                    juego.print();
                }
            }

    }

    /**
     * insertarJuego
     * @param conn conexión a BBDD
     * @param juego pasar un objeto Juego
     * @throws SQLException
     */
    private static void insertar(Connection conn, Juego juego) throws SQLException {

        // Usamos Placeholders para evitar inyección
        String query = """
                INSERT INTO JUEGO (NOMBRE, GENERO, PUNTUACION)
                VALUES (?, ?, ?)
                """;
        
        try (PreparedStatement pst = conn.prepareStatement(query)) {

            // Rellenamos los placeholder
            pst.setString(1, juego.getNombre());
            pst.setString(2, juego.getGenero());
            pst.setDouble(3, juego.getPuntuacion());

            // Ejecutamos la query
            int filas = pst.executeUpdate();

            // Insercción y si no error
            if(filas == 1) {
                System.out.println("====== Insercción de "+juego.getNombre()+" realizada correctamente ======");
            } else {
                System.out.println("ERROR: insercción no realizada filas afectadas "+filas);
            }

        }



    }

    /**
     * actualizarPuntuacionPorNombre
     * @param conn conexión a BBDD
     * @param nombreJuego nombre del juego a modificar
     * @param nuevaPuntuacion nueva puntuacion a sustituir
     * @throws SQLException
     */
    private static void actualizarPuntuacionPorNombre(Connection conn, String nombreJuego, double nuevaPuntuacion) throws SQLException {

        // Query de actualización
        String query = """
                UPDATE JUEGO
                SET PUNTUACION = ?
                WHERE NOMBRE = ?
                """;

        // Cramos el Statement
        try(PreparedStatement pst = conn.prepareStatement(query)){
            pst.setDouble(1, nuevaPuntuacion);
            pst.setString(2, nombreJuego);

            // Ejecutamos la query
            int filas = pst.executeUpdate();

            // Si no hay filas error
            if(filas == 0) {
                System.out.println("ERROR: no se encontró ningún juego "+nombreJuego+" para actualizar.");
            } else {
                System.out.println("====== Puntuación de "+nombreJuego+" actualizada correctamente a "+nuevaPuntuacion+" ======");
            }
        }

    }
    
    /**
     * mostrarPorNombre
     * @param conn conexión a BBDD
     * @param nombreAbuscar nombre a buscar en la base de datos
     * @throws SQLException
     */
    private static void mostrarPorNombre(Connection conn, String nombreAbuscar) throws SQLException {

        // Creamos la consulta
        String query = """
            SELECT ID, NOMBRE, GENERO, PUNTUACION FROM JUEGO WHERE NOMBRE = ?;
        """;

        // Creamos el estado
        try(PreparedStatement pst = conn.prepareStatement(query)) {

            // Insertamos el placeholder
            pst.setString(1, nombreAbuscar);
            
            try(ResultSet rs = pst.executeQuery()) {
                boolean hayResultados = false;
                System.out.println("====== Mostrar juegos por nombre: "+nombreAbuscar+" ======");
                while (rs.next()) {
                    // Si hay resultado cambiamos a true
                    hayResultados = true;
                    
                    // Guardamos el Juego
                    Juego juego = new Juego(
                        rs.getString("NOMBRE"),
                        rs.getString("GENERO"),
                        rs.getDouble("PUNTUACION"),
                        rs.getInt("ID")
                    );
                    // Lo imprimimos
                    juego.print();
                }

                if(!hayResultados){
                    System.out.println("ERROR: No hay resultados encontrados con el nombre "+nombreAbuscar);
                }
            }
        }   

    }

    /**
     * borrarPorNombre
     * @param conn conexión a BBDD
     * @param nombreJuego nombre del juego a borrar
     * @throws SQLException
     */
    private static void borrarPorNombre(Connection conn, String nombreJuego) throws SQLException {

        // Query
        String query = """
                DELETE JUEGO
                WHERE NOMBRE = ?
                """;

        try(PreparedStatement pst = conn.prepareStatement(query)){
            pst.setString(1, nombreJuego);

            // Ejecutamos la query de borrado
            int filas = pst.executeUpdate();

            // Si no hay filas no se ha eliminado
            if(filas == 0) {
                System.out.println("ERROR: no se encontró ningún juego llamado "+nombreJuego+" para borrar.");
            } else {
                System.out.println("====== Juego: "+nombreJuego+" eliminado correctamente ======");
            }
        }

    }

}
