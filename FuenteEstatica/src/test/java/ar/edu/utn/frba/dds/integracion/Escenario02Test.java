package ar.edu.utn.frba.dds.integracion;

import static ar.edu.utn.frba.dds.archivo.TipoArchivo.CSV;
import static org.junit.jupiter.api.Assertions.assertTrue;
import ar.edu.utn.frba.dds.archivo.lector.csv.LectorArchivoCSV;
import ar.edu.utn.frba.dds.archivo.localizador.LocalizadorDeArchivoDelClassPathLocal;
import ar.edu.utn.frba.dds.modelo.coleccion.Coleccion;
import ar.edu.utn.frba.dds.modelo.fuente.estatica.FuenteEstatica;
import org.junit.jupiter.api.Test;

public class Escenario02Test {
  @Test
  public void init() {
    Coleccion coleccionExternaArchivo = new Coleccion(
        "Colección prueba",
        "Esto es una prueba",
        new FuenteEstatica(
            new LectorArchivoCSV(),
            new LocalizadorDeArchivoDelClassPathLocal("desastres_naturales_argentina", CSV)));
    coleccionExternaArchivo.colectarHechos();
    assertTrue(coleccionExternaArchivo.hechos().size() > 0);
  }
}