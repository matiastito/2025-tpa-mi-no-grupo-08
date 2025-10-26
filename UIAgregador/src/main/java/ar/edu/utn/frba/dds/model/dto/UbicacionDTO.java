package ar.edu.utn.frba.dds.model.dto;

public class UbicacionDTO {
  private String latitud;
  private String longitud;

  public UbicacionDTO() {
  }

  public UbicacionDTO(String latitud, String longitud) {
    this.latitud = latitud;
    this.longitud = longitud;
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
}