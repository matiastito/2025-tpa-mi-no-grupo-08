package ar.edu.utn.frba.dds.navegacion;

import static ar.edu.utn.frba.dds.navegacion.TipoConsenso.ABSOLUTO;
import static ar.edu.utn.frba.dds.navegacion.TipoConsenso.MAYORIA_SIMPLE;
import static ar.edu.utn.frba.dds.navegacion.TipoConsenso.MULTIPLES_MENCIONES;
import static java.util.Map.of;

import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class Consensos {
  private final Map<TipoConsenso, Consenso> consensos = of(
      ABSOLUTO, new Absoluto(),
      MAYORIA_SIMPLE, new MayoriaSimple(),
      MULTIPLES_MENCIONES, new MultiplesMenciones()
  );

  public Consenso getConsenso(TipoConsenso tipoConsenso) {
    return consensos.get(tipoConsenso);
  }
}
