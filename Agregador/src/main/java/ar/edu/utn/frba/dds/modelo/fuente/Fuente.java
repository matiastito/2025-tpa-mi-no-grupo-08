package ar.edu.utn.frba.dds.modelo.fuente;

import static org.springframework.web.client.RestClient.create;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

public class Fuente {
  private String baseUrl;

  public Fuente(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public List<Hecho> hechos() {
    ResponseEntity<List<Hecho>> result = create("http://" + baseUrl + "/hechos").
        get()
        .retrieve()
        .toEntity(new ParameterizedTypeReference<List<Hecho>>() {
        });
    return result.getBody();
  }
}
