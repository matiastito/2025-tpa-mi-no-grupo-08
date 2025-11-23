package ar.edu.utn.frba.dds.modelo.hecho;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.CascadeType.ALL;

import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.fuente.TipoFuente;
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
  @Column(name = "ID_EXTERNO")
  private Long idExterno;
  @Column(name = "TITULO", nullable = false)
  private String titulo;
  @Column(name = "DESCRIPCION", nullable = false, length = 200000)
  private String descripcion;
  @ManyToOne
  @JoinColumn(name = "CATEGORIA_ID")
  @Cascade(ALL)
  private Categoria categoria;
  @OneToOne
  @JoinColumn(name = "CONTENIDO_MULTIMEDIA_ID")
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
  @ManyToMany
  @JoinTable(
      name = "HECHO_ETIQUETA",
      joinColumns = @JoinColumn(name = "HECHO_ID"),
      inverseJoinColumns = @JoinColumn(name = "ETIQUETA_ID")
  )
  @Cascade(ALL)
  private Collection<Etiqueta> etiquetas;
  @ManyToOne
  @JoinColumn(name = "FUENTE_ID")
  private Fuente fuente;
  @Column(name = "ELIMINADO", nullable = false)
  private boolean eliminado = false;
  @Column(name = "CONSENSUADO", nullable = false)
  private boolean consensuado = true;

  public Hecho() {
  }

  public Hecho(HechoOrigen hechoOrigen,
               String titulo,
               String descripcion,
               Categoria categoria,
               LocalDate fechaDelHecho,
               Ubicacion ubicacion,
               LocalDateTime fechaDeCarga,
               Fuente fuente) {
    this(hechoOrigen, titulo, descripcion, categoria, fechaDelHecho, ubicacion, fechaDeCarga);
    this.fuente = fuente;
  }

  private Hecho(HechoOrigen hechoOrigen,
                String titulo,
                String descripcion,
                Categoria categoria,
                LocalDate fechaDelHecho,
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

  public void setId(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public void setIdExterno(Long idExterno) {
    this.idExterno = idExterno;
  }

  public Long getIdExterno() {
    return idExterno;
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

  public boolean estaEliminado() {
    return this.eliminado;
  }

  public void eliminar(SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho) {
    this.eliminado = true;
    this.fuente.eliminar(solicitudDeEliminacionDeHecho);
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

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
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

  public boolean esDeFuenteDinamica() {
    return this.fuente.getTipoFuente().equals(TipoFuente.DINAMICA);
  }

  //FIXME reescribir el equals (ID's VS Semáantica)
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Hecho hecho)) return false;
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
