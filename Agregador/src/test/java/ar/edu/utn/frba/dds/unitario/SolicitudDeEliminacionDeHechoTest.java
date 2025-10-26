package ar.edu.utn.frba.dds.unitario;

import static ar.edu.utn.frba.dds.modelo.hecho.Ubicacion.crearUbicacion;
import static java.time.LocalDateTime.now;
import static org.mockito.Mockito.mock;

import ar.edu.utn.frba.dds.Categorias;
import ar.edu.utn.frba.dds.modelo.fuente.Fuente;
import ar.edu.utn.frba.dds.modelo.hecho.Hecho;
import ar.edu.utn.frba.dds.modelo.hecho.HechoOrigen;
import java.time.LocalDate;
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
        LocalDate.of(2025, 3, 12),
        crearUbicacion("-31.4", "-64.2"),
        now(),
        mock(Fuente.class)
    );

  }

}
