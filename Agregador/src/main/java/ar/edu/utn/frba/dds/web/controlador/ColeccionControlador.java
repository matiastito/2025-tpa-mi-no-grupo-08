package ar.edu.utn.frba.dds.web.controlador;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class ColeccionControlador {

  /**
   * GET /colecciones
   * Esta ruta expone todas las colecciones disponibles en esta instancia de MetaMapa,
   * independientemente del origen de sus fuentes.
   */
  @GetMapping("/colecciones")
  public String colecciones() {
    ResponseEntity<String> result = RestClient.create("http://localhost:8082").
        get()
        .retrieve()
        .toEntity(String.class);

    return result.getBody();
  }

  /**
   * GET /colecciones/:identificador/hechos
   * Esta ruta, similar a la anterior, permite obtener los hechos asociados a una colección.
   * Acepta los mismos parámetros y devuelve los resultados en el mismo formato.
   *
   * @return
   */
  @GetMapping("/colecciones/{coleccionId}/hechos")
  public String hechosPorColeccion(String coleccionId) {
    ResponseEntity<String> result = RestClient.create("http://localhost:8082").
        get()
        .retrieve()
        .toEntity(String.class);

    return result.getBody();
  }

}

