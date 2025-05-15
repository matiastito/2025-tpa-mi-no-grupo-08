package ar.edu.utn.frba.dds.web.controlador;

import static org.springframework.web.client.RestClient.create;
import ar.edu.utn.frba.dds.servicio.ColeccionServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ColeccionControlador {

  private ColeccionServicio coleccionServicio;

  /**
   * GET /colecciones
   * Esta ruta expone todas las colecciones disponibles en esta instancia de MetaMapa,
   * independientemente del origen de sus fuentes.
   */
  @GetMapping("/colecciones")
  public String colecciones() {
    return coleccionServicio.colecciones();
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
    ResponseEntity<String> result = create("http://localhost:8082").
        get()
        .retrieve()
        .toEntity(String.class);
    return result.getBody();
  }

}