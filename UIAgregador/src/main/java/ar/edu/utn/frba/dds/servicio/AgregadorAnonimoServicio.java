package ar.edu.utn.frba.dds.servicio;

import ar.edu.utn.frba.dds.model.dto.ColeccionDTO;
import ar.edu.utn.frba.dds.model.dto.HechoDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AgregadorAnonimoServicio {
  private final String agregadorServiceUrl;
  private final WebClient webClient;

  @Autowired
  public AgregadorAnonimoServicio(@Value("${agregador.service.url}") String agregadorServiceUrl) {
    this.webClient = WebClient.builder().build();
    this.agregadorServiceUrl = agregadorServiceUrl;
  }

  public List<HechoDTO> hechos(Long coleccionHandleId) {
    return webClient
        .get()
        .uri(agregadorServiceUrl + "/colecciones/" + coleccionHandleId + "/hechos")
        .retrieve()
        .bodyToFlux(HechoDTO.class)
        .collectList()
        .block();
  }

  public ColeccionDTO coleccion(Long coleccionId) {
    return webClient
        .get()
        .uri(agregadorServiceUrl + "/colecciones/" + coleccionId)
        .retrieve()
        .bodyToMono(ColeccionDTO.class)
        .block();
  }

  public List<ColeccionDTO> colecciones() {
    return webClient
        .get()
        .uri(agregadorServiceUrl + "/colecciones")
        .retrieve()
        .bodyToFlux(ColeccionDTO.class)
        .collectList()
        .block();
  }

  public void crearHecho(HechoDTO hechoDTO) {
    webClient
        .post()
        .uri(agregadorServiceUrl + "/hecho")
        .bodyValue(hechoDTO)
        .retrieve()
        .bodyToMono(Void.class)
        .block();
  }
}
