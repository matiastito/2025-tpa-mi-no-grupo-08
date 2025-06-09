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
  private final AtomicLong proximoId = new AtomicLong(1);

  @Override
  public void guardar(Hecho hecho) {
    hechos.put(proximoId.getAndIncrement(), hecho);
  }

  @Override
  public void modificar(Hecho hecho) {
    hechos.keySet().forEach(id -> {
      if (hechos.get(id).equals(hecho)) {
        hechos.put(id, hecho);
      }
    });
  }

  @Override
  public Hecho dameHecho(Hecho hecho) {
    return hechos.values().stream().filter(h -> h.equals(hecho)).findFirst().get();
  }

  @Override
  public Collection<Hecho> dameHechos() {
    return hechos.values();
  }
}
