package ar.edu.utn.frba.dds.model.dto;

public class SolicitudDeEliminacionDeHechoDTO {
  private Long solicitudDeEliminacionId;
  private Long hechoId;
  private String tituloHecho;
  private String motivo;
  private ContribuyenteDTO repotador;

  private AdministradorDTO administrador;
  private SolicitudDeEliminacionDeHechoEstado solicitudDeEliminacionDeHechoEstado;

  public SolicitudDeEliminacionDeHechoDTO() {
  }

  public Long getHechoId() {
    return hechoId;
  }

  public void setHechoId(Long hechoId) {
    this.hechoId = hechoId;
  }

  public Long getSolicitudDeEliminacionId() {
    return solicitudDeEliminacionId;
  }

  public void setSolicitudDeEliminacionId(Long solicitudDeEliminacionId) {
    this.solicitudDeEliminacionId = solicitudDeEliminacionId;
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

  public SolicitudDeEliminacionDeHechoEstado getSolicitudDeEliminacionDeHechoEstado() {
    return solicitudDeEliminacionDeHechoEstado;
  }

  public void setSolicitudDeEliminacionDeHechoEstado(SolicitudDeEliminacionDeHechoEstado solicitudDeEliminacionDeHechoEstado) {
    this.solicitudDeEliminacionDeHechoEstado = solicitudDeEliminacionDeHechoEstado;
  }

  public AdministradorDTO getAdministrador() {
    return administrador;
  }

  public void setAdministrador(AdministradorDTO administrador) {
    this.administrador = administrador;
  }

  public static class AdministradorDTO {
    private String nombre;

    public AdministradorDTO(String nombre) {
      this.nombre = nombre;
    }

    public String getNombre() {
      return nombre;
    }

    public void setNombre(String nombre) {
      this.nombre = nombre;
    }

  }
}
