package ar.edu.utn.frba.dds.modelo.coleccion.filtro;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.modelo.hecho.contenido.TipoContenidoMultimedia;

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
