package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHecho;
import java.util.Collection;

public interface SolicitudEliminacionRepositorio {

  void guardar(SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho);

  Collection<SolicitudDeEliminacionDeHecho> solicitudesDeEliminacionDeHecho();

  SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho(Long solicitudDeEliminacionDeHechoId);
}
