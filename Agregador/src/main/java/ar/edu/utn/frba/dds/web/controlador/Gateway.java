package ar.edu.utn.frba.dds.web.controlador;

import static ar.edu.utn.frba.dds.modelo.hecho.HechoModificacionEstado.ACEPTADA;
import static ar.edu.utn.frba.dds.modelo.hecho.HechoModificacionEstado.RECHAZADA;
import static ar.edu.utn.frba.dds.web.controlador.dto.ColeccionDTO.FuenteDTO.toDTO;
import static ar.edu.utn.frba.dds.web.controlador.dto.ColeccionDTO.FuenteDTO.toModel;
import static ar.edu.utn.frba.dds.web.controlador.dto.ColeccionDTO.toDTO;
import static ar.edu.utn.frba.dds.web.controlador.dto.ColeccionDTO.toModel;
import static ar.edu.utn.frba.dds.web.controlador.dto.HechoDTO.toHecho;
import static ar.edu.utn.frba.dds.web.controlador.dto.HechoModificacionDTO.toHechoModificacion;

import ar.edu.utn.frba.dds.modelo.coleccion.Coleccion;
import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.hecho.HechoModificacion;
import ar.edu.utn.frba.dds.servicio.ColeccionServicio;
import ar.edu.utn.frba.dds.servicio.FuenteServicio;
import ar.edu.utn.frba.dds.servicio.HechoModificacionServicio;
import ar.edu.utn.frba.dds.web.controlador.dto.ColeccionDTO;
import ar.edu.utn.frba.dds.web.controlador.dto.ColeccionDTO.FuenteDTO;
import ar.edu.utn.frba.dds.web.controlador.dto.HechoDTO;
import ar.edu.utn.frba.dds.web.controlador.dto.HechoModificacionDTO;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
  private HechoModificacionServicio hechoModificacionServicio;

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
    Coleccion coleccion = toModel(coleccionDTO);
    coleccion.setId(coleccionDTO.getId());
    coleccionServicio.editarColeccion(coleccion);
  }

  @GetMapping("/fuentes")
  public List<FuenteDTO> dameFuentes() {
    return toDTO(fuenteServicio.fuentes());
  }

  @PutMapping("/fuentes")
  public void editarFuente(@RequestBody FuenteDTO fuenteDTO) {
    fuenteServicio.editarFuente(toModel(fuenteDTO));
  }

  @GetMapping("/fuentes/{id}")
  public ResponseEntity<FuenteDTO> dameFuentePorId(@PathVariable Long id) {
    Fuente fuenteEncontrada = fuenteServicio.buscarFuentePorId(id);
    if (fuenteEncontrada != null) {
      FuenteDTO dto = new FuenteDTO();
      dto.setId(fuenteEncontrada.getId());
      dto.setBaseUrl(fuenteEncontrada.getBaseUrl());
      dto.setTipoFuente(fuenteEncontrada.getTipoFuente()); // Asume getter en la entidad
      return ResponseEntity.ok(dto);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping("/hechos")
  public void crearHecho(@RequestBody HechoDTO hechoDTO) {
    fuenteServicio.crearHecho(toHecho(hechoDTO, null));
  }

  @PostMapping("/hechos/archivo")
  public void impotarHechos(@RequestParam("archivoCSVDeHechos") MultipartFile archivoCSVDeHechos) {
    fuenteServicio.importarHechos(archivoCSVDeHechos);
  }

  @PostMapping("/hechosModificaciones")
  public void crearHechoModificacion(@RequestBody HechoModificacionDTO hechoModificacionDTO) {
    HechoModificacion hechoModificacion = toHechoModificacion(hechoModificacionDTO);
    hechoModificacionServicio.guardarHechoModificacion(hechoModificacion, hechoModificacionDTO.getId());
  }

  @PutMapping("/hechosModificaciones")
  public void modificarHechoModificacion(@RequestBody HechoModificacionDTO hechoModificacionDTO) {
    if (hechoModificacionDTO.getHechoModificacionEstado().equals(ACEPTADA)) {
      hechoModificacionServicio.aceptarHechoModificacion(hechoModificacionDTO.getId());
    } else if (hechoModificacionDTO.getHechoModificacionEstado().equals(RECHAZADA)) {
      hechoModificacionServicio.rechazarHechoModificacion(hechoModificacionDTO.getId());
    }
  }

  @GetMapping("/hechosModificaciones")
  public List<HechoModificacionDTO> dameModificacionesHechos() {
    return HechoModificacionDTO.toDTO(hechoModificacionServicio.pendientes());
  }
}
