package ar.edu.utn.frba.dds.web.controlador;

import static java.util.stream.Collectors.toSet;

import ar.edu.utn.frba.dds.servicio.ColeccionServicio;
import ar.edu.utn.frba.dds.web.controlador.dto.HechoDTO;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HechoControlador {

  @Autowired
  private ColeccionServicio coleccionServicio;

  /**
   * GET /hechos
   * Esta ruta expone todos los hechos del sistema y los devuelve como una lista en formato JSON. La misma acepta parámetros para filtrar los resultados:
   * categoría, fecha_reporte_desde, fecha_reporte_hasta,
   * fecha_acontecimiento_desde, fecha_acontecimiento_hasta, ubicacion.
   *
   * @return|
   */
  @GetMapping("/hechos")
  public Collection<HechoDTO> hechos() {
    return coleccionServicio.hechos().stream()
        .map(HechoDTO::toDTO)
        .collect(toSet());
  }
}

