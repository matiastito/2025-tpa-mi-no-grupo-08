package ar.edu.utn.frba.dds.web.controlador;

import static ar.edu.utn.frba.dds.web.controlador.dto.ColeccionDTO.toColeccionDTO;
import static java.util.stream.Collectors.toSet;

import ar.edu.utn.frba.dds.servicio.ColeccionServicio;
import ar.edu.utn.frba.dds.web.controlador.dto.ColeccionDTO;
import java.util.Collection;
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
  public Collection<ColeccionDTO> colecciones() {
    return coleccionServicio.colecciones()
        .stream()
        .map(ColeccionDTO::toColeccionDTO)
        .collect(toSet());
  }

  /**
   * GET /colecciones/:identificador/hechos
   * Esta ruta, similar a la anterior, permite obtener los hechos asociados a una colección.
   * Acepta los mismos parámetros y devuelve los resultados en el mismo formato.
   *
   * @return
   */
  @GetMapping("/colecciones/{coleccionId}/hechos")
  public ColeccionDTO hechosPorColeccion(String coleccionId) {
    return toColeccionDTO(coleccionServicio.coleccion(coleccionId));
  }
}