package ar.edu.utn.frba.dds.model.estadistica;

import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ESTADISTICA_SOLICITUDES")
public class EstadisticaSolicitudes {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;

  @Column(name = "CANTIADAD_DE_SOLICITUDES_SPAM", nullable = false)
  private long cantidadDeSolicutudesSpam;

  public EstadisticaSolicitudes() {
  }

  public EstadisticaSolicitudes(int cantidadDeSolicutudesSpam) {
    this.cantidadDeSolicutudesSpam = cantidadDeSolicutudesSpam;
  }
}
