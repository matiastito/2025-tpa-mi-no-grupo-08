package ar.edu.utn.frba.dds.navegacion;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import java.util.Collection;
import java.util.List;

public interface Consenso {
  List<Boolean> analizarHechoEnFuente(Collection<Hecho> hechos, Hecho hecho);

  boolean hayConsenso(List<List<Boolean>> resultList);
}
