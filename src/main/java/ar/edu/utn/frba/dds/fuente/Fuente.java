package ar.edu.utn.frba.dds.fuente;

import ar.edu.utn.frba.dds.hecho.Hecho;

import java.util.Collection;

public interface Fuente {
  Collection<Hecho> traerHechos();
}
