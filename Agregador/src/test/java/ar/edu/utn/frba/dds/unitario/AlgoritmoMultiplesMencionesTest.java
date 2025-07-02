package ar.edu.utn.frba.dds.unitario;

import ar.edu.utn.frba.dds.consenso.MultiplesMenciones;
import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.fuente.TipoFuente;
import ar.edu.utn.frba.dds.modelo.hecho.Categoria;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.modelo.hecho.HechoOrigen;
import ar.edu.utn.frba.dds.modelo.hecho.Ubicacion;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AlgoritmoMultiplesMencionesTest {

  // Fake de fuente para test
  class FuenteFake extends Fuente {
    private final Set<Hecho> hechos;

    public FuenteFake(String baseUrl, TipoFuente tipoFuente, Set<Hecho> hechos) {
      super(baseUrl, tipoFuente);
      this.hechos = hechos;
    }

    @Override
    public Collection<Hecho> hechos() {
      return hechos;
    }
  }

  @Test
  public void testHechoEsConsensuadoConMultiplesMenciones() {
    HechoOrigen origen = HechoOrigen.MANUAL;
    Categoria categoria = new Categoria("Categoria Test");
    Ubicacion ubicacion = Ubicacion.crearUbicacion("-34.6037", "-58.3816");
    LocalDateTime fecha = LocalDateTime.now();

    Hecho hechoA = new Hecho(origen, "Título A", "Descripción A", categoria, fecha, ubicacion, fecha, null);

    Fuente fuente1 = new FuenteFake("Fuente 1", TipoFuente.DINAMICA, Set.of(hechoA));
    Fuente fuente2 = new FuenteFake("Fuente 2", TipoFuente.DINAMICA, Set.of(hechoA));
    Fuente fuente3 = new FuenteFake("Fuente 3", TipoFuente.DINAMICA, Set.of());

    MultiplesMenciones algoritmo = new MultiplesMenciones();

    List<List<Boolean>> resultados = List.of(
        algoritmo.analizarHechoEnFuente(fuente1.hechos(), hechoA),
        algoritmo.analizarHechoEnFuente(fuente2.hechos(), hechoA),
        algoritmo.analizarHechoEnFuente(fuente3.hechos(), hechoA)
    );

    boolean consensuado = algoritmo.hayConsenso(resultados);
    hechoA.setConsensuado(consensuado);

    Assertions.assertTrue(hechoA.isConsensuado(), "El hecho debería considerarse consensuado con múltiples menciones (sin conflicto)");
  }

  @Test
  public void testHechoNoEsConsensuadoConConflicto() {
    HechoOrigen origen = HechoOrigen.MANUAL;
    Categoria categoria = new Categoria("Categoria Test");
    Ubicacion ubicacion = Ubicacion.crearUbicacion("-34.6037", "-58.3816");
    LocalDateTime fecha = LocalDateTime.now();

    Hecho hechoA = new Hecho(origen, "Título A", "Descripción A", categoria, fecha, ubicacion, fecha, null);

    // Hecho conflictivo con mismo título, diferente fecha y categoría
    Hecho hechoConflicto = new Hecho(origen, "Título A", "Otra descripción", new Categoria("Otra Categoria"),
        fecha.minusDays(1), ubicacion, fecha, null);

    Fuente fuente1 = new FuenteFake("Fuente 1", TipoFuente.DINAMICA, Set.of(hechoA));
    Fuente fuente2 = new FuenteFake("Fuente 2", TipoFuente.DINAMICA, Set.of(hechoA));
    Fuente fuente3 = new FuenteFake("Fuente 3", TipoFuente.DINAMICA, Set.of(hechoConflicto));

    MultiplesMenciones algoritmo = new MultiplesMenciones();

    List<List<Boolean>> resultados = List.of(
        algoritmo.analizarHechoEnFuente(fuente1.hechos(), hechoA),
        algoritmo.analizarHechoEnFuente(fuente2.hechos(), hechoA),
        algoritmo.analizarHechoEnFuente(fuente3.hechos(), hechoA)
    );

    boolean consensuado = algoritmo.hayConsenso(resultados);
    hechoA.setConsensuado(consensuado);

    Assertions.assertFalse(hechoA.isConsensuado(), "El hecho no debería considerarse consensuado si hay conflicto");
  }
}



