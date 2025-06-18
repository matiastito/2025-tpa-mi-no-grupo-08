package ar.edu.utn.frba.dds.web.controlador;

import static ar.edu.utn.frba.dds.web.controlador.dto.SolicitudDeEliminacionDeHechoDTO.toModel;
import ar.edu.utn.frba.dds.modelo.administrador.Administrador;
import ar.edu.utn.frba.dds.modelo.colaborador.Contribuyente;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHecho;
import ar.edu.utn.frba.dds.repositorio.AdministradorRespositorio;
import ar.edu.utn.frba.dds.servicio.ColeccionServicio;
import ar.edu.utn.frba.dds.servicio.ContribuyenteServicio;
import ar.edu.utn.frba.dds.servicio.SolicitudEliminacionServicio;
import ar.edu.utn.frba.dds.web.controlador.dto.SolicitudDeEliminacionDeHechoDTO;
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
  private ColeccionServicio coleccionServicio;
  @Autowired
  private ContribuyenteServicio contribuyenteServicio;
  @Autowired
  private AdministradorRespositorio administradorRespositorio;

  /**
   * POST /solicitudes
   * Permite crear solicitudes de eliminación,
   * enviando los datos de la solicitud como un JSON a través del cuerpo (body)
   * de la misma.
   */
  @PostMapping("/solicitudes")
  public void crearSolicitud(@RequestBody SolicitudDeEliminacionDeHechoDTO solicitudDeEliminacionDeHechoDTO) {
    //Buscar Contribuyente
    Contribuyente contribuyente = null;
    if (solicitudDeEliminacionDeHechoDTO.getRepotador() != null) {
      contribuyente = contribuyenteServicio.guardar(
          solicitudDeEliminacionDeHechoDTO.getRepotador().getNombre(),
          solicitudDeEliminacionDeHechoDTO.getRepotador().getApellido());
    }

    //Buscar Hecho
    Hecho hecho = coleccionServicio.buscarHechoPorTitulo(solicitudDeEliminacionDeHechoDTO.getTituloHecho());

    //Registar Solicitud
    solicitudEliminacionServicio.guardarSolicitudDeEliminacionDeHecho(
        toModel(solicitudDeEliminacionDeHechoDTO, hecho, contribuyente));
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
    Hecho hecho = coleccionServicio.buscarHechoPorTitulo(solicitudDeEliminacionDeHechoDTO.getTituloHecho());
    SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho =
        solicitudEliminacionServicio.buscarSolicitudDeEliminacionDeHecho(hecho);

    Administrador administrador =
        administradorRespositorio.administrador(solicitudDeEliminacionDeHechoDTO.getAdministrador().getNombre());
    //Buscar Administrador
    if (solicitudDeEliminacionDeHechoDTO.isAprobada()) {
      solicitudDeEliminacionDeHecho.aprobar(administrador);
    } else {
      solicitudDeEliminacionDeHecho.rechazar(administrador);
    }
  }
}

