package gestor_version3;

import java.util.InputMismatchException;
import java.util.Scanner;


/**
 *  * @authores
	 *  Vera López Fernández
	 *  Yudith Díaz Fernández
	 * 
	 * @version 3.0

 * 
 * 
 * Clase principal del sistema Gestor de Finanzas V3.
 * 
 * RESPONSABILIDAD:
 * - Controlar la interacción con el usuario mediante consola.
 * - Mostrar el menú de opciones.
 * - Recoger datos introducidos por el usuario.
 * - Llamar a la lógica del sistema (GestorFinanzas y DBManager).
 * 
 * RA QUE SE CUMPLEN:
 * ✔ RA5: Entrada y salida por consola (Scanner + System.out)
 */
public class Main {

    // Scanner para leer datos desde teclado (entrada por consola)
    private static Scanner sc = new Scanner(System.in);

    // Instancia del gestor que contiene la lógica del negocio
    private static GestorFinanzas gestor = new GestorFinanzas();

    // Códigos ANSI para colores en consola (mejora visual)
    private static final String AZUL = "\u001B[34m";
    private static final String VERDE = "\033[1;32m";
    private static final String ROJO = "\033[0;31m";
    private static final String PURPURA = "\033[0;35m";
    private static final String NARANJA = "\033[0;33m";
    private static final String CYAN = "\033[0;36m";
    private static final String RESET = "\u001B[0m";

    /**
     * Método principal que inicia la aplicación.
     * Contiene un bucle que mantiene el programa en ejecución
     * hasta que el usuario decide salir.
     */
    public static void main(String[] args) {

        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion();

            switch (opcion) {

                case 1 -> registrarIngreso();
                case 2 -> registrarGasto();

                // Consultas a la base de datos (RA6)
                case 3 -> DBManager.listarIngresos();
                case 4 -> DBManager.listarGastos();

                // Obtención de balance desde BD (consulta SQL)
                case 5 -> System.out.println(
                        NARANJA + "\n💼 BALANCE ACTUAL: " +
                        DBManager.obtenerBalance() + " €" + RESET
                );

                case 6 -> System.out.println(PURPURA + "👋 Saliendo del programa..." + RESET);

                default -> System.out.println(ROJO + "⚠ Opción no válida." + RESET);
            }

        } while (opcion != 6);

        sc.close(); // Cierre del Scanner
    }

    /**
     * Muestra el menú principal del sistema.
     * (Salida por consola → RA5)
     */
    private static void mostrarMenu() {

        System.out.println(VERDE + "\n===============================");
        System.out.println("   GESTOR DE FINANZAS V3");
        System.out.println("===============================" + RESET);

        System.out.println(AZUL + "1. Registrar ingreso");
        System.out.println("2. Registrar gasto");

        System.out.println(CYAN + "3. Listar ingresos (BD)");
        System.out.println("4. Listar gastos (BD)");

        System.out.println(NARANJA + "5. Mostrar balance");

        System.out.println(PURPURA + "6. Salir" + RESET);

        System.out.print("👉 Selecciona una opción: ");
    }

    /**
     * Lee la opción introducida por el usuario.
     * Controla errores si el usuario introduce datos no válidos.
     * (RA5: entrada por consola)
     */
    private static int leerOpcion() {
        try {
            return sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println(ROJO + "⚠ Debes introducir un número." + RESET);
            sc.nextLine(); // limpiar buffer
            return 0;
        }
    }

    /**
     * Permite registrar un ingreso.
     * Recoge datos por consola y llama a la lógica del sistema.
     * 
     * RA5: entrada de datos
     * RA6: posteriormente se guarda en BD a través de GestorFinanzas
     */
    private static void registrarIngreso() {

        try {
            sc.nextLine();

            System.out.print(CYAN + "💬 Descripción: " + RESET);
            String desc = sc.nextLine();

            System.out.print(CYAN + "💰 Monto: " + RESET);
            double monto = sc.nextDouble();

            gestor.registrarIngreso(desc, monto);

            System.out.println(PURPURA + "✅ Ingreso registrado correctamente." + RESET);

        } catch (InputMismatchException e) {
            System.out.println(ROJO + "⚠ Error de entrada." + RESET);
            sc.nextLine();
        }
    }

    /**
     * Permite registrar un gasto.
     * Valida el resultado de la operación.
     * 
     * RA5: entrada de datos
     * RA6: se guarda en BD si es válido
     */
    private static void registrarGasto() {

        try {
            sc.nextLine();

            System.out.print(CYAN + "💬 Descripción: " + RESET);
            String desc = sc.nextLine();

            System.out.print(CYAN + "💸 Monto: " + RESET);
            double monto = sc.nextDouble();

            boolean ok = gestor.registrarGasto(desc, monto);

            if (ok) {
                System.out.println(PURPURA + "✅ Gasto registrado correctamente." + RESET);
            } else {
                System.out.println(ROJO + "⚠ No hay saldo suficiente." + RESET);
            }

        } catch (InputMismatchException e) {
            System.out.println(ROJO + "⚠ Error de entrada." + RESET);
            sc.nextLine();
        }
    }
}
