package ar.edu.utn.frba.dds;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.client.RestClient.create;

import ar.edu.utn.frba.dds.web.dto.LoginResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class FuenteProxy {

  private LoginResponse loginResponse;

  @PostConstruct
  public void init() {
    this.loginResponse = create("https://api-ddsi.disilab.ar/public/api/login")
        .post()
        .accept(APPLICATION_JSON)
        .contentType(APPLICATION_JSON)
        .body("{\"email\":\"ddsi@gmail.com\", \"password\":\"ddsi2025*\"}")
        .retrieve()
        .toEntity(LoginResponse.class)
        .getBody();
  }

  public String hechos() {
    String accessToken = loginResponse.getData().getAccess_token();
    return create("https://api-ddsi.disilab.ar/public/api/desastres")
        .get()
        .header("Authorization", "Bearer " + accessToken)
        .retrieve()
        .toEntity(String.class)
        .getBody();
  }
}
