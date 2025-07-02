package ar.edu.utn.frba.dds.navegacion;

import java.util.Collection;
import java.util.List;
import static java.util.List.of;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;

public class MultiplesMenciones implements Consenso {

  @Override
  public List<Boolean> analizarHechoEnFuente(Collection<Hecho> hechos, Hecho hecho) {
    return of(hechos.contains(hecho), !hayOtroParecido(hecho, hechos));
  }

  private boolean hayOtroParecido(Hecho hecho, Collection<Hecho> hechos) {
    return hechos.stream().anyMatch(h ->
        h.getTitulo().equalsIgnoreCase(hecho.getTitulo())
            && !hecho.getFechaDelHecho().equals(h.getFechaDelHecho())
            && !hecho.getCategoria().equals(h.getCategoria())
    );
  }

  @Override
  public boolean hayConsenso(List<List<Boolean>> resultList) {
    return resultList.stream().allMatch(r -> r.get(1))
        && resultList.stream().filter(r -> r.get(0)).count() > 1;
  }
}
