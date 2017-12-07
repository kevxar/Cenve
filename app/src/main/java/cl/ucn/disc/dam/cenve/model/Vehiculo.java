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

    @Getter
    Persona dueño;
    @Getter
    String placa;
    @Getter
    String marca;
    @Getter
    String color;
    @Getter
    String modelo;
    @Getter
    String anio;
    @Getter
    String descripcion;

    @Getter
    int codigoEstacionamiento;
}
