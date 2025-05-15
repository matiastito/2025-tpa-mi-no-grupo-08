package ar.edu.utn.frba.dds.modelo.fuente;

import static org.springframework.web.client.RestClient.create;
import ar.edu.utn.frba.dds.web.dto.HechoDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class Fuente {
  private String baseUrl;

  public Fuente(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  public List<HechoDTO> hechos() {
    ResponseEntity<List<HechoDTO>> result = create("http://" + baseUrl + "/hechos").
        get()
        .retrieve()
        .toEntity(new ParameterizedTypeReference<List<HechoDTO>>() {
        });
    return result.getBody();
  }
}
