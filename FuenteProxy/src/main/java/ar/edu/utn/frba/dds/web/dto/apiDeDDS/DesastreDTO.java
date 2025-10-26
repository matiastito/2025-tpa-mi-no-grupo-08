package ar.edu.utn.frba.dds.web.dto.apiDeDDS;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DesastreDTO {
  private int id;
  private String titulo;
  private String descripcion;
  private String categoria;
  private double latitud;
  private double longitud;
  @JsonProperty("fecha_hecho")
  private LocalDate fechaHecho;
  @JsonProperty("created_at")
  private LocalDateTime createdAt;
  @JsonProperty("updated_at")
  private LocalDateTime updatedAt;

  public DesastreDTO() {
  }

  // Getters y Setters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getCategoria() {
    return categoria;
  }

  public void setCategoria(String categoria) {
    this.categoria = categoria;
  }

  public double getLatitud() {
    return latitud;
  }

  public void setLatitud(double latitud) {
    this.latitud = latitud;
  }

  public double getLongitud() {
    return longitud;
  }

  public void setLongitud(double longitud) {
    this.longitud = longitud;
  }

  public LocalDate getFechaHecho() {
    return fechaHecho;
  }

  public void setFechaHecho(LocalDate fechaHecho) {
    this.fechaHecho = fechaHecho;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }
}
