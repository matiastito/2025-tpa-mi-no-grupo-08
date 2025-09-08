package ar.edu.utn.frba.dds.modelo.fuente;

import static ar.edu.utn.frba.dds.web.dto.HechoDTO.toHechoDTO;
import static java.lang.System.err;
import static java.lang.System.out;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.client.RestClient.create;
import static reactor.core.publisher.Flux.empty;
import static reactor.core.publisher.Flux.range;

import ar.edu.utn.frba.dds.web.dto.HechoDTO;
import ar.edu.utn.frba.dds.web.dto.apiDeDDS.LoginResponseDTO;
import ar.edu.utn.frba.dds.web.dto.apiDeDDS.PagedResponseDTO;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Entity
@DiscriminatorValue("FUENTE_PROXY_API_DE_DDS")
public class FuenteProxyAPIdeDDS extends FuenteProxy {
  private static final Integer pageSize = 150;
  private static final Integer cantidadDeLlamadasConcurrentes = 10;

  @Column(name = "BASE_URL", nullable = false)
  private String baseUrl;

  @Transient
  private LoginResponseDTO loginResponse;

  public FuenteProxyAPIdeDDS() {
  }

  public FuenteProxyAPIdeDDS(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  private void login() {
    this.loginResponse = create(baseUrl + "/login")
        .post()
        .accept(APPLICATION_JSON)
        .contentType(APPLICATION_JSON)
        .body("{\"email\":\"ddsi@gmail.com\", \"password\":\"ddsi2025*\"}")
        .retrieve()
        .toEntity(LoginResponseDTO.class)
        .getBody();
  }

  public List<HechoDTO> hechos() {
    return fetchPage(0, pageSize)
        .flatMapMany(firstPage -> {
          int totalPages = firstPage.getLastPage();
          out.println("Paginas Totales: " + totalPages);
          if (totalPages == 0) {
            return empty();
          }
          Flux<Integer> pageNumbers = range(0, totalPages);
          return pageNumbers.flatMap(page -> {
                out.println("Obteniendo pagina: " + page);
                return fetchPage(page, pageSize)
                    .doOnError(e -> err.println("Ocurrió un error al obtener la pagina " + page + ": " + e.getMessage()));
              }, cantidadDeLlamadasConcurrentes)
              .flatMapIterable(PagedResponseDTO::getData)
              .map(desastreDTO -> toHechoDTO(desastreDTO));
        })
        .collectList()
        .block();
  }

  private Mono<PagedResponseDTO> fetchPage(int page, int size) {
    String accessToken = loginResponse.getData().getAccess_token();
    return WebClient.create(baseUrl)
        .get()
        .uri(uriBuilder -> uriBuilder
            .path("/desastres")
            .queryParam("page", page)
            .queryParam("per_page", size)
            .build())
        .header("Authorization", "Bearer " + accessToken)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<PagedResponseDTO>() {
        });
  }

}
