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

@DatabaseTable
@Builder
public class Persona {

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
    @DatabaseField(generatedId = true, columnName = RUT)
    private String rut;

    /**
     * nombre de la Persona
     */
    @Setter
    @Getter
    @DatabaseField(columnName = NOMBRE)
    private String nombre;

    /**
     * correo de la Persona
     */
    @Setter
    @Getter
    @DatabaseField(columnName = CORREO)
    private String correo;

    /**
     * telefono de la Persona
     */
    @Setter
    @Getter
    @DatabaseField(columnName = TELEFONO)
    private int telefono;

    /**
     * numero de anexo de la Persona
     */
    @Setter
    @Getter
    @DatabaseField(columnName = NUMERO_ANEXO)
    private int numeroAnexo;

    /**
     * localizacion de la Persona (unidad y oficina)
     */
    @Setter
    @Getter
    @DatabaseField(columnName = LOCALIZACION)
    private String localizacion;

    /**
     * tipo de la Persona (academico, funcionario, apoyo, externo)
     */
    @Setter
    @Getter
    @DatabaseField(columnName = TIPO)
    private String tipo;

    /**
     * cargo de la Persona (director, rector, secretaria, estudiante, etc)
     */
    @Setter
    @Getter
    @DatabaseField(columnName = CARGO)
    private String cargo;
}
