package ar.edu.utn.frba.dds.web.controlador;

import static org.springframework.web.client.RestClient.create;

import ar.edu.utn.frba.dds.web.controlador.dto.HechoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HechoControlador {

  /**
   * GET /hechos
   * Esta ruta expone todos los hechos del sistema y los devuelve como una lista en formato JSON. La misma acepta parámetros para filtrar los resultados:
   * categoría, fecha_reporte_desde, fecha_reporte_hasta,
   * fecha_acontecimiento_desde, fecha_acontecimiento_hasta, ubicacion.
   *
   * @return|
   */
  @GetMapping("/hechos")
  public HechoDTO hechos() {
    ResponseEntity<HechoDTO> result = create("http://localhost:8082").
        get()
        .retrieve()
        .toEntity(HechoDTO.class);
    return result.getBody();
  }

}

