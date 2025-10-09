package ar.edu.utn.frba.dds.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contribuyente")
public class ContribuyenteController {

  @GetMapping("/home")
  public String home(Authentication authentication, Model model) {
    model.addAttribute("titulo", "Biennvenido");
    return "contribuyente/home.html";
  }
}
