package gestor_version3;

import java.util.ArrayList;

/**
 * Clase GestorFinanzas
 * 
 * RESPONSABILIDAD:
 * - Gestionar la lógica principal del sistema.
 * - Controlar ingresos y gastos en memoria.
 * - Validar reglas de negocio (saldo, montos válidos).
 * - Conectar la lógica con la base de datos y ficheros.
 * 
 * RA QUE SE CUMPLEN:
 * ✔ RA4: Uso de herencia (trabaja con Ingreso y Gasto)
 * ✔ RA5: Entrada/salida por consola (mensajes de estado)
 * ✔ RA6: Interacción con BD y persistencia
 */
public class GestorFinanzas {

    // Listas en memoria para almacenar datos de la sesión
    private ArrayList<Ingreso> ingresos;
    private ArrayList<Gasto> gastos;

    /**
     * Constructor
     * Inicializa listas y carga datos desde la base de datos
     */
    public GestorFinanzas() {
        ingresos = new ArrayList<>();
        gastos = new ArrayList<>();

        // Carga inicial desde BD (sincronización con datos existentes)
        ingresos = DBManager.obtenerIngresos();
        gastos = DBManager.obtenerGastos();
    }

    /**
     * Registra un ingreso
     * - Valida datos
     * - Guarda en memoria
     * - Guarda en BD
     * - Guarda en fichero
     * 
     * RA6: inserción en base de datos
     * RA2: escritura en fichero
     */
    public void registrarIngreso(String descripcion, double monto) {

        if (monto <= 0) {
            System.out.println("⚠ El ingreso debe ser mayor que 0.");
            return;
        }

        Ingreso ingreso = new Ingreso(descripcion, monto);
        ingresos.add(ingreso);

        DBManager.insertarIngreso(descripcion, monto);
        FileManager.guardarIngreso(descripcion, monto);
    }

    /**
     * Registra un gasto
     * - Valida datos
     * - Comprueba saldo disponible
     * - Evita gastos sin ingresos
     * - Guarda en memoria, BD y fichero
     * 
     * RA6: inserción en base de datos
     * RA2: escritura en fichero
     */
    public boolean registrarGasto(String descripcion, double monto) {

        if (monto <= 0) {
            System.out.println("⚠ El gasto debe ser mayor que 0.");
            return false;
        }

        double balance = calcularBalance();

        // Validación de negocio
        if (ingresos.isEmpty()) {
            System.out.println("⚠ No puedes registrar gastos sin ingresos.");
            return false;
        }

        if (balance <= 0) {
            System.out.println("⚠ No tienes saldo disponible.");
            return false;
        }

        if (monto > balance) {
            System.out.println("⚠ El gasto supera tu saldo disponible.");
            return false;
        }

        Gasto gasto = new Gasto(descripcion, monto);
        gastos.add(gasto);

        DBManager.insertarGasto(descripcion, monto);
        FileManager.guardarGasto(descripcion, monto);

        return true;
    }

    /**
     * Calcula total de ingresos
     */
    public double getTotalIngresos() {
        double total = 0;

        for (Ingreso i : ingresos) {
            total += i.getMonto();
        }

        return total;
    }

    /**
     * Calcula total de gastos
     */
    public double getTotalGastos() {
        double total = 0;

        for (Gasto g : gastos) {
            total += g.getMonto();
        }

        return total;
    }

    /**
     * Calcula el balance actual
     */
    public double calcularBalance() {
        return getTotalIngresos() - getTotalGastos();
    }

    /**
     * Devuelve lista de ingresos
     */
    public ArrayList<Ingreso> getIngresos() {
        return ingresos;
    }

    /**
     * Devuelve lista de gastos
     */
    public ArrayList<Gasto> getGastos() {
        return gastos;
    }

    /**
     * Muestra ingresos por consola
     */
    public void mostrarIngresos() {

        System.out.println("\n--- INGRESOS ---");

        if (ingresos.isEmpty()) {
            System.out.println("No hay ingresos registrados.");
            return;
        }

        for (Ingreso i : ingresos) {
            System.out.println(i.getDescripcion() + " - " + i.getMonto() + " €");
        }
    }

    /**
     * Muestra gastos por consola
     */
    public void mostrarGastos() {

        System.out.println("\n--- GASTOS ---");

        if (gastos.isEmpty()) {
            System.out.println("No hay gastos registrados.");
            return;
        }

        for (Gasto g : gastos) {
            System.out.println(g.getDescripcion() + " - " + g.getMonto() + " €");
        }
    }
}

