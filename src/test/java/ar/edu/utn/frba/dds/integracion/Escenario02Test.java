package ar.edu.utn.frba.dds.integracion;

import static ar.edu.utn.frba.dds.fuente.estatica.archivo.TipoArchivo.CSV;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ar.edu.utn.frba.dds.coleccion.Coleccion;
import ar.edu.utn.frba.dds.fuente.estatica.FuenteEstatica;
import ar.edu.utn.frba.dds.fuente.estatica.archivo.lector.csv.LectorArchivoCSV;
import ar.edu.utn.frba.dds.fuente.estatica.archivo.localizador.LocalizadorDeArchivoDelClassPathLocal;
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
    System.out.println(coleccionExternaArchivo.hechos().size());
    assertTrue(coleccionExternaArchivo.hechos().size() > 0);
  }
}