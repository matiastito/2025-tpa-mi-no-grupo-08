package ar.edu.utn.frba.dds.modelo.hecho;

import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.hecho.contenido.ContenidoMultimedia;
import ar.edu.utn.frba.dds.modelo.hecho.contenido.TipoContenidoMultimedia;
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
  private Fuente fuente;
  private long fuenteExternaId;
  private boolean eliminado = false;
  private boolean consensuado = true;

  public Hecho(HechoOrigen hechoOrigen,
               String titulo,
               String descripcion,
               Categoria categoria,
               LocalDateTime fechaDelHecho,
               Ubicacion ubicacion,
               LocalDateTime fechaDeCarga,
               long fuenteExternaId,
               Fuente fuente) {
    this(hechoOrigen, titulo, descripcion, categoria, fechaDelHecho, ubicacion, fechaDeCarga);
    this.fuenteExternaId = fuenteExternaId;
    this.fuente = fuente;
  }

  private Hecho(HechoOrigen hechoOrigen,
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
    this.fuente.eliminar(this);
  }

  public long getFuenteExternaId() {
    return fuenteExternaId;
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

  public LocalDateTime getFechaDeCarga() {
    return fechaDeCarga;
  }

  public Hecho setFuente(Fuente fuente) {
    this.fuente = fuente;
    return this;
  }

  public boolean isConsensuado() {
    return consensuado;
  }

  public void setConsensuado(boolean consensuado) {
    this.consensuado = consensuado;
  }

  public Fuente getFuente() {
    return this.fuente;
  }

  //FIXME reescribir el equals (ID's VS Semáantica)
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Hecho hecho)) return false;
    return Objects.equals(titulo, hecho.titulo)
        && Objects.equals(categoria, hecho.categoria)
        && Objects.equals(fechaDelHecho, hecho.fechaDelHecho);
  }

  @Override
  public int hashCode() {
    return Objects.hash(titulo, categoria, fechaDelHecho);
  }
}
