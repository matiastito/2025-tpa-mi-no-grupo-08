package ar.edu.utn.frba.dds.modelo.coleccion;

import static ar.edu.utn.frba.dds.modelo.fuente.TipoFuente.METAMAPA;
import static java.util.stream.Collectors.toSet;

import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class FuenteConHechos {
  private final Fuente fuente;
  private final Set<Hecho> hechos = new HashSet<>();

  public FuenteConHechos(Fuente fuente) {
    this.fuente = fuente;
  }

  public void refrescar() {
    if (!METAMAPA.equals(fuente.getTipoFuente())) {
      actualizarLosNoEliminados();
    }
  }

  public void actualizarLosNoEliminados() {
    hechos.addAll(
        fuente.hechos().stream()
            .filter(h -> !h.estaEliminado()).collect(toSet()));
  }

  public Fuente getFuente() {
    return fuente;
  }

  public Collection<Hecho> getHechos() {
    if (METAMAPA.equals(fuente.getTipoFuente())) {
      return fuente.hechos();
    }
    return hechos;
  }
}
