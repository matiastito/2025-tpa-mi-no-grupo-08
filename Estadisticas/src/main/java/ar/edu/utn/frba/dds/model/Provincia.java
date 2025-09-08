package ar.edu.utn.frba.dds.model;

import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PROVINCIA")
public class Provincia {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;

  @Column(name = "NOMBRE", nullable = false)
  private String nombre;

  public Provincia() {
  }
}
