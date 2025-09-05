package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.fuente.FuenteProxy;
import java.util.Collection;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioFuenteProxy {
  Collection<FuenteProxy> fuentes();

  void agregar(FuenteProxy fuente);
}
