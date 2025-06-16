package ar.edu.utn.frba.dds.modelo.coleccion;

import static ar.edu.utn.frba.dds.modelo.fuente.TipoFuente.METAMAPA;
import static java.util.stream.Collectors.toSet;

import ar.edu.utn.frba.dds.modelo.coleccion.filtro.FiltroDeHecho;
import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Coleccion {
  private String handle;
  private String titulo;
  private String descripcion;
  private Map<Fuente, Collection<Hecho>> fuentes;
  private FiltrosParaHecho criteriosDePertenencia;

  public Coleccion(String titulo, String descripcion, Fuente... fuentes) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.fuentes = new HashMap<>();
    Arrays.stream(fuentes).sequential().forEach(
        f -> this.fuentes.put(f, new LinkedHashSet<>()));
    this.criteriosDePertenencia = new FiltrosParaHecho();
  }

  public String getTitulo() {
    return titulo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public Set<Fuente> getFuentes() {
    return fuentes.keySet();
  }

  public void agregarFiltro(FiltroDeHecho filtro) {
    this.criteriosDePertenencia.agregarFiltro(filtro);
  }

  public void colectarHechos() {
    fuentes.forEach((f, hechosExistentes) -> {
      if (!METAMAPA.equals(f.getTipoFuente())) {
        actualizarLosNoEliminados(f, hechosExistentes);
      }
    });
  }

  public Collection<Hecho> hechos() {
    fuentes.forEach((f, hechosExistentes) -> {
          if (METAMAPA.equals(f.getTipoFuente())) {
            actualizarLosNoEliminados(f, hechosExistentes);
          }
        }
    );
    return this.fuentes.values()
        .stream()
        .flatMap(Collection::stream)
        .collect(toSet())
        .stream()
        .filter(hecho -> !hecho.estaEliminado())
        .filter(criteriosDePertenencia::aplicarFiltros)
        .collect(toSet());
  }

  private void actualizarLosNoEliminados(Fuente f, Collection<Hecho> hechosExistentes) {
    hechosExistentes.addAll(f.hechos().stream().filter(h -> !h.estaEliminado()).collect(toSet()));
  }

}