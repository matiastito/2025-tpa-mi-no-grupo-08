package ar.edu.utn.frba.dds.model.dto;

import java.util.Set;
import java.util.UUID;

public class ColeccionDTO {
  private String titulo;
  private String descripcion;
  private Set<FuenteDTO> fuentes;
  private UUID handle;

  public ColeccionDTO() {
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

  public Set<FuenteDTO> getFuentes() {
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

  public void setFuentes(Set<FuenteDTO> fuentes) {
    this.fuentes = fuentes;
  }

  public static class FuenteDTO {
    private String baseUrl;
    private String tipoFuente;

    public FuenteDTO() {
    }

    public void setBaseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
    }

    public void setTipoFuente(String tipoFuente) {
      this.tipoFuente = tipoFuente;
    }

    public String getTipoFuente() {
      return tipoFuente;
    }

    public String getBaseUrl() {
      return baseUrl;
    }
  }
}
