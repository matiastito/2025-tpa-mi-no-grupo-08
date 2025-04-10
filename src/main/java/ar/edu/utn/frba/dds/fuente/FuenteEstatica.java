package ar.edu.utn.frba.dds.fuente;

import static java.util.Set.of;

import ar.edu.utn.frba.dds.hecho.Hecho;

import java.util.Collection;

public class FuenteEstatica implements Fuente {
  @Override
  public Collection<Hecho> traerHechos() {
    return of();
  }
}
