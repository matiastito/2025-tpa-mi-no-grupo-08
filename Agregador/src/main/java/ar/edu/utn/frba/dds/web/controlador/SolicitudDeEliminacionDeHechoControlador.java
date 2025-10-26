package ar.edu.utn.frba.dds.web.controlador;

import static ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHechoEstado.ACEPTADA;
import static ar.edu.utn.frba.dds.web.controlador.dto.SolicitudDeEliminacionDeHechoDTO.toModel;
import static java.util.stream.Collectors.toList;

import ar.edu.utn.frba.dds.modelo.administrador.Administrador;
import ar.edu.utn.frba.dds.modelo.colaborador.Contribuyente;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHecho;
import ar.edu.utn.frba.dds.repositorio.AdministradorRepositorio;
import ar.edu.utn.frba.dds.servicio.ContribuyenteServicio;
import ar.edu.utn.frba.dds.servicio.HechoServicio;
import ar.edu.utn.frba.dds.servicio.SolicitudEliminacionServicio;
import ar.edu.utn.frba.dds.web.controlador.dto.SolicitudDeEliminacionDeHechoDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SolicitudDeEliminacionDeHechoControlador {
  @Autowired
  private SolicitudEliminacionServicio solicitudEliminacionServicio;
  @Autowired
  private HechoServicio hechoServicio;
  @Autowired
  private ContribuyenteServicio contribuyenteServicio;
  @Autowired
  private AdministradorRepositorio administradorRepositorio;

  /**
   * POST /solicitudes
   * Permite crear solicitudes de eliminación,
   * enviando los datos de la solicitud como un JSON a través del cuerpo (body)
   * de la misma.
   */
  @PostMapping("/solicitudes")
  public void crearSolicitud(
      @RequestBody SolicitudDeEliminacionDeHechoDTO solicitudDeEliminacionDeHechoDTO) {
    //Buscar Contribuyente
    Contribuyente contribuyente = null;
    if (solicitudDeEliminacionDeHechoDTO.getRepotador() != null) {
      contribuyente = contribuyenteServicio.guardar(
          solicitudDeEliminacionDeHechoDTO.getRepotador().getNombre(),
          solicitudDeEliminacionDeHechoDTO.getRepotador().getApellido());
    }

    //Buscar Hecho
    Optional<Hecho> hecho =
        hechoServicio.getHecho(solicitudDeEliminacionDeHechoDTO.getHechoId());

    //Registar Solicitud
    if (hecho.isPresent()) {
      solicitudEliminacionServicio.guardarSolicitudDeEliminacionDeHecho(
          toModel(solicitudDeEliminacionDeHechoDTO, hecho.get(), contribuyente));
    }
  }

  /**
   * POST /solicitudes
   * Permite crear solicitudes de eliminación,
   * enviando los datos de la solicitud como un JSON a través del cuerpo (body)
   * de la misma.
   */
  @PutMapping("/solicitudes")
  public void modificarSolicitud(
      @RequestBody SolicitudDeEliminacionDeHechoDTO solicitudDeEliminacionDeHechoDTO) {
    //Buscar Solicitud

    SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho =
        solicitudEliminacionServicio.solicitudDeEliminacionDeHecho(solicitudDeEliminacionDeHechoDTO.getSolicitudDeEliminacionId());

    //Buscar Administrador
    Administrador administrador = null;
    if (administradorRepositorio
        .findByNombre(solicitudDeEliminacionDeHechoDTO.getAdministrador().getNombre()).isEmpty()) {
      administrador = administradorRepositorio.save(new Administrador("admin"));
    }

    if (ACEPTADA.equals(solicitudDeEliminacionDeHechoDTO.getSolicitudDeEliminacionDeHechoEstado())) {
      solicitudEliminacionServicio.aprobar(solicitudDeEliminacionDeHecho, administrador);
    } else {
      solicitudEliminacionServicio.rechazar(solicitudDeEliminacionDeHecho, administrador);
    }
    solicitudEliminacionServicio.guardarSolicitudDeEliminacionDeHecho(solicitudDeEliminacionDeHecho);
  }

  @GetMapping("/solicitudes")
  public List<SolicitudDeEliminacionDeHechoDTO> solicitudes() {
    return solicitudEliminacionServicio
        .solicitudesDeEliminacionDeHecho()
        .stream()
        .map(SolicitudDeEliminacionDeHechoDTO::toDTO).collect(toList());
  }
}

