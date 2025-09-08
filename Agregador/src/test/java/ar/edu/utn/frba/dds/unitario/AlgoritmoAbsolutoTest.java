package ar.edu.utn.frba.dds.unitario;

import static ar.edu.utn.frba.dds.modelo.hecho.Ubicacion.crearUbicacion;
import static java.time.LocalDateTime.now;
import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ar.edu.utn.frba.dds.consenso.Absoluto;
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
import org.junit.jupiter.api.Test;

public class AlgoritmoAbsolutoTest {

  // Clase fake para testear fuentes sin dependencias externas
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
  public void testHechoEsConsensuadoAbsoluto() {
    // Creamos un hecho base
    HechoOrigen origen = HechoOrigen.MANUAL;
    Categoria categoria = new Categoria("Categoria Test");
    Ubicacion ubicacion = crearUbicacion("-34.6037", "-58.3816");
    LocalDateTime fecha = now();

    Hecho hechoA = new Hecho(origen, "Título A", "Descripción A", categoria,
        fecha, ubicacion, fecha, null);

    // Todas las fuentes contienen el hecho
    Fuente fuente1 = new FuenteFake("Fuente 1", TipoFuente.DINAMICA, Set.of(hechoA));
    Fuente fuente2 = new FuenteFake("Fuente 2", TipoFuente.DINAMICA, Set.of(hechoA));
    Fuente fuente3 = new FuenteFake("Fuente 3", TipoFuente.DINAMICA, Set.of(hechoA));

    // Analizamos con el algoritmo absoluto
    List<List<Boolean>> resultados = of(
        new Absoluto().analizarHechoEnFuente(fuente1.hechos(), hechoA),
        new Absoluto().analizarHechoEnFuente(fuente2.hechos(), hechoA),
        new Absoluto().analizarHechoEnFuente(fuente3.hechos(), hechoA)
    );

    boolean consensuado = new Absoluto().hayConsenso(resultados);
    hechoA.setConsensuado(consensuado);

    // Assert: esperamos true
    assertTrue(hechoA.isConsensuado(), "El hecho debería ser consensuado si está en todas las fuentes (absoluto)");
  }

  @Test
  public void testHechoNoEsConsensuadoAbsoluto() {
    // Creamos el mismo hecho
    HechoOrigen origen = HechoOrigen.MANUAL;
    Categoria categoria = new Categoria("Categoria Test");
    Ubicacion ubicacion = crearUbicacion("-34.6037", "-58.3816");
    LocalDateTime fecha = now();

    Hecho hechoA = new Hecho(origen, "Título A", "Descripción A", categoria,
        fecha, ubicacion, fecha, null);

    // Una fuente no lo contiene
    Fuente fuente1 = new FuenteFake("Fuente 1", TipoFuente.DINAMICA, Set.of(hechoA));
    Fuente fuente2 = new FuenteFake("Fuente 2", TipoFuente.DINAMICA, Set.of(hechoA));
    Fuente fuente3 = new FuenteFake("Fuente 3", TipoFuente.DINAMICA, Set.of());

    // Analizamos
    List<List<Boolean>> resultados = of(
        new Absoluto().analizarHechoEnFuente(fuente1.hechos(), hechoA),
        new Absoluto().analizarHechoEnFuente(fuente2.hechos(), hechoA),
        new Absoluto().analizarHechoEnFuente(fuente3.hechos(), hechoA)
    );

    boolean consensuado = new Absoluto().hayConsenso(resultados);
    hechoA.setConsensuado(consensuado);

    // Assert: esperamos false
    assertFalse(hechoA.isConsensuado(), "El hecho no debería ser consensuado si no está en todas las fuentes (absoluto)");
  }
}
