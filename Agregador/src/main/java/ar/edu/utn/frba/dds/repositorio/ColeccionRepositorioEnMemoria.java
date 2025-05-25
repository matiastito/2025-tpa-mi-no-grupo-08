package ar.edu.utn.frba.dds.repositorio;

import static java.util.Collections.emptySet;

import ar.edu.utn.frba.dds.modelo.coleccion.Coleccion;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class ColeccionRepositorioEnMemoria implements ColeccionRepositorio {
  private Map<Long, Coleccion> colecciones;
  private AtomicLong proximoId;

  @Override
  public void guardar(Coleccion coleccion) {
    this.colecciones.put(proximoId.getAndIncrement(), coleccion);
  }

  public Collection<Coleccion> colleciones() {
    return emptySet();
  }

  public Coleccion collecion(String coleccionId) {
    return null;
  }
}
