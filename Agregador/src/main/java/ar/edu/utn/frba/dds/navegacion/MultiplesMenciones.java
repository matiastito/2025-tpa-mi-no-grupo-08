package ar.edu.utn.frba.dds.navegacion;

import java.util.Collection;
import java.util.List;
import static java.util.List.of;
import java.util.stream.Collectors;

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
    return resultList.stream().anyMatch(r -> r.get(1))
        && resultList.stream().filter(r -> r.get(0)).count() > 1;
  }

   public boolean esConsensuado(Hecho hecho, Collection<Collection<Hecho>> hechosDeTodasLasFuentes) {
    // Juntar todos los hechos en una lista plana
    List<Hecho> todosLosHechos = hechosDeTodasLasFuentes.stream()
        .flatMap(Collection::stream)
        .collect(Collectors.toList());

    // Contar en cuántas fuentes aparece el hecho exacto
    long cantidadFuentesConHecho = hechosDeTodasLasFuentes.stream()
        .filter(coleccionHechos -> coleccionHechos.contains(hecho))
        .count();

    // Verificar si existe un conflicto (mismo título, diferentes atributos)
    boolean hayConflictos = todosLosHechos.stream().anyMatch(h ->
    h.getTitulo().equalsIgnoreCase(hecho.getTitulo())
        && (!h.getFechaDelHecho().equals(hecho.getFechaDelHecho())
        || !h.getCategoria().equals(hecho.getCategoria()))
    );

    // Condición final
    return cantidadFuentesConHecho > 1 && !hayConflictos;
  }
}
