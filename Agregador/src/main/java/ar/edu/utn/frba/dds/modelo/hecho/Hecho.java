package ar.edu.utn.frba.dds.modelo.hecho;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.CascadeType.ALL;
import static org.springframework.data.elasticsearch.annotations.FieldType.Date;
import static org.springframework.data.elasticsearch.annotations.FieldType.Text;
import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
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
import org.hibernate.annotations.Cascade;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "HECHO")
@Document(indexName = "Hechos", createIndex = true)
public class Hecho {
  @org.springframework.data.annotation.Id
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;
  @Field(type = Text)
  @Column(name = "TITULO", nullable = false)
  private String titulo;
  @Field(type = Text)
  @Column(name = "DESCRIPCION", nullable = false)
  private String descripcion;
  @ManyToOne
  @JoinColumn(name = "CATEGORIA_ID")
  private Categoria categoria;
  @OneToOne
  @JoinColumn(name = "CONTENIDO_MULTIMEDIA_ID")
  private ContenidoMultimedia contenidoMultimedia;
  @Embedded
  private Ubicacion ubicacion;
  @Field(type = Date)
  @Column(name = "FECHA_DEL_HECHO", nullable = false)
  private LocalDateTime fechaDelHecho;
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
               LocalDateTime fechaDelHecho,
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

  public void eliminar(SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho) {
    this.eliminado = true;
    this.fuente.eliminar(solicitudDeEliminacionDeHecho);
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
