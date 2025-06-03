package ar.edu.utn.frba.dds.modelo.fuente;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.client.RestClient.create;
import static reactor.core.publisher.Mono.empty;
import ar.edu.utn.frba.dds.web.dto.DesastreDTO;
import ar.edu.utn.frba.dds.web.dto.LoginResponseDTO;
import ar.edu.utn.frba.dds.web.dto.PagedResponseDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// FIXME parametrizar url + agregar un adapter + hacerloASincrónico

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

  public Flux<DesastreDTO> hechos() {
    return getAllPages();
  }

  public Flux<DesastreDTO> getAllPages() {
    return getPage(1) // Start with the first page
        .expand(response -> {
          if (!response.isLastPage()) {
            return getPage(response.getNextPage());
          } else {
            return empty();
          }
        })
        .flatMapIterable(PagedResponseDTO::getData);
  }

  private Mono<PagedResponseDTO> getPage(int pageNumber) {
    String accessToken = loginResponse.getData().getAccess_token();
    return WebClient.create("https://api-ddsi.disilab.ar/public/api")
        .get()
        .uri(
            uriBuilder ->
                uriBuilder.path("/desastres")
                    .queryParam("per_page", 10)
                    .queryParam("page", pageNumber).build())
        .accept(APPLICATION_JSON)
        .header("Authorization", "Bearer " + accessToken)
        .retrieve()
        .bodyToMono(PagedResponseDTO.class);
  }
}
