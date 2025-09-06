package ar.edu.utn.frba.dds.modelo.fuente.cache;

import ar.edu.utn.frba.dds.modelo.fuente.FuenteProxy;
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
  private FuenteProxyServicio fuenteProxyMetaMapaServicio;

  private Map<FuenteProxy, CacheHechos> cacheFuente;

  public Collection<HechoDTO> hechos() {
    Set<HechoDTO> hechos = new HashSet<>();

    Collection<FuenteProxy> fuentes = fuenteProxyMetaMapaServicio.fuentes();
    fuentes.forEach(fuente -> {
      if (!cacheFuente.containsKey(fuente)) {
        cacheFuente.put(fuente, new CacheHechos());
      }
      if (!cacheFuente.get(fuente).isRecent()) {
        cacheFuente.get(fuente).actualizar(fuente.hechos());
      }
      hechos.addAll(cacheFuente.get(fuente).getHechos());
    });

    return hechos;
  }
}
