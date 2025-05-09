package ar.edu.utn.frba.dds.web.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class SolicitudControlador {

  /**
   * POST /solicitudes
   * Permite crear solicitudes de eliminación,
   * enviando los datos de la solicitud como un JSON a través del cuerpo (body)
   * de la misma.
   */
  @GetMapping("/solicitudes")
  public String solicitudes() {
    ResponseEntity<String> result = RestClient.create("http://localhost:8082").
        get()
        .retrieve()
        .toEntity(String.class);

    return result.getBody();
  }

}

