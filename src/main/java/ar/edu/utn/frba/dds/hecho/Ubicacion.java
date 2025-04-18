package ar.edu.utn.frba.dds.hecho;

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
}
