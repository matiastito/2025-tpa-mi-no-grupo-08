package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.administrador.Administrador;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class AdministradorRepositorioEnMemoria implements AdministradorRespositorio {
  private final Map<Long, Administrador> administradores = new HashMap<>();
  private final AtomicLong proximoId = new AtomicLong(1);

  @Override
  public Administrador administrador(String nombre) {
    return administradores.values().stream().filter(a -> a.getNombre().equalsIgnoreCase(nombre)).findFirst().get();
  }
}
