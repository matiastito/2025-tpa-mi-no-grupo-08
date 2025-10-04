package ar.edu.utn.frba.dds.modelo.hecho;

import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "CATEGORIA")
public class Categoria {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;
  @Column(name = "NOMBRE", nullable = false)
  private String nombre;
  @ElementCollection
  @CollectionTable(name = "CATEGORIA_SINONIMO") // Specifies the name of the join table
  @Column(name = "SINONIMO")
  private List<String> sinonimos = new ArrayList<>();

  public Categoria() {
  }

  public Categoria(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Categoria categoria)) return false;
    return nombre.equalsIgnoreCase(categoria.nombre);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(nombre);
  }

  public String getNombre() {
    return nombre;
  }

  public boolean esSinonimo(String potencialCategoria) {
    return sinonimos.contains(potencialCategoria);
  }
}
