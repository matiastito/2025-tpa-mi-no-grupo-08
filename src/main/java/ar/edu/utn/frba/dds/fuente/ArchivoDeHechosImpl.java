package ar.edu.utn.frba.dds.fuente;

import static java.nio.file.Files.lines;
import static java.nio.file.Paths.get;
import static java.util.Arrays.asList;
import ar.edu.utn.frba.dds.hecho.Hecho;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ArchivoDeHechosImpl implements ArchivoDeHechos {
  private static final String COMMA_DELIMITER = ",";
  private String nombreArchivo;

  public ArchivoDeHechosImpl(String nombreArchivo) {
    this.nombreArchivo = nombreArchivo;
  }

  @Override
  public List<List<String>> getRegistros() {
    URI csvFile = null;
    List<List<String>> registros = null;
    List<Hecho> colaboradorHumanos = new ArrayList<>();
    try {
      csvFile = Objects.requireNonNull(getClass().getClassLoader().getResource(nombreArchivo + ".csv")).toURI();
    } catch (URISyntaxException e) {
      throw new RuntimeException("No se pudo armar la ruta al archivo de colaboradores.");
    }
    try (Stream<String> lines = lines(get(csvFile))) {
      registros = lines.map(
          line -> asList(line.split(COMMA_DELIMITER))).toList();
      return registros;
    } catch (IOException e) {
      throw new RuntimeException("Ocurrió un error al parsear el archivo CSV de colaboradores.");
    }
  }

}
