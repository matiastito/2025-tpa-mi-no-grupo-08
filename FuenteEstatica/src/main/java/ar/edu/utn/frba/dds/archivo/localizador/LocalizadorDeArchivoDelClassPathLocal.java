package ar.edu.utn.frba.dds.archivo.localizador;

import static java.util.Objects.requireNonNull;
import ar.edu.utn.frba.dds.archivo.TipoArchivo;

import java.net.URI;
import java.net.URISyntaxException;

public class LocalizadorDeArchivoDelClassPathLocal implements LocalizadorDeArchivo {
  private String nombreArchivo;
  private TipoArchivo tipoArchivo;

  public LocalizadorDeArchivoDelClassPathLocal(String nombreArchivo, TipoArchivo tipoArchivo) {
    this.nombreArchivo = nombreArchivo;
    this.tipoArchivo = tipoArchivo;
  }

  public URI getURI() {
    URI csvFileURI = null;
    try {
      csvFileURI =
          requireNonNull(getClass().getResource(nombreArchivo + tipoArchivo.getExtension())).toURI();
    } catch (
        URISyntaxException e) {
      throw new RuntimeException("No se pudo armar la ruta al archivo de Hechos.");
    }
    return csvFileURI;
  }

}
