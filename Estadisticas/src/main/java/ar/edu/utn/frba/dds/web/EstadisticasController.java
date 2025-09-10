package ar.edu.utn.frba.dds.web;

import static java.lang.String.valueOf;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.parseMediaType;

import ar.edu.utn.frba.dds.repositorio.EstadisticaRepositorio;
import com.opencsv.CSVWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstadisticasController {

  @Autowired
  private EstadisticaRepositorio estadisticaRepositorio;

  @GetMapping(value = "/estadisticas/csv", produces = "text/csv")
  public ResponseEntity<byte[]> generarCSV() throws IOException {
    try (var baos = new ByteArrayOutputStream()) {
      try (OutputStreamWriter osw = new OutputStreamWriter(baos)) {
        try (CSVWriter writer = new CSVWriter(osw)) {

          estadisticaRepositorio.findAll().forEach(e -> {
                String[] header = {"Coleccion", "Provincia", "CantidadDeHechos"};
                writer.writeNext(header);
                e.getEstadisticasColecciones().forEach(ec -> {
                  ec.getEstadisticasPorProvincia().forEach(ep -> {
                    String[] row = {
                        ec.getHandle(),
                        ep.getProvincia().getNombre(),
                        valueOf(ep.getCantidadDeHechos())
                    };
                    writer.writeNext(row);
                  });
                });

                header = new String[]{"Categoria", "Provincia", "CantidadDeHechos"};
                writer.writeNext(header);
                e.getEstadisticasCategoria().forEach(ec -> {
                  ec.getEstadisticaProvincias().forEach(ep -> {
                    String[] row = {
                        ec.getCategoria().getNombre(),
                        ep.getProvincia().getNombre(),
                        valueOf(ep.getCantidadDeHechos())
                    };
                    writer.writeNext(row);
                  });
                });

                header = new String[]{"Categoria", "Hora", "CantidadDeHechos"};
                writer.writeNext(header);
                e.getEstadisticasCategoria().forEach(ec -> {
                  ec.getEstadisticaHoras().forEach(eh -> {
                    String[] row = {
                        ec.getCategoria().getNombre(),
                        valueOf(eh.getHora()),
                        valueOf(eh.getCantidadDeHechos())
                    };
                    writer.writeNext(row);
                  });
                });
              }
          );
          writer.flush();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"users.csv\"");
        headers.setContentType(parseMediaType("text/csv"));
        return new ResponseEntity<>(baos.toByteArray(), headers, OK);
      }
    }
  }
}
