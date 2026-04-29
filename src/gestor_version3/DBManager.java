package gestor_version3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Clase DBManager
 * 
 * RESPONSABILIDAD:
 * - Gestionar la conexión con la base de datos MySQL.
 * - Realizar operaciones CRUD básicas (insertar y consultar).
 * - Servir como capa de acceso a datos del sistema.
 * 
 * PATRÓN UTILIZADO:
 * - Separación de responsabilidades (DAO simple)
 * 
 * RA QUE SE CUMPLEN:
 * ✔ RA6: Conexión a base de datos relacional
 * ✔ RA6: Inserciones (INSERT)
 * ✔ RA6: Consultas (SELECT)
 */
public class DBManager {

    // Datos de conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/gestor_finanzas";
 // Sustituir por credenciales locales antes de ejecutar
    private static final String USER = "TU_USUARIO";
    private static final String PASSWORD = "TU_PASSWORD";

    /**
     * Establece conexión con la base de datos
     */
    private static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Inserta un ingreso en la base de datos
     * RA6: actualización de datos (INSERT)
     */
    public static void insertarIngreso(String descripcion, double monto) {

        String sql = "INSERT INTO ingresos (descripcion, monto) VALUES (?, ?)";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, descripcion);
            ps.setDouble(2, monto);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar ingreso: " + e.getMessage());
        }
    }

    /**
     * Inserta un gasto en la base de datos
     * RA6: actualización de datos (INSERT)
     */
    public static void insertarGasto(String descripcion, double monto) {

        String sql = "INSERT INTO gastos (descripcion, monto) VALUES (?, ?)";

        try (Connection conn = conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, descripcion);
            ps.setDouble(2, monto);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar gasto: " + e.getMessage());
        }
    }

    /**
     * Lista ingresos desde la base de datos
     * RA6: consulta SELECT
     */
    public static void listarIngresos() {

        String sql = "SELECT * FROM ingresos";

        try (Connection conn = conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n--- INGRESOS EN BD ---");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " - " +
                    rs.getString("descripcion") + " - " +
                    rs.getDouble("monto") + " €"
                );
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Lista gastos desde la base de datos
     * RA6: consulta SELECT
     */
    public static void listarGastos() {

        String sql = "SELECT * FROM gastos";

        try (Connection conn = conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n--- GASTOS EN BD ---");

            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " - " +
                    rs.getString("descripcion") + " - " +
                    rs.getDouble("monto") + " €"
                );
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Calcula el balance total desde la base de datos
     * RA6: consulta de agregación (SUM)
     */
    public static double obtenerBalance() {

        double ingresos = 0;
        double gastos = 0;

        try (Connection conn = conectar();
             Statement st = conn.createStatement()) {

            ResultSet rs1 = st.executeQuery("SELECT SUM(monto) FROM ingresos");
            if (rs1.next()) ingresos = rs1.getDouble(1);

            ResultSet rs2 = st.executeQuery("SELECT SUM(monto) FROM gastos");
            if (rs2.next()) gastos = rs2.getDouble(1);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return ingresos - gastos;
    }

    /**
     * Obtiene ingresos como objetos Java
     * RA6: consulta SELECT + transformación a objetos
     */
    public static ArrayList<Ingreso> obtenerIngresos() {

        ArrayList<Ingreso> lista = new ArrayList<>();

        String sql = "SELECT descripcion, monto FROM ingresos";

        try (Connection conn = conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Ingreso(
                        rs.getString("descripcion"),
                        rs.getDouble("monto")
                ));
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return lista;
    }

    /**
     * Obtiene gastos como objetos Java
     * RA6: consulta SELECT + transformación a objetos
     */
    public static ArrayList<Gasto> obtenerGastos() {

        ArrayList<Gasto> lista = new ArrayList<>();

        String sql = "SELECT descripcion, monto FROM gastos";

        try (Connection conn = conectar();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Gasto(
                        rs.getString("descripcion"),
                        rs.getDouble("monto")
                ));
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return lista;
    }
}
