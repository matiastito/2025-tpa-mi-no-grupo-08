package ar.edu.utn.frba.dds.unitario;

import static ar.edu.utn.frba.dds.hecho.Ubicacion.crearUbicacion;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ar.edu.utn.frba.dds.coleccion.Coleccion;
import ar.edu.utn.frba.dds.coleccion.filtro.FiltroDeHecho;
import ar.edu.utn.frba.dds.fuente.Fuente;
import ar.edu.utn.frba.dds.hecho.Categorias;
import ar.edu.utn.frba.dds.hecho.Hecho;
import ar.edu.utn.frba.dds.hecho.HechoOrigen;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ColeccionTest {

  private Hecho hecho;
  private Fuente fuente;
  private Coleccion coleccion;

  @BeforeEach
  public void setUp() {
    hecho = Hecho.crearHechoDeTexto(
        HechoOrigen.MANUAL,
        "Protesta en La Plata",
        "Manifestación pacífica",
        Categorias.categoria("Otro"),
        LocalDateTime.of(2025, 1, 20, 14, 30),
        crearUbicacion("-34.921", "-57.954"),
        LocalDateTime.now()
    );

    // Fuente falsa que devuelve el hecho
    fuente = new Fuente() {
      @Override
      public List<Hecho> traerHechos() {
        return List.of(hecho);
      }
    };

    coleccion = Coleccion.crearColeccion("Protestas", "Protestas sociales en Argentina", fuente);
  }

  @Test
  public void coleccionPuedeColectarHechosDesdeFuente() {
    coleccion.colectarHechos();
    assertTrue(coleccion.hechos().contains(hecho));
  }

  @Test
  public void noIncluyeHechosEliminados() {
    hecho.eliminar();
    coleccion.colectarHechos();
    assertTrue(coleccion.hechos().isEmpty());
  }

  @Test
  public void coleccionAplicaFiltrosCorrectamente() {
    // Filtro falso que siempre devuelve false
    FiltroDeHecho filtroFalso = new FiltroDeHecho() {
      @Override
      public boolean filtrar(Hecho h) {
        return false; // lo filtra siempre
      }
    };

    coleccion.agregarFiltro(filtroFalso);
    coleccion.colectarHechos();

    assertFalse(coleccion.hechos().contains(hecho));
  }
}