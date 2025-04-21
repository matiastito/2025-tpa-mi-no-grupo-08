package ar.edu.utn.frba.dds.unitario;

import static ar.edu.utn.frba.dds.hecho.Ubicacion.crearUbicacion;
import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ar.edu.utn.frba.dds.hecho.Categorias;
import ar.edu.utn.frba.dds.hecho.Hecho;
import ar.edu.utn.frba.dds.hecho.HechoOrigen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HechoTest {
  private Hecho hecho;

  @BeforeEach
  public void setUp() {
    hecho = Hecho.crearHechoDeTexto(
        HechoOrigen.MANUAL,
        "Incendio en Córdoba",
        "Incendio forestal de gran magnitud",
        Categorias.categoria("Incendio forestal"),
        of(2025, 3, 12, 10, 0),
        crearUbicacion("-31.4", "-64.2"),
        now()
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
        now(),
        crearUbicacion("0.0", "0.0"),
        now()
    );
    assertEquals(hecho, otroHecho);
  }
}
