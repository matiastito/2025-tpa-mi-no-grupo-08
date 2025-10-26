package ar.edu.utn.frba.dds.servicio;

import static java.time.LocalDateTime.now;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.stream.Collectors.toSet;

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
    //FIXME buscar las entidades internas
    hechoRepositorio.save(hecho);
  }

  public void modificarHecho(Hecho hechoModificado) {
    Hecho hechoExistente = hechoRepositorio.findById(hechoModificado.getId()).get();

    if (DAYS.between(hechoExistente.getFechaDeCarga(), now()) > CANTIDAD_DE_DIAS_LIMITE_MODIFICACION) {
      throw new RuntimeException("No se puede modificar el Hecho, ha pasado la fecha limite.");
    }
    if (hechoExistente.getContribuyente() != null) {
      throw new RuntimeException("No se puede modificar un Hecho anonimo.");
    }

    hechoExistente.setFechaDelHecho(hechoModificado.getFechaDelHecho());
    hechoExistente.setDescripcion(hechoModificado.getDescripcion());
    hechoExistente.setTitulo(hechoModificado.getTitulo());
    hechoExistente.setUbicacion(hechoModificado.getUbicacion());
    hechoRepositorio.save(hechoExistente);
  }

  public Collection<Hecho> dameHechos() {
    return hechoRepositorio.findAll().stream().filter(h -> !h.isEliminado()).collect(toSet());
  }

  public Hecho buscarHechoPorTitulo(String tituloHecho) {
    return hechoRepositorio.
        findAll().stream()
        .filter(h -> !h.isEliminado() && h.getTitulo().equalsIgnoreCase(tituloHecho))
        .findFirst().get();
  }
}
