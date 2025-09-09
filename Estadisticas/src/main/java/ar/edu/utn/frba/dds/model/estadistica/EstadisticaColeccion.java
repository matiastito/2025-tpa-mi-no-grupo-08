package ar.edu.utn.frba.dds.model.estadistica;

import static jakarta.persistence.GenerationType.IDENTITY;
import static org.hibernate.annotations.CascadeType.ALL;
import ar.edu.utn.frba.dds.model.Provincia;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.Cascade;

import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "ESTADISTICA_COLECCION")
public class EstadisticaColeccion {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;

  @Column(name = "HANDLE", nullable = false)
  private String handle;

  @ManyToMany
  @Cascade(ALL)
  @JoinTable(
      name = "ESTADISTICA_COLECCION_POR_PROVINCIA",
      joinColumns = @JoinColumn(name = "ESTADISTICA_COLECCION_ID"),
      inverseJoinColumns = @JoinColumn(name = "ESTADISTICA_POR_PROVINCA_ID")
  )
  private List<EstadisticaProvincia> estadisticasPorProvincia;

  public EstadisticaColeccion(String handle) {
    this.handle = handle;
  }

  public void registrarHecho(Provincia provincia) {
    Optional<EstadisticaProvincia> estadisticaProvincia = estadisticasPorProvincia.stream().filter(
            ep -> ep.getProvincia().getId() == provincia.getId())
        .findFirst();
    if (estadisticaProvincia.isEmpty()) {
      EstadisticaProvincia ep = new EstadisticaProvincia(provincia);
      ep.registrarHecho();
    } else {
      estadisticaProvincia.get().registrarHecho();
    }
  }

  public String getHandle() {
    return handle;
  }
}
