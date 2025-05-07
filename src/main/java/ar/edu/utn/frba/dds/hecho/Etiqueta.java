package ar.edu.utn.frba.dds.hecho;

import java.util.Objects;

public class Etiqueta {
  private String nombre;

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
