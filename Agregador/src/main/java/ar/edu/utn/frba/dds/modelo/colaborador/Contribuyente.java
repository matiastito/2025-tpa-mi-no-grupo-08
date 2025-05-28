package ar.edu.utn.frba.dds.modelo.colaborador;

import static java.time.LocalDate.now;
import static java.time.Period.between;

import java.time.LocalDate;

public class Contribuyente {
  private String nombre;
  private String apellido;
  private LocalDate fechaNacimiento;

  public Contribuyente(String nombre) {
    this.nombre = nombre;
  }

  public Contribuyente(String nombre, String apellido) {
    this.nombre = nombre;
    this.apellido = apellido;
  }

  public Contribuyente(String nombre, String apellido, LocalDate fechaNacimiento) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaNacimiento = fechaNacimiento;
  }

  public String getApellido() {
    return apellido;
  }

  public String getNombre() {
    return nombre;
  }

  public Integer getEdad() {
    return between(now(), fechaNacimiento).getYears();
  }
}
