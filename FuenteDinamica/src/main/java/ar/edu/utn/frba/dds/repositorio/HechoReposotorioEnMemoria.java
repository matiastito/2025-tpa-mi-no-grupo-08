package ar.edu.utn.frba.dds.repositorio;

import static java.util.Collections.emptySet;
import ar.edu.utn.frba.dds.modelo.Hecho;

import java.util.Collection;

public class HechoReposotorioEnMemoria implements HechoRepositorio {

  @Override
  public void guardar(Hecho hecho) {

  }

  @Override
  public Collection<Hecho> dameHechos() {
    return emptySet();
  }
}
