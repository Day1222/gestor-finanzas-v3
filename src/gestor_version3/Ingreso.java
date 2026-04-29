package gestor_version3;

/**
 * Clase que representa un ingreso en el sistema.
 * 
 * RESPONSABILIDAD:
 * - Representar una transacción de tipo ingreso.
 * - Heredar los atributos y comportamientos de la clase Transaccion.
 * 
 * RA QUE SE CUMPLEN:
 * ✔ RA4: Uso de herencia (Ingreso hereda de Transaccion)
 */
public class Ingreso extends Transaccion {

    /**
     * Constructor de la clase Ingreso.
     * 
     * Utiliza el constructor de la clase padre (Transaccion)
     * mediante la palabra clave super para inicializar los atributos.
     */
    public Ingreso(String descripcion, double monto) {
        super(descripcion, monto);
    }
}
