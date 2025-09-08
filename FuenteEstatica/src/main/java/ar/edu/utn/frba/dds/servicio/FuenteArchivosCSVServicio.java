package ar.edu.utn.frba.dds.servicio;

import ar.edu.utn.frba.dds.modelo.fuente.FuenteArchivoCSV;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.repositorio.RepositorioFuenteArchivosCSV;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FuenteArchivosCSVServicio {

  @Autowired
  private RepositorioFuenteArchivosCSV repositorioFuenteDeArchivosCSV;

  @Autowired
  private ImportadorDeHechosDesdeArchivo importadorDeHechosDesdeArchivo;

  @Value("${app.directorio.fuente}")
  public String UPLOAD_DIR;

  public void guardar(MultipartFile archivoCSV) {
    File uploadDir = new File(UPLOAD_DIR);
    if (!uploadDir.exists()) {
      uploadDir.mkdirs();
    }
    File filePath = new File(UPLOAD_DIR + archivoCSV.getOriginalFilename());
    FileOutputStream fos = null;
    try {
      fos = new FileOutputStream(filePath);
    } catch (FileNotFoundException e) {
      throw new RuntimeException("No se pudo crear el archivo.");
    }
    try {
      fos.write(archivoCSV.getBytes());
      fos.close();
    } catch (IOException e) {
      throw new RuntimeException("Ocurrió un error al copiar el archivo.");
    }
    repositorioFuenteDeArchivosCSV.save(new FuenteArchivoCSV(filePath.getAbsolutePath()));
  }

  public Collection<Hecho> hechos() {
    Set<Hecho> ret = new HashSet<>();
    this.repositorioFuenteDeArchivosCSV.findAll().forEach(fuenteArchivoCSV -> {
          fuenteArchivoCSV.importar(importadorDeHechosDesdeArchivo);
          ret.addAll(fuenteArchivoCSV.hechos());
        }
    );
    return ret;
  }
}
