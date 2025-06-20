package ar.edu.utn.frba.dds.servicio;

import ar.edu.utn.frba.dds.modelo.administrador.Administrador;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
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
  private DetectorDeSpamServicio detectorDeSpamServicioTFIDF;

  public Collection<SolicitudDeEliminacionDeHecho> solicitudesDeEliminacionDeHecho() {
    return solicitudEliminacionRepositorio.solicitudesDeEliminacionDeHecho();
  }

  public SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho(Long solicitudDeEliminacionDeHechoId) {
    return solicitudEliminacionRepositorio
        .solicitudDeEliminacionDeHecho(solicitudDeEliminacionDeHechoId);
  }

  public void guardarSolicitudDeEliminacionDeHecho(SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho) {
    solicitudEliminacionRepositorio.guardar(solicitudDeEliminacionDeHecho);
    detectorDeSpamServicioTFIDF.rechazaSpam(solicitudDeEliminacionDeHecho);
  }

  public SolicitudDeEliminacionDeHecho buscarSolicitudDeEliminacionDeHecho(Hecho hecho) {
    return solicitudEliminacionRepositorio.solicitudesDeEliminacionDeHecho().stream().filter(
        s -> s.getHecho().equals(hecho)
    ).findFirst().get();
  }

  public void aprobar(SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho, Administrador administrador) {
    solicitudDeEliminacionDeHecho.aprobar(administrador);
  }

  public void rechazar(SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho, Administrador administrador) {
    solicitudDeEliminacionDeHecho.rechazar(administrador);
  }
}
