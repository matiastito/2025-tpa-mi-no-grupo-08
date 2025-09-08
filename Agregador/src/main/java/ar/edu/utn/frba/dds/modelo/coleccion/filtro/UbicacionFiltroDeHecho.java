package ar.edu.utn.frba.dds.modelo.coleccion.filtro;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.modelo.hecho.Ubicacion;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("UBICACION_FILTRO_DE_HECHO")
public class UbicacionFiltroDeHecho extends FiltroDeHecho {

  @Embedded
  private final Ubicacion ubucacion;

  public UbicacionFiltroDeHecho(Ubicacion ubicacion) {
    this.ubucacion = ubicacion;
  }

  @Override
  public boolean filtrar(Hecho hecho) {
    return hecho.getUbicacion().equals(this.ubucacion);
  }
}
