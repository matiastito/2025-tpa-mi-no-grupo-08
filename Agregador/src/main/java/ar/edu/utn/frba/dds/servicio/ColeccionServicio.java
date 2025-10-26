package ar.edu.utn.frba.dds.servicio;

import static java.util.stream.Collectors.toSet;

import ar.edu.utn.frba.dds.consenso.TipoConsenso;
import ar.edu.utn.frba.dds.modelo.coleccion.Coleccion;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.repositorio.ColeccionRepositorio;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColeccionServicio {
  @Autowired
  private ColeccionRepositorio coleccionRepositorio;

  public Collection<Coleccion> colecciones() {
    return coleccionRepositorio.findAll();
  }

  public Coleccion coleccion(Long coleccionId) {
    return coleccionRepositorio.findById(coleccionId).get();
  }

  public void guardarColeccion(Coleccion coleccion) {
    coleccionRepositorio.save(coleccion);
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

  public void eliminarColeccion(Long coleccionId) {
    coleccionRepositorio.deleteById(coleccionId);
  }

  public void cambiarTipoConsenso(Long coleccionId, TipoConsenso tipoConsenso) {
    Coleccion coleccion = coleccionRepositorio.findById(coleccionId).get();
    coleccion.setTipoConsenso(tipoConsenso);
    coleccionRepositorio.save(coleccion);
  }

  public void editarColeccion(Coleccion coleccion) {
    Coleccion coleccionAEditar = coleccion(coleccion.getId());
    coleccionAEditar.setTitulo(coleccion.getTitulo());
    coleccionAEditar.setDescripcion(coleccion.getDescripcion());
    coleccionRepositorio.save(coleccionAEditar);
  }
}
