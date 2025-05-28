package ar.edu.utn.frba.dds.servicio;

import static java.util.stream.Collectors.toSet;

import ar.edu.utn.frba.dds.modelo.coleccion.Coleccion;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.repositorio.ColeccionRepositorioEnMemoria;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColeccionServicio {
  @Autowired
  private ColeccionRepositorioEnMemoria coleccionRepositorio;

  public Collection<Coleccion> colecciones() {
    return coleccionRepositorio.colleciones();
  }

  public Coleccion coleccion(Long coleccionId) {
    return coleccionRepositorio.collecion(coleccionId);
  }

  public void guardarColeccion(Coleccion coleccion) {
    coleccionRepositorio.guardar(coleccion);
  }

  public Collection<Hecho> hechos() {
    return colecciones()
        .stream()
        .flatMap(coleccion -> coleccion.hechos().stream())
        .collect(toSet());
  }

  public Hecho buscarHechoPorTitulo(String tituloHecho) {
    return hechos().stream()
        .filter(h -> h.getTitulo().equalsIgnoreCase(tituloHecho))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("No se puede crear un solicitud de eliminacion para un hecho inexistente."));
  }
}
