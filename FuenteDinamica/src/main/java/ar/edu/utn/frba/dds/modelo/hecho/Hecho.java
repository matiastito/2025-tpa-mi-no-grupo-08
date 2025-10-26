package ar.edu.utn.frba.dds.modelo.hecho;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.CascadeType.ALL;

import ar.edu.utn.frba.dds.modelo.colaborador.Contribuyente;
import ar.edu.utn.frba.dds.modelo.hecho.contenido.ContenidoMultimedia;
import ar.edu.utn.frba.dds.modelo.hecho.contenido.TipoContenidoMultimedia;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "HECHO")
public class Hecho {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;
  @ManyToOne
  @JoinColumn(name = "CONTRIBUYENTE_ID")
  @Cascade(ALL)
  private Contribuyente contribuyente;
  @Column(name = "TITULO", nullable = false)
  private String titulo;
  @Column(name = "DESCRIPCION", nullable = false)
  private String descripcion;
  @ManyToOne
  @JoinColumn(name = "CATEGORIA_ID")
  @Cascade(ALL)
  private Categoria categoria;
  @OneToOne
  @Cascade(ALL)
  private ContenidoMultimedia contenidoMultimedia;
  @Embedded
  private Ubicacion ubicacion;
  @Column(name = "FECHA_DEL_HECHO", nullable = false)
  private LocalDate fechaDelHecho;
  @Column(name = "FECHA_DE_CARGA", nullable = false)
  private LocalDateTime fechaDeCarga;
  @Enumerated(STRING)
  private HechoOrigen hechoOrigen;
  @Enumerated(STRING)
  private HechoEstado hechoEstado;
  @ManyToMany
  @Cascade(ALL)
  @JoinTable(
      name = "HECHO_ETIQUETAS",
      joinColumns = @JoinColumn(name = "HECHO_ID"),
      inverseJoinColumns = @JoinColumn(name = "ETIQUETA_ID")
  )
  private Collection<Etiqueta> etiquetas;
  @Column(name = "ELIMINADO", nullable = false)
  private boolean eliminado = false;

  public Hecho() {
  }

  public Hecho(HechoOrigen hechoOrigen,
               HechoEstado hechoEstado,
               Contribuyente contribuyente,
               String titulo,
               String descripcion,
               Categoria categoria,
               LocalDate fechaDelHecho,
               Ubicacion ubicacion,
               LocalDateTime fechaDeCarga) {
    this.hechoOrigen = hechoOrigen;
    this.hechoEstado = hechoEstado;
    this.contribuyente = contribuyente;
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.categoria = categoria;
    this.fechaDelHecho = fechaDelHecho;
    this.ubicacion = ubicacion;
    this.fechaDeCarga = fechaDeCarga;
    this.etiquetas = new HashSet<>();
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void eliminar() {
    this.eliminado = true;
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

  public Contribuyente getContribuyente() {
    return contribuyente;
  }

  public void etiquetar(Etiqueta etiqueta) {
    this.etiquetas.add(etiqueta);
  }

  public Collection<Etiqueta> getEtiquetas() {
    return etiquetas;
  }

  //FIXME
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

  public LocalDateTime getFechaDeCarga() {
    return this.fechaDeCarga;
  }

  public HechoEstado getHechoEstado() {
    return hechoEstado;
  }

  public boolean isEliminado() {
    return eliminado;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public void setUbicacion(Ubicacion ubicacion) {
    this.ubicacion = ubicacion;
  }

  public void setFechaDelHecho(LocalDate fechaDelHecho) {
    this.fechaDelHecho = fechaDelHecho;
  }

  public boolean isAprobado() {
    return this.hechoEstado.equals(HechoEstado.ACEPTADO);
  }
}
