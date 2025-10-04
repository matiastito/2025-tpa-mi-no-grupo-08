package ar.edu.utn.frba.dds.web.controlador;

import static ar.edu.utn.frba.dds.modelo.fuente.TipoFuente.PROXY_DDS;
import static ar.edu.utn.frba.dds.web.dto.FuenteDTO.toModelAPIdeDDS;

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

  @PostMapping("/fuentes")
  public void fuentes(@RequestBody FuenteDTO fuenteDTO) {
    if (PROXY_DDS.equals(fuenteDTO.getTipoFuente())) {
      fuenteProxyServicio.guardar(toModelAPIdeDDS(fuenteDTO));
    } else {
      throw new RuntimeException("Tipo de fuente no reconocida.");
    }
  }
}
