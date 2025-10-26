package ar.edu.utn.frba.dds.model.dto;

public class ContribuyenteDTO {
  private String nombre;
  private String apellido;

  public ContribuyenteDTO(String nombre, String apellido) {
    this.nombre = nombre;
    this.apellido = apellido;
  }

  public ContribuyenteDTO() {
  }

  public String getApellido() {
    return apellido;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }
}
