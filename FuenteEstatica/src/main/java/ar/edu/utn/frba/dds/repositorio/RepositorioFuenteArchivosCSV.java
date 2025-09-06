package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.fuente.FuenteArchivoCSV;
import java.util.List;

public interface RepositorioFuenteArchivosCSV {
  List<FuenteArchivoCSV> archivosAProcesar();

  void agregar(FuenteArchivoCSV fuente);
}
