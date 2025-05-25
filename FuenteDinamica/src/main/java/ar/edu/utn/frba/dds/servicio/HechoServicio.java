package ar.edu.utn.frba.dds.servicio;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.repositorio.HechoRepositorio;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HechoServicio {

  @Autowired
  private HechoRepositorio hechoRepositorio;

  public void guardarHecho(Hecho hecho) {
    hechoRepositorio.guardar(hecho);
  }

  public Collection<Hecho> dameHechos() {
    return hechoRepositorio.dameHechos();
  }
}
