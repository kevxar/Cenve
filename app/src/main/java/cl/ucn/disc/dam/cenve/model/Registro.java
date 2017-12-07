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
 * @author Kevin Araya Reygada
 */

@Builder
@DatabaseTable
public class Registro {

    /**
     * Esquema de la base de datos para Registro
     */
    public static final String ID = "_id";
    public static final String PORTERIA = "porteria";
    public static final String FECHA_INGRESO = "fechaIngreso";
    public static final String VEHICULO = "vehiculo";

    /**
     * ID de cada registro
     */
    @Getter
    @Setter
    @DatabaseField(generatedId = true, columnName = ID)
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
     * Vehiculo que se esta registrando por su ingreso
     */
    @Getter
    @Setter
    @DatabaseField(foreign = true, columnName = VEHICULO)
    private Vehiculo vehiculo;
}
