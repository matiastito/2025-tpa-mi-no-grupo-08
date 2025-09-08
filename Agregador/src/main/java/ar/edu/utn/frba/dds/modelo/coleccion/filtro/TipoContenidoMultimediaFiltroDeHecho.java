package ar.edu.utn.frba.dds.modelo.coleccion.filtro;

import static jakarta.persistence.EnumType.STRING;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.modelo.hecho.contenido.TipoContenidoMultimedia;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;

@Entity
@DiscriminatorValue("TIPO_CONTENIDO_MULTIMEDIA_FILTRO_DE_HECHO")
public class TipoContenidoMultimediaFiltroDeHecho extends FiltroDeHecho {

  @Enumerated(STRING)
  private final TipoContenidoMultimedia tipoContenidoMultimedia;

  public TipoContenidoMultimediaFiltroDeHecho(TipoContenidoMultimedia tipoContenidoMultimedia) {
    this.tipoContenidoMultimedia = tipoContenidoMultimedia;
  }

  @Override
  public boolean filtrar(Hecho hecho) {
    return hecho.getTipoContenidoMultimedia().equals(this.tipoContenidoMultimedia);
  }
}
