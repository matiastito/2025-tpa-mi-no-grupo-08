package ar.edu.utn.frba.dds.servicio;

import static java.time.LocalDateTime.now;
import static java.time.temporal.ChronoUnit.DAYS;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.repositorio.HechoRepositorio;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HechoServicio {

  private static final int CANTIDAD_DE_DIAS_LIMITE_MODIFICACION = 7;

  @Autowired
  private HechoRepositorio hechoRepositorio;

  public void guardarHecho(Hecho hecho) {
    hechoRepositorio.guardar(hecho);
  }

  public void modificarHecho(Hecho hecho) {
    if (DAYS.between(hecho.getFechaDeCarga(), now()) > CANTIDAD_DE_DIAS_LIMITE_MODIFICACION) {
      throw new RuntimeException("No se puede modificar el Hecho, ha pasado la fecha limite.");
    }
    Hecho hechoExistente = hechoRepositorio.dameHecho(hecho);
    if (hechoExistente.getContribuyente() != null) {
      throw new RuntimeException("No se puede modificar un Hecho anonimo.");
    }
    hechoRepositorio.modificar(hecho);
  }

  public Collection<Hecho> dameHechos() {
    return hechoRepositorio.dameHechos();
  }
}
