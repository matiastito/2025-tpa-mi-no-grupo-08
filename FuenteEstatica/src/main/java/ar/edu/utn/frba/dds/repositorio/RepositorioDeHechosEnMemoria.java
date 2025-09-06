package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioDeHechosEnMemoria implements RepositorioDeHechos {
  private final Map<Long, Hecho> hechos = new HashMap<>();
  private final AtomicLong proximoId = new AtomicLong(1);

  public Collection<Hecho> hechos() {
    return hechos.values();
  }

  @Override
  public void agregar(Collection<Hecho> hechos) {
    hechos.forEach(h -> this.hechos.put(proximoId.getAndIncrement(), h));
  }

  @Override
  public void agregar(Hecho hecho) {
    Optional<Long> clave = hechos.entrySet().stream()
        .filter(entry -> entry.getValue().equals(hecho))
        .map(Map.Entry::getKey)
        .findFirst();
    clave.ifPresent(aLong -> this.hechos.replace(aLong, hecho));
  }
}
