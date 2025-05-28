package ar.edu.utn.frba.dds.web.controlador;

import ar.edu.utn.frba.dds.modelo.fuente.FuenteProxy;
import ar.edu.utn.frba.dds.web.dto.HechoDTO;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HechosControlador {

  @Autowired
  private FuenteProxy fuenteProxy;

  @GetMapping("/hechos")
  public Collection<HechoDTO> hechos(String coleccionId) {
    return fuenteProxy.hechos()
        .stream()
        .map(HechoDTO::toHechoDTO)
        .collect(Collectors.toSet());
  }
}