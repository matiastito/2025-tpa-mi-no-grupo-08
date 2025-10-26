package ar.edu.utn.frba.dds.modelo.hecho;

import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Ubicacion {
  private String latitud;
  private String longitud;

  public Ubicacion() {
  }

  private Ubicacion(String latitud, String longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
  }

  public static Ubicacion crearUbicacion(String latitud, String longitud) {
    return new Ubicacion(latitud, longitud);
  }

  public String getLatitud() {
    return latitud;
  }

  public String getLongitud() {
    return longitud;
  }

  public void setLongitud(String longitud) {
    this.longitud = longitud;
  }

  public void setLatitud(String latitud) {
    this.latitud = latitud;
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
