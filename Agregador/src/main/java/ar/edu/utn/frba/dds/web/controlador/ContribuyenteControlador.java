package ar.edu.utn.frba.dds.web.controlador;

import ar.edu.utn.frba.dds.servicio.ContribuyenteServicio;
import ar.edu.utn.frba.dds.web.controlador.dto.ContribuyenteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContribuyenteControlador {

  @Autowired
  private ContribuyenteServicio contribuyenteServicio;

  @PostMapping("/contribuyentes")
  public void crearContribuyente(@RequestBody ContribuyenteDTO contribuyenteDTO) {
    contribuyenteServicio.guardar(contribuyenteDTO.getNombre(), contribuyenteDTO.getApellido());
  }
}