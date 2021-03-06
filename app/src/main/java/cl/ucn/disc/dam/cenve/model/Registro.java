package cl.ucn.disc.dam.cenve.model;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Representa un registro de ingreso
 * para el sistema de control vehicular
 *
 * @author Kevin Araya Reygada, Jean Cortes Taiba
 */

@DatabaseTable(tableName = "Registro")
public class Registro {

    /**
     * Esquema de la base de datos para Registro
     */
    public static final String ID = "registro_id";
    public static final String PORTERIA = "porteria";
    public static final String FECHA_INGRESO = "fechaIngreso";
    public static final String FECHA_SALIDA = "fechaSalida";
    public static final String VEHICULO = "vehiculo";

    public Registro() {

    }

    public Registro(String porteria, Date fechaIngreso,Date fechaSalida, Vehiculo vehiculo) {
        this.porteria = porteria;
        this.fechaIngreso = fechaIngreso;
        this.fechaIngreso = fechaSalida;
        this.vehiculo = vehiculo;
    }

    /**
     * id de cada registro
     */
    @Getter
    @Setter
    @DatabaseField(generatedId = true,canBeNull = false, columnName = ID)
    private int id;

    /**
     * Porteria por la cual el auto ingresara, este puede ser
     * porteria: Central, Sur y Norte.
     */
    @Getter
    @Setter
    @DatabaseField(columnName = PORTERIA)
    private String porteria;

    /**
     * Fecha de ingreso en la cual el auto ingresara a los estacionamientos
     */
    @Getter
    @Setter
    @DatabaseField(columnName = FECHA_INGRESO)
    private Date fechaIngreso;

    /**
    * Fecha de salida en la cual el auto sale de los estacionamientos
    */
    @Getter
    @Setter
    @DatabaseField(columnName = FECHA_SALIDA, canBeNull = true)
    private Date fechaSalida;

    /**
     * Vehiculo que se esta registrando por su ingreso
     */
    @Getter
    @Setter
    @DatabaseField(canBeNull = false, foreign = true,foreignAutoRefresh = true, columnName = VEHICULO)
    private Vehiculo vehiculo;
}
