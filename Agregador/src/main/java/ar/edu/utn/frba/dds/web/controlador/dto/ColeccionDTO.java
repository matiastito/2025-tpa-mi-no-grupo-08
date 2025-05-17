package ar.edu.utn.frba.dds.web.controlador.dto;

import ar.edu.utn.frba.dds.modelo.coleccion.Coleccion;

public class ColeccionDTO {

  public ColeccionDTO(Coleccion coleccion) {
  }

  public static ColeccionDTO toColeccionDTO(Coleccion coleccion) {
    return new ColeccionDTO(coleccion);
  }

  public static Coleccion toColeccion(ColeccionDTO coleccionDTO) {
    return new Coleccion(null, null);
  }
}
