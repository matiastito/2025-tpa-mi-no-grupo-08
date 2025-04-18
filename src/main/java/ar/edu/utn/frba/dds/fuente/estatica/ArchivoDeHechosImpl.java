package ar.edu.utn.frba.dds.fuente.estatica;

import static java.nio.file.Files.lines;
import static java.nio.file.Paths.get;
import static java.util.Objects.requireNonNull;

import ar.edu.utn.frba.dds.hecho.Hecho;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ArchivoDeHechosImpl implements ArchivoDeHechos {
  private final String nombreArchivo;

  public ArchivoDeHechosImpl(String nombreArchivo) {
    this.nombreArchivo = nombreArchivo;
  }

  @Override
  public List<List<String>> getRegistros() {
    URI csvFile = null;
    List<List<String>> registros = null;
    List<Hecho> colaboradorHumanos = new ArrayList<>();
    try {
      csvFile = requireNonNull(getClass().getClassLoader().getResource(nombreArchivo + ".csv")).toURI();
    } catch (URISyntaxException e) {
      throw new RuntimeException("No se pudo armar la ruta al archivo de Hechos.");
    }
    try (Stream<String> lines = lines(get(csvFile)).skip(1)) {
      registros = lines.map(this::parseLine).toList();
      return registros;
    } catch (IOException e) {
      throw new RuntimeException("Ocurrió un error al parsear el archivo CSV de Hechos.");
    }
  }

  private List<String> parseLine(String line) {
    List<String> fields = new ArrayList<>();
    boolean inQuotes = false;
    StringBuilder currentField = new StringBuilder();
    for (char c : line.toCharArray()) {
      if (c == '"') {
        inQuotes = !inQuotes;
      } else if (c == ',' && !inQuotes) {
        fields.add(currentField.toString());
        currentField.setLength(0);
      } else {
        currentField.append(c);
      }
    }
    fields.add(currentField.toString());
    return fields;
  }
}
