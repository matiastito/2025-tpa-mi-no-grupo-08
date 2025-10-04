package ar.edu.utn.frba.dds.modelo.hecho;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "ETIQUETA")
public class Etiqueta {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;
  @Column(name = "NOMBRE", nullable = false)
  private String nombre;

  public Etiqueta() {
  }

  public Etiqueta(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Etiqueta etiqueta)) return false;
    return nombre.equalsIgnoreCase(etiqueta.nombre);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(nombre);
  }

  public String getNombre() {
    return nombre;
  }

}
