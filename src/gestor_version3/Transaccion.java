package gestor_version3;

/**
 * Clase base que representa una transacción genérica.
 * Puede ser un ingreso o un gasto.
 * 
 * RESPONSABILIDAD:
 * - Definir los atributos comunes a todas las transacciones.
 * - Servir como clase padre para aplicar herencia.
 * 
 * RA QUE SE CUMPLEN:
 * ✔ RA4: Uso de herencia (clase base que será extendida por Ingreso y Gasto)
 */
public class Transaccion {

    // Atributos protegidos (accesibles desde clases hijas como Ingreso y Gasto)
    protected String descripcion;
    protected double monto;

    /**
     * Constructor de la clase Transaccion.
     * Inicializa los atributos comunes.
     * 
     * Este constructor será utilizado por las clases hijas mediante super().
     */
    public Transaccion(String descripcion, double monto) {
        this.descripcion = descripcion;
        this.monto = monto;
    }

    /**
     * Devuelve la descripción de la transacción.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Devuelve el monto de la transacción.
     */
    public double getMonto() {
        return monto;
    }
}
