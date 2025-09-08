package ar.edu.utn.frba.dds.modelo.coleccion;

import static ar.edu.utn.frba.dds.modelo.fuente.TipoFuente.METAMAPA;
import static jakarta.persistence.EnumType.STRING;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;
import static org.hibernate.annotations.CascadeType.ALL;

import ar.edu.utn.frba.dds.consenso.TipoConsenso;
import ar.edu.utn.frba.dds.modelo.coleccion.filtro.FiltroDeHecho;
import ar.edu.utn.frba.dds.modelo.coleccion.filtro.FiltrosParaHecho;
import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "COLECCION")
public class Coleccion {
  @Id
  @GeneratedValue
  private long id;
  @Column(name = "HANDLE", nullable = false)
  private String handle;
  @Column(name = "TITULO", nullable = false)
  private String titulo;
  @Column(name = "DESCRIPCION", nullable = false)
  private String descripcion;
  @Enumerated(STRING)
  private TipoConsenso tipoConsenso;
  @ManyToMany
  @Cascade(ALL)
  @JoinTable(
      name = "COLECCION_FUENTE",
      joinColumns = @JoinColumn(name = "COLECCION_ID"),
      inverseJoinColumns = @JoinColumn(name = "FUENTE_ID")
  )
  private List<Fuente> fuentes;
  @ManyToMany
  @Cascade(ALL)
  @JoinTable(
      name = "COLECCION_HECHOS",
      joinColumns = @JoinColumn(name = "COLECCION_ID"),
      inverseJoinColumns = @JoinColumn(name = "HECHO_ID")
  )
  private Set<Hecho> hechos;
  @OneToOne
  @JoinColumn(name = "COLECCION_ID")
  private FiltrosParaHecho criteriosDePertenencia;

  public Coleccion() {
  }

  public Coleccion(String titulo, String descripcion, Fuente... fuentes) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.fuentes = stream(fuentes).toList();
    this.criteriosDePertenencia = new FiltrosParaHecho();
  }

  public String getTitulo() {
    return titulo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public List<Fuente> getFuentes() {
    return this.fuentes;
  }

  public void setTipoConsenso(TipoConsenso tipoConsenso) {
    this.tipoConsenso = tipoConsenso;
  }

  public void agregarFiltro(FiltroDeHecho filtro) {
    this.criteriosDePertenencia.agregarFiltro(filtro);
  }

  public void refrescar() {
    fuentes.forEach(f -> {
          if (!METAMAPA.equals(f.getTipoFuente())) {
            this.hechos.addAll(
                f.hechos().stream()
                    .filter(criteriosDePertenencia::aplicarFiltros)
                    .collect(toSet()));
          }
        }
    );
  }

  public Collection<Collection<Hecho>> hechosAgrupadosPorFuente() {
    return fuentes.stream()
        .map(Fuente::hechos)
        .collect(toSet());
  }

  public Collection<Hecho> hechos() {
    fuentes.forEach(f -> {
          if (METAMAPA.equals(f.getTipoFuente())) {
            this.hechos.addAll(
                f.hechos().stream()
                    .filter(criteriosDePertenencia::aplicarFiltros)
                    .collect(toSet()));
          }
        }
    );
    return this.hechos
        .stream()
        .filter(hecho -> !hecho.estaEliminado())
        .filter(criteriosDePertenencia::aplicarFiltros)
        .collect(toSet());
  }

  public TipoConsenso getTipoConsenso() {
    return tipoConsenso;
  }
}