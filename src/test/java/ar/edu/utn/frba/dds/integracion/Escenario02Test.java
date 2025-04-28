package ar.edu.utn.frba.dds.integracion;

import static ar.edu.utn.frba.dds.coleccion.Coleccion.crearColeccion;
import static ar.edu.utn.frba.dds.fuente.estatica.FuenteEstatica.crear;
import static org.junit.jupiter.api.Assertions.assertTrue;
import ar.edu.utn.frba.dds.coleccion.Coleccion;
import ar.edu.utn.frba.dds.fuente.estatica.ArchivoCSVDelClassPathLocalImpl;
import org.junit.jupiter.api.Test;

public class Escenario02Test {
  @Test
  public void init() {
    Coleccion coleccionExternaArchivo = crearColeccion(
        "Colección prueba",
        "Esto es una prueba",
        crear(new ArchivoCSVDelClassPathLocalImpl("desastres_naturales_argentina")));
    coleccionExternaArchivo.colectarHechos();
    System.out.println(coleccionExternaArchivo.hechos().size());
    assertTrue(coleccionExternaArchivo.hechos().size() > 0);
  }
}