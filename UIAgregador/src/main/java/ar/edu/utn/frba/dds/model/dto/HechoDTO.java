package ar.edu.utn.frba.dds.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class HechoDTO {
  private Long id;
  private HechoOrigen hechoOrigen;
  private String titulo;
  private String descripcion;
  private String categoria;
  private ContribuyenteDTO contribuyenteDTO;
  // TODO
  //private ContenidoMultimediaDTO contenidoMultimedia;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate fechaDelHecho;
  private UbicacionDTO ubicacion = new UbicacionDTO();
  private LocalDateTime fechaDeCarga;
  private boolean eliminado;

  public HechoDTO() {
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
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

  public LocalDate getFechaDelHecho() {
    return fechaDelHecho;
  }

  public boolean isEliminado() {
    return eliminado;
  }

  public void setEliminado(boolean eliminado) {
    this.eliminado = eliminado;
  }

  public void setContribuyenteDTO(ContribuyenteDTO contribuyenteDTO) {
    this.contribuyenteDTO = contribuyenteDTO;
  }

  public ContribuyenteDTO getContribuyenteDTO() {
    return contribuyenteDTO;
  }
}
