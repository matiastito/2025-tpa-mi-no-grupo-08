package ar.edu.utn.frba.dds.modelo.fuente;

import static java.time.LocalDateTime.MIN;
import static java.time.LocalDateTime.now;
import static java.time.temporal.ChronoUnit.MINUTES;

import ar.edu.utn.frba.dds.web.dto.HechoDTO;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;

public class CacheParaFuenteProxyAPIdeDDS {
  private Collection<HechoDTO> hechos = new HashSet<>();
  private LocalDateTime ultimaActualizacion = MIN;

  public Collection<HechoDTO> hechos() {
    return hechos;
  }


  public boolean isRecent() {
    return MINUTES.between(ultimaActualizacion, now()) < 5;
  }

  public void actualizar(Collection<HechoDTO> hechos) {
    this.hechos.clear();
    this.hechos.addAll(hechos);
    this.ultimaActualizacion = now();
  }
}
