package ar.edu.utn.frba.dds.util.archivo.localizador;

import static java.net.URI.create;

import ar.edu.utn.frba.dds.modelo.fuente.FuenteArchivoCSV;
import ar.edu.utn.frba.dds.util.archivo.TipoArchivo;
import java.net.URI;

public class LocalizadorDeArchivoLocal implements LocalizadorDeArchivo {
  private final FuenteArchivoCSV fuenteArchivoCSV;
  private final TipoArchivo tipoArchivo;

  public LocalizadorDeArchivoLocal(FuenteArchivoCSV fuenteArchivoCSV, TipoArchivo tipoArchivo) {
    this.fuenteArchivoCSV = fuenteArchivoCSV;
    this.tipoArchivo = tipoArchivo;
  }

  @Override
  public URI getURI() {
    return create(fuenteArchivoCSV.getUri());
  }
}
