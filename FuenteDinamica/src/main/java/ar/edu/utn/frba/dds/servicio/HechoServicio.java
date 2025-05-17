package ar.edu.utn.frba.dds.servicio;

import static java.util.Collections.emptySet;

import ar.edu.utn.frba.dds.modelo.Hecho;
import ar.edu.utn.frba.dds.repositorio.HechoRepositorio;
import ar.edu.utn.frba.dds.web.dto.HechoDTO;
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

  public Collection<HechoDTO> dameHechos() {
    return emptySet();
  }
}
