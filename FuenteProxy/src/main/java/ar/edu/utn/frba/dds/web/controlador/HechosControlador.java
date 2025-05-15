package ar.edu.utn.frba.dds.web.controlador;

import static org.springframework.web.client.RestClient.create;
import ar.edu.utn.frba.dds.web.dto.LoginResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HechosControlador {

  private LoginResponse loginResponse;

  @PostConstruct
  public void init() {
    this.loginResponse = create("https://api-ddsi.disilab.ar/public/api/login")
        .post()
        .header("accept", "application/json")
        .header("Content-Type", "application/json")
        .body("{\"email\":\"ddsi@gmail.com\", \"password\":\"ddsi2025*\"}")
        .retrieve()
        .toEntity(LoginResponse.class)
        .getBody();
  }

  @GetMapping("/hechos")
  public String hechos(String coleccionId) {
    ResponseEntity<String> result = create("https://api-ddsi.disilab.ar/public/api/desastres")
        .get()
        .header("Bearer", loginResponse.getData().getAccess_token())
        .retrieve()
        .toEntity(String.class);
    return result.getBody();
  }
}