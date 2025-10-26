package ar.edu.utn.frba.dds.tarea;

import static java.lang.System.out;

import ar.edu.utn.frba.dds.normalizador.NormalizadorCategoria;
import ar.edu.utn.frba.dds.repositorio.ColeccionRepositorio;
import ar.edu.utn.frba.dds.repositorio.FuenteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//FIXME Paralelizar, implementar Mono + Flux + ReactiveRepo
@Component
public class ColeccionActualizador {
  @Autowired
  private ColeccionRepositorio coleccionRepositorio;

  @Autowired
  private FuenteRepositorio fuenteRepositorio;
  @Autowired
  private NormalizadorCategoria normalizadorCategoria;

  @Scheduled(fixedRate = 60000)
  @Transactional
  public void actulizarColecciones() {
    out.println("Refrescando Fuentes. INICIO.");
    fuenteRepositorio.findAll().forEach(f -> {
          try {
            f.refrescar(normalizadorCategoria);
            fuenteRepositorio.save(f);
          } catch (Exception e) {
            out.println("Ocurrió un error al refrecar la fuente: " + e.getMessage());
          }
        }
    );
    out.println("Refrescando Fuentes. FIN.");

    out.println("Refrescando Colecciones. INICIO.");
    coleccionRepositorio.findAll().forEach(c -> {
      c.refrescar();
      coleccionRepositorio.save(c);
    });
    out.println("Refrescando Colecciones. FIN.");
  }
}
