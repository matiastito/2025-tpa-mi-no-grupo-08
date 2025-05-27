package ar.edu.utn.frba.dds.web.controlador;

import static ar.edu.utn.frba.dds.web.controlador.dto.SolicitudDeEliminacionDeHechoDTO.toModel;

import ar.edu.utn.frba.dds.modelo.colaborador.Contribuyente;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.servicio.ColeccionServicio;
import ar.edu.utn.frba.dds.servicio.ContribuyenteServicio;
import ar.edu.utn.frba.dds.servicio.SolicitudEliminacionServicio;
import ar.edu.utn.frba.dds.web.controlador.dto.SolicitudDeEliminacionDeHechoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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

  /**
   * POST /solicitudes
   * Permite crear solicitudes de eliminación,
   * enviando los datos de la solicitud como un JSON a través del cuerpo (body)
   * de la misma.
   */
  @PostMapping("/solicitudes")
  public void solicitudes(@RequestBody SolicitudDeEliminacionDeHechoDTO solicitudDeEliminacionDeHechoDTO) {
    Contribuyente contribuyente = null;
    if (solicitudDeEliminacionDeHechoDTO.getRepotador() != null) {
      contribuyente = contribuyenteServicio.guardar(
          solicitudDeEliminacionDeHechoDTO.getRepotador().getNombre(),
          solicitudDeEliminacionDeHechoDTO.getRepotador().getApellido());
    }
    Hecho hecho = coleccionServicio.buscarHechoPorTitulo(solicitudDeEliminacionDeHechoDTO.getTituloHecho());
    solicitudEliminacionServicio.guardarSolicitudDeEliminacionDeHecho(
        toModel(solicitudDeEliminacionDeHechoDTO, hecho, contribuyente));
  }
}

