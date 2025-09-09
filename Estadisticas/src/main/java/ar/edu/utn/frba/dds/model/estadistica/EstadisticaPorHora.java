package ar.edu.utn.frba.dds.model.estadistica;

import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ESTADISTICA_POR_HORA")
public class EstadisticaPorHora {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;
  @Column(name = "HORA_DEL_HECHO", nullable = false)
  private int horaDelHecho;
  @Column(name = "CANTIDAD_DE_HECHOS", nullable = false)
  private long cantidadDeHechos;

  public EstadisticaPorHora() {
  }

  public EstadisticaPorHora(int horaDelHecho) {
    this.horaDelHecho = horaDelHecho;
  }

  public int getHora() {
    return horaDelHecho;
  }

  public void registrarHecho() {
    cantidadDeHechos++;
  }
}
