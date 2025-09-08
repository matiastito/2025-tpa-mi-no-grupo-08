package ar.edu.utn.frba.dds.modelo.hecho;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Objects;

@Embeddable
public class Ubicacion {
  private String latitud;
  private String longitud;

  @ManyToOne
  @JoinColumn(name = "PROVINCIA_ID")
  private Provincia provincia;

  private Ubicacion(String latitud, String longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
  }

  public String getLatitud() {
    return latitud;
  }

  public String getLongitud() {
    return longitud;
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
