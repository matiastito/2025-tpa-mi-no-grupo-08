package ar.edu.utn.frba.dds.util.archivo.localizador;

import static java.net.URI.create;

import ar.edu.utn.frba.dds.modelo.fuente.ArchivoCSV;
import ar.edu.utn.frba.dds.util.archivo.TipoArchivo;
import java.net.URI;

public class LocalizadorDeArchivoLocal implements LocalizadorDeArchivo {
  private final ArchivoCSV archivoCSV;
  private final TipoArchivo tipoArchivo;

  public LocalizadorDeArchivoLocal(ArchivoCSV archivoCSV, TipoArchivo tipoArchivo) {
    this.archivoCSV = archivoCSV;
    this.tipoArchivo = tipoArchivo;
  }

  @Override
  public URI getURI() {
    return create(archivoCSV.getUri());
  }
}
