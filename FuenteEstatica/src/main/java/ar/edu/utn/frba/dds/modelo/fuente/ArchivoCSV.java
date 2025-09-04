package ar.edu.utn.frba.dds.modelo.fuente;

public class ArchivoCSV {
  private final String uri;
  private boolean procesado;

  public ArchivoCSV(String uri) {
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
