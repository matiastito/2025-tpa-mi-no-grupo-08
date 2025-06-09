package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import java.util.Collection;

public interface HechoRepositorio {
  void guardar(Hecho hecho);

  void modificar(Hecho hecho);

  Hecho dameHecho(Hecho hecho);

  Collection<Hecho> dameHechos();
}
