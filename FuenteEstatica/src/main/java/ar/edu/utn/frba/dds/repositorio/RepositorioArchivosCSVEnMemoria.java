package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.fuente.ArchivoCSV;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioArchivosCSVEnMemoria implements RepositorioDeArchivosCSV {
  private final Map<Long, ArchivoCSV> archivos = new HashMap<>();
  private final AtomicLong proximoId = new AtomicLong(1);

  @Override
  public Collection<ArchivoCSV> archivosAProcesar() {
    return archivos.values();
  }

  @Override
  public void agregar(ArchivoCSV archivoCSV) {
    archivos.put(proximoId.getAndIncrement(), archivoCSV);
  }
}
