package ar.edu.utn.frba.dds.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("titulo", "Agregador de Colecciones");
    return "landing.html";
  }

  @GetMapping("/404")
  public String notFound(Model model) {
    model.addAttribute("titulo", "No encontrado");
    return "404";
  }
}