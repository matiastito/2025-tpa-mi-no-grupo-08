package ar.edu.utn.frba.dds.servicio;

import static ar.edu.utn.frba.dds.modelo.hecho.HechoModificacionEstado.PENDIENTE;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.modelo.hecho.HechoModificacion;
import ar.edu.utn.frba.dds.repositorio.HechoModiciacionRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HechoModificacionServicio {
  @Autowired
  private HechoModiciacionRepositorio hechoModificacionRepositorio;
  @Autowired
  private FuenteServicio fuenteServicio;
  @Autowired
  private HechoServicio hechoServicio;

  public void guardarHechoModificacion(HechoModificacion hechoModificacion, Long hechoId) {
    Optional<Hecho> hecho = hechoServicio.getHecho(hechoId);
    if (hecho.isPresent()) {
      hechoModificacion.setHecho(hecho.get());
      hechoModificacionRepositorio.save(hechoModificacion);
    }
  }

  public List<HechoModificacion> pendientes() {
    return hechoModificacionRepositorio.findByHechoModificacionEstado(PENDIENTE);
  }

  public void aceptarHechoModificacion(Long hechoModificacionId) {
    Optional<HechoModificacion> hechoModificacion = hechoModificacionRepositorio.findById(hechoModificacionId);
    if (hechoModificacion.isPresent()) {
      hechoModificacion.get().aceptar();
      hechoModificacionRepositorio.save(hechoModificacion.get());
      if (hechoModificacion.get().getHecho().esDeFuenteDinamica()) {
        fuenteServicio.editarHecho(hechoModificacion.get().getHecho());
      }
    }
  }

  public void rechazarHechoModificacion(Long hechoModificacionId) {
    Optional<HechoModificacion> hechoModificacion = hechoModificacionRepositorio.findById(hechoModificacionId);
    if (hechoModificacion.isPresent()) {
      hechoModificacion.get().rechazar();
      hechoModificacionRepositorio.save(hechoModificacion.get());
    }
  }
}
