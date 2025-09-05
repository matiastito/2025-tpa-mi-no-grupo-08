package ar.edu.utn.frba.dds.servicio;

import static ar.edu.utn.frba.dds.util.archivo.TipoArchivo.CSV;
import static java.util.stream.Collectors.toSet;

import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.repositorio.RepositorioDeHechos;
import ar.edu.utn.frba.dds.repositorio.RepositorioFuenteArchivosCSV;
import ar.edu.utn.frba.dds.util.archivo.lector.csv.LectorArchivoCSV;
import ar.edu.utn.frba.dds.util.archivo.localizador.LocalizadorDeArchivoLocal;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HechoServicio {

  @Autowired
  private RepositorioFuenteArchivosCSV repositorioDeArchivosCSV;
  @Autowired
  private RepositorioDeHechos repositorioDeHechos;
  @Autowired
  private LectorArchivoCSV lectorArchivoCSV;

  public Collection<Hecho> hechos() {
    this.repositorioDeArchivosCSV.archivosAProcesar().forEach(a -> {
          ImportadorDeHechosDesdeArchivo importadorDeHechosDesdeArchivo =
              new ImportadorDeHechosDesdeArchivo(
                  lectorArchivoCSV,
                  new LocalizadorDeArchivoLocal(a, CSV));
          Collection<Hecho> hechos = importadorDeHechosDesdeArchivo.traerHechos();
          repositorioDeHechos.agregar(hechos);
          a.marcarComoProcesado();
          repositorioDeArchivosCSV.agregar(a);
        }
    );
    return repositorioDeHechos.hechos().stream().filter(h -> !h.estaEliminado()).collect(toSet());
  }

  public void modificarHecho(Hecho hecho) {
    repositorioDeHechos.agregar(hecho);
  }
}
