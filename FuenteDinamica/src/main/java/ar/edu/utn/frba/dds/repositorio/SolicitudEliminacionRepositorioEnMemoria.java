package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHecho;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class SolicitudEliminacionRepositorioEnMemoria implements SolicitudEliminacionRepositorio {
  private final Map<Long, SolicitudDeEliminacionDeHecho> solicitudesDeEliminacionDeHecho = new HashMap<>();
  private final AtomicLong proximoId = new AtomicLong(1);

  @Override
  public void guardar(SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho) {
    this.solicitudesDeEliminacionDeHecho.put(proximoId.getAndIncrement(), solicitudDeEliminacionDeHecho);
  }

  @Override
  public Collection<SolicitudDeEliminacionDeHecho> solicitudesDeEliminacionDeHecho() {
    return solicitudesDeEliminacionDeHecho.values();
  }

  @Override
  public SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho(Long solicitudDeEliminacionDeHechoId) {
    return solicitudesDeEliminacionDeHecho.get(solicitudDeEliminacionDeHechoId);
  }
}
