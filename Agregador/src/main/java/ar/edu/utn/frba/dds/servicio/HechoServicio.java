package ar.edu.utn.frba.dds.servicio;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.repositorio.HechoRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HechoServicio {
  @Autowired
  private HechoRepositorio hechoRepositorio;

  public Optional<Hecho> getHecho(Long id) {
    return hechoRepositorio.findById(id);
  }
}
