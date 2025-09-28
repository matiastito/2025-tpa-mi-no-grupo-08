package ar.edu.utn.frba.dds.web.controlador.dto;

import ar.edu.utn.frba.dds.modelo.colaborador.Contribuyente;

public class ContribuyenteDTO {
  private String nombre;
  private String apellido;

  public ContribuyenteDTO() {
  }

  public static Contribuyente toModel(ContribuyenteDTO contribuyenteDTO) {
    return new Contribuyente(contribuyenteDTO.getNombre(),
        contribuyenteDTO.getApellido());
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
