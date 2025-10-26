package ar.edu.utn.frba.dds.model.dto;

import java.time.LocalDate;

public class HechoModificacionDTO {
  private Long id;
  private String titulo;
  private String descripcion;
  private String categoria;
  private ContribuyenteDTO contribuyenteDTO;
  private LocalDate fechaDelHecho;
  private UbicacionDTO ubicacion = new UbicacionDTO();
  private HechoModificacionEstado hechoModificacionEstado;

  public HechoModificacionDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setHechoModificacionEstado(HechoModificacionEstado hechoModificacionEstado) {
    this.hechoModificacionEstado = hechoModificacionEstado;
  }

  public HechoModificacionEstado getHechoModificacionEstado() {
    return hechoModificacionEstado;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public void setFechaDelHecho(LocalDate fechaDelHecho) {
    this.fechaDelHecho = fechaDelHecho;
  }

  public void setUbicacion(UbicacionDTO ubicacion) {
    this.ubicacion = ubicacion;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public String getTitulo() {
    return titulo;
  }

  public UbicacionDTO getUbicacion() {
    return ubicacion;
  }

  public String getCategoria() {
    return categoria;
  }

  public LocalDate getFechaDelHecho() {
    return fechaDelHecho;
  }

  public void setContribuyenteDTO(ContribuyenteDTO contribuyenteDTO) {
    this.contribuyenteDTO = contribuyenteDTO;
  }

  public ContribuyenteDTO getContribuyenteDTO() {
    return contribuyenteDTO;
  }

  private class UbicacionDTO {
    private String latitud;
    private String longitud;

    public UbicacionDTO() {
    }

    public UbicacionDTO(String latitud, String longitud) {
      this.latitud = latitud;
      this.longitud = longitud;
    }


    public String getLatitud() {
      return latitud;
    }

    public String getLongitud() {
      return longitud;
    }

    public void setLongitud(String longitud) {
      this.longitud = longitud;
    }

    public void setLatitud(String latitud) {
      this.latitud = latitud;
    }
  }
}
