package ar.edu.utn.frba.dds.integracion;

import static java.util.List.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ar.edu.utn.frba.dds.modelo.coleccion.Coleccion;
import ar.edu.utn.frba.dds.modelo.coleccion.filtro.CategoriaFiltroDeHecho;
import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.hecho.Categoria;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.modelo.hecho.HechoOrigen;
import ar.edu.utn.frba.dds.modelo.hecho.Ubicacion;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestIntegrador_E3_1 {

  @Test
  void testColeccionFiltraHechosPorCategoria() {
    Fuente fuenteMock = new Fuente("localhost", null) {
      @Override
      public List<Hecho> hechos() {
        return of(
            new Hecho(
                HechoOrigen.EXTERNO,
                "Inundación",
                "Inundación leve",
                new Categoria("Inundación"),
                LocalDateTime.now(),
                Ubicacion.crearUbicacion("10", "20"),
                LocalDateTime.now(),
                1,
                null // Fuente dummy
            ),
            new Hecho(
                HechoOrigen.EXTERNO,
                "Incendio",
                "Incendio en zona rural",
                new Categoria("Incendio"),
                LocalDateTime.now(),
                Ubicacion.crearUbicacion("30", "40"),
                LocalDateTime.now(),
                1,
                null // Fuente dummy
            )
        );
      }
    };

    Coleccion coleccion = new Coleccion("Prueba", "Colección de prueba", fuenteMock);
    coleccion.agregarFiltro(new CategoriaFiltroDeHecho(new Categoria("Inundación")));
    coleccion.refrescar();

    var hechosFiltrados = coleccion.hechos();

    assertEquals(1, hechosFiltrados.size());
    assertTrue(hechosFiltrados.stream().anyMatch(h -> h.getTitulo().equalsIgnoreCase("Inundación")));
  }

}



