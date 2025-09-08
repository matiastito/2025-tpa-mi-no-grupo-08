package ar.edu.utn.frba.dds.modelo.administrador;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ADMINISTRADOR")
public class Administrador {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;
  @Column(name = "NOMBRE", nullable = false)
  private String nombre;

  public Administrador() {
  }

  public Administrador(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }
}
