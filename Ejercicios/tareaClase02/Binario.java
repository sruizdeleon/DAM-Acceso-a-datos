package ejercicios.tareaClase02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Binario {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String ruta = "Ejercicios\\tareaClase02\\data.bin";

		
		try (
			// Creamos el flujo de salida binario con el file que abre el fichero
			DataOutputStream n = new DataOutputStream(new FileOutputStream(ruta))
			)
		{
			// Registramos un número entero
			n.writeInt(10);

			// Registramos un string en formato UTF
			n.writeUTF("Estoy escribiendo datos");
			
			// Registramos un número decimal			
			n.writeDouble(0.05);
			
			// Avisamos de la escritura correcta
			System.out.println("Datos escritos en ruta: " + ruta);
			
			// Cerramos la transferencia de datos
			n.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error de escritura: "+e);
		}

		try (
				// Abrimos el flujo de datos de lectura y creamos el file que abre el archivo físico
				DataInputStream m = new DataInputStream(new FileInputStream(ruta));
			)
		{
			System.out.println("Leyendo los datos:");
		
			// Leemos e imprimimos el entero
			System.out.println(m.readInt());

			// Leemos e imprimimos el string
			System.out.println(m.readUTF());
			
			// Leemos e imprimimos el double
			System.out.println(m.readDouble());

			// Cerramos la transferencia de datos
			m.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error de lectura: "+e);
		}
		
	}

}