package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import java.util.Collection;

public interface RepositorioDeHechos {
  Collection<Hecho> hechos();

  void agregar(Collection<Hecho> hechos);

  void agregar(Hecho hechos);
}
