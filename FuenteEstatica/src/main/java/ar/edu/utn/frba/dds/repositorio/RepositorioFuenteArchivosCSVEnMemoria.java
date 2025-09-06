package ar.edu.utn.frba.dds.repositorio;

import ar.edu.utn.frba.dds.modelo.fuente.FuenteArchivoCSV;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioFuenteArchivosCSVEnMemoria implements RepositorioFuenteArchivosCSV {
  private final Map<Long, FuenteArchivoCSV> fuentes = new HashMap<>();
  private final AtomicLong proximoId = new AtomicLong(1);

  @Override
  public List<FuenteArchivoCSV> archivosAProcesar() {
    return fuentes.values().stream().toList();
  }

  @Override
  public void agregar(FuenteArchivoCSV fuenteArchivoCSV) {
    long id = proximoId.getAndIncrement();
    fuentes.put(id, fuenteArchivoCSV);
    fuenteArchivoCSV.setId(id);
  }
}
