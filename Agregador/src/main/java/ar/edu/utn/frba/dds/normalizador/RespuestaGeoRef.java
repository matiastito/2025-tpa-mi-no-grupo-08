package ar.edu.utn.frba.dds.normalizador;

public class RespuestaGeoRef {

  private UbicacionDTO ubicacion;

  public UbicacionDTO getUbicacion() {
    return ubicacion;
  }

  public void setUbicacion(UbicacionDTO ubicacion) {
    this.ubicacion = ubicacion;
  }

  public class UbicacionDTO {
    private ProvinciaDTO provincia;

    public ProvinciaDTO getProvincia() {
      return provincia;
    }

    public void setProvincia(ProvinciaDTO provincia) {
      this.provincia = provincia;
    }

    public class ProvinciaDTO {
      private String id;
      private String nombre;

      public String getId() {
        return id;
      }

      public void setId(String id) {
        this.id = id;
      }

      public String getNombre() {
        return nombre;
      }

      public void setNombre(String nombre) {
        this.nombre = nombre;
      }
    }
  }
}