package ar.edu.utn.frba.dds.integracion;

import static ar.edu.utn.frba.dds.archivo.TipoArchivo.CSV;
import static org.junit.jupiter.api.Assertions.assertFalse;

import ar.edu.utn.frba.dds.archivo.lector.LectorDeArchivo;
import ar.edu.utn.frba.dds.archivo.lector.csv.LectorArchivoCSV;
import ar.edu.utn.frba.dds.archivo.localizador.LocalizadorDeArchivo;
import ar.edu.utn.frba.dds.archivo.localizador.LocalizadorDeArchivoDelClassPathLocal;
import ar.edu.utn.frba.dds.modelo.fuente.estatica.FuenteEstatica;
import org.junit.jupiter.api.Test;

public class Escenario02Test {
  @Test
  public void init() {
    LectorDeArchivo lectorDeArchivoDeHechos = new LectorArchivoCSV();
    LocalizadorDeArchivo desastresNaturalesArgentina =
        new LocalizadorDeArchivoDelClassPathLocal("desastres_naturales_argentina", CSV);
    FuenteEstatica fuenteEstatica = new FuenteEstatica(lectorDeArchivoDeHechos, desastresNaturalesArgentina);
    assertFalse(fuenteEstatica.traerHechos().isEmpty());
  }
}