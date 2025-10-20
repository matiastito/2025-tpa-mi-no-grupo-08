package ar.edu.utn.frba.dds.tarea;

import static java.lang.System.out;
import static reactor.core.publisher.Flux.fromIterable;

import ar.edu.utn.frba.dds.modelo.hecho.Provincia;
import ar.edu.utn.frba.dds.normalizador.NormalizadorDeCoordenadas;
import ar.edu.utn.frba.dds.repositorio.HechoRepositorio;
import ar.edu.utn.frba.dds.repositorio.ProvinciaRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.scheduler.Schedulers;

@Component
public class Normalizador {
  @Autowired
  private HechoRepositorio hechoRepositorio;

  @Autowired
  private ProvinciaRepositorio provinciaRepositorio;

  @Autowired
  private NormalizadorDeCoordenadas normalizadorDeCoordenadas;

  //TODO cambiar a una ver por dia, de fuentes que no son PROXY
  @Scheduled(fixedRate = 60000)
  public void actulizarColecciones() {
    out.println("Normalizando Hechos.");
    fromIterable(hechoRepositorio.hechosSinNormalizarProvinca())
        .parallel()
        .runOn(Schedulers.parallel())
        .doOnNext(h -> {
          String provinciaNombre = normalizadorDeCoordenadas.provincia(h.getUbicacion());
          Optional<Provincia> provincia = provinciaRepositorio.findByNombre(provinciaNombre);
          Provincia p;
          if (provincia.isEmpty()) {
            p = new Provincia(provinciaNombre);
            provinciaRepositorio.save(p);
          } else {
            p = provincia.get();
          }
          h.getUbicacion().setProvincia(p);

          //TODO agregar el de categorias
          hechoRepositorio.save(h);
        })
        .sequential()
        .subscribe();
  }
}
