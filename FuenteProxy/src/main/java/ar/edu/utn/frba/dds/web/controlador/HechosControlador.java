package ar.edu.utn.frba.dds.web.controlador;

import ar.edu.utn.frba.dds.modelo.fuente.CacheParaFuenteProxy;
import ar.edu.utn.frba.dds.web.dto.HechoDTO;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HechosControlador {
  @Autowired
  private CacheParaFuenteProxy cacheParaFuenteProxy;

  @GetMapping("/hechos")
  public Collection<HechoDTO> hechos() {
    return cacheParaFuenteProxy.hechos();
  }
}