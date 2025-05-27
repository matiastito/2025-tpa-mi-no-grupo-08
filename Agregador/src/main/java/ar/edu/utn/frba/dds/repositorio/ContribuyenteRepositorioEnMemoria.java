package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.colaborador.Contribuyente;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class ContribuyenteRepositorioEnMemoria implements ContribuyenteRepositorio {
  private final Map<Long, Contribuyente> contribuyentes = new HashMap<>();
  private final AtomicLong proximoId = new AtomicLong(1);

  @Override
  public void guardar(Contribuyente contribuyente) {
    this.contribuyentes.put(proximoId.getAndIncrement(), contribuyente);
  }

  @Override
  public Collection<Contribuyente> contribuyentes() {
    return contribuyentes.values();
  }

  @Override
  public Contribuyente contribuyente(Long contribuyenteId) {
    return contribuyentes.get(contribuyenteId);
  }

  @Override
  public Contribuyente contribuyente(String nombre, String apellido) {
    return contribuyentes().stream().filter(c ->
            c.getApellido().equalsIgnoreCase(apellido) && c.getNombre().equalsIgnoreCase(nombre))
        .findFirst()
        .orElse(null);
  }
}
