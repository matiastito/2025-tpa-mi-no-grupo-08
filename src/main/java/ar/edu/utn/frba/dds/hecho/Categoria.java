package ar.edu.utn.frba.dds.hecho;

import java.util.Objects;

public class Categoria {
  private String nombre;

  public Categoria(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Categoria categoria)) return false;
    return Objects.equals(nombre, categoria.nombre);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(nombre);
  }

  public String getNombre() {
    return nombre;
  }
}
