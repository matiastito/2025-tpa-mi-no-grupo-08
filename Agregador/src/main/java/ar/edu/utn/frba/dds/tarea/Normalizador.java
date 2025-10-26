package ar.edu.utn.frba.dds.tarea;

import static java.lang.System.out;

import ar.edu.utn.frba.dds.modelo.hecho.Provincia;
import ar.edu.utn.frba.dds.normalizador.NormalizadorDeCoordenadas;
import ar.edu.utn.frba.dds.repositorio.HechoRepositorio;
import ar.edu.utn.frba.dds.repositorio.ProvinciaRepositorio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//FIXME Paralelizar, implementar Mono + Flux + ReactiveRepo
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
    out.println("Normalizando Provincias. INICIO.");
    hechoRepositorio.hechosSinNormalizarProvincia().forEach(h -> {
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
      hechoRepositorio.save(h);
    });
    out.println("Normalizando Provincias. FIN.");
  }
}
