package ar.edu.utn.frba.dds.modelo.fuente;

import static java.util.stream.Collectors.toSet;
import static org.springframework.web.client.RestClient.create;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.web.controlador.dto.HechoDTO;
import java.util.Collection;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

public class Fuente {
  private final String baseUrl;
  private final TipoFuente tipoFuente;

  public Fuente(String baseUrl, TipoFuente tipoFuente) {
    this.baseUrl = baseUrl;
    this.tipoFuente = tipoFuente;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public TipoFuente getTipoFuente() {
    return tipoFuente;
  }

  public Collection<Hecho> hechos() {
    ResponseEntity<List<HechoDTO>> result =
        create("http://" + baseUrl + "/hechos")
            .get()
            .retrieve()
            .toEntity(new ParameterizedTypeReference<>() {
            });
    return result
        .getBody()
        .stream()
        .map(HechoDTO::toHecho)
        .collect(toSet());
  }
}


