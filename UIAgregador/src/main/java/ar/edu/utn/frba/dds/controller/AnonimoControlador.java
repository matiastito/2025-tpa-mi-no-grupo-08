package ar.edu.utn.frba.dds.controller;

import ar.edu.utn.frba.dds.model.dto.ColeccionDTO;
import ar.edu.utn.frba.dds.model.dto.UbicacionDTO;
import ar.edu.utn.frba.dds.servicio.AgregadorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnonimoControlador {

  @Autowired
  private final AgregadorServicio agregadorServicio;

  public AnonimoControlador(AgregadorServicio agregadorServicio) {
    this.agregadorServicio = agregadorServicio;
  }

  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("titulo", "Bienvenido MetaMAPA");
    return "anonimo/landing.html";
  }

  @GetMapping("/login")
  public String login(Model model) {
    model.addAttribute("titulo", "Inicio de Sesion - LogIn");
    return "anonimo/login.html";
  }

  @GetMapping("/anonimo/colecciones")
  public String colecciones(Model model) {
    List<ColeccionDTO> colecciones = agregadorServicio.colecciones();
    model.addAttribute("colecciones", colecciones);

    UbicacionDTO[] ubicaciones =
        new UbicacionDTO[]{new UbicacionDTO(-34.649766, -58.454446),
            new UbicacionDTO(-34.650041, -58.455002)};
    model.addAttribute("ubicaciones", ubicaciones);
    return "anonimo/colecciones.html";
  }

  @GetMapping("/404")
  public String notFound(Model model) {
    model.addAttribute("titulo", "No encontrado");
    return "404";
  }

}