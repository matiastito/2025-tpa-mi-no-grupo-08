package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.fuente.FuenteArchivoCSV;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioFuenteArchivosCSVEnMemoria implements RepositorioFuenteArchivosCSV {
  private final Map<Long, FuenteArchivoCSV> archivos = new HashMap<>();
  private final AtomicLong proximoId = new AtomicLong(1);

  @Override
  public Collection<FuenteArchivoCSV> archivosAProcesar() {
    return archivos.values();
  }

  @Override
  public void agregar(FuenteArchivoCSV fuenteArchivoCSV) {
    archivos.put(proximoId.getAndIncrement(), fuenteArchivoCSV);
  }
}
