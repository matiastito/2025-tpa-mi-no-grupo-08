package ar.edu.utn.frba.dds.modelo.coleccion;

import static java.util.stream.Collectors.toCollection;
import ar.edu.utn.frba.dds.modelo.coleccion.filtro.FiltroDeHecho;
import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Coleccion {
  private String handle;
  private String titulo;
  private String descripcion;
  private Set<Hecho> hechos;
  private Fuente fuente;
  private FiltrosParaHecho criteriosDePertenencia;

  public Coleccion(String titulo, String descripcion, Fuente fuente) {
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
    this.hechos.addAll(fuente.hechos());
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
