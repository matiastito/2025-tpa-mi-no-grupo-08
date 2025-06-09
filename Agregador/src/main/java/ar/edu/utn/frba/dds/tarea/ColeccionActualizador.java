package ar.edu.utn.frba.dds.tarea;

import static java.lang.System.out;
import static reactor.core.publisher.Flux.fromIterable;
import static reactor.core.publisher.Mono.empty;

import ar.edu.utn.frba.dds.repositorio.ColeccionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.scheduler.Schedulers;

@Component
public class ColeccionActualizador {
  @Autowired
  private ColeccionRepositorio coleccionRepositorio;

  @Scheduled(fixedRate = 500)
  public void actulizarColecciones() {
    out.println("Ejecutando Actualizador.");
    fromIterable(coleccionRepositorio.colleciones())
        .flatMap(c -> {
          c.colectarHechos();
          return empty();
        }, 5)
        .subscribeOn(Schedulers.boundedElastic())
        .doOnComplete(() -> out.println("Finalizo."))
        .subscribe();
  }
}
