package ar.edu.utn.frba.dds.model.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ColeccionDTO {
  private Long id;
  private String titulo;
  private String descripcion;
  private List<FuenteDTO> fuentes = new ArrayList<>();
  private UUID handle;
  private TipoConsenso tipoConsenso;

  public ColeccionDTO() {
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public UUID getHandle() {
    return handle;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public List<FuenteDTO> getFuentes() {
    return fuentes;
  }

  public void setHandle(UUID handle) {
    this.handle = handle;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public void setFuentes(List<FuenteDTO> fuentes) {
    this.fuentes = fuentes;
  }

  public TipoConsenso getTipoConsenso() {
    return tipoConsenso;
  }

  public void setTipoConsenso(TipoConsenso tipoConsenso) {
    this.tipoConsenso = tipoConsenso;
  }

  public static class FuenteDTO {
    private Long id;
    private String baseUrl;
    private TipoFuente tipoFuente;

    public FuenteDTO() {
    }

    public FuenteDTO(Long id, String baseUrl, TipoFuente tipoFuente) {
      this.id = id;
      this.baseUrl = baseUrl;
      this.tipoFuente = tipoFuente;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public Long getId() {
      return id;
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

}
