package ar.edu.utn.frba.dds.web.controlador.dto;

import static java.util.stream.Collectors.toSet;
import ar.edu.utn.frba.dds.modelo.coleccion.Coleccion;
import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.fuente.TipoFuente;

import java.util.Set;

public class ColeccionDTO {
  private String titulo;
  private String descripcion;
  private Set<FuenteDTO> fuentes;
  private String handle;

  public ColeccionDTO() {
  }

  public String getHandle() {
    return handle;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public Set<FuenteDTO> getFuentes() {
    return fuentes;
  }

  public void setHandle(String handle) {
    this.handle = handle;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public void setFuentes(Set<FuenteDTO> fuentes) {
    this.fuentes = fuentes;
  }

  public static class FuenteDTO {
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
        coleccionDTO.fuentes.stream().map(f ->
            new Fuente(f.baseUrl, f.tipoFuente)).toList().toArray(new Fuente[0]));
  }

  public static ColeccionDTO toDTO(Coleccion coleccion) {
    ColeccionDTO coleccionDTO = new ColeccionDTO();
    coleccionDTO.titulo = coleccion.getTitulo();
    coleccionDTO.handle = coleccion.getHandle();
    coleccionDTO.descripcion = coleccion.getDescripcion();
    coleccionDTO.fuentes =
        coleccion.getFuentes().stream().map(f ->
            new FuenteDTO(f.getBaseUrl(), f.getTipoFuente())).collect(toSet());
    return coleccionDTO;
  }
}
