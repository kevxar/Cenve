package cl.ucn.disc.dam.cenve.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Representa un vehiculo a ingresar
 * para el sistema de control vehicular
 *
 * @author Kevin Araya Reygada, Jean Cortes Taiba
 */

@DatabaseTable(tableName = "Vehiculo")
public class Vehiculo {

    /**
     * Esquema de la base de datos para Vehiculo
     */
    public static final String ID = "vehiculo_id";
    public static final String PATENTE = "patente";
    public static final String MARCA = "marca";
    public static final String COLOR = "color";
    public static final String MODELO = "modelo";
    public static final String ANIO = "anio";
    public static final String DESCRIPCION = "descripcion";
    public static final String CODIGO_ESTACIONAMIENTO = "codigo_estacionamiento";
    public static final String PERSONA = "persona";

    public Vehiculo() {

    }

    public Vehiculo(String patente, String marca, String color, String modelo, String anio,
                    String descripcion, String codigoEstacionamiento, Persona persona) {
        this.patente = patente;
        this.marca = marca;
        this.color = color;
        this.modelo = modelo;
        this.anio = anio;
        this.descripcion = descripcion;
        this.codigoEstacionamiento = codigoEstacionamiento;
        this.persona = persona;
    }

    /**
     * id de cada Vehiculo
     */
    @Setter
    @Getter
    @DatabaseField(generatedId = true, columnName = ID)
    private int id;

    /**
     * patente del Vehiculo
     */
    @Setter
    @Getter
    @DatabaseField(columnName = PATENTE)
    private String patente;

    /**
     * marca del Vehiculo
     */
    @Setter
    @Getter
    @DatabaseField(columnName = MARCA)
    private String marca;

    /**
     * color del Vehiculo
     */
    @Setter
    @Getter
    @DatabaseField(columnName = COLOR)
    private String color;

    /**
     * modelo del Vehiculo
     */
    @Setter
    @Getter
    @DatabaseField(columnName = MODELO)
    private String modelo;

    /**
     * anio del Vehiculo
     */
    @Setter
    @Getter
    @DatabaseField(columnName = ANIO)
    private String anio;

    /**
     * descripcion del responsable del Vehiculo
     */
    @Setter
    @Getter
    @DatabaseField(columnName = DESCRIPCION)
    private String descripcion;

    /**
     * codigo del estacionamiento del Vehiculo
     */
    @Setter
    @Getter
    @DatabaseField(columnName = CODIGO_ESTACIONAMIENTO)
    private String codigoEstacionamiento;

    /**
     * dueño o responsable del Vehiculo
     */
    @Setter
    @Getter
    @DatabaseField(foreign = true, columnName = PERSONA)
    private Persona persona;

}
