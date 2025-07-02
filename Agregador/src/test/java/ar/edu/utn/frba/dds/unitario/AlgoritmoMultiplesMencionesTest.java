package ar.edu.utn.frba.dds.unitario;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ar.edu.utn.frba.dds.modelo.coleccion.Coleccion;
import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.fuente.TipoFuente;
import ar.edu.utn.frba.dds.modelo.hecho.Categoria;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.modelo.hecho.HechoOrigen;
import ar.edu.utn.frba.dds.modelo.hecho.Ubicacion;
import ar.edu.utn.frba.dds.navegacion.MultiplesMenciones;

public class AlgoritmoMultiplesMencionesTest {

    //fuente fake para tests
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
        LocalDateTime fechaCreacion = LocalDateTime.now();
        LocalDateTime ultimaModificacion = LocalDateTime.now();

        Hecho hechoA = new Hecho(origen, "Título A", "Descripción A", categoria,
                fechaCreacion, ubicacion, ultimaModificacion, null);

        Fuente fuente1 = new FuenteFake("Fuente 1", TipoFuente.DINAMICA, Set.of(hechoA));
        Fuente fuente2 = new FuenteFake("Fuente 2", TipoFuente.DINAMICA, Set.of(hechoA));

        Coleccion coleccion = new Coleccion("Coleccion Test", "Descripcion de prueba", fuente1, fuente2);

        MultiplesMenciones algoritmo = new MultiplesMenciones();

        coleccion.colectarHechos();
        boolean consensuado = algoritmo.esConsensuado(hechoA, coleccion.hechoss());

        // Seteamos en el hecho
        hechoA.setConsensuado(consensuado);

        Assertions.assertTrue(consensuado, "El hecho debería considerarse consensuado con múltiples menciones");
    }

    @Test
    public void testHechoNoEsConsensuadoSiHayConflicto() {
        HechoOrigen origen = HechoOrigen.MANUAL;
        Categoria categoria = new Categoria("Categoria Test");
        Ubicacion ubicacion = Ubicacion.crearUbicacion("-34.6037", "-58.3816");
        LocalDateTime fechaCreacion = LocalDateTime.now();
        LocalDateTime ultimaModificacion = LocalDateTime.now();

        Hecho hechoA = new Hecho(origen, "Título A", "Descripción A", categoria,
                fechaCreacion, ubicacion, ultimaModificacion, null);

        // Hecho conflictivo
        Categoria categoriaConflictiva = new Categoria("Otra Categoria");
        Ubicacion ubicacionConflictiva = Ubicacion.crearUbicacion("-31.4167", "-64.1833");
        Hecho hechoConflictivo = new Hecho(origen, "Título A", "Descripción diferente", categoriaConflictiva,
                fechaCreacion.minusDays(1), ubicacionConflictiva, ultimaModificacion.minusDays(1), null);

        Fuente fuente1 = new FuenteFake("Fuente 1", TipoFuente.DINAMICA, Set.of(hechoA));
        Fuente fuente2 = new FuenteFake("Fuente 2", TipoFuente.DINAMICA, Set.of(hechoA));
        Fuente fuenteConflictiva = new FuenteFake("Fuente Conflictiva", TipoFuente.DINAMICA, Set.of(hechoConflictivo));

        Coleccion coleccion = new Coleccion("Coleccion Test Conflictiva", "Descripcion con conflicto", fuente1, fuente2, fuenteConflictiva);

        MultiplesMenciones algoritmo = new MultiplesMenciones();
        coleccion.colectarHechos();
        boolean consensuado = algoritmo.esConsensuado(hechoA, coleccion.hechoss());

        hechoA.setConsensuado(consensuado);
        Assertions.assertFalse(consensuado, "El hecho no debería considerarse consensuado si hay conflicto");
    }
}


