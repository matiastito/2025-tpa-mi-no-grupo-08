package ar.edu.utn.frba.dds.web.controlador;

import ar.edu.utn.frba.dds.servicio.FuenteArchivosCSVServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FuentesControlador {

  @Autowired
  private FuenteArchivosCSVServicio fuenteArchivosCSVServicio;

  @PostMapping("/fuentes")
  public String fuente(@RequestParam("archivoCSVDeHechos") MultipartFile archivoCSV) {
    if (archivoCSV.isEmpty()) {
      return "Por favor, seleccione un archivo CSV para cargar.";
    }
    try {
      fuenteArchivosCSVServicio.guardar(archivoCSV);
      return "Archivo CSV '" + archivoCSV.getOriginalFilename() + "' guardado exitosamente.";
    } catch (RuntimeException e) {
      return "Error al guardar el archivo CSV: " + e.getMessage();
    }
  }
}
