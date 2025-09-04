package ar.edu.utn.frba.dds.util.archivo.lector.csv;

import static java.nio.file.Files.lines;
import static java.nio.file.Paths.get;

import ar.edu.utn.frba.dds.util.archivo.lector.LectorDeArchivo;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LectorArchivoCSV implements LectorDeArchivo {

  public List<List<String>> getRegistros(URI uri) {
    List<List<String>> registros = null;
    try (
        Stream<String> lines = lines(get(uri)).skip(1)) {
      registros = lines.map(this::parseLine).toList();
      return registros;
    } catch (
        IOException e) {
      throw new RuntimeException("Ocurrió un error al parsear el archivo CSV.");
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
