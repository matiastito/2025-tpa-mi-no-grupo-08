package ar.edu.utn.frba.dds.servicio;

import static java.lang.Math.random;
import static reactor.core.publisher.Mono.fromCallable;

import ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHecho;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DetectorDeSpamServicioDummy implements DetectorDeSpamServicio {
  @Override
  public void rechazaSpam(SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho) {
    fromCallable(() -> {
      if (((int) (random() * 10) + 1) < 8)
        solicitudDeEliminacionDeHecho.rechazar();
      return Mono.empty();
    }).subscribe();
  }
}
