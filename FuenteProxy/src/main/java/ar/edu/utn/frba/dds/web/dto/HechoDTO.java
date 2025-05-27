package ar.edu.utn.frba.dds.web.dto;

import static ar.edu.utn.frba.dds.modelo.fuente.HechoOrigen.EXTERNO;
import static java.lang.String.valueOf;
import ar.edu.utn.frba.dds.modelo.fuente.HechoOrigen;

import java.time.LocalDateTime;
import java.util.Collection;

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
  private Collection<String> etiquetas;

  public void setHechoOrigen(HechoOrigen hechoOrigen) {
    this.hechoOrigen = hechoOrigen;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public void setUbicacion(UbicacionDTO ubicacion) {
    this.ubicacion = ubicacion;
  }

  public void setFechaDelHecho(LocalDateTime fechaDelHecho) {
    this.fechaDelHecho = fechaDelHecho;
  }

  public void setFechaDeCarga(LocalDateTime fechaDeCarga) {
    this.fechaDeCarga = fechaDeCarga;
  }

  public void setEtiquetas(Collection<String> etiquetas) {
    this.etiquetas = etiquetas;
  }

  public HechoOrigen getHechoOrigen() {
    return hechoOrigen;
  }

  public LocalDateTime getFechaDelHecho() {
    return fechaDelHecho;
  }

  public Collection<String> getEtiquetas() {
    return etiquetas;
  }

  public String getTitulo() {
    return titulo;
  }

  public LocalDateTime getFechaDeCarga() {
    return fechaDeCarga;
  }

  public String getCategoria() {
    return categoria;
  }

  public UbicacionDTO getUbicacion() {
    return ubicacion;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public class UbicacionDTO {
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

    public void setLatitud(String latitud) {
      this.latitud = latitud;
    }

    public void setLongitud(String longitud) {
      this.longitud = longitud;
    }
  }

  public class HechoOrigenDTO {

  }

  public static HechoDTO toHechoDTO(DesastreDTO desastreDTO) {
    HechoDTO hechoDTO = new HechoDTO();
    hechoDTO.hechoOrigen = EXTERNO;
    hechoDTO.titulo = desastreDTO.getTitulo();
    hechoDTO.descripcion = desastreDTO.getDescripcion();
    hechoDTO.fechaDelHecho = desastreDTO.getFechaHecho();
    hechoDTO.fechaDeCarga = desastreDTO.getCreatedAt();
    hechoDTO.ubicacion = hechoDTO.new UbicacionDTO(
        valueOf(desastreDTO.getLatitud()),
        valueOf(desastreDTO.getLongitud()));
    hechoDTO.categoria = desastreDTO.getCategoria();
    return hechoDTO;
  }
}
