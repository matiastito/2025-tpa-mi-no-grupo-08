package ar.edu.utn.frba.dds.controller;

import ar.edu.utn.frba.dds.model.dto.ColeccionDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("titulo", "Agregador de Colecciones");
    return "anonimo/landing.html";
  }

  @GetMapping("/login")
  public String login(Model model) {
    model.addAttribute("titulo", "Agregador de Colecciones - LogIn");
    return "anonimo/login.html";
  }

  @GetMapping("/colecciones")
  public String colecciones(Model model) {
    ColeccionDTO coleccionDTO = new ColeccionDTO();
    coleccionDTO.setTitulo("Fuentes proxies");
    model.addAttribute("titulo", "Agregador de Colecciones - Colecciones");
    model.addAttribute("coleccion", coleccionDTO);
    return "anonimo/colecciones.html";
  }

  @GetMapping("/404")
  public String notFound(Model model) {
    model.addAttribute("titulo", "No encontrado");
    return "404";
  }
}