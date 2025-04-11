package ar.edu.utn.frba.dds.colaborador;

public class Contribuyente {
  private String nombre;
  private String apellido;
  private String edad;

  //Representa un contribuyente anónimo.
  public Contribuyente() {
  }

  public Contribuyente(String nombre) {
    this.nombre = nombre;
  }

  public Contribuyente(String nombre, String apellido, String edad) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.edad = edad;
  }
}
