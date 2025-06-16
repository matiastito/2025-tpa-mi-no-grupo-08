package ar.edu.utn.frba.dds.modelo.fuente;

import static java.util.Objects.requireNonNull;
import static org.springframework.web.client.RestClient.create;

import ar.edu.utn.frba.dds.web.dto.HechoDTO;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(prefix = "proxy", name = "type", havingValue = "MetaMapa")
public class FuenteProxyMetaMapa implements FuenteProxy {
  private String baseUrl;

  public FuenteProxyMetaMapa(@Value("${proxy.baseUrl}") String baseUrl) {
    this.baseUrl = baseUrl;
  }

  @Override
  public Collection<HechoDTO> hechos() {
    ResponseEntity<List<HechoDTO>> result = create("http://" + baseUrl + "/hechos")
        .get()
        .retrieve()
        .toEntity(new ParameterizedTypeReference<>() {
        });
    return new HashSet<>(requireNonNull(result.getBody()));
  }

  @Override
  public void eliminar(HechoDTO hecho) {
    create("http://" + baseUrl + "/hechos")
        .put()
        .body(hecho)
        .retrieve()
        .toEntity(new ParameterizedTypeReference<>() {
        });
  }
}
