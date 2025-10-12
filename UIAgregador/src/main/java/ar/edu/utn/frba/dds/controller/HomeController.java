package ar.edu.utn.frba.dds.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  @GetMapping("/home")
  public String home(Authentication authentication, Model model) {
    model.addAttribute("titulo", "Biennvenido");
    boolean isAdmin = authentication.getAuthorities()
        .stream()
        .anyMatch(g -> g.getAuthority().equalsIgnoreCase("ROLE_ADMIN"));
    return isAdmin ? "redirect:admin/home" : "redirect:contribuyente/home";
  }
}
