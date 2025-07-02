package ar.edu.utn.frba.dds.unitario;

import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.of;

import org.junit.jupiter.api.BeforeEach;
import static org.mockito.Mockito.mock;

import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.hecho.Categorias;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.modelo.hecho.HechoOrigen;
import static ar.edu.utn.frba.dds.modelo.hecho.Ubicacion.crearUbicacion;

public class SolicitudDeEliminacionDeHechoTest {
  private Hecho hecho;

  @BeforeEach
  public void setUp() {
    hecho = new Hecho(
        HechoOrigen.MANUAL,
        "Incendio en Córdoba",
        "Incendio forestal de gran magnitud",
        Categorias.categoria("Incendio forestal"),
        of(2025, 3, 12, 10, 0),
        crearUbicacion("-31.4", "-64.2"),
        now(),
        mock(Fuente.class)
    );

  }

}
