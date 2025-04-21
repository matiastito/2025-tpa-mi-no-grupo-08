package ar.edu.utn.frba.dds.colaborador;

public class Contribuyente {
  private String nombre;
  private String apellido;
  private String edad;

  //Representa un contribuyente anónimo.
  private Contribuyente() {
  }

  private Contribuyente(String nombre) {
    this.nombre = nombre;
  }

  private Contribuyente(String nombre, String apellido, String edad) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.edad = edad;
  }

  public static Contribuyente crearContribuyenteAnonimo() {
    return new Contribuyente();
  }

  public static Contribuyente crearContribuyente(String nombre) {
    return new Contribuyente(nombre);
  }

  public static Contribuyente crearContribuyente(String nombre, String apellido, String edad) {
    return new Contribuyente(nombre, apellido, edad);
  }
}
