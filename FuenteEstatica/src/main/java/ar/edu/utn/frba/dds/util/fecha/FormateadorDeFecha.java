package ar.edu.utn.frba.dds.util.fecha;

import static java.time.LocalDate.parse;
import static java.time.format.DateTimeFormatter.ofPattern;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormateadorDeFecha {
  private static DateTimeFormatter formatoDeFecha = ofPattern("dd/MM/yyyy");

  public static LocalDateTime formatearFecha(String fecha) {
    return parse(fecha, formatoDeFecha).atStartOfDay();
  }
}
