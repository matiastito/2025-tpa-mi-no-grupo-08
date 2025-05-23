package ar.edu.utn.frba.dds.modelo.hecho.contenido;

public class ContenidoMultimedia {
  private TipoContenidoMultimedia tipoContenidoMultimedia;
  private String uri;

  public ContenidoMultimedia(TipoContenidoMultimedia tipoContenidoMultimedia, String uri) {
    this.tipoContenidoMultimedia = tipoContenidoMultimedia;
    this.uri = uri;
  }

  public TipoContenidoMultimedia getTipoContenidoMultimedia() {
    return tipoContenidoMultimedia;
  }
}
