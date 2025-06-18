package ar.edu.utn.frba.dds.integracion;

import static ar.edu.utn.frba.dds.util.archivo.TipoArchivo.CSV;
import static org.junit.jupiter.api.Assertions.assertFalse;
import ar.edu.utn.frba.dds.servicio.ImportadorDeHechosDesdeArchivo;
import ar.edu.utn.frba.dds.util.archivo.lector.LectorDeArchivo;
import ar.edu.utn.frba.dds.util.archivo.lector.csv.LectorArchivoCSV;
import ar.edu.utn.frba.dds.util.archivo.localizador.LocalizadorDeArchivo;
import ar.edu.utn.frba.dds.util.archivo.localizador.LocalizadorDeArchivoDelClassPathLocal;
import org.junit.jupiter.api.Test;

public class Escenario02Test {
  @Test
  public void init() {
    LectorDeArchivo lectorDeArchivoDeHechos = new LectorArchivoCSV();
    LocalizadorDeArchivo desastresNaturalesArgentina =
        new LocalizadorDeArchivoDelClassPathLocal("desastres_naturales_argentina", CSV);
    ImportadorDeHechosDesdeArchivo importadorDeHechosDesdeArchivo =
        new ImportadorDeHechosDesdeArchivo(lectorDeArchivoDeHechos, desastresNaturalesArgentina);
    assertFalse(importadorDeHechosDesdeArchivo.traerHechos().isEmpty());
  }
}