package ar.edu.utn.frba.dds.modelo.fuente;

import static java.lang.System.err;
import static java.lang.System.out;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.client.RestClient.create;
import static reactor.core.publisher.Flux.empty;
import static reactor.core.publisher.Flux.range;

import ar.edu.utn.frba.dds.web.dto.HechoDTO;
import ar.edu.utn.frba.dds.web.dto.LoginResponseDTO;
import ar.edu.utn.frba.dds.web.dto.PagedResponseDTO;
import jakarta.annotation.PostConstruct;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@ConditionalOnProperty(prefix = "proxy", name = "type", havingValue = "APIDDS")
public class FuenteProxyAPIdeDDS implements FuenteProxy {
  private CacheParaFuenteProxyAPIdeDDS cacheParaFuenteProxyAPIdeDDS;
  private String baseUrl;
  private static final Integer pageSize = 150;
  private static final Integer cantidadDeLlamadasConcurrentes = 10;

  private LoginResponseDTO loginResponse;

  @Autowired
  public FuenteProxyAPIdeDDS(CacheParaFuenteProxyAPIdeDDS cacheParaFuenteProxyAPIdeDDS, @Value("${proxy.baseUrl}") String baseUrl) {
    this.cacheParaFuenteProxyAPIdeDDS = cacheParaFuenteProxyAPIdeDDS;
    this.baseUrl = baseUrl;
  }

  @PostConstruct
  public void init() {
    this.loginResponse = create(baseUrl + "/login")
        .post()
        .accept(APPLICATION_JSON)
        .contentType(APPLICATION_JSON)
        .body("{\"email\":\"ddsi@gmail.com\", \"password\":\"ddsi2025*\"}")
        .retrieve()
        .toEntity(LoginResponseDTO.class)
        .getBody();
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

  public Collection<HechoDTO> hechos() {
    if (cacheParaFuenteProxyAPIdeDDS.isRecent())
      return this.cacheParaFuenteProxyAPIdeDDS.hechos();
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
              .map(HechoDTO::toHechoDTO);
        })
        .collectList()
        .doOnSuccess(hechos -> {
          this.cacheParaFuenteProxyAPIdeDDS.actualizar(hechos);
        })
        .block();
  }
}
