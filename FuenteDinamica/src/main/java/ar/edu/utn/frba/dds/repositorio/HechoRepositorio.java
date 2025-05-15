package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.Hecho;

import java.util.Collection;

public interface HechoRepositorio {
  void guardar(Hecho hecho);

  Collection<Hecho> dameHechos();
}
