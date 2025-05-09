package ar.edu.utn.frba.dds.web.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class HechoControlador {

  @GetMapping("/")
  public String home() {
    ResponseEntity<String> result = RestClient.create("http://localhost:8082").
        get()
        .retrieve()
        .toEntity(String.class);

    return result.getBody();
  }
}

