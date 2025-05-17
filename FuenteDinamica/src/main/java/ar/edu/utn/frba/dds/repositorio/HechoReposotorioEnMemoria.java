package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.Hecho;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class HechoReposotorioEnMemoria implements HechoRepositorio {
  private Map<Long, Hecho> hechos;
  private AtomicLong proximoId;

  @Override
  public void guardar(Hecho hecho) {
    hechos.put(proximoId.incrementAndGet(), hecho);
  }

  @Override
  public Collection<Hecho> dameHechos() {
    return hechos.values();
  }
}
