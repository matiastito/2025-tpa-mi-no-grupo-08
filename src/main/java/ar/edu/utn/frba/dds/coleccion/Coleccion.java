package ar.edu.utn.frba.dds.coleccion;

import static java.util.stream.Collectors.toCollection;
import ar.edu.utn.frba.dds.coleccion.filtro.FiltroDeHecho;
import ar.edu.utn.frba.dds.fuente.Fuente;
import ar.edu.utn.frba.dds.hecho.Hecho;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Coleccion {
  private String titulo;
  private String descripcion;
  private Set<Hecho> hechos;
  private Fuente fuente;
  private List<FiltroDeHecho> criteriosDePertenencia;

  private Coleccion(String titulo, String descripcion, Fuente fuente) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.fuente = fuente;
    this.criteriosDePertenencia = new ArrayList<>();
    this.hechos = new HashSet<>();
  }

  private Coleccion(String titulo, String descripcion) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.criteriosDePertenencia = new ArrayList<>();
    this.hechos = new HashSet<>();
  }

  public static Coleccion crearColeccionManual(String titulo, String descripcion) {
    return new Coleccion(titulo, descripcion);
  }

  public static Coleccion crearColeccion(String titulo, String descripcion, Fuente fuente) {
    return new Coleccion(titulo, descripcion, fuente);
  }

  public void agregarFiltro(FiltroDeHecho filtro) {
    this.criteriosDePertenencia.add(filtro);
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
        .filter(this::aplicarFiltros)
        .collect(toCollection(HashSet::new));
  }

  private boolean aplicarFiltros(Hecho hecho) {
    return this.criteriosDePertenencia.stream().anyMatch(
        filtroDeHecho -> filtroDeHecho.filtrar(hecho));
  }

}
