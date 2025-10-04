package ar.edu.utn.frba.dds.web.controlador;

import static ar.edu.utn.frba.dds.modelo.hecho.HechoEstado.PENDIENTE;
import static ar.edu.utn.frba.dds.web.dto.HechoDTO.toHecho;
import static java.util.stream.Collectors.toSet;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import ar.edu.utn.frba.dds.servicio.HechoServicio;
import ar.edu.utn.frba.dds.web.dto.HechoDTO;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HechosControlador {

  @Autowired
  private HechoServicio hechoServicio;

  @PostMapping("/hechos")
  public void crearHecho(@RequestBody HechoDTO hechoDTO) {
    hechoDTO.setHechoEstado(PENDIENTE);
    hechoServicio.guardarHecho(toHecho(hechoDTO));
  }

  @PutMapping("/hechos")
  public void modificarHecho(@RequestBody HechoDTO hechoDTO) {
    hechoServicio.modificarHecho(toHecho(hechoDTO));
  }

  @GetMapping("/hechos")
  public Collection<HechoDTO> hechos() {
    return hechoServicio.dameHechos()
        .stream()
        .map(HechoDTO::toHechoDTO)
        .collect(toSet());
  }

  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler(RuntimeException.class)
  public String handleException(Exception exception) {
    return exception.getMessage();
  }
}