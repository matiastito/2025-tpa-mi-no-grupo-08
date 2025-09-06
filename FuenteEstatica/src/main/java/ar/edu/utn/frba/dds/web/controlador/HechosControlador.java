package ar.edu.utn.frba.dds.web.controlador;

import static java.util.stream.Collectors.toList;

import ar.edu.utn.frba.dds.servicio.HechoServicio;
import ar.edu.utn.frba.dds.web.dto.HechoDTO;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HechosControlador {

  @Autowired
  private HechoServicio hechoServicio;

  @GetMapping("/hechos")
  public Collection<HechoDTO> hechos() {
    return hechoServicio.
        hechos()
        .stream()
        .map(HechoDTO::toDTO)
        .collect(toList());
  }
}