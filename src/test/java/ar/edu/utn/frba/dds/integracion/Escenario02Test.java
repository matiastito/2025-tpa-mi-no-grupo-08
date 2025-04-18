package ar.edu.utn.frba.dds.integracion;

import static ar.edu.utn.frba.dds.coleccion.Coleccion.crearColeccion;

import ar.edu.utn.frba.dds.coleccion.Coleccion;
import ar.edu.utn.frba.dds.fuente.estatica.FuenteEstatica;
import org.junit.jupiter.api.Test;

public class Escenario02Test {
  @Test
  public void init() {
    Coleccion coleccionExternaArchivo = crearColeccion(
        "Colección prueba",
        "Esto es una prueba",
        FuenteEstatica.crear());
    coleccionExternaArchivo.colectarHechos();
    System.out.println(coleccionExternaArchivo.hechos().size());
  }
}