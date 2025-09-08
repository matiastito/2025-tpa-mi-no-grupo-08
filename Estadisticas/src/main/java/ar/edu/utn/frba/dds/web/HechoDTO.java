package ar.edu.utn.frba.dds.web;

import java.time.LocalDateTime;

public class HechoDTO {
  private String titulo;
  private String descripcion;
  private String categoria;
  // TODO
  //private ContenidoMultimediaDTO contenidoMultimedia;
  private LocalDateTime fechaDelHecho;
  private UbicacionDTO ubicacion;
  private LocalDateTime fechaDeCarga;
  private boolean eliminado;

  public HechoDTO() {
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public void setFechaDelHecho(LocalDateTime fechaDelHecho) {
    this.fechaDelHecho = fechaDelHecho;
  }

  public void setFechaDeCarga(LocalDateTime fechaDeCarga) {
    this.fechaDeCarga = fechaDeCarga;
  }

  public void setUbicacion(UbicacionDTO ubicacion) {
    this.ubicacion = ubicacion;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public LocalDateTime getFechaDeCarga() {
    return fechaDeCarga;
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

  public LocalDateTime getFechaDelHecho() {
    return fechaDelHecho;
  }

  public boolean isEliminado() {
    return eliminado;
  }

  public void setEliminado(boolean eliminado) {
    this.eliminado = eliminado;
  }

  public class UbicacionDTO {
    private String latitud;
    private String longitud;
    private String provincia;

    public UbicacionDTO() {
    }

    public UbicacionDTO(String latitud, String longitud, String provincia) {
      this.latitud = latitud;
      this.longitud = longitud;
      this.provincia = provincia;
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

    public String getProvincia() {
      return provincia;
    }

    public void setProvincia(String provincia) {
      this.provincia = provincia;
    }
  }
}
