package ar.edu.utn.frba.dds.controller;

import ar.edu.utn.frba.dds.model.dto.ColeccionDTO;
import ar.edu.utn.frba.dds.model.dto.HechoDTO;
import ar.edu.utn.frba.dds.model.dto.UbicacionDTO;
import ar.edu.utn.frba.dds.servicio.AgregadorAnonimoServicio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.List;
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

  public AnonimoControlador(AgregadorAnonimoServicio agregadorAnonimoServicio) {
    this.agregadorAnonimoServicio = agregadorAnonimoServicio;
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
    return "anonimo/hecho.html";
  }

  @PostMapping("/anonimo/hecho/crear")
  public String hechoCrear(Model model, @ModelAttribute HechoDTO hechoDTO) {
    agregadorAnonimoServicio.crearHecho(hechoDTO);
    return "anonimo/colecciones.html";
  }

  @GetMapping("/anonimo/colecciones")
  public String colecciones(Model model) {
    List<ColeccionDTO> colecciones = agregadorAnonimoServicio.colecciones();
    model.addAttribute("colecciones", colecciones);

    UbicacionDTO[] ubicaciones =
        new UbicacionDTO[]{new UbicacionDTO(-34.649766, -58.454446),
            new UbicacionDTO(-34.650041, -58.455002)};
    model.addAttribute("ubicaciones", ubicaciones);
    return "anonimo/colecciones.html";
  }

  @GetMapping("/anonimo/colecciones/{coleccionId}/hechos")
  public String hechos(Model model, @PathVariable Long coleccionId) {
    ColeccionDTO coleccion = agregadorAnonimoServicio.coleccion(coleccionId);
    model.addAttribute("coleccion", coleccion);


    List<HechoDTO> hechos = agregadorAnonimoServicio.hechos(coleccionId);
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String json = null;
    try {
      json = ow.writeValueAsString(hechos);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    model.addAttribute("hechos", json);

    UbicacionDTO[] ubicaciones =
        new UbicacionDTO[]{new UbicacionDTO(-34.649766, -58.454446),
            new UbicacionDTO(-34.650041, -58.455002)};
    model.addAttribute("ubicaciones", ubicaciones);
    return "anonimo/coleccion.html";
  }

}