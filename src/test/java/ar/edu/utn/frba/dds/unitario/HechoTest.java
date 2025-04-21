package ar.edu.utn.frba.dds.unitario;
import ar.edu.utn.frba.dds.hecho.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class HechoTest {
  private Hecho hecho;

  @BeforeEach
  public void setUp() {
    hecho = Hecho.crearHechoDeTexto(
        HechoOrigen.MANUAL,
        "Incendio en Córdoba",
        "Incendio forestal de gran magnitud",
        Categorias.categoria("Incendio forestal"),
        LocalDateTime.of(2025, 3, 12, 10, 0),
        new Ubicacion("-31.4", "-64.2"),
        LocalDateTime.now()
    );
  }

  @Test
  public void hechoSeCreaCorrectamente() {
    assertEquals("Incendio en Córdoba", hecho.getTitulo());
    assertEquals(Categorias.categoria("Incendio forestal"), hecho.getCategoria());
  }

  @Test
  public void puedeEtiquetarse() {
    hecho.etiquetar("urgente");
    assertTrue(hecho.getEtiquetas().contains("urgente"));
  }

  @Test
  public void puedeSolicitarseEliminacion() {
    var solicitud = hecho.solicitarEliminacion("Contenido sensible por razones legales");
    assertTrue(hecho.getSolicitudesDeEliminacionPendientes().contains(solicitud));
  }

  @Test
  public void puedeSerMarcadoComoEliminado() {
    hecho.eliminar();
    assertTrue(hecho.estaEliminado());
  }

  @Test
  public void dosHechosConElMismoTituloSonIguales() {
    Hecho otroHecho = Hecho.crearHechoDeTexto(
        HechoOrigen.MANUAL,
        "Incendio en Córdoba",
        "Otro contenido",
        Categorias.categoria("Otro"),
        LocalDateTime.now(),
        new Ubicacion("0.0", "0.0"),
        LocalDateTime.now()
    );
    assertEquals(hecho, otroHecho);
  }
}
