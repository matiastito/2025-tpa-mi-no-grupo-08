package ar.edu.utn.frba.dds.util;

import static java.time.LocalDate.parse;
import static java.time.format.DateTimeFormatter.ofPattern;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormateadorDeFecha {
  private static DateTimeFormatter formatoDeFecha = ofPattern("dd/MM/yyyy");

  public static LocalDate formatearFecha(String fecha) {
    return parse(fecha, formatoDeFecha);
  }
}
