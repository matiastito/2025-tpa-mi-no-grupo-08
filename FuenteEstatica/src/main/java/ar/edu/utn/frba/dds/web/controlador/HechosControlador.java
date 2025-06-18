package ar.edu.utn.frba.dds.web.controlador;

import static ar.edu.utn.frba.dds.web.dto.HechoDTO.toHecho;
import static java.util.stream.Collectors.toList;
import ar.edu.utn.frba.dds.servicio.HechoServicio;
import ar.edu.utn.frba.dds.web.dto.HechoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class HechosControlador {

  @Autowired
  private HechoServicio hechoServicio;

  @GetMapping("/hechos")
  public Collection<HechoDTO> home() {
    return hechoServicio.hechos()
        .stream()
        .map(HechoDTO::toDTO)
        .collect(toList());
  }

  @PutMapping("/hechos")
  public void modificarHecho(@RequestBody HechoDTO hechoDTO) {
    hechoServicio.modificarHecho(toHecho(hechoDTO));
  }
}