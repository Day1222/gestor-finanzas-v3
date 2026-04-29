package gestor_version3;

/**
 * Clase que representa un gasto dentro del sistema.
 * 
 * RESPONSABILIDAD:
 * - Modelar una transacción de tipo gasto.
 * - Heredar atributos comunes desde la clase Transaccion.
 * 
 * RA QUE SE CUMPLEN:
 * ✔ RA4: Uso de herencia (Gasto hereda de Transaccion)
 */
public class Gasto extends Transaccion {

    /**
     * Constructor de la clase Gasto.
     * 
     * Utiliza el constructor de la clase padre (Transaccion)
     * mediante super() para inicializar los atributos.
     */
    public Gasto(String descripcion, double monto) {
        super(descripcion, monto);
    }
}
