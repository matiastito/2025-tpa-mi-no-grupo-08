package ar.edu.utn.frba.dds.model.estadistica;

import static jakarta.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.CascadeType.ALL;

import ar.edu.utn.frba.dds.model.Categoria;
import ar.edu.utn.frba.dds.model.Provincia;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "ESTADISTICA_CATEGORIA")
public class EstadisticaCategoria {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;

  @ManyToOne
  @JoinColumn(name = "CATEGORIA_ID")
  private Categoria categoria;

  @ManyToMany
  @Cascade(ALL)
  @JoinTable(
      name = "ESTADISTICA_CATEGORIA_POR_PROVINCA",
      joinColumns = @JoinColumn(name = "CATEGORIA_ID"),
      inverseJoinColumns = @JoinColumn(name = "ESTADISTICA_PROVINCA_ID")
  )
  private List<EstadisticaProvincia> estadisticaProvincias = new ArrayList<>();
  @ManyToMany
  @Cascade(ALL)
  @JoinTable(
      name = "ESTADISTICA_CATEGORIA_POR_HORA",
      joinColumns = @JoinColumn(name = "CATEGORIA_ID"),
      inverseJoinColumns = @JoinColumn(name = "ESTADISTICA_POR_HORA_ID")
  )
  private List<EstadisticaPorHora> estadisticaHoras = new ArrayList<>();

  @Column(name = "CANTIDAD_DE_HECHOS", nullable = false)
  private long cantidadDeHechos;

  public EstadisticaCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public EstadisticaCategoria() {
  }

  public long getCantidadDeHechos() {
    return cantidadDeHechos;
  }

  public List<EstadisticaProvincia> getEstadisticaProvincias() {
    return estadisticaProvincias;
  }

  public List<EstadisticaPorHora> getEstadisticaHoras() {
    return estadisticaHoras;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void registrarHecho(Provincia provincia, int horaDelHecho) {
    Optional<EstadisticaProvincia> estadisticaProvincia = estadisticaProvincias.stream()
        .filter(ep -> ep.getProvincia().getId() == provincia.getId())
        .findFirst();
    if (estadisticaProvincia.isEmpty()) {
      EstadisticaProvincia ep = new EstadisticaProvincia(provincia);
      ep.registrarHecho();
    } else {
      estadisticaProvincia.get().registrarHecho();
    }

    Optional<EstadisticaPorHora> estadisticaHora = estadisticaHoras.stream()
        .filter(eh -> eh.getHora() == horaDelHecho)
        .findFirst();
    if (estadisticaHora.isEmpty()) {
      EstadisticaPorHora eh = new EstadisticaPorHora(horaDelHecho);
      eh.registrarHecho();
    } else {
      estadisticaHora.get().registrarHecho();
    }

    cantidadDeHechos++;
  }
}
