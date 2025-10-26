package ar.edu.utn.frba.dds.web.dto;

import ar.edu.utn.frba.dds.modelo.colaborador.Contribuyente;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHecho;
import ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHechoEstado;

public class SolicitudDeEliminacionDeHechoDTO {
  private String tituloHecho;
  private String motivo;
  private ContribuyenteDTO repotador;

  private AdministradorDTO administrador;
  private SolicitudDeEliminacionDeHechoEstado solicitudDeEliminacionDeHechoEstado;

  public SolicitudDeEliminacionDeHechoDTO() {
  }

  public static SolicitudDeEliminacionDeHecho toModel(
      SolicitudDeEliminacionDeHechoDTO solicitudDeEliminacionDeHechoDTO, Hecho hecho, Contribuyente contribuyente) {
    return new SolicitudDeEliminacionDeHecho(contribuyente, hecho, solicitudDeEliminacionDeHechoDTO.getMotivo());
  }

  public static SolicitudDeEliminacionDeHechoDTO toDTO(SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho) {
    return new SolicitudDeEliminacionDeHechoDTO();
  }

  public ContribuyenteDTO getRepotador() {
    return repotador;
  }

  public String getTituloHecho() {
    return tituloHecho;
  }

  public String getMotivo() {
    return motivo;
  }

  public void setTituloHecho(String tituloHecho) {
    this.tituloHecho = tituloHecho;
  }

  public void setMotivo(String motivo) {
    this.motivo = motivo;
  }

  public void setRepotador(ContribuyenteDTO repotador) {
    this.repotador = repotador;
  }

  public void setSolicitudDeEliminacionDeHechoEstado(SolicitudDeEliminacionDeHechoEstado solicitudDeEliminacionDeHechoEstado) {
    this.solicitudDeEliminacionDeHechoEstado = solicitudDeEliminacionDeHechoEstado;
  }

  public SolicitudDeEliminacionDeHechoEstado getSolicitudDeEliminacionDeHechoEstado() {
    return solicitudDeEliminacionDeHechoEstado;
  }

  public AdministradorDTO getAdministrador() {
    return administrador;
  }

  public void setAdministrador(AdministradorDTO administrador) {
    this.administrador = administrador;
  }

  public class ContribuyenteDTO {
    private String nombre;
    private String apellido;

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

  public class AdministradorDTO {
    private String nombre;

    public String getNombre() {
      return nombre;
    }

    public void setNombre(String nombre) {
      this.nombre = nombre;
    }

  }
}
