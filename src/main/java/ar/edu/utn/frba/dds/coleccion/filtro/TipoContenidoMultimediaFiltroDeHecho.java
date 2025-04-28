package ar.edu.utn.frba.dds.coleccion.filtro;

import ar.edu.utn.frba.dds.hecho.Hecho;
import ar.edu.utn.frba.dds.hecho.contenido.TipoContenidoMultimedia;

public class TipoContenidoMultimediaFiltroDeHecho implements FiltroDeHecho {

  private final TipoContenidoMultimedia tipoContenidoMultimedia;

  public TipoContenidoMultimediaFiltroDeHecho(TipoContenidoMultimedia tipoContenidoMultimedia) {
    this.tipoContenidoMultimedia = tipoContenidoMultimedia;
  }

  @Override
  public boolean filtrar(Hecho hecho) {
    return hecho.getTipoContenidoMultimedia().equals(this.tipoContenidoMultimedia);
  }
}
