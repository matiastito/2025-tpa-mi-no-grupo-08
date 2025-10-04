package ar.edu.utn.frba.dds.unitario;

import static ar.edu.utn.frba.dds.modelo.hecho.Ubicacion.crearUbicacion;

import ar.edu.utn.frba.dds.consenso.MayoriaSimple;
import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.fuente.TipoFuente;
import ar.edu.utn.frba.dds.modelo.hecho.Categoria;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.modelo.hecho.HechoOrigen;
import ar.edu.utn.frba.dds.modelo.hecho.Ubicacion;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AlgoritmoMayoriaSimpleTest {

  // fuente fake
  class FuenteFake extends Fuente {
    private final Set<Hecho> hechos;

    public FuenteFake(String baseUrl, TipoFuente tipoFuente, Set<Hecho> hechos) {
      super(baseUrl, tipoFuente);
      this.hechos = hechos;
    }

    @Override
    public Set<Hecho> hechos() {
      return hechos;
    }
  }

  @Test
  public void testHechoEsConsensuadoMayoriaSimple() {
    // creo un hecho con sus atributos
    HechoOrigen origen = HechoOrigen.MANUAL;
    Categoria categoria = new Categoria("Categoria Test");
    Ubicacion ubicacion = crearUbicacion("-34.6037", "-58.3816");
    LocalDateTime fechaCreacion = LocalDateTime.now();

    Hecho hechoA = new Hecho(origen, "Título A", "Descripción A", categoria,
        fechaCreacion, ubicacion, fechaCreacion, null);
    // Creamos 3 fuentes, dos fuentes contienen el hecho, y otra no
    Fuente fuente1 = new FuenteFake("Fuente 1", TipoFuente.DINAMICA, Set.of(hechoA));
    Fuente fuente2 = new FuenteFake("Fuente 2", TipoFuente.DINAMICA, Set.of(hechoA));
    Fuente fuente3 = new FuenteFake("Fuente 3", TipoFuente.DINAMICA, Set.of());

    // validamos con el algoritmo mayoriaSimple
    List<List<Boolean>> resultados = List.of(
        new MayoriaSimple().analizarHechoEnFuente(fuente1.hechos(), hechoA),
        new MayoriaSimple().analizarHechoEnFuente(fuente2.hechos(), hechoA),
        new MayoriaSimple().analizarHechoEnFuente(fuente3.hechos(), hechoA)
    );
    // verificamos que haya consenso
    boolean consensuado = new MayoriaSimple().hayConsenso(resultados);
    hechoA.setConsensuado(consensuado);

    // se espera true
    Assertions.assertTrue(hechoA.isConsensuado(), "El hecho debería considerarse consensuado por mayoría simple");
  }

  @Test
  public void testHechoNoEsConsensuadoMayoriaSimple() {

    // creamos el mismo hecho
    HechoOrigen origen = HechoOrigen.MANUAL;
    Categoria categoria = new Categoria("Categoria Test");
    Ubicacion ubicacion = crearUbicacion("-34.6037", "-58.3816");
    LocalDateTime fechaCreacion = LocalDateTime.now();

    Hecho hechoA = new Hecho(origen, "Título A", "Descripción A", categoria,
        fechaCreacion, ubicacion, fechaCreacion, null);

    // creamos 4 fuentes, y solo una contiene el hecho
    Fuente fuente1 = new FuenteFake("Fuente 1", TipoFuente.DINAMICA, Set.of(hechoA));
    Fuente fuente2 = new FuenteFake("Fuente 2", TipoFuente.DINAMICA, Set.of());
    Fuente fuente3 = new FuenteFake("Fuente 3", TipoFuente.DINAMICA, Set.of());
    Fuente fuente4 = new FuenteFake("Fuente 4", TipoFuente.DINAMICA, Set.of());

    // validamos el algoritmo de mayoriaSimple
    List<List<Boolean>> resultados = List.of(
        new MayoriaSimple().analizarHechoEnFuente(fuente1.hechos(), hechoA),
        new MayoriaSimple().analizarHechoEnFuente(fuente2.hechos(), hechoA),
        new MayoriaSimple().analizarHechoEnFuente(fuente3.hechos(), hechoA),
        new MayoriaSimple().analizarHechoEnFuente(fuente4.hechos(), hechoA)
    );

    // verificamos si está consensuado o no
    boolean consensuado = new MayoriaSimple().hayConsenso(resultados);
    hechoA.setConsensuado(consensuado);
    // devuelve false
    Assertions.assertFalse(hechoA.isConsensuado(), "El hecho no debería considerarse consensuado si no tiene mayoría simple");
  }
}
