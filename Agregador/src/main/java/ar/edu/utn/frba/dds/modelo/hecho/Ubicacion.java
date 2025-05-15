package ar.edu.utn.frba.dds.modelo.hecho;

import java.util.Objects;

public class Ubicacion {
  private String latitud;
  private String longitud;

  private Ubicacion(String latitud, String longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
  }

  public static Ubicacion crearUbicacion(String latitud, String longitud) {
    return new Ubicacion(latitud, longitud);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Ubicacion ubicacion)) return false;
    return Objects.equals(latitud, ubicacion.latitud) && Objects.equals(longitud, ubicacion.longitud);
  }

  @Override
  public int hashCode() {
    return Objects.hash(latitud, longitud);
  }
}
