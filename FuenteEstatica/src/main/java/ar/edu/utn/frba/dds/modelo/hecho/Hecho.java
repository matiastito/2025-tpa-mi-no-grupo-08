package ar.edu.utn.frba.dds.modelo.hecho;

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
import java.time.LocalDateTime;
import java.util.Objects;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "HECHO")
public class Hecho {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;
  @Column(name = "TITULO", nullable = false)
  private String titulo;
  @Column(name = "DESCRIPCION", nullable = false, length = 200000)
  private String descripcion;
  @ManyToOne
  @JoinColumn(name = "CATEGORIA_ID")
  @Cascade(ALL)
  private Categoria categoria;
  @Embedded
  private Ubicacion ubicacion;
  @Column(name = "FECHA_DEL_HECHO", nullable = false)
  private LocalDate fechaDelHecho;
  @Column(name = "FECHA_DE_CARGA", nullable = false)
  private LocalDateTime fechaDeCarga;
  @Enumerated(STRING)
  private HechoOrigen hechoOrigen;

  public Hecho() {
  }

  public Hecho(HechoOrigen hechoOrigen,
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
  }

  public LocalDate getFechaDelHecho() {
    return fechaDelHecho;
  }

  public LocalDateTime getFechaDeCarga() {
    return fechaDeCarga;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public String getTitulo() {
    return titulo;
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
}
