package ar.edu.utn.frba.dds.web.controlador.dto;

import static ar.edu.utn.frba.dds.modelo.hecho.Ubicacion.crearUbicacion;

import ar.edu.utn.frba.dds.modelo.hecho.Categoria;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.modelo.hecho.HechoOrigen;
import java.time.LocalDateTime;

public class HechoDTO {
  private HechoOrigen hechoOrigen;
  private String titulo;
  private String descripcion;
  private String categoria;
  // TODO
  //private ContenidoMultimediaDTO contenidoMultimedia;
  private LocalDateTime fechaDelHecho;
  private UbicacionDTO ubicacion;
  private LocalDateTime fechaDeCarga;

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

  public void setHechoOrigen(HechoOrigen hechoOrigen) {
    this.hechoOrigen = hechoOrigen;
  }

  public HechoOrigen getHechoOrigen() {
    return hechoOrigen;
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

  public static HechoDTO toDTO(Hecho hecho) {
    HechoDTO hechoDTO = new HechoDTO();
    hechoDTO.titulo = hecho.getTitulo();
    hechoDTO.descripcion = hecho.getDescripcion();
    hechoDTO.hechoOrigen = hecho.getHechoOrigen();
    hechoDTO.fechaDelHecho = hecho.getFechaDelHecho();
    hechoDTO.categoria = hecho.getCategoria().getNombre();
    hechoDTO.fechaDeCarga = hecho.getFechaDeCarga();
    hechoDTO.ubicacion = hechoDTO.
        new UbicacionDTO(hecho.getUbicacion().getLatitud(), hecho.getUbicacion().getLongitud());
    return hechoDTO;
  }

  public static Hecho toHecho(HechoDTO hechoDTO) {
    return new Hecho(
        HechoOrigen.EXTERNO,
        hechoDTO.titulo,
        hechoDTO.descripcion,
        new Categoria(hechoDTO.categoria),
        hechoDTO.fechaDeCarga,
        crearUbicacion(hechoDTO.ubicacion.getLatitud(), hechoDTO.ubicacion.getLongitud()),
        hechoDTO.fechaDelHecho
    );
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
