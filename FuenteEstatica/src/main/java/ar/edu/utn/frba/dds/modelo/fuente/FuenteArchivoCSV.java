package ar.edu.utn.frba.dds.modelo.fuente;

public class FuenteArchivoCSV {
  private final String uri;
  private boolean procesado;

  public FuenteArchivoCSV(String uri) {
    this.uri = uri;
    this.procesado = false;
  }

  public String getUri() {
    return uri;
  }

  public void marcarComoProcesado() {
    this.procesado = true;
  }
}
