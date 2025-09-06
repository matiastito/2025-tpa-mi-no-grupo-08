package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.fuente.FuenteProxy;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioFuenteProxy {
  List<FuenteProxy> fuentes();

  void agregar(FuenteProxy fuente);
}
