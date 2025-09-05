package ar.edu.utn.frba.dds.modelo.fuente;

import static java.util.Objects.requireNonNull;
import static org.springframework.web.client.RestClient.create;

import ar.edu.utn.frba.dds.web.dto.HechoDTO;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;

public class FuenteProxyMetaMapaImpl implements FuenteProxyMetaMapa {
  private String baseUrl;

  public FuenteProxyMetaMapaImpl(String baseUrl) {
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
