package ar.edu.utn.frba.dds.web.controlador;

import static ar.edu.utn.frba.dds.web.dto.FuenteDTO.toModel;

import ar.edu.utn.frba.dds.servicio.FuenteProxyServicio;
import ar.edu.utn.frba.dds.web.dto.FuenteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FuenteControlador {

  @Autowired
  private FuenteProxyServicio fuenteProxyServicio;

  @PostMapping("/fuente")
  public void fuente(@RequestBody FuenteDTO fuenteDTO) {
    fuenteProxyServicio.guardar(toModel(fuenteDTO));
  }
}
