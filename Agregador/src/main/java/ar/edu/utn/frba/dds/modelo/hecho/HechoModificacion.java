package ar.edu.utn.frba.dds.modelo.hecho;

import static ar.edu.utn.frba.dds.modelo.hecho.HechoModificacionEstado.ACEPTADA;
import static ar.edu.utn.frba.dds.modelo.hecho.HechoModificacionEstado.PENDIENTE;
import static ar.edu.utn.frba.dds.modelo.hecho.HechoModificacionEstado.RECHAZADA;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.CascadeType.ALL;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "HECHO_MODIFICACION")
public class HechoModificacion {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;
  @ManyToOne
  @JoinColumn(name = "HECHO_ID")
  @Cascade(ALL)
  private Hecho hecho;
  @Enumerated(STRING)
  private HechoModificacionEstado hechoModificacionEstado;
  @Column(name = "TITULO", nullable = false)
  private String titulo;
  @Column(name = "DESCRIPCION", nullable = false)
  private String descripcion;
  @ManyToOne
  @JoinColumn(name = "CATEGORIA_ID")
  @Cascade(ALL)
  private Categoria categoria;
  @Embedded
  private Ubicacion ubicacion;
  @Column(name = "FECHA_DEL_HECHO", nullable = false)
  private LocalDate fechaDelHecho;

  public HechoModificacion() {
  }

  public HechoModificacion(String titulo,
                           String descripcion,
                           Categoria categoria,
                           LocalDate fechaDelHecho,
                           Ubicacion ubicacion) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.fechaDelHecho = fechaDelHecho;
    this.ubicacion = ubicacion;
    this.hechoModificacionEstado = PENDIENTE;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public LocalDate getFechaDelHecho() {
    return fechaDelHecho;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public Ubicacion getUbicacion() {
    return this.ubicacion;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public void setHecho(Hecho hecho) {
    this.hecho = hecho;
  }

  public Hecho getHecho() {
    return hecho;
  }

  public HechoModificacionEstado getHechoModificacionEstado() {
    return hechoModificacionEstado;
  }

  public void rechazar() {
    this.hechoModificacionEstado = RECHAZADA;
  }

  public void aceptar() {
    hecho.setTitulo(this.titulo);
    hecho.setDescripcion(this.descripcion);
    hecho.setFechaDelHecho(this.fechaDelHecho);
    hecho.setCategoria(this.categoria);
    hecho.setUbicacion(this.ubicacion);
    this.hechoModificacionEstado = ACEPTADA;
  }

  //FIXME reescribir el equals (ID's VS Semáantica)
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof HechoModificacion hecho)) return false;
    return Objects.equals(id, hecho.id) ||
        (Objects.equals(titulo, hecho.titulo)
            && Objects.equals(categoria, hecho.categoria)
            && Objects.equals(fechaDelHecho, hecho.fechaDelHecho));
  }

  @Override
  public int hashCode() {
    return Objects.hash(titulo, categoria, fechaDelHecho);
  }
}
