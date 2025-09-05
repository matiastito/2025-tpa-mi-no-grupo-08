package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.fuente.FuenteProxy;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioFuenteProxyEnMemoria implements RepositorioFuenteProxy {
  private final Map<Long, FuenteProxy> fuentes = new HashMap<>();
  private final AtomicLong proximoId = new AtomicLong(1);


  @Override
  public Collection<FuenteProxy> fuentes() {
    return fuentes.values();
  }

  @Override
  public void agregar(FuenteProxy fuente) {
    fuentes.put(proximoId.getAndIncrement(), fuente);
  }
}
