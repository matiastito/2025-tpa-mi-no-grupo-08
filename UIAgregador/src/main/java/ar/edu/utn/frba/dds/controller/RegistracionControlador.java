package ar.edu.utn.frba.dds.controller;

import ar.edu.utn.frba.dds.servicio.ExternalAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistracionControlador {

  @Autowired
  private final ExternalAuthService externalAuthService;

  public RegistracionControlador(ExternalAuthService externalAuthService) {
    this.externalAuthService = externalAuthService;
  }

  @GetMapping("/registrar")
  public String registro(Model model) {
    model.addAttribute("titulo", "Registro de Usuario");
    return "anonimo/registrar";
  }

  @PostMapping("/registrar")
  public String registrar(
      Authentication authentication,
      @RequestParam String username,
      @RequestParam String password,
      @RequestParam(required = false) String nombre,
      RedirectAttributes ra) {
    try {
      externalAuthService.registrar(username, password, nombre);
      ra.addFlashAttribute("msjOk", "Registro exitoso. Ingresá con tus credenciales.");
      return "redirect:/login";
    } catch (Exception e) {
      ra.addFlashAttribute("msjError", "No se pudo registrar: " + e.getMessage());
      return "redirect:/registrar";
    }
  }
}