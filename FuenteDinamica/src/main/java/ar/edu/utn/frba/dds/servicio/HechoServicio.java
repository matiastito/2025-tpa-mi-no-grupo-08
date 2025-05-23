package ar.edu.utn.frba.dds.servicio;

import ar.edu.utn.frba.dds.repositorio.HechoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

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
