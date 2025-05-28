package ar.edu.utn.frba.dds.web.controlador;

import static ar.edu.utn.frba.dds.web.controlador.dto.ColeccionDTO.toModel;
import static java.util.stream.Collectors.toSet;

import ar.edu.utn.frba.dds.servicio.ColeccionServicio;
import ar.edu.utn.frba.dds.web.controlador.dto.ColeccionDTO;
import ar.edu.utn.frba.dds.web.controlador.dto.HechoDTO;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ColeccionControlador {

  @Autowired
  private ColeccionServicio coleccionServicio;

  @PostMapping("/colecciones")
  public void coleccion(@RequestBody ColeccionDTO coleccionDTO) {
    coleccionServicio.guardarColeccion(toModel(coleccionDTO));
  }

  /**
   * GET /colecciones
   * Esta ruta expone todas las colecciones disponibles en esta instancia de MetaMapa,
   * independientemente del origen de sus fuentes.
   */
  @GetMapping("/colecciones")
  public Collection<ColeccionDTO> colecciones() {
    return coleccionServicio.colecciones()
        .stream()
        .map(ColeccionDTO::toDTO)
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
  public Collection<HechoDTO> hechosPorColeccion(@PathVariable Long coleccionId) {
    return coleccionServicio
        .coleccion(coleccionId)
        .hechos()
        .stream()
        .map(HechoDTO::toDTO)
        .collect(toSet());
  }
}