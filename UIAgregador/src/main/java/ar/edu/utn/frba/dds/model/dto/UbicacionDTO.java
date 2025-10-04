package ar.edu.utn.frba.dds.model.dto;

public class UbicacionDTO {
  private double lat;
  private double lon;

  public UbicacionDTO() {

  }

  public UbicacionDTO(double lat, double lon) {
    this.lat = lat;
    this.lon = lon;
  }

  public double getLat() {
    return lat;
  }

  public double getLon() {
    return lon;
  }
}
