package ar.edu.utn.frba.dds.coleccion;

import static java.util.stream.Collectors.toCollection;

import ar.edu.utn.frba.dds.coleccion.filtro.FiltroDeHecho;
import ar.edu.utn.frba.dds.fuente.estatica.FuenteEstatica;
import ar.edu.utn.frba.dds.hecho.Hecho;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Coleccion {
  private String titulo;
  private String descripcion;
  private Set<Hecho> hechos;
  private FuenteEstatica fuente;
  private FiltrosParaHecho criteriosDePertenencia;

  public Coleccion(String titulo, String descripcion, FuenteEstatica fuente) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.fuente = fuente;
    this.criteriosDePertenencia = new FiltrosParaHecho();
    this.hechos = new HashSet<>();
  }

  public Coleccion(String titulo, String descripcion) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.criteriosDePertenencia = new FiltrosParaHecho();
    this.hechos = new HashSet<>();
  }

  public void agregarFiltro(FiltroDeHecho filtro) {
    this.criteriosDePertenencia.agregarFiltro(filtro);
  }

  public void colectarHechos() {
    this.hechos.addAll(fuente.traerHechos());
  }

  public void agregarHecho(Hecho hecho) {
    if (!hecho.estaEliminado()) {
      this.hechos.add(hecho);
    }
  }

  public Collection<Hecho> hechos() {
    return this.hechos.stream()
        .filter(hecho -> !hecho.estaEliminado())
        .filter(criteriosDePertenencia::aplicarFiltros)
        .collect(toCollection(HashSet::new));
  }
}
