package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.fuente.FuenteProxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioFuenteProxyEnMemoria implements RepositorioFuenteProxy {
  private final Map<Long, FuenteProxy> fuentes = new HashMap<>();
  private final AtomicLong proximoId = new AtomicLong(1);

  @Override
  public List<FuenteProxy> fuentes() {
    return fuentes.values().stream().toList();
  }

  @Override
  public void agregar(FuenteProxy fuente) {
    long id = proximoId.getAndIncrement();
    fuentes.put(id, fuente);
    fuente.setId(id);
  }
}
