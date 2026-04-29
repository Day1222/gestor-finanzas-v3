package gestor_version3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Clase FileManager
 * 
 * RESPONSABILIDAD:
 * - Gestionar la escritura de datos en fichero de texto.
 * - Guardar un historial de ingresos y gastos.
 * - Añadir persistencia adicional a la base de datos.
 * 
 * RA QUE SE CUMPLEN:
 * ✔ RA2: Uso de ficheros para almacenamiento de información
 */
public class FileManager {

    // Nombre del archivo donde se guarda el historial
    private static final String ARCHIVO = "historial_finanzas.txt";

    /**
     * Guarda un ingreso en el fichero de texto.
     * Añade fecha y hora para registro histórico.
     */
    public static void guardarIngreso(String descripcion, double monto) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO, true))) {

            pw.println("[" + java.time.LocalDateTime.now() + "] INGRESO | "
                    + descripcion + " | " + monto + " €");

        } catch (IOException e) {
            System.out.println("Error al guardar ingreso en fichero: " + e.getMessage());
        }
    }

    /**
     * Guarda un gasto en el fichero de texto.
     * Añade fecha y hora para registro histórico.
     */
    public static void guardarGasto(String descripcion, double monto) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO, true))) {

            pw.println("[" + java.time.LocalDateTime.now() + "] GASTO | "
                    + descripcion + " | " + monto + " €");

        } catch (IOException e) {
            System.out.println("Error al guardar gasto en fichero: " + e.getMessage());
        }
    }
}
