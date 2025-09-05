package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.fuente.FuenteArchivoCSV;
import java.util.Collection;

public interface RepositorioFuenteArchivosCSV {
  Collection<FuenteArchivoCSV> archivosAProcesar();

  void agregar(FuenteArchivoCSV fuente);
}
