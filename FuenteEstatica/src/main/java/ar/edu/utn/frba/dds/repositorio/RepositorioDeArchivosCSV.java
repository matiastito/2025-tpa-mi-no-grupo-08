package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.fuente.ArchivoCSV;
import java.util.Collection;

public interface RepositorioDeArchivosCSV {
  Collection<ArchivoCSV> archivosAProcesar();

  void agregar(ArchivoCSV fuente);
}
