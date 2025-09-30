package ar.edu.utn.frba.dds.servicio;

import static java.net.URI.create;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AgregadorServicio {
  private final RestTemplate restTemplate;

  private final URI apiUrl = create("http://localhost:8080/");

  @Autowired
  public AgregadorServicio(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public String hechos() {
    return restTemplate.getForObject(apiUrl, String.class);
  }

  public <T> List<T> colecciones() {
    ParameterizedTypeReference<List<T>> responseType =
        new ParameterizedTypeReference<List<T>>() {
        };
    ResponseEntity<List<T>> responseEntity =
        restTemplate.exchange(apiUrl.resolve("./colecciones"), GET, null, responseType);
    return responseEntity.getBody();
  }

  //Para fwd solicitudes de eliminación
  public <T, R> R postDataToApi(T requestBody, Class<R> responseType) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(APPLICATION_JSON); // Set content type as needed

    HttpEntity<T> requestEntity = new HttpEntity<>(requestBody, headers);

    return restTemplate.postForObject(apiUrl, requestEntity, responseType);
  }
}
