package ar.edu.utn.frba.dds.servicio;

import ar.edu.utn.frba.dds.modelo.coleccion.Coleccion;
import ar.edu.utn.frba.dds.repositorio.ColeccionRepositorio;

import java.util.Collection;

public class ColeccionServicio {
  private ColeccionRepositorio coleccionRepositorio;

  public Collection<Coleccion> colecciones() {
    return coleccionRepositorio.colleciones();
  }
}
