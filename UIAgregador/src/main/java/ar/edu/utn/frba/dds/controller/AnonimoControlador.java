package ar.edu.utn.frba.dds.controller;

import static java.time.LocalDateTime.now;
import static java.util.List.of;

import ar.edu.utn.frba.dds.model.dto.ColeccionDTO;
import ar.edu.utn.frba.dds.model.dto.HechoDTO;
import ar.edu.utn.frba.dds.model.dto.SolicitudDeEliminacionDeHechoDTO;
import ar.edu.utn.frba.dds.servicio.AgregadorAnonimoServicio;
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

@Controller
public class AnonimoControlador {

  @Autowired
  private final AgregadorAnonimoServicio agregadorAnonimoServicio;

  @Autowired
  private final ObjectWriter ow;

  public AnonimoControlador(AgregadorAnonimoServicio agregadorAnonimoServicio, ObjectWriter ow) {
    this.agregadorAnonimoServicio = agregadorAnonimoServicio;
    this.ow = ow;
  }

  @GetMapping("/")
  public String home(Authentication authentication, Model model) {
    if (authentication != null && authentication.isAuthenticated())
      return "redirect:home";
    model.addAttribute("titulo", "Bienvenido MetaMAPA");
    return "anonimo/landing.html";
  }

  @GetMapping("/login")
  public String login(Model model) {
    model.addAttribute("titulo", "LogIn");
    return "anonimo/login";
  }

  @GetMapping("/anonimo/hecho/crear")
  public String hechoCreacion(Model model) {
    model.addAttribute("hecho", new HechoDTO());
    return "anonimo/crearHecho.html";
  }

  @PostMapping("/anonimo/hecho")
  public String hechoCrear(@ModelAttribute("hecho") HechoDTO hechoDTO) {
    hechoDTO.setFechaDeCarga(now());
    agregadorAnonimoServicio.crearHecho(hechoDTO);
    return "redirect:/anonimo/colecciones";
  }

  @GetMapping("/anonimo/colecciones")
  public String colecciones(Model model) {
    List<ColeccionDTO> colecciones = agregadorAnonimoServicio.colecciones();
    model.addAttribute("colecciones", colecciones);
    Long coleccionId = colecciones.stream().findFirst().get().getId();
    List<HechoDTO> hechosDTO = agregadorAnonimoServicio.hechos(coleccionId);
    String hechosJSON = null;
    try {
      hechosJSON = ow.writeValueAsString(hechosDTO);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    model.addAttribute("hechosJSON", hechosJSON);

    return "anonimo/colecciones.html";
  }

  @GetMapping("/anonimo/colecciones/{coleccionId}/hechos")
  public String hechos(Model model, @PathVariable Long coleccionId) {
    ColeccionDTO coleccion = agregadorAnonimoServicio.coleccion(coleccionId);
    model.addAttribute("coleccion", coleccion);

    List<HechoDTO> hechos = agregadorAnonimoServicio.hechos(coleccionId);
    model.addAttribute("hechos", hechos);

    String hechosJSON = null;
    try {
      hechosJSON = ow.writeValueAsString(hechos);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    model.addAttribute("hechosJSON", hechosJSON);
    return "anonimo/coleccion.html";
  }

  @GetMapping("/anonimo/colecciones/{coleccionId}/hechos/{hechoId}")
  public String hecho(Model model, @PathVariable Long coleccionId, @PathVariable Long hechoId) {
    ColeccionDTO coleccion = agregadorAnonimoServicio.coleccion(coleccionId);
    model.addAttribute("coleccion", coleccion);

    Optional<HechoDTO> hecho =
        agregadorAnonimoServicio
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

    return "anonimo/verHecho.html";
  }

  @PostMapping("/anonimo/solicitudEliminacion")
  public String solicitarEliminarHecho(@ModelAttribute("solicitudDeEliminacionDeHecho") SolicitudDeEliminacionDeHechoDTO solicitudDeEliminacionDeHechoDTO) {
    agregadorAnonimoServicio.solicitarEliminacionDeHecho(solicitudDeEliminacionDeHechoDTO);
    return "redirect:/anonimo/colecciones";
  }
}