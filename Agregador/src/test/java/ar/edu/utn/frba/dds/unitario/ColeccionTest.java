package ar.edu.utn.frba.dds.unitario;

import static ar.edu.utn.frba.dds.Categorias.categoria;
import static ar.edu.utn.frba.dds.modelo.hecho.Ubicacion.crearUbicacion;
import static java.time.LocalDateTime.now;
import static java.util.Set.of;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import ar.edu.utn.frba.dds.modelo.coleccion.Coleccion;
import ar.edu.utn.frba.dds.modelo.coleccion.filtro.FiltroDeHecho;
import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.modelo.hecho.HechoOrigen;
import ar.edu.utn.frba.dds.modelo.hecho.SolicitudDeEliminacionDeHecho;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ColeccionTest {

  private Hecho hecho;
  private Fuente fuente;
  private Coleccion coleccion;
  private SolicitudDeEliminacionDeHecho solicitudDeEliminacionDeHecho;

  @BeforeEach
  public void setUp() {
    solicitudDeEliminacionDeHecho = mock(SolicitudDeEliminacionDeHecho.class);
    fuente = mock(Fuente.class);
    hecho = new Hecho(
        HechoOrigen.MANUAL,
        "Protesta en La Plata",
        "Manifestación pacífica",
        categoria("Otro"),
        LocalDate.of(2025, 1, 20),
        crearUbicacion("-34.921", "-57.954"),
        now(),
        fuente
    );
    when(fuente.hechos()).thenReturn(of(hecho));

    coleccion = new Coleccion("Protestas", "Protestas sociales en Argentina", fuente);
  }

  @Test
  public void coleccionPuedeRefrescarDesdeFuente() {
    coleccion.refrescar();
    assertTrue(coleccion.hechos().contains(hecho));
  }

  @Test
  public void noIncluyeHechosEliminados() {
    hecho.eliminar(solicitudDeEliminacionDeHecho);
    coleccion.refrescar();
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
    coleccion.refrescar();

    assertFalse(coleccion.hechos().contains(hecho));
  }
}