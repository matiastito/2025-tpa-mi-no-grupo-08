package ar.edu.utn.frba.dds.coleccion.filtro;

import ar.edu.utn.frba.dds.hecho.Hecho;
import ar.edu.utn.frba.dds.hecho.Ubicacion;

public class UbicacionFiltroDeHecho implements FiltroDeHecho {

  private final Ubicacion ubucacion;

  public UbicacionFiltroDeHecho(Ubicacion ubicacion) {
    this.ubucacion = ubicacion;
  }

  @Override
  public boolean filtrar(Hecho hecho) {
    return hecho.getUbicacion().equals(this.ubucacion);
  }
}
