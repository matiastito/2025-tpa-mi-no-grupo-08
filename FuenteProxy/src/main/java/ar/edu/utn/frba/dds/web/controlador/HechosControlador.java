package ar.edu.utn.frba.dds.web.controlador;

import static java.util.Collections.EMPTY_SET;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class HechosControlador {

  @GetMapping("/")
  public Collection<?> home() {
    return EMPTY_SET;
  }
}