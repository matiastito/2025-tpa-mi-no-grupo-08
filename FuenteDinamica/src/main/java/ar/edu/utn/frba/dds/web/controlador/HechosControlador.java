package ar.edu.utn.frba.dds.web.controlador;

import static ar.edu.utn.frba.dds.web.dto.HechoDTO.toHecho;
import static java.util.stream.Collectors.toSet;

import ar.edu.utn.frba.dds.servicio.HechoServicio;
import ar.edu.utn.frba.dds.web.dto.HechoDTO;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//FIXME agregar la modificacion de los hechos
@RestController
public class HechosControlador {

  @Autowired
  private HechoServicio hechoServicio;

  @PostMapping("/hechos")
  public void hecho(@RequestBody HechoDTO hechoDTO) {
    hechoServicio.guardarHecho(toHecho(hechoDTO));
  }

  @GetMapping("/hechos")
  public Collection<HechoDTO> hechos() {
    return hechoServicio.dameHechos()
        .stream()
        .map(HechoDTO::toHechoDTO)
        .collect(toSet());
  }
}