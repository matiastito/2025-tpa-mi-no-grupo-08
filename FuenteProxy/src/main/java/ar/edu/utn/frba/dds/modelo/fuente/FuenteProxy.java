package ar.edu.utn.frba.dds.modelo.fuente;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.client.RestClient.create;

import ar.edu.utn.frba.dds.web.dto.DesastreDTO;
import ar.edu.utn.frba.dds.web.dto.LoginResponseDTO;
import ar.edu.utn.frba.dds.web.dto.PagedResponseDTO;
import jakarta.annotation.PostConstruct;
import java.util.Collection;
import org.springframework.stereotype.Component;

// FIXME parametrizar url + agregar un adapter + hacerloASincronicoó

@Component
public class FuenteProxy {

  private LoginResponseDTO loginResponse;

  @PostConstruct
  public void init() {
    this.loginResponse = create("https://api-ddsi.disilab.ar/public/api/login")
        .post()
        .accept(APPLICATION_JSON)
        .contentType(APPLICATION_JSON)
        .body("{\"email\":\"ddsi@gmail.com\", \"password\":\"ddsi2025*\"}")
        .retrieve()
        .toEntity(LoginResponseDTO.class)
        .getBody();
  }

  public Collection<DesastreDTO> hechos() {
    String accessToken = loginResponse.getData().getAccess_token();
    return create("https://api-ddsi.disilab.ar/public/api/desastres")
        .get()
        .header("Authorization", "Bearer " + accessToken)
        .retrieve()
        .toEntity(PagedResponseDTO.class)
        .getBody()
        .getData();
  }


  public class HechoDTO {
    private String titulo;

    public HechoDTO() {
    }

    public void setTitulo(String titulo) {
      this.titulo = titulo;
    }

    public String getTitulo() {
      return titulo;
    }
  }
}
