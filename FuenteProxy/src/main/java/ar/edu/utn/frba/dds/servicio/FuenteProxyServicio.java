package ar.edu.utn.frba.dds.servicio;

import ar.edu.utn.frba.dds.modelo.fuente.FuenteProxy;
import ar.edu.utn.frba.dds.repositorio.RepositorioFuenteProxy;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuenteProxyServicio {

  @Autowired
  private RepositorioFuenteProxy repositorioFuenteProxy;

  public void guardar(FuenteProxy fuenteProxy) {
    repositorioFuenteProxy.agregar(fuenteProxy);
  }

  public List<FuenteProxy> fuentes() {
    return repositorioFuenteProxy.fuentes();
  }
}
