package ar.edu.utn.frba.dds.normalizador;

import static org.springframework.web.client.RestClient.create;


public class NormalizadorDeCoordenadas {
  public static void main(String[] args) {
    String lat = "-27.2741";
    String lon = "-66.7529";
    RespuestaGeoRef response =
        create("https://apis.datos.gob.ar/georef/api/ubicacion?lat=" + lat + "&lon=" + lon)
            .get()
            .retrieve()
            .body(RespuestaGeoRef.class);
    System.out.println(response.getUbicacion().getProvincia().getNombre());
  }

}
