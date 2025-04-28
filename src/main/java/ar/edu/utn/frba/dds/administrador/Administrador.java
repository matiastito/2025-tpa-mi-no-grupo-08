package ar.edu.utn.frba.dds.administrador;

public class Administrador {
  private String nombre;

  private Administrador(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }

  public static Administrador crearAdministrador(String nombre) {
    return new Administrador(nombre);
  }
}
