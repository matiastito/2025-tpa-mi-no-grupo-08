package ar.edu.utn.frba.dds.modelo.coleccion;

import static ar.edu.utn.frba.dds.modelo.fuente.TipoFuente.METAMAPA;
import static java.util.stream.Collectors.toSet;

import ar.edu.utn.frba.dds.consenso.TipoConsenso;
import ar.edu.utn.frba.dds.modelo.coleccion.filtro.FiltroDeHecho;
import ar.edu.utn.frba.dds.modelo.coleccion.filtro.FiltrosParaHecho;
import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class Coleccion {
  private String handle;
  private String titulo;
  private String descripcion;
  private TipoConsenso tipoConsenso;
  //TODO considerar la bidereccionalidad en fuente
  private List<FuenteConHechos> fuentesConHechos;
  private FiltrosParaHecho criteriosDePertenencia;

  public Coleccion(String titulo, String descripcion, Fuente... fuentes) {
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.fuentesConHechos = new ArrayList<>();
    this.criteriosDePertenencia = new FiltrosParaHecho();
  }

  public String getTitulo() {
    return titulo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public Set<Fuente> getFuentes() {
    return fuentesConHechos.stream().map(FuenteConHechos::getFuente).collect(toSet());
  }

  public void setTipoConsenso(TipoConsenso tipoConsenso) {
    this.tipoConsenso = tipoConsenso;
  }

  public void agregarFiltro(FiltroDeHecho filtro) {
    this.criteriosDePertenencia.agregarFiltro(filtro);
  }

  public void refrescar() {
    fuentesConHechos.forEach(FuenteConHechos::refrescar);
  }

  public Collection<Collection<Hecho>> hechosAgrupadosPorFuente() {
    return fuentesConHechos.stream()
        .map(fuenteConHechos -> fuenteConHechos.getHechos())
        .collect(toSet());
  }

  public Collection<Hecho> hechos() {
    fuentesConHechos.forEach(f -> {
          if (METAMAPA.equals(f.getFuente().getTipoFuente())) {
            f.actualizarLosNoEliminados();
          }
        }
    );
    return this.fuentesConHechos
        .stream()
        .map(FuenteConHechos::getHechos)
        .flatMap(Collection::stream)
        .filter(hecho -> !hecho.estaEliminado())
        .filter(criteriosDePertenencia::aplicarFiltros)
        .collect(toSet());
  }

  public TipoConsenso getTipoConsenso() {
    return tipoConsenso;
  }
}