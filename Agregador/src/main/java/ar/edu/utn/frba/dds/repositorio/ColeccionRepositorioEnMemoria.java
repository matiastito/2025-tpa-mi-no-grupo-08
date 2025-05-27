package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.coleccion.Coleccion;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class ColeccionRepositorioEnMemoria implements ColeccionRepositorio {
  private final Map<Long, Coleccion> colecciones = new HashMap<>();
  private final AtomicLong proximoId = new AtomicLong(1);

  @Override
  public void guardar(Coleccion coleccion) {
    this.colecciones.put(proximoId.getAndIncrement(), coleccion);
  }

  @Override
  public Collection<Coleccion> colleciones() {
    return colecciones.values();
  }

  @Override
  public Coleccion collecion(Long coleccionId) {
    return colecciones.get(coleccionId);
  }
}
