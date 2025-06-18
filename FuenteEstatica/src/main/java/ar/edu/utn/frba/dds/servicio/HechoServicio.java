package ar.edu.utn.frba.dds.servicio;

import static ar.edu.utn.frba.dds.util.archivo.TipoArchivo.CSV;
import static java.util.stream.Collectors.toSet;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.repositorio.RepositorioDeHechos;
import ar.edu.utn.frba.dds.util.archivo.lector.csv.LectorArchivoCSV;
import ar.edu.utn.frba.dds.util.archivo.localizador.LocalizadorDeArchivoDelClassPathLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class HechoServicio {

  @Autowired
  private RepositorioDeHechos repositorioDeHechos;
  @Autowired
  private LectorArchivoCSV lectorArchivoCSV;

  public Collection<Hecho> hechos() {
    if (repositorioDeHechos.hechos().isEmpty()) {
      ImportadorDeHechosDesdeArchivo importadorDeHechosDesdeArchivo = new ImportadorDeHechosDesdeArchivo(
          lectorArchivoCSV,
          new LocalizadorDeArchivoDelClassPathLocal("desastres_naturales_argentina", CSV));
      repositorioDeHechos.agregar(importadorDeHechosDesdeArchivo.traerHechos());
    }
    return repositorioDeHechos.hechos().stream().filter(h -> !h.estaEliminado()).collect(toSet());
  }

  public void modificarHecho(Hecho hecho) {
    repositorioDeHechos.agregar(hecho);
  }
}
