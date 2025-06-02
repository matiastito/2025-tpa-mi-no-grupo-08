package ar.edu.utn.frba.dds.modelo.coleccion;

import static ar.edu.utn.frba.dds.modelo.fuente.TipoFuente.PROXY;
import static java.util.stream.Collectors.toCollection;

import ar.edu.utn.frba.dds.modelo.coleccion.filtro.FiltroDeHecho;
import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

//FIXME agergar una coleccion de fuentes, ver cache para fuentesProxy
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

  public String getTitulo() {
    return titulo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public Fuente getFuente() {
    return fuente;
  }

  public void agregarFiltro(FiltroDeHecho filtro) {
    this.criteriosDePertenencia.agregarFiltro(filtro);
  }

  public void colectarHechos() {
    //FIXME acá que lo saque y lo vuelva a agregar (ver que hace el addAll), que se quede con el mas nuevo.
    this.hechos.addAll(fuente.hechos());
  }

  public void agregarHecho(Hecho hecho) {
    if (!hecho.estaEliminado()) {
      this.hechos.add(hecho);
    }
  }

  public Collection<Hecho> hechos() {
    if (PROXY.equals(fuente.getTipoFuente())) {
      colectarHechos();
    }
    return this.hechos.stream()
        .filter(hecho -> !hecho.estaEliminado())
        .filter(criteriosDePertenencia::aplicarFiltros)
        .collect(toCollection(HashSet::new));
  }
}
