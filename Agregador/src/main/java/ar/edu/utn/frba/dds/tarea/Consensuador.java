package ar.edu.utn.frba.dds.tarea;

import static java.lang.System.err;
import static java.lang.System.out;
import static reactor.core.publisher.Flux.fromIterable;
import static reactor.core.scheduler.Schedulers.parallel;

import ar.edu.utn.frba.dds.navegacion.Consensos;
import ar.edu.utn.frba.dds.repositorio.ColeccionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Consensuador {
  @Autowired
  private Consensos consensos;
  @Autowired
  private ColeccionRepositorio coleccionRepositorio;

  @Scheduled(cron = "0 0 4 * * *")
  public void consensuar() {
    out.println("Ejecutando Consensuador.");
    coleccionRepositorio.colleciones().forEach(
        coleccion -> {
          coleccion.hechos().forEach(
              hecho -> {
                fromIterable(coleccion.hechoss())
                    .parallel()
                    .runOn(parallel())
                    .map(hechos ->
                        consensos.getConsenso(coleccion.getTipoConsenso()).analizarHechoEnFuente(hechos, hecho))
                    .sequential()
                    .collectList()
                    .subscribe(
                        resultList -> {
                          hecho.setConsensuado(consensos.getConsenso(coleccion.getTipoConsenso()).hayConsenso(resultList));
                        },
                        error -> err.println("Error: " + error)
                    );
              }
          );
        }
    );
  }
}
