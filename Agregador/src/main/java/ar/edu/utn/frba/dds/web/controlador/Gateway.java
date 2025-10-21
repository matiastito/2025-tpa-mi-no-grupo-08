package ar.edu.utn.frba.dds.web.controlador;

import static ar.edu.utn.frba.dds.web.controlador.dto.ColeccionDTO.FuenteDTO.toDTO;
import static ar.edu.utn.frba.dds.web.controlador.dto.ColeccionDTO.FuenteDTO.toModel;
import static ar.edu.utn.frba.dds.web.controlador.dto.ColeccionDTO.toDTO;
import static ar.edu.utn.frba.dds.web.controlador.dto.ColeccionDTO.toModel;
import static ar.edu.utn.frba.dds.web.controlador.dto.HechoDTO.toHecho;

import ar.edu.utn.frba.dds.servicio.ColeccionServicio;
import ar.edu.utn.frba.dds.servicio.FuenteServicio;
import ar.edu.utn.frba.dds.web.controlador.dto.ColeccionDTO;
import ar.edu.utn.frba.dds.web.controlador.dto.ColeccionDTO.FuenteDTO;
import ar.edu.utn.frba.dds.web.controlador.dto.HechoDTO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class Gateway {
  @Autowired
  private ColeccionServicio coleccionServicio;

  @Autowired
  private FuenteServicio fuenteServicio;

  //Gateway URLs
  @DeleteMapping("/colecciones/{coleccionId}")
  public void borrarColeccion(@PathVariable Long coleccionId) {
    coleccionServicio.eliminarColeccion(coleccionId);
  }

  @GetMapping("/colecciones/{coleccionId}")
  public ColeccionDTO dameColeccion(@PathVariable Long coleccionId) {
    return toDTO(coleccionServicio.coleccion(coleccionId));
  }

  @PostMapping("/colecciones")
  public void crearColeccion(@RequestBody ColeccionDTO coleccionDTO) {
    coleccionServicio.guardarColeccion(toModel(coleccionDTO));
  }

  @PutMapping("/colecciones")
  public void editarColeccion(@RequestBody ColeccionDTO coleccionDTO) {
    coleccionServicio.editarColeccion(toModel(coleccionDTO));
  }

  @GetMapping("/fuentes")
  public List<FuenteDTO> dameFuentes() {
    return toDTO(fuenteServicio.fuentes());
  }

  @PutMapping("/fuentes")
  public void editarFuente(@RequestBody FuenteDTO fuenteDTO) {
    fuenteServicio.editarFuente(toModel(fuenteDTO));
  }

  @PostMapping("/hechos")
  public void crearHecho(@RequestBody HechoDTO hechoDTO) {
    //FIXME? ver esto de no pasarle fuente.
    fuenteServicio.crearHecho(toHecho(hechoDTO, null));
  }

  @PostMapping("/importarHechos")
  public void crearHecho(@RequestParam("archivoCSVDeHechos") MultipartFile archivoCSVDeHechos) {
    fuenteServicio.importarHechos(archivoCSVDeHechos);
  }
}
