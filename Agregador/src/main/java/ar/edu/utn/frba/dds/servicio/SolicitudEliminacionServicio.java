package ar.edu.utn.frba.dds.servicio;

import ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHecho;
import ar.edu.utn.frba.dds.repositorio.SolicitudEliminacionRepositorio;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitudEliminacionServicio {
  @Autowired
  private SolicitudEliminacionRepositorio solicitudEliminacionRepositorio;
  @Autowired
  private DetectorDeSpamServicio detectorDeSpamServicio;

  public Collection<SolicitudDeEliminacionDeHecho> solicitudesDeEliminacionDeHecho() {
    return solicitudEliminacionRepositorio.solicitudesDeEliminacionDeHecho();
  }

  public SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho(Long solicitudDeEliminacionDeHechoId) {
    return solicitudEliminacionRepositorio
        .solicitudDeEliminacionDeHecho(solicitudDeEliminacionDeHechoId);
  }

  public void guardarSolicitudDeEliminacionDeHecho(SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho) {
    solicitudEliminacionRepositorio.guardar(solicitudDeEliminacionDeHecho);
    detectorDeSpamServicio.rechazaSpam(solicitudDeEliminacionDeHecho);
  }
}
