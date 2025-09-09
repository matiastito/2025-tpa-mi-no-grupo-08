package ar.edu.utn.frba.dds.tarea;

import static java.lang.System.out;
import static reactor.core.publisher.Flux.fromIterable;
import static reactor.core.publisher.Mono.empty;
import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.normalizador.NormalizadorCategoria;
import ar.edu.utn.frba.dds.repositorio.ColeccionRepositorio;
import ar.edu.utn.frba.dds.repositorio.ElasticsearchHechoRepositorio;
import ar.edu.utn.frba.dds.repositorio.FuenteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.scheduler.Schedulers;

import java.util.Collection;

@Component
public class ColeccionActualizador {
  @Autowired
  private ColeccionRepositorio coleccionRepositorio;
  @Autowired
  private ElasticsearchHechoRepositorio elasticsearchHechoRepositorio;
  @Autowired
  private FuenteRepositorio fuenteRepositorio;
  @Autowired
  private NormalizadorCategoria normalizadorCategoria;

  @Scheduled(fixedRate = 500)
  public void actulizarColecciones() {
    out.println("Refrescando Fuentes.");
    fromIterable(fuenteRepositorio.findAll())
        .parallel()
        .runOn(Schedulers.parallel())
        .doOnNext(Fuente::refrescar)
        .doOnNext(f -> {
          Collection<Hecho> hechos = f.hechos();
          hechos.forEach(h -> {
            normalizadorCategoria.normalizar(h);
          });
        })
        .doOnNext(fuenteRepositorio::save)
        .sequential()
        .subscribe();

    out.println("Refrescando Colecciones.");
    fromIterable(coleccionRepositorio.findAll())
        .flatMap(c -> {
          c.refrescar();
          elasticsearchHechoRepositorio.saveAll(c.hechos());
          return empty();
        }, 5)
        .subscribeOn(Schedulers.boundedElastic())
        .doOnComplete(() -> out.println("Finalizo."))
        .subscribe();
  }
}
