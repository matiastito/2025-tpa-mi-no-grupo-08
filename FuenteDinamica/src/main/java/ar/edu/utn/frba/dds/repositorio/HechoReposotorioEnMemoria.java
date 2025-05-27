package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class HechoReposotorioEnMemoria implements HechoRepositorio {
  private final Map<Long, Hecho> hechos = new HashMap<>();
  private final AtomicLong proximoId = new AtomicLong();

  @Override
  public void guardar(Hecho hecho) {
    hechos.put(proximoId.incrementAndGet(), hecho);
  }

  @Override
  public Collection<Hecho> dameHechos() {
    return hechos.values();
  }
}
