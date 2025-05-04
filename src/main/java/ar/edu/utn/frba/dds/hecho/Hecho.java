package ar.edu.utn.frba.dds.hecho;

import ar.edu.utn.frba.dds.hecho.contenido.ContenidoMultimedia;
import ar.edu.utn.frba.dds.hecho.contenido.TipoContenidoMultimedia;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

public class Hecho {
  private String titulo;
  private String descripcion;
  private Categoria categoria;
  private ContenidoMultimedia contenidoMultimedia;
  private Ubicacion ubicacion;
  private LocalDateTime fechaDelHecho;
  private LocalDateTime fechaDeCarga;
  private HechoOrigen hechoOrigen;
  private Collection<Etiqueta> etiquetas;
  private boolean eliminado = false;

  public Hecho(HechoOrigen hechoOrigen,
               String titulo,
               String descripcion,
               Categoria categoria,
               LocalDateTime fechaDelHecho,
               Ubicacion ubicacion,
               LocalDateTime fechaDeCarga) {
    this.hechoOrigen = hechoOrigen;
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.fechaDelHecho = fechaDelHecho;
    this.ubicacion = ubicacion;
    this.fechaDeCarga = fechaDeCarga;
    this.etiquetas = new HashSet<>();
  }

  public boolean estaEliminado() {
    return this.eliminado;
  }

  public void eliminar() {
    this.eliminado = true;
  }

  public LocalDateTime getFechaDelHecho() {
    return fechaDelHecho;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public String getTitulo() {
    return titulo;
  }

  public void etiquetar(Etiqueta etiqueta) {
    this.etiquetas.add(etiqueta);
  }

  public Collection<Etiqueta> getEtiquetas() {
    return etiquetas;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Hecho hecho)) return false;
    return Objects.equals(titulo, hecho.titulo);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(titulo);
  }

  public String getDescripcion() {
    return descripcion;
  }

  public HechoOrigen getHechoOrigen() {
    return hechoOrigen;
  }

  public Ubicacion getUbicacion() {
    return this.ubicacion;
  }

  public TipoContenidoMultimedia getTipoContenidoMultimedia() {
    return this.contenidoMultimedia.getTipoContenidoMultimedia();
  }
}
