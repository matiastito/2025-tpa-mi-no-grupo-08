package ar.edu.utn.frba.dds.normalizador;

import static org.springframework.web.client.RestClient.create;


public class NormalizadorDeCoordenadas {
  public static void main(String[] args) {
    String response = create("https://apis.datos.gob.ar/georef/api/ubicacion?lat=-27.2741&lon=-66.7529")
        .get()
        .retrieve()
        .body(String.class);
    System.out.println(response);
  }

}
