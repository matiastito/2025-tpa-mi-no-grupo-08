package ar.edu.utn.frba.dds.controller;

import static java.time.LocalDateTime.now;
import static java.util.List.of;
import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

import ar.edu.utn.frba.dds.model.dto.ColeccionDTO;
import ar.edu.utn.frba.dds.model.dto.ContribuyenteDTO;
import ar.edu.utn.frba.dds.model.dto.HechoDTO;
import ar.edu.utn.frba.dds.model.dto.HechoModificacionDTO;
import ar.edu.utn.frba.dds.model.dto.SolicitudDeEliminacionDeHechoDTO;
import ar.edu.utn.frba.dds.servicio.AgregadorServicio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
@RequestMapping("/contribuyente")
public class ContribuyenteController {
  @Autowired
  private final AgregadorServicio agregadorServicio;

  @Autowired
  private final ObjectWriter ow;

  public ContribuyenteController(AgregadorServicio agregadorServicio, ObjectWriter ow) {
    this.agregadorServicio = agregadorServicio;
    this.ow = ow;
  }

  @GetMapping("/home")
  public String home(Authentication authentication, Model model) {
    model.addAttribute("titulo", "Bienvenido Contribuyente " + authentication.getName().toUpperCase());
    return "contribuyente/home.html";
  }

  @GetMapping("/hecho/crear")
  public String hechoCreacion(Model model) {
    model.addAttribute("hecho", new HechoDTO());
    return "contribuyente/crearHecho.html";
  }

  @PostMapping("/hecho")
  public String hechoCrear(@ModelAttribute("hecho") HechoDTO hechoDTO) {
    ServletRequestAttributes attributes = (ServletRequestAttributes) currentRequestAttributes();
    String username = (String) attributes.getRequest().getSession().getAttribute("username");
    hechoDTO.setFechaDeCarga(now());
    // FIXME ver el apellido
    hechoDTO.setContribuyenteDTO(new ContribuyenteDTO(username, username));
    agregadorServicio.crearHecho(hechoDTO);
    return "redirect:/contribuyente/colecciones";
  }

  @PostMapping("/solicitudEliminacion")
  public String solicitarEliminarHecho(@ModelAttribute("solicitudDeEliminacionDeHecho") SolicitudDeEliminacionDeHechoDTO solicitudDeEliminacionDeHechoDTO) {
    ServletRequestAttributes attributes = (ServletRequestAttributes) currentRequestAttributes();
    String username = (String) attributes.getRequest().getSession().getAttribute("username");
    // FIXME ver el apellido
    solicitudDeEliminacionDeHechoDTO.setRepotador(new ContribuyenteDTO(username, username));
    agregadorServicio.solicitarEliminacionDeHecho(solicitudDeEliminacionDeHechoDTO);
    return "redirect:/contribuyente/colecciones";
  }

  @PostMapping("/hechoModificacion")
  public String modificarHecho(@ModelAttribute("hechoModificacion") HechoModificacionDTO hechoModificacionDTO) {
    agregadorServicio.crearModificacionHecho(hechoModificacionDTO);
    return "redirect:/contribuyente/colecciones";
  }

  @GetMapping("/colecciones")
  public String colecciones(Model model) {
    List<ColeccionDTO> colecciones = agregadorServicio.colecciones();
    model.addAttribute("colecciones", colecciones);
    return "contribuyente/colecciones.html";
  }

  @GetMapping("/colecciones/{coleccionId}/hechos")
  public String hechos(Model model, @PathVariable Long coleccionId) {
    ColeccionDTO coleccion = agregadorServicio.coleccion(coleccionId);
    model.addAttribute("coleccion", coleccion);

    List<HechoDTO> hechos = agregadorServicio.hechos(coleccionId);
    model.addAttribute("hechos", hechos);

    String hechosJSON = null;
    try {
      hechosJSON = ow.writeValueAsString(hechos);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    model.addAttribute("hechosJSON", hechosJSON);

    return "contribuyente/coleccion.html";
  }

  @GetMapping("/colecciones/{coleccionId}/hechos/{hechoId}")
  public String hecho(Model model, @PathVariable Long coleccionId, @PathVariable Long hechoId) {
    ColeccionDTO coleccion = agregadorServicio.coleccion(coleccionId);
    model.addAttribute("coleccion", coleccion);

    Optional<HechoDTO> hecho =
        agregadorServicio
            .hechos(coleccionId).stream().filter(h -> h.getId().equals(hechoId)).findFirst();
    if (hecho.isEmpty()) {
      throw new RuntimeException("El hecho no existe");
    }
    model.addAttribute("hecho", hecho.get());

    String hechosJSON = null;
    try {
      hechosJSON = ow.writeValueAsString(of(hecho.get()));
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error conviertiendo a JSON: " + e.getMessage());
    }
    model.addAttribute("hechosJSON", hechosJSON);

    SolicitudDeEliminacionDeHechoDTO solicitudDeEliminacionDeHechoDTO = new SolicitudDeEliminacionDeHechoDTO();
    solicitudDeEliminacionDeHechoDTO.setHechoId(hechoId);
    model.addAttribute("solicitudDeEliminacionDeHecho", solicitudDeEliminacionDeHechoDTO);
    return "contribuyente/verHecho.html";
  }

  @GetMapping("/colecciones/{coleccionId}/hechos/{hechoId}/editar")
  public String editarHecho(Model model, @PathVariable Long coleccionId, @PathVariable Long hechoId) {
    ColeccionDTO coleccion = agregadorServicio.coleccion(coleccionId);
    model.addAttribute("coleccion", coleccion);

    Optional<HechoDTO> hecho =
        agregadorServicio
            .hechos(coleccionId).stream().filter(h -> h.getId().equals(hechoId)).findFirst();
    if (hecho.isEmpty()) {
      throw new RuntimeException("El hecho no existe");
    }
    model.addAttribute("hechoModificacion", hecho.get());

    String hechosJSON = null;
    try {
      hechosJSON = ow.writeValueAsString(of(hecho.get()));
    } catch (JsonProcessingException e) {
      throw new RuntimeException("Error conviertiendo a JSON: " + e.getMessage());
    }
    model.addAttribute("hechosJSON", hechosJSON);
    return "contribuyente/editarHecho.html";
  }
}
