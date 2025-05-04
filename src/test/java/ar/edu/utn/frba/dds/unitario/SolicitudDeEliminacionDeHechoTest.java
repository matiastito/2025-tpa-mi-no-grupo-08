package ar.edu.utn.frba.dds.unitario;

import static ar.edu.utn.frba.dds.hecho.Ubicacion.crearUbicacion;
import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.of;

import ar.edu.utn.frba.dds.hecho.Categorias;
import ar.edu.utn.frba.dds.hecho.Hecho;
import ar.edu.utn.frba.dds.hecho.HechoOrigen;
import org.junit.jupiter.api.BeforeEach;

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
        now()
    );

  }

}
