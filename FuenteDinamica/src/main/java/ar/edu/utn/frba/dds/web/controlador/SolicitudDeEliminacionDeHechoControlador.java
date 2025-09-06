package ar.edu.utn.frba.dds.web.controlador;

import static ar.edu.utn.frba.dds.web.dto.SolicitudDeEliminacionDeHechoDTO.toModel;

import ar.edu.utn.frba.dds.modelo.administrador.Administrador;
import ar.edu.utn.frba.dds.modelo.colaborador.Contribuyente;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHecho;
import ar.edu.utn.frba.dds.repositorio.AdministradorRepositorio;
import ar.edu.utn.frba.dds.servicio.ContribuyenteServicio;
import ar.edu.utn.frba.dds.servicio.HechoServicio;
import ar.edu.utn.frba.dds.servicio.SolicitudEliminacionServicio;
import ar.edu.utn.frba.dds.web.dto.SolicitudDeEliminacionDeHechoDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
  private AdministradorRepositorio administradorRespositorio;

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
    Hecho hecho = hechoServicio.buscarHechoPorTitulo(
        solicitudDeEliminacionDeHechoDTO.getTituloHecho());

    //Registar Solicitud
    solicitudEliminacionServicio.guardarSolicitudDeEliminacionDeHecho(
        toModel(solicitudDeEliminacionDeHechoDTO, hecho, contribuyente));
  }

  @PutMapping("/solicitudes")
  public void modificarSolicitud(
      @RequestBody SolicitudDeEliminacionDeHechoDTO solicitudDeEliminacionDeHechoDTO) {
    //Buscar Solicitud
    Hecho hecho = hechoServicio.buscarHechoPorTitulo(solicitudDeEliminacionDeHechoDTO.getTituloHecho());
    SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho =
        solicitudEliminacionServicio.buscarSolicitudDeEliminacionDeHecho(hecho);

    //Buscar Administrador
    Administrador administrador =
        administradorRespositorio.administrador(solicitudDeEliminacionDeHechoDTO.getAdministrador().getNombre());

    if (solicitudDeEliminacionDeHechoDTO.isAprobada()) {
      solicitudEliminacionServicio.aprobar(solicitudDeEliminacionDeHecho, administrador);
    } else {
      solicitudEliminacionServicio.rechazar(solicitudDeEliminacionDeHecho, administrador);
    }
  }
}

