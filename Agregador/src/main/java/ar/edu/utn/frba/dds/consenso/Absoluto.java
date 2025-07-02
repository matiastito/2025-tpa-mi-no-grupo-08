package ar.edu.utn.frba.dds.consenso;

import static java.util.List.of;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import java.util.Collection;
import java.util.List;

public class Absoluto implements Consenso {
  @Override
  public List<Boolean> analizarHechoEnFuente(Collection<Hecho> hechos, Hecho hecho) {
    return of(hechos.contains(hecho));
  }

  @Override
  public boolean hayConsenso(List<List<Boolean>> resultList) {
    return resultList.stream().allMatch(b -> b.get(0));
  }
}
