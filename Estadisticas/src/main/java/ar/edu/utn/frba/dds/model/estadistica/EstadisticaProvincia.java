package ar.edu.utn.frba.dds.model.estadistica;

import static jakarta.persistence.GenerationType.IDENTITY;
import ar.edu.utn.frba.dds.model.Provincia;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ESTADISTICA_PROVINCIA")
public class EstadisticaProvincia {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;
  @ManyToOne
  @JoinColumn(name = "PROVINCIA_ID")
  private Provincia provincia;
  @Column(name = "CANTIDAD_DE_HECHOS", nullable = false)
  private long cantidadDeHechos;

  public EstadisticaProvincia(Provincia provincia) {
    this.provincia = provincia;
  }

  public Provincia getProvincia() {
    return provincia;
  }

  public void registrarHecho() {
    cantidadDeHechos++;
  }
}
