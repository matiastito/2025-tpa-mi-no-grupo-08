package ar.edu.utn.frba.dds.web.controlador.dto;

import ar.edu.utn.frba.dds.modelo.coleccion.Coleccion;
import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.fuente.TipoFuente;

public class ColeccionDTO {
  private String titulo;
  private String descripcion;
  private FuenteDTO fuente;

  public ColeccionDTO() {
  }

  public String getTitulo() {
    return titulo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public FuenteDTO getFuente() {
    return fuente;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public void setFuente(FuenteDTO fuente) {
    this.fuente = fuente;
  }

  public class FuenteDTO {
    private String baseUrl;
    private TipoFuente tipoFuente;

    public FuenteDTO() {
    }

    public FuenteDTO(String baseUrl, TipoFuente tipoFuente) {
      this.baseUrl = baseUrl;
      this.tipoFuente = tipoFuente;
    }

    public void setBaseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
    }

    public void setTipoFuente(TipoFuente tipoFuente) {
      this.tipoFuente = tipoFuente;
    }

    public TipoFuente getTipoFuente() {
      return tipoFuente;
    }

    public String getBaseUrl() {
      return baseUrl;
    }
  }

  public static Coleccion toModel(ColeccionDTO coleccionDTO) {
    return new Coleccion(
        coleccionDTO.titulo,
        coleccionDTO.descripcion,
        new Fuente(coleccionDTO.getFuente().baseUrl,
            coleccionDTO.getFuente().tipoFuente));
  }

  public static ColeccionDTO toDTO(Coleccion coleccion) {
    ColeccionDTO coleccionDTO = new ColeccionDTO();
    coleccionDTO.titulo = coleccion.getTitulo();
    coleccionDTO.descripcion = coleccion.getDescripcion();
    coleccionDTO.fuente =
        coleccionDTO.new FuenteDTO(coleccion.getFuente().getBaseUrl(), coleccion.getFuente().getTipoFuente());
    return coleccionDTO;
  }
}
