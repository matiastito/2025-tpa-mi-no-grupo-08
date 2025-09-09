package ar.edu.utn.frba.dds.normalizador;

import static org.springframework.web.client.RestClient.create;
import ar.edu.utn.frba.dds.modelo.hecho.Ubicacion;


public class NormalizadorDeCoordenadas {
  public static void main(String[] args) {
    String lat = "-27.2741";
    String lon = "-66.7529";
    System.out.println(new NormalizadorDeCoordenadas().provincia(lat, lon));
  }

  public String provincia(String lat, String lon) {
    RespuestaGeoRef response =
        create("https://apis.datos.gob.ar/georef/api/ubicacion?lat=" + lat + "&lon=" + lon)
            .get()
            .retrieve()
            .body(RespuestaGeoRef.class);
    return response.getUbicacion().getProvincia().getNombre();
  }

  public String provincia(Ubicacion ubicacion) {
    return provincia(
        ubicacion.getLatitud().replace(',', '.'),
        ubicacion.getLongitud().replace(',', '.'));
  }
}
