package cl.ucn.disc.dam.cenve.model;

import lombok.Builder;
import lombok.Getter;

/**
 * Representa un vehiculo a ingresar
 * para el sistema de control vehicular
 *
 * @author Kevin Araya Reygada
 */

@Builder
public class Vehiculo {

    /**
     * dueño o responsable del Vehiculo
     */
    @Getter
    Persona dueño;

    /**
     * placa del Vehiculo
     */
    @Getter
    String placa;

    /**
     * marca del Vehiculo
     */
    @Getter
    String marca;

    /**
     * color del Vehiculo
     */
    @Getter
    String color;

    /**
     * modelo del Vehiculo
     */
    @Getter
    String modelo;

    /**
     * anio del Vehiculo
     */
    @Getter
    String anio;

    /**
     * descripcion del responsable del Vehiculo
     */
    @Getter
    String descripcion;

    @Getter
    int codigoEstacionamiento;
}
