package cl.ucn.disc.dam.cenve.model;

import lombok.Builder;
import lombok.Getter;

/**
 * Representa una persona
 * para el sistema de control vehicular
 *
 * @author Kevin Araya Reygada
 */

@Builder
public class Persona {
    @Getter
    String rut;
    @Getter
    String nombre;
    @Getter
    String correo;
    @Getter
    int telefono;
    @Getter
    int numeroAnexo;
    @Getter
    String localizacion;
    @Getter
    String tipo;
    @Getter
    String cargo;
}
