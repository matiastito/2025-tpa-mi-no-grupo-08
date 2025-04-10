package ar.edu.utn.frba.dds.coleccion;

import static java.util.stream.Collectors.toCollection;

import ar.edu.utn.frba.dds.hecho.Hecho;
import ar.edu.utn.frba.dds.fuente.Fuente;

import java.util.Collection;
import java.util.HashSet;

public class Coleccion {
  private String titulo;
  private String descripcion;
  private Collection<Hecho> hechos;
  private Fuente fuente;
  private CriterioDePertenencia criterioDePertenencia;

  public void bajarHechosDesdeLaFuente() {
    this.hechos.addAll(
        fuente.traerHechos().stream()
            .filter(criterioDePertenencia::pertenece)
            .collect(toCollection(HashSet::new)));
  }

  public Collection<Hecho> dameHechos() {
    return this.hechos.stream().filter(hecho -> !hecho.isEliminado())
        .collect(toCollection(HashSet::new));
  }
}
