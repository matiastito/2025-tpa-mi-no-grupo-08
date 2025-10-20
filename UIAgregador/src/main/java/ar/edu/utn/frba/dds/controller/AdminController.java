package ar.edu.utn.frba.dds.controller;

import ar.edu.utn.frba.dds.model.dto.ColeccionDTO;
import ar.edu.utn.frba.dds.model.dto.ColeccionDTO.FuenteDTO;
import ar.edu.utn.frba.dds.model.dto.TipoConsenso;
import ar.edu.utn.frba.dds.servicio.AgregadorServicio;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {
  @Autowired
  private final AgregadorServicio agregadorServicio;

  public AdminController(AgregadorServicio agregadorServicio) {
    this.agregadorServicio = agregadorServicio;
  }

  @GetMapping("/home")
  public String home(Authentication authentication, Model model) {
    model.addAttribute("titulo", "Bienvenido Administrador");
    return "admin/home.html";
  }

  @GetMapping("/panelDeControl")
  public String panelDeControl(Model model) {
    List<FuenteDTO> fuentes = agregadorServicio.fuentes();
    model.addAttribute("fuentesDTO", fuentes);
    return "admin/panelDeControl.html";
  }

  @GetMapping("/fuentes")
  public String fuentes(Model model) {
    List<FuenteDTO> fuentes = agregadorServicio.fuentes();
    model.addAttribute("fuentesDTO", fuentes);
    return "admin/fuentes.html";
  }

  @GetMapping("/fuentes/{fuenteId}")
  public String fuente(Model model, @PathVariable Long fuenteId) {
    Optional<FuenteDTO> fuenteDTO = agregadorServicio.fuentes().stream().filter(f -> f.getId().equals(fuenteId)).findFirst();
    model.addAttribute("fuenteDTO", fuenteDTO.get());
    return "admin/fuente.html";
  }

  @PostMapping("/fuentes")
  public String fuenteEditar(Model model, @ModelAttribute("fuenteDTO") FuenteDTO fuenteDTO) {
    agregadorServicio.editarFuente(fuenteDTO);
    return "redirect:/admin/fuentes";
  }

  @GetMapping("/colecciones")
  public String colecciones(Model model) {
    List<ColeccionDTO> colecciones = agregadorServicio.colecciones();
    model.addAttribute("colecciones", colecciones);
    return "admin/colecciones.html";
  }

  @GetMapping("/colecciones/crear")
  public String crearColeccion(Model model) {
    model.addAttribute("coleccionDTO", new ColeccionDTO());
    return "admin/crearColeccion.html";
  }

  @GetMapping("/colecciones/editar/{coleccionId}")
  public String crearColeccion(@PathVariable Long coleccionId, Model model) {
    ColeccionDTO coleccionDTO = agregadorServicio.coleccion(coleccionId);
    model.addAttribute("coleccionDTO", coleccionDTO);
    return "admin/editarColeccion.html";
  }

  @PostMapping("/colecciones")
  public String crearColecciones(@ModelAttribute("coleccionDTO") ColeccionDTO coleccionDTO) {
    agregadorServicio.crearColeccion(coleccionDTO);
    return "redirect:/admin/colecciones";
  }

  @PostMapping("/colecciones/eliminar/{coleccionId}")
  public String eliminarColeccion(@PathVariable Long coleccionId, Model model) {
    agregadorServicio.eliminar(coleccionId);
    return "redirect:/admin/colecciones";
  }

  @PostMapping("/colecciones/{coleccionId}/consenso")
  public String cambiarConsenso(@PathVariable Long coleccionId, @RequestParam TipoConsenso tipoConsenso) {
    agregadorServicio.cambiarConsenso(coleccionId, tipoConsenso);
    return "redirect:/admin/colecciones";
  }
}

