package ar.edu.utn.frba.dds.modelo.fuente;

import static org.springframework.web.client.RestClient.create;
import org.springframework.http.ResponseEntity;

public class Fuente {
  private String baseUrl;

  public Fuente(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public String hechos() {
    ResponseEntity<String> result = create("http://" + baseUrl + "/hechos").
        get()
        .retrieve()
        .toEntity(String.class);
    return result.getBody();
  }
}
