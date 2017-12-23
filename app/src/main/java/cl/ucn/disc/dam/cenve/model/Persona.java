package cl.ucn.disc.dam.cenve.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


/**
 * Representa una persona
 * para el sistema de control vehicular
 *
 * @author Kevin Araya Reygada
 */

@DatabaseTable(tableName = "Persona")
public class Persona{

    /**
     * Esquema de la base de datos para Persona
     */
    public static final String RUT = "rut";
    public static final String NOMBRE = "nombre";
    public static final String CORREO = "correo";
    public static final String TELEFONO = "telefono";
    public static final String NUMERO_ANEXO = "numero_Anexo";
    public static final String LOCALIZACION = "localizacion";
    public static final String TIPO = "tipo";
    public static final String CARGO = "cargo";

    /**
     * rut de la Persona
     */
    @Setter
    @Getter
    @DatabaseField(columnName = RUT, canBeNull = false, id = true)
    private String rut;

    /**
     * nombre de la Persona
     */
    @Setter
    @Getter
    @DatabaseField(columnName = NOMBRE,canBeNull = false)
    private String nombre;

    /**
     * correo de la Persona
     */
    @Setter
    @Getter
    @DatabaseField(columnName = CORREO, canBeNull = true)
    private String correo;

    /**
     * telefono de la Persona
     */
    @Setter
    @Getter
    @DatabaseField(columnName = TELEFONO, canBeNull = false)
    private int telefono;

    /**
     * numero de anexo de la Persona
     */
    @Setter
    @Getter
    @DatabaseField(columnName = NUMERO_ANEXO,canBeNull = true)
    private int numeroAnexo;

    /**
     * localizacion de la Persona (unidad y oficina)
     */
    @Setter
    @Getter
    @DatabaseField(columnName = LOCALIZACION,canBeNull = true)
    private String localizacion;

    /**
     * tipo de la Persona (academico, funcionario, apoyo, externo)
     */
    @Setter
    @Getter
    @DatabaseField(columnName = TIPO,canBeNull = true)
    private String tipo;

    /**
     * cargo de la Persona (director, rector, secretaria, estudiante, etc)
     */
    @Setter
    @Getter
    @DatabaseField(columnName = CARGO, canBeNull = true)
    private String cargo;

    public Persona(){

    }

    public Persona(String rut, String nombre, String correo, int telefono, int numeroAnexo,
                   String localizacion, String tipo, String cargo) {
        super();
        this.rut = rut;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.numeroAnexo = numeroAnexo;
        this.localizacion = localizacion;
        this.tipo = tipo;
        this.cargo = cargo;
    }
}
