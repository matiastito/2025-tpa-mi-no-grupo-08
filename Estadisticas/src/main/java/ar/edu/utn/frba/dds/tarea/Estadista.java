package ar.edu.utn.frba.dds.tarea;

import ar.edu.utn.frba.dds.model.Fuente;
import ar.edu.utn.frba.dds.model.estadistica.EstadisticaColeccion;
import ar.edu.utn.frba.dds.repositorio.FuenteRepositorio;
import ar.edu.utn.frba.dds.repositorio.ProvinciaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Estadista {

  @Autowired
  private FuenteRepositorio fuenteRepositorio;

  @Autowired
  private ProvinciaRepositorio provinciaRepositorio;

  @Scheduled(fixedRate = 500)
  public void generarEstadisticas() {
    List<Fuente> fuentes = fuenteRepositorio.findAll();
    fuentes.forEach(f -> {
      f.colecciones().forEach(c -> {
        new EstadisticaColeccion(c.getHandle());
        f.coleccion(c.getHandle()).forEach(h -> {
          h.getUbicacion().getProvincia();

        });
      });
    });
  }
}
