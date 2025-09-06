package ar.edu.utn.frba.dds.servicio;

import static java.util.stream.Collectors.toSet;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.repositorio.RepositorioDeHechos;
import ar.edu.utn.frba.dds.repositorio.RepositorioFuenteArchivosCSV;
import ar.edu.utn.frba.dds.util.archivo.lector.csv.LectorArchivoCSV;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HechoServicio {

  @Autowired
  private RepositorioFuenteArchivosCSV repositorioFuenteArchivosCSV;
  @Autowired
  private RepositorioDeHechos repositorioDeHechos;
  @Autowired
  private LectorArchivoCSV lectorArchivoCSV;
  @Autowired
  private ImportadorDeHechosDesdeArchivo importadorDeHechosDesdeArchivo;

  public Collection<Hecho> hechos() {
    this.repositorioFuenteArchivosCSV.archivosAProcesar().forEach(fuenteArchivoCSV -> {
          Collection<Hecho> hechos = importadorDeHechosDesdeArchivo.traerHechos(fuenteArchivoCSV);
          repositorioDeHechos.agregar(hechos);
          fuenteArchivoCSV.marcarComoProcesado();
        }
    );
    return repositorioDeHechos.hechos().stream().filter(h -> !h.estaEliminado()).collect(toSet());
  }

  public void modificarHecho(Hecho hecho) {
    repositorioDeHechos.agregar(hecho);
  }
}
