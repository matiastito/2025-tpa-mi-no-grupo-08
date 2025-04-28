package ar.edu.utn.frba.dds.colaborador;

import static java.time.LocalDate.now;
import static java.time.Period.between;

import java.time.LocalDate;

public class Contribuyente {
  private String nombre;
  private String apellido;
  private LocalDate fechaNacimiento;

  //Representa un contribuyente anónimo.
  private Contribuyente() {
  }

  private Contribuyente(String nombre) {
    this.nombre = nombre;
  }

  private Contribuyente(String nombre, String apellido, LocalDate fechaNacimiento) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaNacimiento = fechaNacimiento;
  }


  public Integer getEdad() {
    return between(now(), fechaNacimiento).getYears();
  }

  public static Contribuyente crearContribuyente(String nombre) {
    return new Contribuyente(nombre);
  }

  public static Contribuyente crearContribuyente(String nombre, String apellido, LocalDate fechaNacimiento) {
    return new Contribuyente(nombre, apellido, fechaNacimiento);
  }
}
