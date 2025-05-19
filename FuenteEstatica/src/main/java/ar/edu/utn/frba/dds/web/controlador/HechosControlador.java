package ar.edu.utn.frba.dds.web.controlador;

import static ar.edu.utn.frba.dds.archivo.TipoArchivo.CSV;
import static java.util.stream.Collectors.toList;
import ar.edu.utn.frba.dds.archivo.lector.csv.LectorArchivoCSV;
import ar.edu.utn.frba.dds.archivo.localizador.LocalizadorDeArchivoDelClassPathLocal;
import ar.edu.utn.frba.dds.modelo.fuente.estatica.FuenteEstatica;
import ar.edu.utn.frba.dds.web.dto.HechoDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class HechosControlador {

  @GetMapping("/hechos")
  public Collection<HechoDTO> home() {
    FuenteEstatica fuenteEstatica = new FuenteEstatica(
        new LectorArchivoCSV(),
        new LocalizadorDeArchivoDelClassPathLocal("desastres_naturales_argentina", CSV));
    return fuenteEstatica.traerHechos()
        .stream()
        .map(HechoDTO::toDTO)
        .collect(toList());
  }
}