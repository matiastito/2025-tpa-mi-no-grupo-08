package ar.edu.utn.frba.dds.controller;

import static ar.edu.utn.frba.dds.model.dto.SolicitudDeEliminacionDeHechoEstado.ACEPTADA;
import static ar.edu.utn.frba.dds.model.dto.SolicitudDeEliminacionDeHechoEstado.PENDIENTE;
import static ar.edu.utn.frba.dds.model.dto.SolicitudDeEliminacionDeHechoEstado.RECHAZADA;
import static java.util.stream.Collectors.toList;
import static org.springframework.web.context.request.RequestContextHolder.currentRequestAttributes;

import ar.edu.utn.frba.dds.model.dto.ColeccionDTO;
import ar.edu.utn.frba.dds.model.dto.ColeccionDTO.FuenteDTO;
import ar.edu.utn.frba.dds.model.dto.HechoModificacionDTO;
import ar.edu.utn.frba.dds.model.dto.SolicitudDeEliminacionDeHechoDTO;
import ar.edu.utn.frba.dds.model.dto.SolicitudDeEliminacionDeHechoDTO.AdministradorDTO;
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
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

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
    int hechosTotales = agregadorServicio.hechos().size();
    int hechosPendientes = agregadorServicio.hechosModificaciones().size();
    long solicitudesPendientes = agregadorServicio.solicitudesEliminacion().stream()
            .filter(solicitud -> PENDIENTE.equals(solicitud.getSolicitudDeEliminacionDeHechoEstado())) // Filtra por estado PENDIENTE
            .count();
    int cantidadFuentes = agregadorServicio.fuentes().size();
    List<SolicitudDeEliminacionDeHechoDTO> solicitudesPendientesLista = agregadorServicio.solicitudesEliminacion().stream()
            .filter(solicitud -> PENDIENTE.equals(solicitud.getSolicitudDeEliminacionDeHechoEstado()))
            .toList();

    model.addAttribute("hechosTotales", hechosTotales);
    model.addAttribute("hechosPendientes", hechosPendientes);
    model.addAttribute("solicitudesPendientes", solicitudesPendientes);
    model.addAttribute("fuentesTotales", cantidadFuentes);

    return "admin/panelDeControl";
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
    ColeccionDTO coleccionDTO = new ColeccionDTO();
    coleccionDTO.getFuentes().add(new FuenteDTO());
    model.addAttribute("coleccionDTO", coleccionDTO);
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
    if (coleccionDTO.getId() != null) {
      agregadorServicio.editarColeccion(coleccionDTO);
    } else {
      agregadorServicio.crearColeccion(coleccionDTO);
    }
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

  @GetMapping("/importarHechos")
  public String importarHechos() {
    return "admin/importarHechos.html"; // Returns the upload.html template
  }

  @PostMapping("/importarHechos")
  public String importarHechosDesdeArchivo(@RequestParam("file") MultipartFile file, Model model) {
    agregadorServicio.importarHechos(file);
    return "redirect:/admin/panelDeControl"; // Return to the upload page, potentially with a message
  }

  @GetMapping("/hechosModificaciones")
  public String hechosModificaciones(Model model) {
    List<HechoModificacionDTO> hechosModificaciones = agregadorServicio.hechosModificaciones();
    model.addAttribute("hechosModificaciones", hechosModificaciones);
    model.addAttribute("hechoModificacion", new HechoModificacionDTO());
    return "admin/hechosModificaciones.html";
  }

  @PostMapping("/hechosModificaciones/{hechoModificacionId}")
  public String hechosModificaciones(
      @PathVariable("hechoModificacionId") Long hechoModificacionId,
      @ModelAttribute("hechoModificacionDTO") HechoModificacionDTO hechoModificacionDTO) {
    hechoModificacionDTO.setId(hechoModificacionId);
    agregadorServicio.editarModificacionHecho(hechoModificacionDTO);
    return "redirect:/admin/hechosModificaciones";
  }

  @GetMapping("/solicitudesEliminacion")
  public String solicitudesEliminacion(Model model) {
    List<SolicitudDeEliminacionDeHechoDTO> solicitudesEliminacion =
        agregadorServicio.solicitudesEliminacion();
    model.addAttribute("solicitudesEliminacion", solicitudesEliminacion);
    return "admin/solicitudes.html";
  }

  @PostMapping("/solicitudesEliminacion/{solicitudesEliminacionId}/aceptar")
  public String hechosModificacionesAceptar(
      @PathVariable("solicitudesEliminacionId") Long solicitudesEliminacionId) {
    SolicitudDeEliminacionDeHechoDTO solicitudDeEliminacionDeHechoDTO = completarSolicitudDeEliminacion(solicitudesEliminacionId);
    solicitudDeEliminacionDeHechoDTO.setSolicitudDeEliminacionDeHechoEstado(ACEPTADA);
    agregadorServicio.editarSolicitudEliminacion(solicitudDeEliminacionDeHechoDTO);
    return "redirect:/admin/solicitudesEliminacion";
  }

  @PostMapping("/solicitudesEliminacion/{solicitudesEliminacionId}/rechazar")
  public String hechosModificacionesRechazar(
      @PathVariable("solicitudesEliminacionId") Long solicitudesEliminacionId) {
    SolicitudDeEliminacionDeHechoDTO solicitudDeEliminacionDeHechoDTO = completarSolicitudDeEliminacion(solicitudesEliminacionId);
    solicitudDeEliminacionDeHechoDTO.setSolicitudDeEliminacionDeHechoEstado(RECHAZADA);
    agregadorServicio.editarSolicitudEliminacion(solicitudDeEliminacionDeHechoDTO);
    return "redirect:/admin/solicitudesEliminacion";
  }

  private SolicitudDeEliminacionDeHechoDTO completarSolicitudDeEliminacion(Long solicitudesEliminacionId) {
    ServletRequestAttributes attributes = (ServletRequestAttributes) currentRequestAttributes();
    String username = (String) attributes.getRequest().getSession().getAttribute("username");

    SolicitudDeEliminacionDeHechoDTO solicitudDeEliminacionDeHechoDTO = new SolicitudDeEliminacionDeHechoDTO();
    solicitudDeEliminacionDeHechoDTO.setSolicitudDeEliminacionId(solicitudesEliminacionId);
    solicitudDeEliminacionDeHechoDTO.setAdministrador(new AdministradorDTO(username));
    return solicitudDeEliminacionDeHechoDTO;
  }
}

