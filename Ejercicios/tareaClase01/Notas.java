package Ejercicios.tareaClase01;

import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class Notas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] frases = new String[3];

        System.out.println("Introduce 3 frases (pulsa Enter tras cada una):");
        for (int i = 0; i < 3; i++) {
            System.out.print((i + 1) + "ª frase: ");
            frases[i] = scanner.nextLine();
        }
        scanner.close();

        Path fichero = Paths.get("frases.txt");

        // Escribir las frases en frases.txt
        try (BufferedWriter writer = Files.newBufferedWriter(fichero)) {
            for (String frase : frases) {
                writer.write(frase);
                writer.newLine();
            }
            System.out.println("Frases guardadas en " + fichero.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error al escribir en el fichero: " + e.getMessage());
            return;
        }

        // Leer el fichero y mostrar cada línea con número
        System.out.println("\nContenido de frases.txt:");
        try (BufferedReader reader = Files.newBufferedReader(fichero)) {
            String linea;
            int numero = 1;
            while ((linea = reader.readLine()) != null) {
                System.out.println(numero + ": " + linea);
                numero++;
            }
        } catch (IOException e) {
            System.err.println("Error al leer el fichero: " + e.getMessage());
        }
    }
}