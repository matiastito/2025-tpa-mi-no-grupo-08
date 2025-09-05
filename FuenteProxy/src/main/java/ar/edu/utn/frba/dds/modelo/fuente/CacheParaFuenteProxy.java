package ar.edu.utn.frba.dds.modelo.fuente;

import ar.edu.utn.frba.dds.servicio.FuenteProxyServicio;
import ar.edu.utn.frba.dds.web.dto.HechoDTO;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacheParaFuenteProxy {
  @Autowired
  private FuenteProxyServicio fuenteProxyServicio;

  private Map<FuenteProxy, CacheHechos> cacheFuente;

  public Collection<HechoDTO> hechos() {
    Set<HechoDTO> hechos = new HashSet<>();

    Collection<FuenteProxy> fuentes = fuenteProxyServicio.fuentes();
    fuentes.forEach(f -> {
      if (!cacheFuente.containsKey(f)) {
        cacheFuente.put(f, new CacheHechos());
      }
      if (!cacheFuente.get(f).isRecent()) {
        cacheFuente.get(f).actualizar(f.hechos());
      }
      hechos.addAll(cacheFuente.get(f).getHechos());
    });

    return hechos;
  }
}
