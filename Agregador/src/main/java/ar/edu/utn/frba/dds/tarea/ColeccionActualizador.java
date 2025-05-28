package ar.edu.utn.frba.dds.tarea;

import static ar.edu.utn.frba.dds.modelo.fuente.TipoFuente.PROXY;

import ar.edu.utn.frba.dds.modelo.coleccion.Coleccion;
import ar.edu.utn.frba.dds.repositorio.ColeccionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ColeccionActualizador {
  @Autowired
  private ColeccionRepositorio coleccionRepositorio;

  @Scheduled(fixedRate = 5000)
  public void actulizarColecciones() {
    coleccionRepositorio.colleciones().stream()
        .filter(c -> !PROXY.equals(c.getFuente().getTipoFuente()))
        .forEach(Coleccion::colectarHechos);
  }
}
